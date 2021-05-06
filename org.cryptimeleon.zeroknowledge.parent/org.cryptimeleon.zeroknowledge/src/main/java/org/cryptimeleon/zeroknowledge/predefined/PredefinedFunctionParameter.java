package org.cryptimeleon.zeroknowledge.predefined;

import org.cryptimeleon.zeroknowledge.model.Type;

public class PredefinedFunctionParameter {
	private String name;
	private Type type;
	private int size;
	
	public PredefinedFunctionParameter(String name, Type type) {
		this(name, type, 1);
	}
	
	public PredefinedFunctionParameter(String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	public Type getType() {
		return type;
	}
	public int getSize() {
		return size;
	}
	
	
}
