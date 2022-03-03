package org.cryptimeleon.subzero.predefined;

import org.cryptimeleon.subzero.model.Type;

public class FunctionParameter {
	private final String name;
	private final Type type;
	private final int size;
	
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
