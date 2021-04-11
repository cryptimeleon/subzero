package prototype;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.math.serialization.ObjectRepresentation;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.serialization.StandaloneRepresentable;
import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;

public class MyProtocolPublicParameters implements StandaloneRepresentable {
    public final BilinearGroup bilinearGroup;
    public final SetMembershipPublicParameters rangeProofpp; //Jeremy: only needed if there's a two-sided range proof
    //public final GroupElementVector crossOrCommitmentBases; //Jeremy: only needed if there's an AND node with an OR descendant.

    public MyProtocolPublicParameters(BilinearGroup bilinearGroup, SetMembershipPublicParameters rangeProofpp/*, GroupElementVector crossOrCommitmentBases*/) {
        this.bilinearGroup = bilinearGroup;
        this.rangeProofpp = rangeProofpp;
        //this.crossOrCommitmentBases = crossOrCommitmentBases;
    }

    public MyProtocolPublicParameters(Representation repr) {
        bilinearGroup = (BilinearGroup) repr.obj().get("group").repr().recreateRepresentable();
        rangeProofpp = new SetMembershipPublicParameters(bilinearGroup, repr.obj().get("setMembershipPp"));
        //crossOrCommitmentBases = bilinearGroup.getG1().restoreVector(repr.obj().get("commitmentBases"));
    }

    public static MyProtocolPublicParameters generateNewParameters(BilinearGroup bilinearGroup) {
        SetMembershipPublicParameters rangeProof1pp = TwoSidedRangeProof.generatePublicParameters(bilinearGroup, 100);
        //int numberOfZnWitnesses = 2; //Jeremy: you need to count that - for MyOrProof, it's two because it's "(x,r)". Only count exponent variables. Just disallow that a group element witness occurs in both subtrees of an [OR with an AND ascendant]
        //GroupElementVector crossOrCommitmentBases = bilinearGroup.getG1().getUniformlyRandomNonNeutrals(numberOfZnWitnesses + 1);
        return new MyProtocolPublicParameters(bilinearGroup, rangeProof1pp/*, crossOrCommitmentBases*/);
    }

    @Override
    public Representation getRepresentation() {
        ObjectRepresentation repr = new ObjectRepresentation();
        repr.put("group", bilinearGroup.getRepresentation());
        repr.put("rangeProofpp", rangeProofpp.getRepresentation());
        //repr.put("commitmentBases", crossOrCommitmentBases.getRepresentation());
        return repr;
    }
}
