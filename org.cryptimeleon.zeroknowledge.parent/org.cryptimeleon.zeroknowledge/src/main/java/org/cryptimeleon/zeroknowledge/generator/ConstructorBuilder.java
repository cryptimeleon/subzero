package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single class constructor in generated code
 */
public class ConstructorBuilder extends FunctionBuilder {
	
	public ConstructorBuilder() {
		super();
	}
	
	public ConstructorBuilder(Modifier accessModifier) {
		super(accessModifier);
	}
	
	public void setClassName(String className) {
		this.methodName = className;
	}
	
}
