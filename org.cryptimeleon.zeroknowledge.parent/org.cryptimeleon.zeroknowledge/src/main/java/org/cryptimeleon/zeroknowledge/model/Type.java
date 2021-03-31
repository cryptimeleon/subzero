package org.cryptimeleon.zeroknowledge.model;

import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;

/**
 * An enum to represent the 0K type of a variable or return value
 */
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
			case "String" : return Type.STRING;
			default: return Type.UNKNOWN;
		}
	}
	
	public String toString() {
		switch(this) {
			case BOOLEAN: return "boolean";
			case GROUP_ELEMENT: return "GroupElementExpression";
			case EXPONENT: return "ExponentExpr";
			case STRING: return "String";
			default: return "void";
		}
	}
	
	public Class<?> getTypeClass() {
		switch (this) {
			case BOOLEAN: return Boolean.class;
			case GROUP_ELEMENT: return GroupElement.class;
			case EXPONENT: return ZpElement.class;
			case STRING: return String.class;
			default: return void.class;
		}
	}
}


