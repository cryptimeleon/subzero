package org.cryptimeleon.zeroknowledge.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;

/**
 * A general helper class to assist with code generation
 */
public class GenerationHelper {
	
	public final static String DEFAULT_PROTOCOL_NAME = "MySigmaProtocol";
	public final static String DEFAULT_PACKAGE_NAME = "prototype";
	public final static String INPUT_VARIABLE = "input";
//	public final static String WITNESS_SUFFIX = "Var";
	public final static String WITNESS_SUFFIX = "";
	public final static String SUBPROTOCOL_VARIABLE = "name";
	
	public static String createLocalName(String variableName) {
		return "#" + variableName + "#";
	}
	
	public static String createCommonInputClassName(String protocolName) {
		return protocolName + CommonInput.class.getSimpleName();
	}
	
	public static String createSecretInputClassName(String protocolName) {
		return protocolName + SecretInput.class.getSimpleName();
	}
	
	public static String createPublicParametersClassName(String protocolName) {
		return protocolName + "PublicParameters";
	}

	// Converts variable names and witness names to Java variable names
	public static String convertToJavaName(String name) {
		return name.replace("'", "_prime");
	}
	
	// Converts a class into a camelCase variable name
	public static String convertClassToVariableName(Class<?> clazz) {
		String className = clazz.getSimpleName();
		return Character.toLowerCase(className.charAt(0)) + className.substring(1);
	}
	
	// Converts a string (possibly containing spaces) into a PascalCase class name
	public static String convertToClassName(String name) {
		StringBuilder builder = new StringBuilder();
		boolean lastCharWasWhitespace = true;
		
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			
			if (lastCharWasWhitespace) {
				builder.append(Character.toUpperCase(c));
				lastCharWasWhitespace = false;
			} else if (c == ' ' ) {
				lastCharWasWhitespace = true;
			} else {
				builder.append(c);
			}
		}
		
		return builder.toString();
	}
	
	// Creates a comma-delimited list (with spaces) from a list of strings
	public static String createCommaList(List<String> argumentNames) {
		StringBuilder builder = new StringBuilder();
		int size = argumentNames.size();
		
		for (int i = 0; i < size; i++) {
			builder.append(argumentNames.get(i));
			if (i != size-1) builder.append(", ");
		}
		
		return builder.toString();
	}
	
	//
	public static String organizeImports(String imports) {
		String[] importList = imports.split("\n");
		Arrays.sort(importList);
		return String.join("\n", importList) + '\n';
	}
	
}
