package org.cryptimeleon.subzero.latex

import org.cryptimeleon.subzero.model.VariableIdentifier

class LatexIdentifier {
	
	VariableIdentifier varIdentifier;
	
	new(String identifier) {
		this.varIdentifier = new VariableIdentifier(identifier);
	}
	
	// Formats identifiers into a nicer format for LaTeX
	override String toString() {
		var String name = varIdentifier.getName();
		
		var String greekLetter = LatexGreekAlphabet.getCommand(name);
		if (greekLetter !== null) {
			name = greekLetter;
		}
		
		if (varIdentifier.hasTilde()) {
			name = "\\tilde{" + name + "}";
		} else if (varIdentifier.hasBar()) {
			name = "\\bar{" + name + "}"; 
		} else if (varIdentifier.hasHat()) {
			name = "\\hat{" + name + "}";
		}
		
		var String subscript = "";
		if (varIdentifier.hasSubscript()) {
			subscript = "_{" + varIdentifier.getSubscript() + "}";
		}
		
		var String primeString = "";
		if (varIdentifier.hasPrimes()) {
			val int primes = varIdentifier.getPrimes();
			for (var int i = 0; i < primes; i++) primeString += "'";
		}
		
		return name + subscript + primeString;
	}
}