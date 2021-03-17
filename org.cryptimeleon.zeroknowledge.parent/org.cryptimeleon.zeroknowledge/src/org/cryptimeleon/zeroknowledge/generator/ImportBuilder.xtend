package org.cryptimeleon.zeroknowledge.generator

import java.util.HashSet

/**
 * A class to collect import statements required for
 * the generated code
 */
class ImportBuilder {
	StringBuilder importBuilder;
	HashSet<String> importedClasses;
	
	new() {
		importBuilder = new StringBuilder();
	}
	
	def addImport(String importStatement) {
		importBuilder.append(importStatement);
	}
	
	def StringBuilder getBuilder() {
		return importBuilder;
	}
	
	def String getCode() {
		return importBuilder.toString();
	}
}