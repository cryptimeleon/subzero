package org.cryptimeleon.subzero.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single class (possibly an nested class) in generated code
 */
public class ClassBuilder {
	
	private final Deque<FieldBuilder> fields;
	private final Deque<ConstructorBuilder> constructors;
	private final Deque<MethodBuilder> methods;
	private final Deque<ClassBuilder> nestedClasses;
	
	private final String name;
	
	private final Modifier accessModifier;
	private final boolean isStatic;
	private final boolean isFinal;
	private final List<String> interfaceNames;
	private String baseClassName;

	boolean hasBasicConstructor;
	Modifier basicConstructorModifier;
	
	public ClassBuilder(Modifier accessModifier, String name) {
		this(accessModifier, false, false, name);
	}
	
	public ClassBuilder(Modifier accessModifier, Modifier extraModifier, String name) {
		this(
			accessModifier,
			extraModifier == Modifier.STATIC,
			extraModifier == Modifier.FINAL,
			name
		);
	}
	
	public ClassBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, String name) {
		this(
			accessModifier, 
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier1 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			name
		);
		
	}
	
	public ClassBuilder extend(String baseClassName) {
		this.baseClassName = baseClassName;
		
		return this;
	}
	
	public ClassBuilder extend(Class<?> baseClass) {
		this.baseClassName = baseClass.getSimpleName();
		
		return this;
	}
	
	public ClassBuilder implement(String ... interfaceNames) {
		Collections.addAll(this.interfaceNames, interfaceNames);
		return this;
	}
	
	public ClassBuilder implement(Class<?> ... interfaces) {
		for (Class<?> interfaze : interfaces) {
			this.interfaceNames.add(interfaze.getSimpleName());
		}
		
		return this;
	}
	
	private ClassBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, String name) {
		fields = new LinkedList<>();
		constructors = new LinkedList<>();
		methods = new LinkedList<>();
		nestedClasses = new LinkedList<>();
		
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.name = name;
		
		hasBasicConstructor = false;
		
		interfaceNames = new ArrayList<>();
		baseClassName = null;
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
		nestedClasses.add(classBuilder);
	}
	
	public void prependField(FieldBuilder fieldBuilder) {
		fields.addFirst(fieldBuilder);
	}
	
	public void prependConstructor(ConstructorBuilder constructorBuilder) {
		constructorBuilder.setClassName(name);
		constructors.addFirst(constructorBuilder);
	}
	
	public void prependMethod(MethodBuilder methodBuilder) {
		methods.addFirst(methodBuilder);
	}
	
	public void prependInnerClass(ClassBuilder classBuilder) {
		nestedClasses.addFirst(classBuilder);
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
		
		builder.append("class ");
		
		builder.append(name);
		builder.space();

		if (baseClassName != null) {
			builder.append("extends ");
			builder.append(baseClassName);
			builder.append(" ");
		}
		
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
				String fieldName = fieldBuilder.getName();
				basicConstructor.addParameter(fieldBuilder);
				basicConstructor.addStatement("this." + fieldName + " = " + fieldName + ';');
			}
			
			basicConstructor.setClassName(name);
			builder.append(basicConstructor);
			if (constructors.size() > 0) builder.newLine();
		}
		
		int i = 0;
		for (ConstructorBuilder constructor : constructors) {
			builder.append(constructor);
			if (i != constructors.size()-1) builder.newLine();
			i++;
		}
		addNewline = addNewline || constructors.size() > 0;

		// Build all methods
		if (addNewline && methods.size() > 0) {
			addNewline = false;
			builder.newLine();
		}
		i = 0;
		for (MethodBuilder method : methods) {
			builder.append(method);
			if (i != methods.size()-1) builder.newLine();
			i++;
		}
		addNewline = addNewline || methods.size() > 0;
		
		// Build all nested classes
		if (addNewline && nestedClasses.size() > 0) {
			addNewline = false;
			builder.newLine();
		}
		i = 0;
		for (ClassBuilder nestedClass : nestedClasses) {
			builder.append(nestedClass);
			if (i != nestedClasses.size()-1) builder.newLine();
			i++;
		}
		
		builder.outdent();
		builder.append("}");
		builder.newLine();
		
		return builder.toString();
	}
	
}
