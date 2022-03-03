package org.cryptimeleon.subzero.predefined;

import org.cryptimeleon.subzero.builder.CodeBuilder;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.model.Type;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PredefinedFunction {
	public abstract String getName();
	public abstract Type getReturnType();
	public abstract List<FunctionParameter> getParameters();
	public abstract String generateFunction();
	
	public int getReturnSize() {
		return 1;
	}
	
	public final String generateFunctionSignature() {
		CodeBuilder builder = new CodeBuilder();
		
		builder.append("private");
		builder.space();
		builder.append(getReturnType());
		builder.space();
		builder.append(getName());
		builder.openParen();
		builder.append(GenerationUtils.createCommaList(getParameters().stream().map(parameter -> parameter.getName()).collect(Collectors.toList())));
		builder.closeParen();

		return builder.toString();
	}

	public final String generateFunctionFromBody(String body) {
		CodeBuilder builder = new CodeBuilder();
		builder.append(generateFunctionSignature());
		builder.openBrace();
		builder.newLine();
		builder.indent();
		for (String statement : body.split("\n")) {
			builder.append(statement);
		}
		builder.outdent();
		builder.closeBrace();
		return builder.toString();
	}
	
	public final String generateFunctionCall(List<String> argumentNames) {
		int size = argumentNames.size();
		if (size != getParameters().size()) throw new IllegalArgumentException();
		
		CodeBuilder builder = new CodeBuilder();
		
		builder.append(getName());
		builder.openParen();
		builder.append(GenerationUtils.createCommaList(argumentNames));
		builder.closeParen();
		
		return builder.toString();
	}
}
