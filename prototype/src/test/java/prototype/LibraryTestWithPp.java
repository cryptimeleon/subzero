package prototype;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
import org.cryptimeleon.math.structures.groups.lazy.LazyGroup;
import org.cryptimeleon.math.structures.rings.zn.Zp;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LibraryTestWithPp {
    @Test public void protocolTest() {
        //Set up public paramters
        Group group = new LazyGroup(new Secp256k1());
        Zp zp = (Zp) group.getZn();
        GroupElement g = group.getNeutralElement();
        GroupElement h = group.getNeutralElement();

        //Set witness
        Zp.ZpElement x = zp.getUniformlyRandomElement();
        Zp.ZpElement r = zp.getUniformlyRandomElement();

        //Set constants
        GroupElement C = group.getNeutralElement(); //Jeremy: for now. Later, we may want generate a more useful example
        GroupElement C2 = group.getNeutralElement();

        //Instantiate protocol and input
        MySigmaProtocolWithPp protocol = new MySigmaProtocolWithPp(group, g, h);

        CommonInput commonInput = new MySigmaProtocolWithPp.MySigmaProtocolCommonInput(C, C2);
        SecretInput secretInput = new MySigmaProtocolWithPp.MySigmaProtocolSecretInput(x, r);

        SigmaProtocolProverInstance prover = protocol.getProverInstance(commonInput, secretInput);
        SigmaProtocolVerifierInstance verifier = protocol.getVerifierInstance(commonInput);

        protocol.runProtocolLocally(prover, verifier);
        assertTrue(verifier.hasTerminated());
        assertTrue(verifier.isAccepting());
        if (verifier.isAccepting())
            System.out.println("Yay, the protocol worked!");
    }
}
