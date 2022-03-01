package org.cryptimeleon.subzero.latex;

import org.cryptimeleon.subzero.model.VariableIdentifier;

public class LatexIdentifier {
    private final VariableIdentifier varIdentifier;

	LatexIdentifier(String identifier) {
        varIdentifier = new VariableIdentifier(identifier);
    }

    // Formats identifiers into a nicer format for LaTeX
    @Override
    public String toString() {
        String name = varIdentifier.getName();

        String greekLetter = LatexGreekAlphabet.getCommand(name);
        if (greekLetter != null) {
            name = greekLetter;
        }

        if (varIdentifier.hasTilde()) {
            name = "\\tilde{" + name + "}";
        } else if (varIdentifier.hasBar()) {
            name = "\\bar{" + name + "}";
        } else if (varIdentifier.hasHat()) {
            name = "\\hat{" + name + "}";
        }

        String subscript = "";
        if (varIdentifier.hasSubscript()) {
            subscript = "_{" + varIdentifier.getSubscript() + "}";
        }

        String primes = "'".repeat(varIdentifier.getPrimes());

        return name + subscript + primes;
    }
}