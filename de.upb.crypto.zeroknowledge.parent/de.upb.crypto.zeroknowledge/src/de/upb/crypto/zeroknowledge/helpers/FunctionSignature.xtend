package de.upb.crypto.zeroknowledge.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import java.lang.reflect.Method

// Contains information about a function's name, return type, and parameters
class FunctionSignature {

	String name;
	Type returnType;
	ArrayList<Type> parameterTypes;
	int parameterCount;
	
	new(String name, String returnType, int parameterCount, String[] parameterTypes) {
		this.name = name;
		this.returnType = Type.toType(returnType);
		this.parameterCount = parameterCount;
		this.parameterTypes = new ArrayList();
		for (String parameterType : parameterTypes) {
			this.parameterTypes.add(Type.toType(parameterType));
		}
	}
	
	new(String name, Type returnType, int parameterCount, Type[] parameterTypes) {
		this.name = name;
		this.returnType = returnType;
		this.parameterCount = parameterCount;
		this.parameterTypes = new ArrayList(parameterTypes);		
	}
	
	new(Method method) {
		this.name = method.getName();
		this.returnType = Type.toType(trimTypeName(method.getReturnType().getName()));
		this.parameterCount = method.getParameterTypes.size();
		this.parameterTypes = new ArrayList<Type>;

		for (Class<?> classObject : method.getParameterTypes()) {
			this.parameterTypes.add(Type.toType(trimTypeName(classObject.getName())));			
		}
	}
	
	def String getName() {
		return name;
	}
	
	def int getParameterCount() {
		return parameterCount;
	}
	
	def Type getReturnType() {
		return returnType;
	}
	
	def ArrayList<Type> getParameterTypes() {
		return parameterTypes;
	}
	
	// Returns a map of all function names to their corresponding function signature
	def static ArrayList<String> getAllNames(Map<String, FunctionSignature> map) {
		val ArrayList<String> names = new ArrayList<String>();
		for (Map.Entry<String, FunctionSignature> entry : map.entrySet()) {
			names.add(entry.getValue().getName());
		}
		return names;
	}
		
	// Converts internal class name to simpler form
	// Possibly remove and simply use fully qualified name?
	def private static String trimTypeName(String type) {
	  val int periodIndex = type.lastIndexOf('.');
	  if (periodIndex > 0) {
	    return type.substring(periodIndex + 1);
	  }
	  return type;
	}
	
}
