package prototype;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.math.serialization.ObjectRepresentation;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.serialization.StandaloneRepresentable;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;

public class MyProtocolPublicParameters implements StandaloneRepresentable {
    public final BilinearGroup bilinearGroup;
    public final SetMembershipPublicParameters rangeProofpp; //only needed if there's a two-sided range proof

    public MyProtocolPublicParameters(BilinearGroup bilinearGroup, SetMembershipPublicParameters rangeProofpp) {
        this.bilinearGroup = bilinearGroup;
        this.rangeProofpp = rangeProofpp;
    }

    public MyProtocolPublicParameters(Representation repr) {
        bilinearGroup = (BilinearGroup) repr.obj().get("bilinearGroup").repr().recreateRepresentable();
        rangeProofpp = new SetMembershipPublicParameters(bilinearGroup, repr.obj().get("setMembershipPp"));
    }

    public static MyProtocolPublicParameters generateNewParameters(BilinearGroup bilinearGroup) {
        SetMembershipPublicParameters rangeProof1pp = TwoSidedRangeProof.generatePublicParameters(bilinearGroup, 100);
        return new MyProtocolPublicParameters(bilinearGroup, rangeProof1pp);
    }

    @Override
    public Representation getRepresentation() {
        ObjectRepresentation repr = new ObjectRepresentation();
        repr.put("bilinearGroup", bilinearGroup.getRepresentation());
        repr.put("rangeProofpp", rangeProofpp.getRepresentation());
        return repr;
    }
}
