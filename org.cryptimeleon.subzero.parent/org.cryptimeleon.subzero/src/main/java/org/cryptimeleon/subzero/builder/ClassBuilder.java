package org.cryptimeleon.subzero.builder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single class (possibly an inner class) in generated code
 */
public class ClassBuilder {
	
	private Deque<FieldBuilder> fields;
	private Deque<ConstructorBuilder> constructors;
	private Deque<MethodBuilder> methods;
	private Deque<ClassBuilder> innerClasses;
	
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
		fields = new LinkedList<FieldBuilder>();
		constructors = new LinkedList<ConstructorBuilder>();
		methods = new LinkedList<MethodBuilder>();
		innerClasses = new LinkedList<ClassBuilder>();
		
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.name = name;
		
		hasBasicConstructor = false;
		
		interfaceNames = new ArrayList<String>();
		baseClassName = null;
		
		for (Class<?> clazz : classes) {
			if (clazz.isInterface()) {
				interfaceNames.add(clazz.getSimpleName());
			} else if (baseClassName == null) {
				baseClassName = clazz.getSimpleName();
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
		innerClasses.addFirst(classBuilder);
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
		
		// Build all inner classes
		if (addNewline && innerClasses.size() > 0) {
			addNewline = false;
			builder.newLine();
		}
		i = 0;
		for (ClassBuilder innerClass : innerClasses) {
			builder.append(innerClass);
			if (i != innerClasses.size()-1) builder.newLine();
			i++;
		}
		
		builder.outdent();
		builder.append("}");
		builder.newLine();
		
		return builder.toString();
	}
	
}