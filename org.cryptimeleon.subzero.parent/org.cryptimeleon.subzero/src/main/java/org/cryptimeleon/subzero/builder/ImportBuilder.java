package org.cryptimeleon.subzero.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents all import statements for a source code file
 */
public class ImportBuilder {

	private final Set<String> priorityClasses;
	private final Set<String> fullyQualifiedPriorityClasses;
	private final Set<String> importedClasses;
	
	public ImportBuilder() {
		priorityClasses = new HashSet<>();
		fullyQualifiedPriorityClasses = new HashSet<>();
		importedClasses = new HashSet<>();
	}
	
	// Prioritizes a class so that if multiple classes with the same
	// name are imported, the prioritized class will be referenced by name
	// and the other class will be referenced by enclosing class name and name
	// The prioritized class, however, must still be imported by calling the 'use' method
	public void prioritize(Class<?> priorityClass) {
		priorityClasses.add(priorityClass.getSimpleName());
		fullyQualifiedPriorityClasses.add(priorityClass.getName());
	}
	
	// Adds a class to the list of import statements
	public String use(Class<?> importClass) {
		String className = importClass.getSimpleName();
		String fullyQualifiedName = importClass.getName();
		
		if (priorityClasses.contains(className) && !fullyQualifiedPriorityClasses.contains(fullyQualifiedName)) {
			// Use getEnclosingClass instead of getName, to import the enclosing class instead for nested classes
			Class<?> enclosingClass = importClass.getEnclosingClass();
			importedClasses.add(enclosingClass.getCanonicalName());
			
			// Use getSimpleName for enclosing class, to prefix the nested class name with the name of its enclosing class
			className = enclosingClass.getSimpleName() + '.' + className;
		} else {
			// Use getCanonicalName instead of getName to avoid dollar signs in name for nested classes
			importedClasses.add(importClass.getCanonicalName());
		}
		
		return className;
	}
	
	// Adds an import statement manually, using the fully qualified name of a class
	public void use(String fullyQualifiedClassName) {
		importedClasses.add(fullyQualifiedClassName);
	}
	
	// Adds a static method to the list of import statements
	public void useStatic(String importMember) {
		importedClasses.add("static " + importMember);
	}
	
	// Combine import
	public void merge(ImportBuilder importBuilder) {
		importedClasses.addAll(importBuilder.importedClasses);
	}
	
	@Override
	// Generates all import statements, in sorted order of full class name
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<String> imports = new ArrayList<>(importedClasses);
		Collections.sort(imports);
		
		for (String importClass : imports) {
			builder.append("import ").append(importClass.replace('$', '.')).append(";\n");
		}
		
		return builder.toString();
	}
	
}
