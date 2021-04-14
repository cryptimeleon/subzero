package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.ChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.ZnChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.partial.ProofOfPartialKnowledge;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendFirstValue;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
import org.cryptimeleon.math.expressions.bool.BooleanExpression;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.structures.cartesian.ExponentExpressionVector;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.rings.cartesian.RingElementVector;
import org.cryptimeleon.math.structures.rings.zn.Zn;
import org.cryptimeleon.math.structures.rings.zn.Zp;

/**
 * Protocol for
 * (x,r); (h^r = C2 | h^x = C2)
 *
 * Jeremy: Because there is no OR with an AND parent, we can get rid of the commitment stuff
 */
public class MySimpleOrProof extends ProofOfPartialKnowledge { //Jeremy: extend this class as soon as there's an "OR" anywhere.
    //protected MyOrProofPublicParameters pp; //Jeremy: not needed anymore because the commitment is not necessary here anymore.
    protected Group group; //Jeremy: OR proofs also work if this is a BilinearGroup. In that case, replace all occurrences of group below with group.getG1()
    protected Zp zp;

    public MySimpleOrProof(Group group) {
        this.group = group;
        this.zp = (Zp) this.group.getZn();
    }

    @Override
    protected ProtocolTree provideProtocolTree(CommonInput commonInput, SendFirstValue sendFirstValue) {
        return
                or(
                        leaf("Subprotocol2", new Subprotocol2(), commonInput),
                        leaf("Subprotocol3", new Subprotocol3(), commonInput)
                )
        ;
    }

    @Override
    protected ProverSpec provideProverSpec(CommonInput commonInput, SecretInput secretInput, ProverSpecBuilder builder) {
        MyOrProofSecretInput overallSecretInput = (MyOrProofSecretInput) secretInput;

        //Send this commitment
        builder.setSendFirstValue(SendFirstValue.EMPTY);

        //Set up witnesses for subprotocols
        builder.putSecretInput("Subprotocol2", secretInput);
        builder.putSecretInput("Subprotocol3", secretInput);

        return builder.build();
    }

    @Override
    protected SendFirstValue restoreSendFirstValue(CommonInput commonInput, Representation repr) {
        return SendFirstValue.EMPTY;
    }

    @Override
    protected SendFirstValue simulateSendFirstValue(CommonInput commonInput) {
        return SendFirstValue.EMPTY;
    }

    @Override
    protected BooleanExpression provideAdditionalCheck(CommonInput commonInput, SendFirstValue sendFirstValue) {
        return BooleanExpression.TRUE; //no additional check on the sendFirstValue needed
    }

    @Override
    public ChallengeSpace getChallengeSpace(CommonInput commonInput) {
        return new ZnChallengeSpace(zp);
    }

    public class Subprotocol2 extends DelegateProtocol { //Jeremy: this is like a normal protocol for (x,r); h^r = C2
        @Override
        protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
            MyOrProofCommonInput input = (MyOrProofCommonInput) commonInput;

            //Add variables (witnesses)
            SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
            SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

            //Add statements
            subprotocolSpecBuilder.addSubprotocol("statement1",
                    new LinearStatementFragment(input.h.pow(r).isEqualTo(input.C2))
            );

            return subprotocolSpecBuilder.build();
        }

        @Override
        protected SendThenDelegateFragment.ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, SendThenDelegateFragment.ProverSpecBuilder proverSpecBuilder) {
            MyOrProofSecretInput witness = (MyOrProofSecretInput) secretInput;

            proverSpecBuilder.putWitnessValue("x", witness.x);
            proverSpecBuilder.putWitnessValue("r", witness.r);

            return proverSpecBuilder.build();
        }

        @Override
        public ZnChallengeSpace getChallengeSpace(CommonInput commonInput) {
            return new ZnChallengeSpace(zp);
        }
    }


    public class Subprotocol3 extends DelegateProtocol { //Jeremy: this is like a normal protocol for (x,r); h^x = C2
        @Override
        protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
            MyOrProofCommonInput input = (MyOrProofCommonInput) commonInput; //Jeremy note that we retrieve the MyOrProofCommonInput here

            //Add variables (witnesses)
            SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
            SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

            //Add statements
            subprotocolSpecBuilder.addSubprotocol("statement1",
                    new LinearStatementFragment(input.h.pow(x).isEqualTo(input.C2))
            );

            return subprotocolSpecBuilder.build();
        }

        @Override
        protected SendThenDelegateFragment.ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, SendThenDelegateFragment.ProverSpecBuilder proverSpecBuilder) {
            MyOrProofSecretInput witness = (MyOrProofSecretInput) secretInput;

            proverSpecBuilder.putWitnessValue("x", witness.x);
            proverSpecBuilder.putWitnessValue("r", witness.r);
            
            return proverSpecBuilder.build();
        }

        @Override
        public ZnChallengeSpace getChallengeSpace(CommonInput commonInput) {
            return new ZnChallengeSpace(zp);
        }
    }


    public static class MyOrProofCommonInput implements CommonInput { //this derives as it currently would. Collect all the variables that are not pp or witnesses (no difference whether an OR is present or not)
        public final GroupElement C;
        public final GroupElement C2;
        public final GroupElement g;
        public final GroupElement h;

        public MyOrProofCommonInput(GroupElement C, GroupElement C2, GroupElement g, GroupElement h) {
            this.C = C;
            this.C2 = C2;
            this.g = g;
            this.h = h;
        }
    }

    public static class MyOrProofSecretInput implements SecretInput { //this derives as it currently would (no difference whether an OR is present or not)
        public final Zp.ZpElement x;
        public final Zp.ZpElement r;

        public MyOrProofSecretInput(Zp.ZpElement x, Zp.ZpElement r) {
            this.x = x;
            this.r = r;
        }
    }
}
