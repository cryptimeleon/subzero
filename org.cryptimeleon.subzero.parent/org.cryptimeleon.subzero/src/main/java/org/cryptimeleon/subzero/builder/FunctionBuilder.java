package org.cryptimeleon.subzero.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single class function (method or constructor) in generated code
 */
public abstract class FunctionBuilder {

	private final String returnTypeName;
	private final List<String> parameterNames;
	private final List<String> parameterTypeNames;
	private final List<String> statements;
	
	private final boolean isConstructor;
	private final Modifier accessModifier;
	private final boolean isStatic;
	private final boolean isFinal;

	private boolean isTest;
	private boolean isOverride;
	
	protected String methodName;

	protected FunctionBuilder() {
		this(Modifier.PACKAGE, false, false, true, null, null);
	}
	
	protected FunctionBuilder(Modifier accessModifier) {
		this(accessModifier, false, false, true, null, null);
	}
	
	protected FunctionBuilder(Class<?> returnType, String methodName) {
		this(returnType.getSimpleName(), methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Class<?> returnType, String methodName) {
		this(accessModifier, returnType.getSimpleName(), methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier, Class<?> returnType, String methodName) {
		this(accessModifier, extraModifier, returnType.getSimpleName(), methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, Class<?> returnType, String methodName) {
		this(accessModifier, extraModifier1, extraModifier2, returnType.getSimpleName(), methodName);
	}
	
	protected FunctionBuilder(String returnTypeName, String methodName) {
		this(Modifier.PACKAGE, false, false, false, returnTypeName, methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, String returnTypeName, String methodName) {
		this(accessModifier, false, false, false, returnTypeName, methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier, String returnTypeName, String methodName) {
		this(
			accessModifier,
			extraModifier == Modifier.STATIC,
			extraModifier == Modifier.FINAL,
			false,
			returnTypeName,
			methodName
		);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, String returnTypeName, String methodName) {
		this(
			accessModifier,
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier1 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			false,
			returnTypeName,
			methodName
		);
	}
	
	
	private FunctionBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, boolean isConstructor, String returnTypeName, String methodName) {
		if (!accessModifier.isAccessModifier()) {
			throw new IllegalArgumentException("Invalid acccess modifier");
		}
		
		statements = new ArrayList<>();
		parameterNames = new ArrayList<>();
		parameterTypeNames = new ArrayList<>();

		this.isConstructor = isConstructor;
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.returnTypeName = returnTypeName;
		this.methodName = methodName;
		this.isTest = false;
		this.isOverride = false;
	}
	
	public void addParameter(String parameterClassName, String parameterName) {
		parameterTypeNames.add(parameterClassName);
		parameterNames.add(parameterName);
	}
	
	public void addParameter(Class<?> parameterClass, String parameterName) {
		addParameter(parameterClass.getSimpleName(), parameterName);
	}
	
	public void addParameter(FieldBuilder field) {
		addParameter(field.getTypeName(), field.getName());
	}
	
	public void addStatement(String statement) {
		statements.add(statement);
	}
	
	public void addBody(String body) {
		Collections.addAll(statements, body.split("\n"));
	}
	
	public void addStatementNewline() {
		statements.add("");
	}
	
	public void setTest() {
		isTest = true;
	}
	
	public void setOverride() {
		isOverride = true;
	}
	
	@Override
	public String toString() {
		return this.toString(0);
	}

	public String toString(int indentLevel) {
		CodeBuilder builder = new CodeBuilder(indentLevel);

		if (isTest) {
			builder.append("@Test");
			builder.newLine();
		}
		
		if (isOverride) {
			builder.append("@Override");
			builder.newLine();
		}
		
		String modifier = accessModifier.toString();
		builder.append(modifier);
		if (!modifier.isEmpty()) builder.space();
		
		if (isStatic) builder.append("static ");
		if (isFinal) builder.append("final ");
		
		if (returnTypeName != null) {
			builder.append(returnTypeName);
			builder.space();
		}
		
		builder.append(methodName);
		builder.append("(");
		
		for (int i = 0; i < parameterNames.size(); i++) {
			builder.append(parameterTypeNames.get(i));
			builder.space();
			builder.append(parameterNames.get(i));
			builder.append(i == parameterNames.size()-1 ? "" : ", ");
		}
		
		builder.append(") {");
		builder.newLine();
		builder.indent();
		
		for (String statement : statements) {
			builder.append(statement);
			builder.newLine();
		}
		
		builder.outdent();
		builder.append("}");
		builder.newLine();
		
		return builder.toString();
	}
	
	
	
	
	
}
