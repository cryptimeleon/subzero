package de.upb.crypto.zeroknowledge.generator

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
	
	def addImport() {
		importBuilder.append();
	}
	
	def StringBuilder getBuilder() {
		return importBuilder;
	}
	
	def String getCode() {
		return importBuilder.toString();
	}
}