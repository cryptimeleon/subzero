package prototype;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.math.serialization.ObjectRepresentation;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.serialization.StandaloneRepresentable;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;

/**
 * Jeremy: Ignore for now.
 */
public class MyProtocolPublicParameters implements StandaloneRepresentable {
    public final BilinearGroup bilinearGroup;
    //public final SetMembershipPublicParameters setMembershipPp;

    public MyProtocolPublicParameters(BilinearGroup bilinearGroup/*, SetMembershipPublicParameters setMembershipPp*/) {
        this.bilinearGroup = bilinearGroup;
        //this.setMembershipPp = setMembershipPp;
    }

    public MyProtocolPublicParameters(Representation repr) {
        bilinearGroup = (BilinearGroup) repr.obj().get("bilinearGroup").repr().recreateRepresentable();
        //setMembershipPp = new SetMembershipPublicParameters(bilinearGroup, repr.obj().get("setMembershipPp"));
    }

    public static MyProtocolPublicParameters generateNewParameters() {
        BilinearGroup bilinearGroup = new BarretoNaehrigBilinearGroup(128);
        //SetMembershipPublicParameters setMembershipPp = TwoSidedRangeProof.generatePublicParameters(bilinearGroup, 100);
        return new MyProtocolPublicParameters(bilinearGroup/*, setMembershipPp*/);
    }

    @Override
    public Representation getRepresentation() {
        return new ObjectRepresentation("bilinearGroup", bilinearGroup.getRepresentation());
    }
}
