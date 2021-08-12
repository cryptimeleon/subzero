package org.cryptimeleon.subzero.model

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Method
import java.lang.annotation.Annotation
import java.util.Collections
import org.cryptimeleon.subzero.model.Type
import org.cryptimeleon.subzero.predefined.TupleParameters
import org.cryptimeleon.subzero.predefined.ReturnsTuple
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SchnorrFragment
import java.util.List

/**
 * Contains information about a function's name, return type, and parameters
 */
class FunctionSignature {

	String name;
	Type returnType;
	int returnSize;
	int parameterCount;
	List<String> parameterNames;
	List<Type> parameterTypes;
	List<Integer> parameterSizes;
	
	new(String name, Type returnType, int returnSize, List<String> parameterNames, List<Type> parameterTypes, int[] parameterSizes) {
		this.name = name;
		this.returnType = returnType;
		this.returnSize = returnSize;
		this.parameterCount = parameterNames.length;
		this.parameterNames = new ArrayList(parameterNames);
		this.parameterTypes = new ArrayList(parameterTypes);
		this.parameterSizes = new ArrayList(parameterSizes);
	}
	
	new(String name, Type returnType, List<String> parameterNames, List<Type> parameterTypes) {
		this(name, returnType, 1, parameterNames, parameterTypes, new ArrayList<Integer>(Collections.nCopies(parameterTypes.length, 1)));
	}
	
	new(Method method) {
		this.name = method.getName();
		
		val Class<?> returnType = method.getReturnType();
		if (returnType == void || returnType == SchnorrFragment) {
			this.returnType = Type.BOOLEAN;
		} else {
			this.returnType = Type.toType(method.getReturnType().getSimpleName());
		}
		
		
		val ReturnsTuple returnAnnotation = method.getAnnotation(ReturnsTuple);
		if (returnAnnotation !== null) {
			this.returnSize = returnAnnotation.value();
		} else {
			this.returnSize = 1;
		}
		
		val TupleParameters parametersAnnotation = method.getAnnotation(TupleParameters);
		
		if (parametersAnnotation !== null) {
			this.parameterSizes = new ArrayList(parametersAnnotation.value());
		} else {
			this.parameterSizes = new ArrayList<Integer>(Collections.nCopies(method.getParameterCount(), 1));
		}
		
		this.parameterCount = method.getParameterTypes.size();
		this.parameterNames = new ArrayList<String>();
		this.parameterTypes = new ArrayList<Type>;
		
		for (var i = 0; i < this.parameterCount; i++) {
			this.parameterNames.add("arg" + (i+1));
		}

		for (Class<?> classObject : method.getParameterTypes()) {
			this.parameterTypes.add(Type.toType(classObject.getSimpleName()));			
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
	
	def List<String> getParameterNames() {
		return parameterNames;
	}
	
	def List<Type> getParameterTypes() {
		return parameterTypes;
	}
	
	def List<Integer> getParameterSizes() {
		return parameterSizes;
	}
		
}
