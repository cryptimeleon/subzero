package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
import org.cryptimeleon.math.structures.rings.zn.Zp;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LibraryTestWithPairing {
    @Test public void protocolTest() {
        //Set up public paramters
        BilinearGroup bilinearGroup = new BarretoNaehrigBilinearGroup(80); //this (instead of a simple group) becomes necessary when there's a range proof or an e(g,h) call anywhere
        //MyProtocolPublicParameters pp = MyProtocolPublicParameters.generateNewParameters(bilinearGroup); //these become necessary when there's a range proof
        Group groupG1 = bilinearGroup.getG1(); //Jeremy: default for all elements that don't fit the following criteria (for the sake of writing an example)
        Group groupG2 = bilinearGroup.getG2(); //Jeremy: elements h that appear (anywhere) within the second parameter of a e(g,h) call.
        Group groupGT = bilinearGroup.getGT(); //Jeremy: elements z that appear (anywhere) in an equality that contains an e(...) call, e.g., "z = e(g,h)^x".
        Zp zp = (Zp) groupG1.getZn();

        //Set witness
        Zp.ZpElement x = zp.getUniformlyRandomElement();
        Zp.ZpElement r = zp.getUniformlyRandomElement();

        //Set constants
        GroupElement C = groupG1.getNeutralElement();
        GroupElement z = groupGT.getNeutralElement(); //Jeremy: not the GT here because z appears within an equation containing e.
        GroupElement g = groupG1.getNeutralElement();
        GroupElement h = groupG2.getNeutralElement(); //Jeremy: note the G2 because h appears within second argument of e.

        //Instantiate protocol and input
        MySigmaProtocolWithPairing protocol = new MySigmaProtocolWithPairing(bilinearGroup);

        CommonInput commonInput = new MySigmaProtocolWithPairing.MySigmaProtocolCommonInput(C, z, g, h);
        SecretInput secretInput = new MySigmaProtocolWithPairing.MySigmaProtocolSecretInput(x, r);

        SigmaProtocolProverInstance prover = protocol.getProverInstance(commonInput, secretInput);
        SigmaProtocolVerifierInstance verifier = protocol.getVerifierInstance(commonInput);

        protocol.runProtocolLocally(prover, verifier);
        assertTrue(verifier.hasTerminated());
        assertTrue(verifier.isAccepting());
        if (verifier.isAccepting())
            System.out.println("Yay, the protocol worked!");
    }
}
