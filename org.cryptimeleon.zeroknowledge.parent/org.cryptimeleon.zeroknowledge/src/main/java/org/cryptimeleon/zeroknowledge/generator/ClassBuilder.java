package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single class (possibly an inner class) in generated code
 */
class ClassBuilder {
	
	private List<FieldBuilder> fields;
	private List<ConstructorBuilder> constructors;
	private List<MethodBuilder> methods;
	private List<ClassBuilder> innerClasses;
	
	private String name;
	
	private Modifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private String baseClassName;
	private List<String> interfaceNames;
	
	boolean hasBasicConstructor;
	Modifier basicConstructorModifier;
	
	public ClassBuilder(Modifier accessModifier, String name, Class<?> ... classes) {
		this(accessModifier, false, false, name, classes);
	}
	
	public ClassBuilder(Modifier accessModifier, Modifier extraModifier, String name, Class<?> ... classes) {
		this(
			accessModifier,
			extraModifier == Modifier.STATIC,
			extraModifier == Modifier.FINAL,
			name,
			classes
		);
	}
	
	public ClassBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, String name, Class<?> ... classes) {
		this(
			accessModifier, 
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier2 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			name,
			classes
		);
		
	}
	
	private ClassBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, String name, Class<?> ... classes) {
		fields = new ArrayList<FieldBuilder>();
		constructors = new ArrayList<ConstructorBuilder>();
		methods = new ArrayList<MethodBuilder>();
		innerClasses = new ArrayList<ClassBuilder>();
		
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.name = name;
		
		hasBasicConstructor = false;
		
		interfaceNames = new ArrayList<String>();
		baseClassName = null;
		
		for (Class<?> clazz : classes) {
			if (clazz.isInterface()) {
				interfaceNames.add(GenerationHelper.getClassName(clazz));
			} else if (baseClassName == null) {
				baseClassName = GenerationHelper.getClassName(clazz);
			} else {
				throw new IllegalArgumentException("Cannot have multiple base classes");
			}
		}
	}
	
	public void addField(FieldBuilder fieldBuilder) {
		fields.add(fieldBuilder);
	}
	
	public void addConstructor(ConstructorBuilder constructorBuilder) {
		constructorBuilder.setClassName(name);
		constructors.add(constructorBuilder);
	}
	
	public void addBasicConstructor() {
		addBasicConstructor(Modifier.PACKAGE);
	}
	
	public void addBasicConstructor(Modifier accessModifier) {
		if (!accessModifier.isAccessModifier()) {
			throw new IllegalArgumentException("Invalid access modifier");
		}
		
		hasBasicConstructor = true;
		basicConstructorModifier = accessModifier;
	}
	
	public void addMethod(MethodBuilder methodBuilder) {
		methods.add(methodBuilder);
	}
	
	public void addInnerClass(ClassBuilder classBuilder) {
		innerClasses.add(classBuilder);
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
		
		builder.append("class ");
		
		builder.append(name);
		builder.append(" ");

		if (baseClassName != null) {
			builder.append("extends ");
			builder.append(baseClassName);
			builder.append(" ");
		};
		
		if (interfaceNames.size() > 0) {
			builder.append("implements ");
		}
		for (int i = 0; i < interfaceNames.size(); i++) {
			builder.append(interfaceNames.get(i));
			builder.append(i == interfaceNames.size()-1 ? " " : ", ");
		}
		
		builder.append("{");
		builder.newLine();
		builder.indent();
		
		boolean addNewline = false;
		
		// Build all fields
		for (FieldBuilder fieldBuilder : fields) {
			builder.append(fieldBuilder);
		}
		addNewline = fields.size() > 0;

		// Build all constructors
		if (addNewline && (constructors.size() > 0 || hasBasicConstructor)) {
			addNewline = false;
			builder.newLine();
		}
		if (hasBasicConstructor) {
			ConstructorBuilder basicConstructor = new ConstructorBuilder(basicConstructorModifier);
			
			for (FieldBuilder fieldBuilder : fields) {
				Class<?> fieldType = fieldBuilder.getType();
				String fieldName = fieldBuilder.getName();
				basicConstructor.addParameter(fieldType, fieldName);
				basicConstructor.addStatement("this." + fieldName + " = " + fieldName + ';');
			}
			
			basicConstructor.setClassName(name);
			builder.append(basicConstructor);
			if (constructors.size() > 0) builder.newLine();
		}
		
		for (int i = 0; i < constructors.size(); i++) {
			builder.append(constructors.get(i));
			if (i != constructors.size()-1) builder.newLine();
		}
		addNewline = addNewline || constructors.size() > 0;

		// Build all methods
		if (addNewline && methods.size() > 0) {
			addNewline = false;
			builder.newLine();
		}
		for (int i = 0; i < methods.size(); i++) {
			builder.append(methods.get(i));
			if (i != methods.size()-1) builder.newLine();
		}
		addNewline = addNewline || methods.size() > 0;
		
		// Build all inner classes
		if (addNewline && innerClasses.size() > 0) {
			addNewline = false;
			builder.newLine();
		}
		for (int i = 0; i < innerClasses.size(); i++) {
			builder.append(innerClasses.get(i));
			if (i != innerClasses.size()-1) builder.newLine();
		}
		
		builder.outdent();
		builder.append("}");
		builder.newLine();
		
		return builder.toString();
	}
	
}
