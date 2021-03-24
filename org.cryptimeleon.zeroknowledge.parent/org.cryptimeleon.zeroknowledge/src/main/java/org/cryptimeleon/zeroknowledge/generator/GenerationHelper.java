package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * A general helper class to assist with code generation
 */
public class GenerationHelper {

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
	
	
	
}
