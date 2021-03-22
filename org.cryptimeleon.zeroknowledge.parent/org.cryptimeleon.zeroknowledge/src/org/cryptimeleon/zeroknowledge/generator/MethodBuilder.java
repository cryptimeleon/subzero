package org.cryptimeleon.zeroknowledge.generator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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