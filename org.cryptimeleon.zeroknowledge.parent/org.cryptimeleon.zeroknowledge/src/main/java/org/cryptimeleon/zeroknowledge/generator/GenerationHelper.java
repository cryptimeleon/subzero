package org.cryptimeleon.zeroknowledge.generator;

import java.util.List;

/**
 * A general helper class to assist with code generation
 */
public class GenerationHelper {

	// Converts variable names and witness names to Java variable names
	public static String convertToJavaName(String name) {
		return name.replace("'", "_prime");
	}
	
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
