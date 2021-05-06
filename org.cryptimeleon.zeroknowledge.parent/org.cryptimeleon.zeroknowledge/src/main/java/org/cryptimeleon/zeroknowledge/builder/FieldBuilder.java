package org.cryptimeleon.zeroknowledge.builder;

/**
 * Represents a single class field in generated code
 */
public class FieldBuilder {
	
	private Modifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private String typeName;
	private String name;
	
	public FieldBuilder(Class<?> type, String name) {
		this(type.getSimpleName(), name);
	}
	
	public FieldBuilder(Modifier accessModifier, Class<?> type, String name) {
		this(accessModifier, type.getSimpleName(), name);
	}
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier, Class<?> type, String name) {
		this(accessModifier, extraModifier, type.getSimpleName(), name);
	}
	
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, Class<?> type, String name) {
		this(accessModifier, extraModifier1, extraModifier2, type.getSimpleName(), name);
	}
	
	public FieldBuilder(String typeName, String name) {
		this(Modifier.PACKAGE, false, false, typeName, name);
	}
	
	public FieldBuilder(Modifier accessModifier, String typeName, String name) {
		this(accessModifier, false, false, typeName, name);
	}
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier, String typeName, String name) {
		this(accessModifier, extraModifier == Modifier.STATIC, extraModifier == Modifier.FINAL, typeName, name);
	}
	
	
	public FieldBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, String typeName, String name) {
		this(
			accessModifier,
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier1 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			typeName,
			name
		);
	}
	
	private FieldBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, String typeName, String name) {
		if (!accessModifier.isAccessModifier()) {
			throw new IllegalArgumentException("Invalid access modifier");
		}
		
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.typeName = typeName;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public String toString() {
		return this.toString(0);
	}
	
	
	
	public String toString(int indentLevel) {
		CodeBuilder builder = new CodeBuilder(indentLevel);
		String modifier = accessModifier.toString();
		builder.append(modifier);
		if (!modifier.isEmpty()) builder.space();
		if (isStatic) builder.append("static ");
		if (isFinal) builder.append("final ");
		builder.append(typeName);
		builder.space();
		builder.append(name);
		builder.append(";");
		builder.newLine();
		
		return builder.toString();
	}
	
}
