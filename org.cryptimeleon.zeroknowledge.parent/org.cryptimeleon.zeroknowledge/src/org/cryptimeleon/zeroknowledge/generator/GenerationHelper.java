package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

public class GenerationHelper {
	
	private static String[] packages = {
			"org.cryptimeleon.craco.protocols",
			"org.cryptimeleon.craco.protocols.arguments.sigma.schnorr",
			"org.cryptimeleon.math.structures.groups",
	};

	public static String getClassName(Class<?> clazz) {
		return clazz.getSimpleName();
//		return clazz.getCanonicalName().substring(clazz.getPackageName().length()+1);
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
	
	public static List<String> getImports(String code) {
		List<String> imports = new ArrayList<String>();
		
		int startingIndex = 0;
		int index = 0;
		boolean inClassName = false;
		boolean isStartOfWord = true;
		
		while (index < code.length()) {
			
			char letter = code.charAt(index);
			
			if (inClassName && Character.isWhitespace(letter)) {
				inClassName = false;
				isStartOfWord = true;
				String className = code.substring(startingIndex, index);
				
				for (String packageName : packages) {
					try {
						String fullyQualifiedName = Class.forName(packageName + "." + className).getName();
						imports.add(fullyQualifiedName);
						break;
					} catch (ClassNotFoundException e) {
						continue;
					}
				}

			} else if (isStartOfWord && Character.isUpperCase(letter)) {
				startingIndex = index;
				inClassName = true;
			}
			
			isStartOfWord = Character.isWhitespace(letter);
			index++;
		}
		
		return imports;
	}
	
}
