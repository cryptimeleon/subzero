package org.cryptimeleon.subzero.model;

public enum GroupType {
	G1,
	G2,
	GT,
	UNKNOWN; // Used when a group element is assigned multiple groups, which will create a validation error
	
	public String toString() {
		if (this == UNKNOWN) return "unknown";
		return name();
	}
}
