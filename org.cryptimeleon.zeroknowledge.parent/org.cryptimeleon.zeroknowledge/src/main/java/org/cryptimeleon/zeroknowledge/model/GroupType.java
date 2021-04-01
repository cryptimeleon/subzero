package org.cryptimeleon.zeroknowledge.model;

public enum GroupType {
	G1,
	G2,
	GT,
	UNKNOWN; // Used when a group element is assigned multiple groups, which will create a validation error
	
	public String toString() {
		switch(this) {
			case G1: return "groupG1";
			case G2: return "groupG2";
			case GT: return "groupGT";
			default: return "";
		}
	}
}
