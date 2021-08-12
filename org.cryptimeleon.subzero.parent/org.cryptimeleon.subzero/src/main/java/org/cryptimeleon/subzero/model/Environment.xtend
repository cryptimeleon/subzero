package org.cryptimeleon.subzero.model

import java.util.List
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
import java.util.Map.Entry
import java.util.Map

class Environment {
	
	val String representation;	
	
	new(AugmentedModel augmentedModel) {
		val StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		val Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		val Map<String, FunctionSignature> userFunctions = augmentedModel.getUserFunctionSignatures();
		
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
		
		buildFunctions(builder, predefinedFunctions, "predefined");
		buildFunctions(builder, userFunctions , "user");
		
		buildVariables(builder, publicParameterNames, publicParameterTypes, variableGroups, "public parameter");
		buildVariables(builder, witnessNames, witnessTypes, variableGroups, "witness");
		buildVariables(builder, commonInputNames, commonInputTypes, variableGroups, "common input");
		
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
		String functionOrigin
	) {
		for (Entry<String, FunctionSignature> entry : signatures.entrySet()) {
			val String functionName = entry.getKey();
			val FunctionSignature function = entry.getValue();
			
			builder.append("{");
			builder.append('''"construct":"function",''');
			builder.append('''"name":"«functionName»",''');
			builder.append('''"returnType":"«function.getReturnType()»",''');
			builder.append('''"parameterNames":[«FOR String parameterName : function.getParameterNames() SEPARATOR ','»"«parameterName»"«ENDFOR»],''');
			builder.append('''"parameterTypes":[«FOR Type parameterType : function.getParameterTypes() SEPARATOR ','»"«parameterType»"«ENDFOR»],''');
			builder.append('''"origin":"«functionOrigin»"''');
			builder.append("},");
		}
	}
	
	def void buildVariables(
		StringBuilder builder,
		List<String> variableNames,
		Map<String, Type> variableTypes,
		Map<String, GroupType> variableGroups,
		String variableRole
	) {
		for (String variableName : variableNames) {
			val Type variableType = variableTypes.get(variableName);
			builder.append("{");
			builder.append('''"construct": "variable",''');
			builder.append('''"name": "«variableName»",''');
			if (variableGroups !== null && variableGroups.containsKey(variableName)) {
				val GroupType variableGroup = variableGroups.get(variableName);
				builder.append('''"group": "«variableGroup»",''');
			}
			builder.append('''"type": "«variableType»",''');
			builder.append('''"role": "«variableRole»"''');
			
			builder.append("},");
		}
	}
	
	
	override String toString() {
		return representation;
	}
	
}