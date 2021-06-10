package org.cryptimeleon.subzero.model;

public enum GroupType {
	G1,
	G2,
	GT,
	UNKNOWN; // Used when a group element is assigned multiple groups, which will create a validation error
	
	public String toString() {
		switch(this) {
			case G1: return "G1";
			case G2: return "G2";
			case GT: return "GT";
			default: return "";
		}
	}
}
