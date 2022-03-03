package org.cryptimeleon.subzero.builder;

import java.util.EnumSet;
import java.util.Set;

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

	private static final Set<Modifier> accessModifiers = EnumSet.of(PUBLIC, PROTECTED, PRIVATE, PACKAGE);

	public boolean isAccessModifier() {
		return accessModifiers.contains(this);
	}
	
	@Override
	public String toString() {
		return (this == PACKAGE) ? "" : this.name().toLowerCase();
	}
}
