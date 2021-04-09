package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.ZnChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearExponentStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
import org.cryptimeleon.math.expressions.group.GroupElementExpression;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearMap;
import org.cryptimeleon.math.structures.rings.zn.Zp;

import java.math.BigInteger;

/**
 * Protocol for
 * (x,r); e(g^x, h) = z * e(g*C, h^x)
 */
public class MySigmaProtocolWithPairing extends DelegateProtocol {
    //protected MyProtocolPublicParameters pp;
    protected BilinearGroup bilinearGroup;
    protected Zp zp;
    protected BilinearMap e;

    public MySigmaProtocolWithPairing(BilinearGroup bilinearGroup) {
        //this.pp = pp;
        this.bilinearGroup = bilinearGroup;
        this.zp = (Zp) bilinearGroup.getZn();
        this.e = bilinearGroup.getBilinearMap();
    }

    @Override
    protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
        MySigmaProtocolCommonInput input = (MySigmaProtocolCommonInput) commonInput;

        //Add variables (witnesses)
        SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
        SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

        //Add statements
        subprotocolSpecBuilder.addSubprotocol("statement1",
                new LinearStatementFragment(this.e.applyExpr(input.g.pow(x), input.h).isEqualTo(input.z.op(this.e.applyExpr(input.g.op(input.C), input.h.pow(x)))))
        );

        return subprotocolSpecBuilder.build();
    }

    @Override
    protected ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, ProverSpecBuilder proverSpecBuilder) {
        MySigmaProtocolSecretInput witness = (MySigmaProtocolSecretInput) secretInput;

        proverSpecBuilder.putWitnessValue("x", witness.x);
        proverSpecBuilder.putWitnessValue("r", witness.r);

        return proverSpecBuilder.build();
    }

    @Override
    public ZnChallengeSpace getChallengeSpace(CommonInput commonInput) {
        return new ZnChallengeSpace(zp);
    }

    public static class MySigmaProtocolCommonInput implements CommonInput {
        public final GroupElement C;
        public final GroupElement z;
        public final GroupElement g;
        public final GroupElement h;

        public MySigmaProtocolCommonInput(GroupElement C, GroupElement z, GroupElement g, GroupElement h) {
            this.C = C;
            this.z = z;
            this.g = g;
            this.h = h;
        }
    }

    public static class MySigmaProtocolSecretInput implements SecretInput {
        public final Zp.ZpElement x;
        public final Zp.ZpElement r;

        public MySigmaProtocolSecretInput(Zp.ZpElement x, Zp.ZpElement r) {
            this.x = x;
            this.r = r;
        }
    }
}
