package org.cryptimeleon.subzero.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a single source file in generated code (consisting of a class, its package
 * declaration, and its required imports)
 */
public class SourceBuilder {
	
	private String packageName;
	private ClassBuilder classBuilder;
	private ImportBuilder importBuilder;
	
	public SourceBuilder(String packageName, ClassBuilder classBuilder) {
		this(packageName, classBuilder, new ImportBuilder());
	}
	
	public SourceBuilder(String packageName, ClassBuilder classBuilder, ImportBuilder importBuilder) {
		this.packageName = packageName;
		this.classBuilder = classBuilder;
		this.importBuilder = importBuilder;
	}
	
	@Override
	public String toString() {
		CodeBuilder builder = new CodeBuilder();
		
		builder.append("package ");
		builder.append(packageName);
		builder.append(";");
		builder.newLine();
		builder.newLine();
		
		builder.append(importBuilder);
		builder.newLine();
		
		builder.append(classBuilder);
		
		getImports(classBuilder.toString());
		
		return builder.toString();
	}
	
	private static String[] packages = {
			"org.cryptimeleon.craco.protocols",
			"org.cryptimeleon.craco.protocols.arguments.sigma.schnorr",
			"org.cryptimeleon.math.structures.groups",
	};
	
	private static List<String> getImports(String code) {
		List<String> imports = new ArrayList<String>();
		Set<String> classNames = new HashSet<String>();
		
		int startingIndex = 0;
		int index = 0;
		boolean inClassName = false;
		boolean isStartOfWord = true;
		
		while (index < code.length()) {
			
			char letter = code.charAt(index);
			
			if (inClassName && !Character.isJavaIdentifierPart(letter)) {
				inClassName = false;
				isStartOfWord = true;
				String className = code.substring(startingIndex, index);
				classNames.add(className);

			} else if (isStartOfWord && Character.isUpperCase(letter)) {
				startingIndex = index;
				inClassName = true;
			}
			
			isStartOfWord = Character.isWhitespace(letter);
			index++;
		}
		
		for (String className : classNames) {
			for (String packageName : packages) {
				try {
					String fullyQualifiedName = Class.forName(packageName + "." + className).getName();
					imports.add(fullyQualifiedName);
					break;
				} catch (ClassNotFoundException e) {
					continue;
				}
			}
		}
		
		return imports;
	}
	
}
