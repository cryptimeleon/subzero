package de.upb.crypto.zeroknowledge.helpers;

import java.util.Set;
import java.util.HashSet

class VariableEnvironment {

	Set<String> variables;
	
	new() {
		this.variables = new HashSet<String>();
	}
	
	new(Set<String> variableSet) {
		this.variables = variableSet;
	}
	
	def boolean contains(String name) {
		return variables.contains(name);
	}
	
	def boolean add(String name) {
		this.variables.add(name);	
	}
	
}
