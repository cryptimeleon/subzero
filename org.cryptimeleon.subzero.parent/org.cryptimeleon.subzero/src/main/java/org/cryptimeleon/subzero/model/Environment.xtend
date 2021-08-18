package org.cryptimeleon.subzero.model

import java.util.List
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
import java.util.Map.Entry
import java.util.Map
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.Variable
import java.util.ArrayList
import org.cryptimeleon.subzero.subzero.LocalVariable

class Environment {
	
	val String representation;	
	
	new(AugmentedModel augmentedModel) {
		val StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		val Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		val Map<String, FunctionSignature> userFunctions = augmentedModel.getUserFunctionSignatures();
		
		val Map<String, List<FunctionCall>> userFunctionCalls = augmentedModel.getUserFunctionCallNodes();
		val Map<String, List<Variable>> variableNodes = augmentedModel.getVariableNodes();
		val Map<String, Map<String, List<LocalVariable>>> localVariableNodes = augmentedModel.getLocalVariableNodes();
		
		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		
		val List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		
		val List<String> commonInputNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> commonInputTypes = augmentedModel.getConstantVariableTypes();
		
		var Map<String, GroupType> variableGroups = null;
		if (augmentedModel.hasPairing()) {
			variableGroups = augmentedModel.getGroupsByName();
		}

		buildFunctions(builder, predefinedFunctions, userFunctionCalls, localVariableNodes, "predefined");
		buildFunctions(builder, userFunctions, userFunctionCalls, localVariableNodes, "user");
		buildVariables(builder, publicParameterNames, publicParameterTypes, variableGroups, variableNodes, "public parameter");
		buildVariables(builder, witnessNames, witnessTypes, variableGroups, variableNodes, "witness");
		buildVariables(builder, commonInputNames, commonInputTypes, variableGroups, variableNodes, "common input");

		// Trim the trailing comma
		if (builder.length() > 1) {
			builder.setLength(builder.length() - 1);
		}
		
		builder.append("]");
		representation = builder.toString();
	}
	
	
	def void buildFunctions(
		StringBuilder builder,
		Map<String, FunctionSignature> signatures,
		Map<String, List<FunctionCall>> userFunctionCalls,
		Map<String, Map<String, List<LocalVariable>>> localVariableNodes,
		String functionOrigin
	) {
		for (Entry<String, FunctionSignature> entry : signatures.entrySet()) {
			val String functionName = entry.getKey();
			val FunctionSignature function = entry.getValue();
			
			val Map<String, List<LocalVariable>> functionLocalVariables = localVariableNodes.get(functionName);
			val skipCheck = functionLocalVariables === null;
			
			var Type returnType;
			if (skipCheck || userFunctionCalls.containsKey(functionName)) {
				returnType = function.getReturnType();
			} else {
				returnType = Type.UNKNOWN;
			}
			
			val List<String> parameterNames = function.getParameterNames();
			val List<Type> parameterTypes = function.getParameterTypes();
			val List<String> formattedParameterTypes = new ArrayList<String>();
			
			for (var i = 0; i < parameterNames.size(); i++) {
				val String parameterName = parameterNames.get(i);
				val Type parameterType = parameterTypes.get(i);
				
				if (skipCheck || functionLocalVariables.get(parameterName).size() > 0) {
					formattedParameterTypes.add(parameterType.toString());
				} else {
					formattedParameterTypes.add(Type.UNKNOWN.toString());
				}
			}
			
			builder.append("{");
			builder.append('''"construct":"function",''');
			builder.append('''"name":"«functionName»",''');
			builder.append('''"returnType":"«returnType»",''');
			builder.append('''"parameterNames":[«FOR String parameterName : parameterNames SEPARATOR ','»"«parameterName»"«ENDFOR»],''');
			builder.append('''"parameterTypes":[«FOR String parameterType : formattedParameterTypes SEPARATOR ','»"«parameterType»"«ENDFOR»],''');
			builder.append('''"origin":"«functionOrigin»"''');
			builder.append("},");
		}
	}
	
	def void buildVariables(
		StringBuilder builder,
		List<String> variableNames,
		Map<String, Type> variableTypes,
		Map<String, GroupType> variableGroups,
		Map<String, List<Variable>> variableNodes,
		String variableRole
	) {
		for (String variableName : variableNames) {
			var Type variableType;
			if (variableNodes.containsKey(variableName)) {
				variableType = variableTypes.get(variableName);
			} else {
				variableType = Type.UNKNOWN;
			}
			
			builder.append("{");
			builder.append('''"construct":"variable",''');
			builder.append('''"name":"«variableName»",''');
			if (variableGroups !== null && variableType !== Type.UNKNOWN && variableGroups.containsKey(variableName)) {
				val GroupType variableGroup = variableGroups.get(variableName);
				builder.append('''"group":"«variableGroup»",''');
			}
			builder.append('''"type":"«variableType»",''');
			builder.append('''"role":"«variableRole»"''');
			builder.append("},");
		}
	}
	
	
	override String toString() {
		return representation;
	}
	
}