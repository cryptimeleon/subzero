package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.ZnChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.rings.zn.Zp;

import java.math.BigInteger;

/**
 * Protocol for
 * (x,r); g^x * h^r = C
 * & h^r = C2
 */
public class MySigmaProtocol extends DelegateProtocol {
    protected Group group;
    protected Zp zp;

    public MySigmaProtocol(Group group) {
        this.group = group;
        this.zp = (Zp) this.group.getZn();
    }

    @Override
    protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
        MySigmaProtocolCommonInput input = (MySigmaProtocolCommonInput) commonInput;

        //Add variables (witnesses)
        SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
        SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

        //Add statements
        subprotocolSpecBuilder.addSubprotocol("statement1",
                new LinearStatementFragment(input.g.pow(x).op(input.h.pow(r)).isEqualTo(input.C))
        );
        subprotocolSpecBuilder.addSubprotocol("statement2",
                new LinearStatementFragment(input.h.pow(r).isEqualTo(input.C2))
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
        public final Zp.ZpElement r;

        public MySigmaProtocolSecretInput(Zp.ZpElement x, Zp.ZpElement r) {
            this.x = x;
            this.r = r;
        }
    }
}
