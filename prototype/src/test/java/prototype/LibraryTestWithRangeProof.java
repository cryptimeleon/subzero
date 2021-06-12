package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
import org.cryptimeleon.math.structures.groups.lazy.LazyGroup;
import org.cryptimeleon.math.structures.rings.zn.Zp;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LibraryTestWithRangeProof {
    @Test public void protocolTest() {
        //Set up public paramters
        BilinearGroup bilinearGroup = new BarretoNaehrigBilinearGroup(80); //this (instead of a simple group) becomes necessary when there's a range proof or an e(g,h) call anywhere
        MyProtocolPublicParameters pp = MyProtocolPublicParameters.generateNewParameters(bilinearGroup); //these become necessary when there's a range proof
        Group groupG1 = bilinearGroup.getG1(); //Jeremy: default for all elements that don't fit the following criteria (for the sake of writing an example)
        Group groupG2 = bilinearGroup.getG2(); //Jeremy: elements h that appear (anywhere) within the second parameter of a e(g,h) call.
        Group groupGT = bilinearGroup.getGT(); //Jeremy: elements z that appear (anywhere) in an equality that contains an e(...) call, e.g., "z = e(g,h)^x".
        Zp zp = (Zp) groupG1.getZn();

        //Set witness
        Zp.ZpElement x = zp.valueOf(5);
        Zp.ZpElement x2 = zp.valueOf(2); //Jeremy: so that x+x2 is in the range [5,200] and x1-x2 = 3. There's a general question here how we handle example input instantiation. In general, we'd have to solve some equation system to come up with valid input. There do exist equation solvers for such things (like Integer Linear Programs). Maybe that's overkill and we should just choose everything randomly for now and let people correct this towards a valid witness themselves.
        Zp.ZpElement r = zp.getUniformlyRandomElement(); //Jeremy: if there are no range or linear exponent constraints, just choose it randomly

        //Set constants
        GroupElement C = groupG1.getNeutralElement(); //Jeremy: for now. Later, we may want generate a more useful example
        GroupElement C2 = groupG1.getNeutralElement();
        GroupElement g = groupG1.getNeutralElement();
        GroupElement h = groupG1.getNeutralElement();

        //Instantiate protocol and input
        MySigmaProtocolWithRangeProof protocol = new MySigmaProtocolWithRangeProof(bilinearGroup, pp);

        CommonInput commonInput = new MySigmaProtocolWithRangeProof.MySigmaProtocolCommonInput(C, C2, g, h);
        SecretInput secretInput = new MySigmaProtocolWithRangeProof.MySigmaProtocolSecretInput(x, x2, r);

        SigmaProtocolProverInstance prover = protocol.getProverInstance(commonInput, secretInput);
        SigmaProtocolVerifierInstance verifier = protocol.getVerifierInstance(commonInput);

        protocol.runProtocolLocally(prover, verifier);
        assertTrue(verifier.hasTerminated());
        assertTrue(verifier.isAccepting());
        if (verifier.isAccepting())
            System.out.println("Yay, the protocol worked!");
    }
}
