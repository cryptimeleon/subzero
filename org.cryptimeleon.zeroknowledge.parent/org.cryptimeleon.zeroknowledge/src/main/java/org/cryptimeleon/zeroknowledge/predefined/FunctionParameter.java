package org.cryptimeleon.zeroknowledge.predefined;

import org.cryptimeleon.zeroknowledge.model.Type;

public class FunctionParameter {
	private String name;
	private Type type;
	private int size;
	
	public FunctionParameter(String name, Type type) {
		this(name, type, 1);
	}
	
	public FunctionParameter(String name, Type type, int size) {
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
