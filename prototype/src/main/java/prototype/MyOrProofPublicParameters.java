package prototype;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.math.serialization.ObjectRepresentation;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.serialization.StandaloneRepresentable;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;

public class MyOrProofPublicParameters implements StandaloneRepresentable {
    public final Group group;
    //public final SetMembershipPublicParameters rangeProofpp; //Jeremy: only needed if there's a two-sided range proof
    public final GroupElementVector crossOrCommitmentBases; //Jeremy: only needed if there's an AND node with an OR descendant.

    public MyOrProofPublicParameters(Group group, GroupElementVector crossOrCommitmentBases) {
        this.group = group;
        this.crossOrCommitmentBases = crossOrCommitmentBases;
    }

    public MyOrProofPublicParameters(Representation repr) {
        group = (Group) repr.obj().get("group").repr().recreateRepresentable();
        crossOrCommitmentBases = group.restoreVector(repr.obj().get("commitmentBases"));
    }

    public static MyOrProofPublicParameters generateNewParameters(Group group) {
        int numberOfZnWitnesses = 2; //Jeremy: you need to count that - for MyOrProof, it's two because it's "(x,r)". Only count exponent variables. Just disallow that a group element witness occurs in both subtrees of an [OR with an AND ascendant]
        GroupElementVector crossOrCommitmentBases = group.getUniformlyRandomNonNeutrals(numberOfZnWitnesses + 1);
        return new MyOrProofPublicParameters(group, crossOrCommitmentBases);
    }

    @Override
    public Representation getRepresentation() {
        ObjectRepresentation repr = new ObjectRepresentation();
        repr.put("group", group.getRepresentation());
        repr.put("commitmentBases", crossOrCommitmentBases.getRepresentation());
        return repr;
    }
}
