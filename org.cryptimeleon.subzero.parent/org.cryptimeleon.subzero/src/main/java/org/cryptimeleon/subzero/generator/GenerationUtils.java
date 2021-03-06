package org.cryptimeleon.subzero.generator;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.subzero.model.VariableIdentifier;

import java.util.List;

/**
 * A general helper class to assist with code generation
 */
public class GenerationUtils {
	
	public final static String DEFAULT_PROTOCOL_NAME = "MySigmaProtocol";
	public final static String DEFAULT_PACKAGE_NAME = "prototype";
	public final static String INPUT_VARIABLE = "input";
	public final static String SUBPROTOCOL_VARIABLE = "name";
	
	public static String createWitnessName(String witnessName) {
		return convertToJavaName(witnessName);
	}
	
	public static String createConstantName(String constantName) {
		return INPUT_VARIABLE + '.' + convertToJavaName(constantName);
	}
	
	public static String createPPName(String ppName) {
		return convertToJavaName(ppName);
	}
	
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
	public static String convertToJavaName(String identifier) {
		VariableIdentifier varIdentifier = new VariableIdentifier(identifier);
		String name = varIdentifier.getName();
		
		if (varIdentifier.hasTilde()) {
			name += "Tilde";
		} else if (varIdentifier.hasBar()) {
			name += "Bar";
		} else if (varIdentifier.hasHat()) {
			name += "Hat";
		}
		
		if (varIdentifier.hasSubscript()) {
			name += "_" + varIdentifier.getSubscript();
		}
		
		int primes = varIdentifier.getPrimes();
		for (int i = 0; i < primes; i++) {
			name += "Prime";
		}
		
		return name;
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
	
}
