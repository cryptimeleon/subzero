package org.cryptimeleon.zeroknowledge.model

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Method
import java.lang.annotation.Annotation
import java.util.Collections
import org.cryptimeleon.zeroknowledge.model.Type
import org.cryptimeleon.zeroknowledge.predefined.TupleParameters
import org.cryptimeleon.zeroknowledge.predefined.ReturnsTuple

/**
 * Contains information about a function's name, return type, and parameters
 */
class FunctionSignature {

	String name;
	Type returnType;
	int returnSize;
	int parameterCount;
	ArrayList<Type> parameterTypes;
	ArrayList<Integer> parameterSizes;
	
	new(String name, String returnType, int returnSize, String[] parameterTypes, int[] parameterSizes) {
		this.name = name;
		this.returnType = Type.toType(returnType);
		this.returnSize = returnSize;
		this.parameterCount = parameterTypes.length;
		this.parameterTypes = new ArrayList();
		for (String parameterType : parameterTypes) {
			this.parameterTypes.add(Type.toType(parameterType));
		}
		this.parameterSizes = new ArrayList(parameterSizes);
	}
	
	new(String name, Type returnType, int returnSize, Type[] parameterTypes, int[] parameterSizes) {
		this.name = name;
		this.returnType = returnType;
		this.returnSize = returnSize;
		this.parameterCount = parameterTypes.length;
		this.parameterTypes = new ArrayList(parameterTypes);
		this.parameterSizes = new ArrayList(parameterSizes);
	}
	
	new(String name, String returnType, String[] parameterTypes) {
		this(name, returnType, 1, parameterTypes, new ArrayList<Integer>(Collections.nCopies(parameterTypes.length, 1)));
	}
	
	new(String name, Type returnType, Type[] parameterTypes) {
		this(name, returnType, 1, parameterTypes, new ArrayList<Integer>(Collections.nCopies(parameterTypes.length, 1)));
	}
	
	new(Method method) {
		this.name = method.getName();
		this.returnType = Type.toType(trimTypeName(method.getReturnType().getName()));
		val ReturnsTuple returnAnnotation = method.getAnnotation(ReturnsTuple);
		val TupleParameters parametersAnnotation = method.getAnnotation(TupleParameters);
		if (returnAnnotation !== null) {
			this.returnSize = returnAnnotation.value();
		} else {
			this.returnSize = 1;
		}
		if (parametersAnnotation !== null) {
			this.parameterSizes = new ArrayList(parametersAnnotation.value());
		} else {
			this.parameterSizes = new ArrayList<Integer>(Collections.nCopies(method.getParameterCount(), 1));
		}
		
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
	
	def int getReturnSize() {
		return returnSize;
	}
	
	def boolean returnsTuple() {
		return returnSize > 1;
	}
	
	def ArrayList<Type> getParameterTypes() {
		return parameterTypes;
	}
	
	def ArrayList<Integer> getParameterSizes() {
		return parameterSizes;
	}
		
	// Converts internal class name to simpler form (without package prefix)
	// Possibly remove and simply use fully qualified name?
	def private static String trimTypeName(String type) {
	  val int periodIndex = type.lastIndexOf('.');
	  if (periodIndex > 0) {
	    return type.substring(periodIndex + 1);
	  }
	  return type;
	}
	
}
