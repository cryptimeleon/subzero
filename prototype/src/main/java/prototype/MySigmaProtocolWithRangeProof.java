package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearExponentStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.rings.zn.Zp;

import java.math.BigInteger;

/**
 * Protocol for
 * (x,x2,r); g^x * h^r = C
 * & C2 = g^x2 * h^r
 * & 5 <= x+x2 <= 200
 * & x-x2 = 3
 */
public class MySigmaProtocolWithRangeProof extends DelegateProtocol {
    protected MyProtocolPublicParameters pp;
    protected BilinearGroup bilinearGroup;
    protected Zp zp;

    public MySigmaProtocolWithRangeProof(BilinearGroup bilinearGroup, MyProtocolPublicParameters pp) {
        this.pp = pp;
        this.bilinearGroup = bilinearGroup;
        this.zp = (Zp) bilinearGroup.getZn();
    }
    
    @Override
    protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
        MySigmaProtocolCommonInput input = (MySigmaProtocolCommonInput) commonInput;

        //Add variables (witnesses)
        SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
        SchnorrZnVariable x2 = subprotocolSpecBuilder.addZnVariable("x2", zp);
        SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);
        
        //Add statements
        subprotocolSpecBuilder.addSubprotocol("statement1",
                new LinearStatementFragment(input.g.pow(x).op(input.h.pow(r)).isEqualTo(input.C))
        );
        subprotocolSpecBuilder.addSubprotocol("statement2",
                new LinearStatementFragment(input.C2.expr().isEqualTo(input.g.pow(x2).op(input.h.pow(r)))) //Jeremy: actually, I think we may just add isEqualTo() to GroupElement instead of forcing you to handle this one special case where you need to call .expr(). It's not all that confusing.
        );
        subprotocolSpecBuilder.addSubprotocol("statement3",
                new TwoSidedRangeProof(x.add(x2), zp.valueOf(5), zp.valueOf(200), pp.rangeProofpp)
        );
        subprotocolSpecBuilder.addSubprotocol("statement4",
                new LinearExponentStatementFragment(x.sub(x2).isEqualTo(zp.valueOf(3)), zp) //Jeremy: any constants "3" in the DSL should be treated as zp.valueOf(3). See also range proof above (even though there, the zp.valueOf() call is actually optional because the method is overloaded to accept integers, too).
        );

        return subprotocolSpecBuilder.build();
    }

    @Override
    protected ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, ProverSpecBuilder proverSpecBuilder) {
        MySigmaProtocolSecretInput witness = (MySigmaProtocolSecretInput) secretInput;

        proverSpecBuilder.putWitnessValue("x", witness.x);
        proverSpecBuilder.putWitnessValue("x2", witness.x2);
        proverSpecBuilder.putWitnessValue("r", witness.r);

        return proverSpecBuilder.build();
    }

    @Override
    public BigInteger getChallengeSpaceSize() {
        return zp.size();
    }

    public static class MySigmaProtocolCommonInput implements CommonInput {
        public final GroupElement C;
        public final GroupElement C2;
        public final GroupElement g;
        public final GroupElement h;

        public MySigmaProtocolCommonInput(GroupElement C, GroupElement C2, GroupElement g, GroupElement h) {
            this.C = C;
            this.C2 = C2;
            this.g = g;
            this.h = h;
        }
    }

    public static class MySigmaProtocolSecretInput implements SecretInput {
        public final Zp.ZpElement x;
        public final Zp.ZpElement x2;
        public final Zp.ZpElement r;

        public MySigmaProtocolSecretInput(Zp.ZpElement x, Zp.ZpElement x2, Zp.ZpElement r) {
            this.x = x;
            this.x2 = x2;
            this.r = r;
        }
    }
}
