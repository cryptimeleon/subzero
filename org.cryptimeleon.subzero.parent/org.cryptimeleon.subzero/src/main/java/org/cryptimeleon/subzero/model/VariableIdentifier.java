package org.cryptimeleon.subzero.model;

/*
 * A class to parse the format of a variable identifier
 */
public class VariableIdentifier {
    private static final String UNDERSCORE = "_";
    private static final String TILDE = "~";
    private static final String QUOTE = "'";
    private static final String SUBSCRIPT_KEYWORD = "Sub";
    private static final String PRIME_KEYWORD = "Prime";
    private static final String TILDE_KEYWORD = "Tilde";
    private static final String BAR_KEYWORD = "Bar";
    private static final String HAT_KEYWORD = "Hat";

    private final String identifier;
    private String name;

    private String subscript = "";
    private int primes = 0;

    private boolean hasBar = false;
    private boolean hasTilde = false;
    private boolean hasHat = false;

    private boolean invalidQuote = false;
    private boolean invalidUnderscore = false;
    private boolean invalidTilde = false;

	public VariableIdentifier(String identifier) {
        this.identifier = identifier;
        name = identifier;

        // Get all terminating single quotes
        while (name.endsWith(QUOTE)) {
            primes++;
            name = name.substring(0, name.length() - QUOTE.length());
        }

        if (primes == 0) {
            while (name.endsWith(PRIME_KEYWORD)) {
                primes++;
                name = name.substring(0, name.length() - PRIME_KEYWORD.length());
            }
        }

        // Any more single quotes are invalid
        if (name.contains(QUOTE)) {
            invalidQuote = true;
        }

        if (name.endsWith(UNDERSCORE)) {
            // Check if the variable should have a bar over it
            hasBar = true;
            name = name.substring(0, name.length() - UNDERSCORE.length());

        } else if (name.endsWith(TILDE)) {
            // Check if the variable should have a tilde over it
            hasTilde = true;
            name = name.substring(0, name.length() - TILDE.length());
        }

        // Any more tildes are invalid
        if (name.contains(TILDE)) {
            invalidTilde = true;
        }

        // Get the variable subscript
        int underscoreIndex = name.lastIndexOf(UNDERSCORE);
        if (underscoreIndex == name.length() - UNDERSCORE.length()) {
            invalidUnderscore = true;
        } else if (underscoreIndex != -1) {
            subscript = name.substring(underscoreIndex + UNDERSCORE.length());
            name = name.substring(0, underscoreIndex);
        }

        // Any more underscores are invalid
        if (name.contains(UNDERSCORE)) {
            invalidUnderscore = true;
        }

        if (subscript.isEmpty()) {
            int subIndex = name.indexOf(SUBSCRIPT_KEYWORD);
            if (subIndex != -1) {
                subscript = name.substring(subIndex + SUBSCRIPT_KEYWORD.length());
                if (!subscript.isEmpty()) {
                    name = name.substring(0, subIndex);
                }
            }
        }


        // Check if the variable should have a bar, tilde, or hat over it
        if (!(hasTilde || hasBar)) {
            if (name.endsWith(TILDE_KEYWORD)) {
                hasTilde = true;
                name = name.substring(0, name.length() - TILDE_KEYWORD.length());
            } else if (name.endsWith(BAR_KEYWORD)) {
                hasBar = true;
                name = name.substring(0, name.length() - BAR_KEYWORD.length());
            } else if (name.endsWith(HAT_KEYWORD)) {
                hasHat = true;
                name = name.substring(0, name.length() - HAT_KEYWORD.length());
            }
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getSubscript() {
        return subscript;
    }

    public int getPrimes() {
        return primes;
    }

    public boolean hasSubscript() {
        return !subscript.isEmpty();
    }

    public boolean hasPrimes() {
        return primes > 0;
    }

    public boolean hasBar() {
        return hasBar;
    }

    public boolean hasTilde() {
        return hasTilde;
    }

    public boolean hasHat() {
        return hasHat;
    }

    public boolean isValid() {
        return !(invalidQuote || invalidUnderscore || invalidTilde);
    }

    public boolean hasInvalidQuote() {
        return invalidQuote;
    }

    public boolean hasInvalidUnderscore() {
        return invalidUnderscore;
    }

    public boolean hasInvalidTilde() {
        return invalidTilde;
    }
}