package org.cryptimeleon.zeroknowledge.generator;

/**
 * Represents a single class method in generated code
 */
public class MethodBuilder extends FunctionBuilder {
	
	public MethodBuilder(Class<?> returnType, String methodName) {
		super(returnType, methodName);
	}
	
	public MethodBuilder(Modifier accessModifier, Class<?> returnType, String methodName) {
		super(accessModifier, returnType, methodName);
	}
	
	public MethodBuilder(Modifier accessModifier, Modifier extraModifier, Class<?> returnType, String methodName) {
		super(accessModifier, extraModifier, returnType, methodName);
	}
	
	public MethodBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, Class<?> returnType, String methodName) {
		super(accessModifier, extraModifier1, extraModifier2, returnType, methodName);
	}
	
}