package org.cryptimeleon.subzero.model

/*
 * A class to parse the format of a variable identifier
 */
class VariableIdentifier {
	static val String underscore = '_';
	static val String tilde = '~';
	static val String quote = '\'';
	
	String identifier;
	String name;
	String subscript;
	int primes;
	
	boolean hasBar;
	boolean hasTilde;
	boolean hasHat;
	
	boolean invalidQuote;
	boolean invalidUnderscore;
	boolean invalidTilde;
	
	new(String identifier) {
		this.identifier = identifier;
		name = identifier;
		
		subscript = "";
		primes = 0;
		
		hasBar = false;
		hasTilde = false;
		hasHat = false;
		
		invalidQuote = false;
		invalidUnderscore = false;
		invalidTilde = false;
		
		// Get all terminating single quotes
		while (name.endsWith(quote)) {
			primes++;
			name = name.substring(0, name.length() - 1);
		}
		
		if (primes == 0) {
			while (name.endsWith("Prime")) {
				primes++;
				name = name.substring(0, name.length() - 5);
			}
		}
		
		// Any more single quotes are invalid
		if (name.contains(quote)) {
			invalidQuote = true;
		}
		
		if (name.endsWith(underscore)) {
			// Check if the variable should have a bar over it
			hasBar = true;
			name = name.substring(0, name.length()-1);
			
		} else if (name.endsWith(tilde)) {
			// Check if the variable should have a tilde over it
			hasTilde = true;
			name = name.substring(0, name.length()-1);
		}

		// Any more tildes are invalid
		if (name.contains(tilde)) {
			invalidTilde = true;
		}
		
		// Get the variable subscript
		val int underscoreIndex = name.lastIndexOf(underscore);
		if (underscoreIndex == name.length()-1) {
			invalidUnderscore = true;
		} else if (underscoreIndex !== -1) {
			subscript = name.substring(underscoreIndex + 1);
			name = name.substring(0, underscoreIndex);
		}
		
		// Any more underscores are invalid
		if (name.contains(underscore)) {
			invalidUnderscore = true;
		}
		
		if (subscript.isEmpty()) {
			val int subIndex = name.indexOf("Sub");
			if (subIndex !== -1) {
				subscript = name.substring(subIndex + 3);
				if (!subscript.isEmpty()) {
					name = name.substring(0, subIndex);
				}
			}
		}
		
		
		// Check if the variable should have a bar, tilde, or hat over it
		if (!(hasTilde || hasBar)) {
			if (name.endsWith("Tilde")) {
				hasTilde = true;
				name = name.substring(0, name.length() - 5);
			} else if (name.endsWith("Bar")) {
				hasBar = true;
				name = name.substring(0, name.length() - 3);
			} else if (name.endsWith("Hat")) {
				hasHat = true;
				name = name.substring(0, name.length() - 3);
			}
		}
	}
	
	def String getIdentifier() {
		return identifier;
	}
	
	def String getName() {
		return name;
	}
	
	def String getSubscript() {
		return subscript;
	}
	
	def int getPrimes() {
		return primes;
	}
	
	def boolean hasSubscript() {
		return !subscript.isEmpty();
	}
	
	def boolean hasPrimes() {
		return primes !== 0;
	}
	
	def boolean hasBar() {
		return hasBar;
	}
	
	def boolean hasTilde() {
		return hasTilde;
	}
	
	def boolean hasHat() {
		return hasHat;
	}
	
	def boolean isValid() {
		return !(invalidQuote || invalidUnderscore || invalidTilde);
	}
	
	def boolean hasInvalidQuote() {
		return invalidQuote;
	}
	
	def boolean hasInvalidUnderscore() {
		return invalidUnderscore;
	}
	
	def boolean hasInvalidTilde() {
		return invalidTilde;
	}	
}