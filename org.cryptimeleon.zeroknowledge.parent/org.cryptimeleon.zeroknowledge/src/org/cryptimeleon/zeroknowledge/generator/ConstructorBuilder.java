package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

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
