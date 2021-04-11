package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.ChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.ZnChallengeSpace;
import org.cryptimeleon.craco.protocols.arguments.sigma.partial.OrProof;
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
import org.cryptimeleon.math.structures.rings.Ring;
import org.cryptimeleon.math.structures.rings.cartesian.RingElementVector;
import org.cryptimeleon.math.structures.rings.zn.Zn;
import org.cryptimeleon.math.structures.rings.zn.Zp;

/**
 * Protocol for
 * (x,r); g^x * h^r = C
 * & (h^r = C2 | h^x = C2)
 *
 * Jeremy: every subtree that does not contain an "OR" becomes a DelegateProtocol (basically generated as before) (okay, obviously only "maximal" subtrees with the "no OR" property). Then this ProofOfPartialKnowledge class glues the DelegateProtocols together
 */
public class MyOrProof extends ProofOfPartialKnowledge { //Jeremy: extend this class as soon as there's an "OR" anywhere.
    protected MyOrProofPublicParameters pp; //Jeremy: needed here because of the OR. If a bilinear map or range proof is used, use MyProtocolPublicParameters instead with the appropriate stuff commented in
    protected Group group; //Jeremy: OR proofs also work if this is a BilinearGroup. In that case, replace all occurrences of group below with group.getG1()
    protected Zp zp;

    public MyOrProof(Group group, MyOrProofPublicParameters pp) {
        this.pp = pp;
        this.group = group;
        this.zp = (Zp) this.group.getZn();
    }

    @Override
    protected ProtocolTree provideProtocolTree(CommonInput commonInput, SendFirstValue sendFirstValue) {
        SubprotocolCommonInput subprotocolCommonInput = new SubprotocolCommonInput((MyOrProofCommonInput) commonInput, ((SendFirstValue.AlgebraicSendFirstValue) sendFirstValue).getGroupElement(0));
        return and(
                leaf("Subprotocol1", new Subprotocol1(), subprotocolCommonInput),
                or(
                        leaf("Subprotocol2", new Subprotocol2(), subprotocolCommonInput),
                        leaf("Subprotocol3", new Subprotocol3(), subprotocolCommonInput)
                )
        );
    }

    @Override
    protected ProverSpec provideProverSpec(CommonInput commonInput, SecretInput secretInput, ProverSpecBuilder builder) {
        MyOrProofSecretInput overallSecretInput = (MyOrProofSecretInput) secretInput;

        //Commit to all Zn variables
        Zn.ZnElement crossOrCommitmentRandomness = zp.getUniformlyRandomElement();
        GroupElement crossOrCommitment = pp.crossOrCommitmentBases.innerProduct(RingElementVector.of(overallSecretInput.x, overallSecretInput.r, crossOrCommitmentRandomness)); //Jeremy: list all Zn witnesses here.

        //Send this commitment
        builder.setSendFirstValue(new SendFirstValue.AlgebraicSendFirstValue(crossOrCommitment));

        //Set up witnesses for subprotocols (which is the secret input for the whole protocol plus the commitment randomness)
        SecretInput subprotocolSecret = new SubprotocolSecretInput(overallSecretInput, crossOrCommitmentRandomness);
        builder.putSecretInput("Subprotocol1", subprotocolSecret);
        builder.putSecretInput("Subprotocol2", subprotocolSecret);
        builder.putSecretInput("Subprotocol3", subprotocolSecret);

        return builder.build();
    }

    @Override
    protected SendFirstValue restoreSendFirstValue(CommonInput commonInput, Representation repr) {
        return new SendFirstValue.AlgebraicSendFirstValue(repr, group);
    }

    @Override
    protected SendFirstValue simulateSendFirstValue(CommonInput commonInput) {
        return new SendFirstValue.AlgebraicSendFirstValue(group.getUniformlyRandomElement());
    }

    @Override
    protected BooleanExpression provideAdditionalCheck(CommonInput commonInput, SendFirstValue sendFirstValue) {
        return BooleanExpression.TRUE; //no additional check on the sendFirstValue needed
    }

    @Override
    public ChallengeSpace getChallengeSpace(CommonInput commonInput) {
        return new ZnChallengeSpace(zp);
    }

    public class Subprotocol1 extends DelegateProtocol { //Jeremy: this is like a normal protocol for (x,r); g^x * h^r = C except that pp, group, etc. are stored outside of it now and CommonInput and SecretInput are not inner classes of _this_ anymore.
        @Override
        protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
            MyOrProofCommonInput input = ((SubprotocolCommonInput) commonInput).commonInput; //Jeremy note that we retrieve the MyOrProofCommonInput here

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

            //Add proof that the same values are used in this subprotocol as in the others //Jeremy: this is new in the OR case. It's the same for all subprotocols
            SchnorrZnVariable crossOrCommitmentRandomness = subprotocolSpecBuilder.addZnVariable("crossOrCommitmentRandomness", zp);
            subprotocolSpecBuilder.addSubprotocol("orProofConsistency",
                    new LinearStatementFragment(
                            pp.crossOrCommitmentBases.expr().innerProduct(ExponentExpressionVector.of(x, r, crossOrCommitmentRandomness)) //Jeremy: same order as in MyOrProof::provideProverSpec
                            .isEqualTo(((SubprotocolCommonInput) commonInput).crossOrCommitment)
                    )
            );

            return subprotocolSpecBuilder.build();
        }

        @Override
        protected SendThenDelegateFragment.ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, SendThenDelegateFragment.ProverSpecBuilder proverSpecBuilder) {
            MyOrProofSecretInput witness = ((SubprotocolSecretInput) secretInput).secretInput; //Jeremy: same note as first line in provideSubprotocolSpec

            proverSpecBuilder.putWitnessValue("x", witness.x);
            proverSpecBuilder.putWitnessValue("r", witness.r);

            proverSpecBuilder.putWitnessValue("crossOrCommitmentRandomness", ((SubprotocolSecretInput) secretInput).crossOrCommitmentRandomness);

            return proverSpecBuilder.build();
        }

        @Override
        public ZnChallengeSpace getChallengeSpace(CommonInput commonInput) {
            return new ZnChallengeSpace(zp);
        }
    }

    public class Subprotocol2 extends DelegateProtocol { //Jeremy: this is like a normal protocol for (x,r); h^r = C2
        @Override
        protected SubprotocolSpec provideSubprotocolSpec(CommonInput commonInput, SubprotocolSpecBuilder subprotocolSpecBuilder) {
            MyOrProofCommonInput input = ((SubprotocolCommonInput) commonInput).commonInput; //Jeremy note that we retrieve the MyOrProofCommonInput here

            //Add variables (witnesses)
            SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
            SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

            //Add statements
            subprotocolSpecBuilder.addSubprotocol("statement1",
                    new LinearStatementFragment(input.h.pow(r).isEqualTo(input.C2))
            );

            //Add proof that the same values are used in this subprotocol as in the others //Jeremy: this is new in the OR case. It's the same for all subprotocols
            SchnorrZnVariable crossOrCommitmentRandomness = subprotocolSpecBuilder.addZnVariable("crossOrCommitmentRandomness", zp);
            subprotocolSpecBuilder.addSubprotocol("orProofConsistency",
                    new LinearStatementFragment(
                            pp.crossOrCommitmentBases.expr().innerProduct(ExponentExpressionVector.of(x, r, crossOrCommitmentRandomness)) //Jeremy: same order as in MyOrProof::provideProverSpec
                                    .isEqualTo(((SubprotocolCommonInput) commonInput).crossOrCommitment)
                    )
            );

            return subprotocolSpecBuilder.build();
        }

        @Override
        protected SendThenDelegateFragment.ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, SendThenDelegateFragment.ProverSpecBuilder proverSpecBuilder) {
            MyOrProofSecretInput witness = ((SubprotocolSecretInput) secretInput).secretInput; //Jeremy: same note as first line in provideSubprotocolSpec

            proverSpecBuilder.putWitnessValue("x", witness.x);
            proverSpecBuilder.putWitnessValue("r", witness.r);

            proverSpecBuilder.putWitnessValue("crossOrCommitmentRandomness", ((SubprotocolSecretInput) secretInput).crossOrCommitmentRandomness);

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
            MyOrProofCommonInput input = ((SubprotocolCommonInput) commonInput).commonInput; //Jeremy note that we retrieve the MyOrProofCommonInput here

            //Add variables (witnesses)
            SchnorrZnVariable x = subprotocolSpecBuilder.addZnVariable("x", zp);
            SchnorrZnVariable r = subprotocolSpecBuilder.addZnVariable("r", zp);

            //Add statements
            subprotocolSpecBuilder.addSubprotocol("statement1",
                    new LinearStatementFragment(input.h.pow(x).isEqualTo(input.C2))
            );

            //Add proof that the same values are used in this subprotocol as in the others //Jeremy: this is new in the OR case. It's the same for all subprotocols
            SchnorrZnVariable crossOrCommitmentRandomness = subprotocolSpecBuilder.addZnVariable("crossOrCommitmentRandomness", zp);
            subprotocolSpecBuilder.addSubprotocol("orProofConsistency",
                    new LinearStatementFragment(
                            pp.crossOrCommitmentBases.expr().innerProduct(ExponentExpressionVector.of(x, r, crossOrCommitmentRandomness)) //Jeremy: same order as in MyOrProof::provideProverSpec
                                    .isEqualTo(((SubprotocolCommonInput) commonInput).crossOrCommitment)
                    )
            );

            return subprotocolSpecBuilder.build();
        }

        @Override
        protected SendThenDelegateFragment.ProverSpec provideProverSpecWithNoSendFirst(CommonInput commonInput, SecretInput secretInput, SendThenDelegateFragment.ProverSpecBuilder proverSpecBuilder) {
            MyOrProofSecretInput witness = ((SubprotocolSecretInput) secretInput).secretInput; //Jeremy: same note as first line in provideSubprotocolSpec

            proverSpecBuilder.putWitnessValue("x", witness.x);
            proverSpecBuilder.putWitnessValue("r", witness.r);

            proverSpecBuilder.putWitnessValue("crossOrCommitmentRandomness", ((SubprotocolSecretInput) secretInput).crossOrCommitmentRandomness);

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

    private static class SubprotocolCommonInput implements CommonInput {
        public final MyOrProofCommonInput commonInput;
        /**
         * Pedersen commitment to all the witnesses to ensure that the same witnesses are used for in all of the OR branches in the overall proof.
         */
        public final GroupElement crossOrCommitment;

        public SubprotocolCommonInput(MyOrProofCommonInput commonInput, GroupElement crossOrCommitment) {
            this.commonInput = commonInput;
            this.crossOrCommitment = crossOrCommitment;
        }
    }


    private static class SubprotocolSecretInput implements SecretInput {
        public final MyOrProofSecretInput secretInput;
        /**
         * Randomness used for crossOrCommitment
         */
        public final Zn.ZnElement crossOrCommitmentRandomness;

        public SubprotocolSecretInput(MyOrProofSecretInput secretInput, Zn.ZnElement crossOrCommitmentRandomness) {
            this.secretInput = secretInput;
            this.crossOrCommitmentRandomness = crossOrCommitmentRandomness;
        }
    }
}
