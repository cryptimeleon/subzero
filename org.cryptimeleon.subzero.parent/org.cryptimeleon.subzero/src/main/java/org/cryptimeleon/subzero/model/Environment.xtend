package org.cryptimeleon.subzero.model

import java.util.List
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
import java.util.Map.Entry
import java.util.Map

class Environment {
	
	val String representation;	
	
	new(AugmentedModel augmentedModel) {
		val StringBuilder builder = new StringBuilder();
		builder.append("{");
		
		builder.append('"predefinedFunctions":[');
		buildFunctions(builder, PredefinedFunctionsHelper.getAllPredefinedFunctions());
		builder.append("],");
		
		builder.append('"userFunctions":[');
		buildFunctions(builder, augmentedModel.getUserFunctionSignatures());
		builder.append("],");
		
		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		
		val List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		
		val List<String> commonInputNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> commonInputTypes = augmentedModel.getConstantVariableTypes();
		
		val Map<String, GroupType> variableGroups = augmentedModel.getGroupsByName();

		builder.append('"publicParameterVariables":[');
		buildVariables(builder, publicParameterNames, publicParameterTypes, variableGroups);
		builder.append("],");
		
		builder.append('"witnessVariables":[');
		buildVariables(builder, witnessNames, witnessTypes, variableGroups);
		builder.append("],");
		
		builder.append('"commonInputVariables":[');
		buildVariables(builder, commonInputNames, commonInputTypes, variableGroups);
		builder.append("]");
		builder.append("}");
		representation = builder.toString();
	}
	
	
	def void buildFunctions(StringBuilder builder, Map<String, FunctionSignature> signatures) {
		var String delimiter = '';
		for (Entry<String, FunctionSignature> entry : signatures.entrySet()) {
			val String functionName = entry.getKey();
			val FunctionSignature function = entry.getValue();
			
			builder.append(delimiter);
			builder.append("{");
			builder.append('''"name": "«functionName»",''');
			builder.append('''"returnType": "«function.getReturnType()»",''');
			builder.append('''"parameterTypes": [«FOR Type parameterType : function.getParameterTypes() SEPARATOR ', '»"«parameterType»"«ENDFOR»]''');
			builder.append("}");
			delimiter = ", ";
		}
	}
	
	def void buildVariables(StringBuilder builder, List<String> variableNames, Map<String, Type> variableTypes, Map<String, GroupType> variableGroups) {
		var String delimiter = '';
		for (String variableName : variableNames) {
			val Type variableType = variableTypes.get(variableName);
			builder.append(delimiter);
			builder.append("{");
			builder.append('''"name": "«variableName»",''');
			if (variableGroups.containsKey(variableName)) {
				val GroupType variableGroup = variableGroups.get(variableName);
				builder.append('''"group": "«variableGroup»",''');
			}
			builder.append('''"type": "«variableType»"''');
			
			builder.append("}");
			delimiter = ", ";
		}
	}
	
	
	override String toString() {
		return representation;
	}
	
}