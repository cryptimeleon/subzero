package org.cryptimeleon.zeroknowledge.generator;

public class FieldBuilder {
	
	private Modifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private Class<?> type;
	private String typeName;
	private String name;
	
	public FieldBuilder(Class<?> type, String name) {
		this(Modifier.PACKAGE, false, false, type, name);
	}
	
	public FieldBuilder(Modifier accessModifier, Class<?> type, String name) {
		this(accessModifier, false, false, type, name);
	}
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier, Class<?> type, String name) {
		this(accessModifier, extraModifier == Modifier.STATIC, extraModifier == Modifier.FINAL, type, name);
	}
	
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, Class<?> type, String name) {
		this(
			accessModifier,
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier1 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			type,
			name
		);
	}
	
	private FieldBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, Class<?> type, String name) {
		if (!accessModifier.isAccessModifier()) {
			throw new IllegalArgumentException("Invalid access modifier");
		}
		
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.type = type;
		this.typeName = GenerationHelper.getClassName(type);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return this.toString(0);
	}
	
	
	
	public String toString(int indentLevel) {
		CodeBuilder builder = new CodeBuilder(indentLevel);
		builder.append(accessModifier.toString());
		builder.append(" ");
		if (isStatic) builder.append("static ");
		if (isFinal) builder.append("final ");
		builder.append(typeName);
		builder.append(" ");
		builder.append(name);
		builder.append(";");
		builder.newLine();
		
		return builder.toString();
	}
	
}
