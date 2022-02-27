package org.cryptimeleon.subzero.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrGroupElemVariable;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
import org.cryptimeleon.math.expressions.bool.BooleanExpression;
import org.cryptimeleon.math.expressions.exponent.ExponentExpr;
import org.cryptimeleon.math.expressions.group.GroupElementExpression;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;

/**
 * An enum to represent the Subzero type of a variable or return value
 */
public enum Type {
	BOOLEAN(
		"boolean",
		Boolean.class,
		Boolean.class,
		null
	),
	
	GROUP_ELEMENT(
		"group element",
		GroupElement.class,
		GroupElementExpression.class,
		SchnorrGroupElemVariable.class
	),
	
	EXPONENT(
		"exponent",
		ZpElement.class,
		ExponentExpr.class,
		SchnorrZnVariable.class
	),
	
	// TODO: remove STRING
	STRING(
		"string",
		String.class,
		String.class,
		null
	),
	
	UNKNOWN(
		"unknown",
		void.class,
		void.class,
		null
	);
	
	private static final Map<Class<?>, Type> typesByClass = new HashMap<>();
	static {
		for (Type type : Type.values()) {
			typesByClass.put(type.typeExprClass, type);
		}
	}
	
	private final String typeName;
	private final Class<?> typeClass;
	private final Class<?> typeExprClass;
	private final Class<?> typeWitnessClass;
	
	private Type(String typeName, Class<?> typeClass, Class<?> typeExprClass, Class<?> typeWitnessClass) {
		this.typeName = typeName;
		this.typeClass = typeClass;
		this.typeExprClass = typeExprClass;
		this.typeWitnessClass = typeWitnessClass;
	}
	
	public static Type toType(Class<?> clazz) {
		Type classType = typesByClass.get(clazz);
		return classType == null ? Type.UNKNOWN : classType;
	}
	
	public Class<?> getTypeClass() {
		return typeClass;
	}
	
	public Class<?> getTypeExprClass() {
		return typeExprClass;
	}
	
	public Class<?> getTypeWitnessClass() {
		return typeWitnessClass;
	}
	
	@Override
	public String toString() {
		return typeName;
	}
}


