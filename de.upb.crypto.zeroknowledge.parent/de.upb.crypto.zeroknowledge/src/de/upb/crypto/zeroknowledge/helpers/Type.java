package de.upb.crypto.zeroknowledge.helpers;

public enum Type {
	BOOLEAN,
	GROUP_ELEMENT,
	EXPONENT,
	GROUP_ELEMENT_TUPLE,
	EXPONENT_TUPLE,
	STRING,
	UNKNOWN;
	
	public static Type convert(String type) {
		switch(type) {
			case "boolean": return Type.BOOLEAN;
			case "GroupElementExpression": return Type.GROUP_ELEMENT;
			case "ExponentExpr": return Type.EXPONENT;
			case "GroupElementTuple": return Type.GROUP_ELEMENT_TUPLE;
			case "ExponentTuple": return Type.EXPONENT_TUPLE;
			default: return Type.UNKNOWN;
		}
	}
	
	public static String toReturnType(Type type) {
		switch(type) {
			case BOOLEAN: return "boolean";
			case GROUP_ELEMENT: return "GroupElementExpression";
			case EXPONENT: return "ExponentExpr";
			case GROUP_ELEMENT_TUPLE: return "GroupElementTuple";
			case EXPONENT_TUPLE: return "ExponentTuple";
			default: return "void";
		}
	}
}

