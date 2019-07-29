package de.upb.crypto.zeroknowledge.helpers;

public enum Type {
	BOOLEAN,
	GROUP_ELEMENT,
	EXPONENT,
	STRING,
	UNKNOWN;
	
	public static Type toType(String type) {
		switch(type) {
			case "boolean": return Type.BOOLEAN;
			case "GroupElementExpression": return Type.GROUP_ELEMENT;
			case "ExponentExpr": return Type.EXPONENT;
			default: return Type.UNKNOWN;
		}
	}
	
	public static String toString(Type type) {
		switch(type) {
			case BOOLEAN: return "boolean";
			case GROUP_ELEMENT: return "GroupElementExpression";
			case EXPONENT: return "ExponentExpr";
			default: return "void";
		}
	}
}

