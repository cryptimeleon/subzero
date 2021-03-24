package org.cryptimeleon.zeroknowledge.generator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single class function (method or constructor) in generated code
 */
public abstract class FunctionBuilder {
	// TODO: refactor ConstructorBuilder and MethodBuilder to derive from this class
	
	private List<String> statements;
	
	private boolean isConstructor;
	private Modifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private String returnTypeName;
	
	private boolean isTest;
	private boolean isOverride;
	
	protected String methodName;
	
	private List<String> parameterNames;
	private List<String> parameterTypeNames;
	private List<String> annotations;
	
	protected FunctionBuilder() {
		this(Modifier.PACKAGE, false, false, true, null, null);
	}
	
	protected FunctionBuilder(Modifier accessModifier) {
		this(accessModifier, false, false, true, null, null);
	}
	
	protected FunctionBuilder(Class<?> returnType, String methodName) {
		this(Modifier.PACKAGE, false, false, false, returnType, methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Class<?> returnType, String methodName) {
		this(accessModifier, false, false, false, returnType, methodName);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier, Class<?> returnType, String methodName) {
		this(
			accessModifier,
			extraModifier == Modifier.STATIC,
			extraModifier == Modifier.FINAL,
			false,
			returnType,
			methodName
		);
	}
	
	protected FunctionBuilder(Modifier accessModifier, Modifier extraModifier1, Modifier extraModifier2, Class<?> returnType, String methodName) {
		this(
			accessModifier,
			extraModifier1 == Modifier.STATIC || extraModifier2 == Modifier.STATIC,
			extraModifier1 == Modifier.FINAL || extraModifier2 == Modifier.FINAL,
			false,
			returnType,
			methodName
		);
	}
	
	private FunctionBuilder(Modifier accessModifier, boolean isStatic, boolean isFinal, boolean isConstructor, Class<?> returnType, String methodName) {
		if (!accessModifier.isAccessModifier()) {
			throw new IllegalArgumentException("Invalid acccess modifier");
		}
		
		statements = new ArrayList<String>();
		parameterNames = new ArrayList<String>();
		parameterTypeNames = new ArrayList<String>();
		annotations = new ArrayList<String>();
		
		this.isConstructor = isConstructor;
		this.accessModifier = accessModifier;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.returnTypeName = isConstructor ? null : GenerationHelper.getClassName(returnType);
		this.methodName = methodName;
		this.isTest = false;
		this.isOverride = false;
	}
	
	public void addParameter(Class<?> parameterClass, String parameterName) {
		parameterTypeNames.add(GenerationHelper.getClassName(parameterClass));
		parameterNames.add(parameterName);
	}
	
	public void addStatement(String statement) {
		statements.add(statement);
	}
	
	public void addBody(String body) {
		for (String statement : body.split("\n")) {
			statements.add(statement);
		}
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
		
		for (String annotation : annotations) {
			builder.append('@' + annotation);
			builder.newLine();
		}
		
		if (isTest) {
			builder.append("@Test");
		}
		
		if (isOverride) {
			builder.append("@Override");
			builder.newLine();
		}
		
		builder.append(accessModifier.toString());
		builder.space();
		
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
