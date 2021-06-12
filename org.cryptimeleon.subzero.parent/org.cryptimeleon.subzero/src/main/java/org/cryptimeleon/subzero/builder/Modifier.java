package org.cryptimeleon.subzero.builder;

/**
 * Represents the possible modifier values for classes, methods, and fields
 */
public enum Modifier {
	PUBLIC,
	PROTECTED,
	PRIVATE,
	PACKAGE,
	STATIC,
	FINAL;
	
	public boolean isAccessModifier() {
		return this == PUBLIC || this == PROTECTED || this == PRIVATE || this == PACKAGE;
	}
	
	@Override
	public String toString() {
		return (this == PACKAGE) ? "" : this.name().toLowerCase();
	}
}
