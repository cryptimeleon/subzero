package de.upb.crypto.zeroknowledge.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;


class FunctionSignature {

	String name;
	String type;
	int parameter_count;
	
	new(String name, String type, int parameter_count) {
		this.name = name;
		this.type = type;
		this.parameter_count = parameter_count;
	}
	
	def String getName() {
		return name;
	}
	
	def int getParameterCount() {
		return parameter_count;
	}
	
	def String getType() {
		return type;
	}
	
	// Returns a map of all function names to their corresponding function signature
	def static List<String> getAllNames(Map<String, FunctionSignature> map) {
		val List<String> names = new ArrayList<String>();
		for (Map.Entry<String, FunctionSignature> entry : map.entrySet()) {
			names.add(entry.getValue().getName());
		}
		return names;
	}
	
}
