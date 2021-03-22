package org.cryptimeleon.zeroknowledge.generator;

public enum Modifier {
	PUBLIC,
	PROTECTED,
	PRIVATE,
	PACKAGE,
	STATIC,
	FINAL;
	
	public boolean isAccessModifier() {
		return this == PUBLIC || this == PROTECTED || this == PROTECTED || this == PACKAGE;
	}
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
