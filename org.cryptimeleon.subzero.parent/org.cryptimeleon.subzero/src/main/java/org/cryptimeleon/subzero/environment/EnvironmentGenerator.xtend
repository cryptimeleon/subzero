package org.cryptimeleon.subzero.environment

import java.util.List
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
import java.util.Map.Entry
import java.util.Map
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.Variable
import java.util.ArrayList
import org.cryptimeleon.subzero.subzero.LocalVariable
import java.util.Set
import java.util.HashSet
import java.util.Collection
import org.cryptimeleon.subzero.builder.JsonBuilder
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.model.FunctionSignature
import org.cryptimeleon.subzero.model.Type
import org.cryptimeleon.subzero.model.GroupType
import org.cryptimeleon.subzero.generator.CodeGenerator

class EnvironmentGenerator implements CodeGenerator {
	
	var AugmentedModel augmentedModel;
	var JsonBuilder builder;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
	}
	
	override String generate() {
		builder = new JsonBuilder(true);
		buildEnvironment();
		return builder.toString();
	}
	
	def private buildEnvironment() {
		val Map<String, List<FunctionCall>> functionCalls = augmentedModel.getUserFunctionCallNodes();
		val Map<String, Map<String, List<LocalVariable>>> localVariables = augmentedModel.getLocalVariableNodes();
		buildPredefinedFunctions(functionCalls, localVariables);
		buildUserFunctions(functionCalls, localVariables);
		
		
		val Map<String, List<Variable>> variables = augmentedModel.getVariableNodes();
		var Map<String, GroupType> variableGroups = null;
		if (augmentedModel.hasPairing()) {
			variableGroups = augmentedModel.getGroups();
		}
		
		buildWitnessVariables(variables, variableGroups);
		buildPublicParameterVariables(variables, variableGroups);
		buildConstantVariables(variables, variableGroups);
	}
	
	def private buildPredefinedFunctions(
		Map<String, List<FunctionCall>> functionCalls,
		Map<String, Map<String, List<LocalVariable>>> localVariables
	) {
		val Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		buildFunctions(predefinedFunctions, functionCalls, localVariables, "built-in");
	}
	
	def private buildUserFunctions(
		Map<String, List<FunctionCall>> functionCalls,
		Map<String, Map<String, List<LocalVariable>>> localVariables
	) {
		val Map<String, FunctionSignature> userFunctions = augmentedModel.getUserFunctionSignatures();
		buildFunctions(userFunctions, functionCalls, localVariables, "user");
	}
	
	def private buildWitnessVariables(
		Map<String, List<Variable>> variables,
		Map<String, GroupType> variableGroups
	) {
		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		buildVariables(witnessNames, witnessTypes, variableGroups, variables, "witness");
	}
	
	def private buildPublicParameterVariables(
		Map<String, List<Variable>> variables,
		Map<String, GroupType> variableGroups
	) {
		val List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		buildVariables(publicParameterNames, publicParameterTypes, variableGroups, variables, "public parameter");
	}
	
	def private buildConstantVariables(
		Map<String, List<Variable>> variables,
		Map<String, GroupType> variableGroups
	) {
		val List<String> constantNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> constantTypes = augmentedModel.getConstantVariableTypes();
		
		if (augmentedModel.hasExplicitConstants()) {
			val Set<String> declaredConstantNames = augmentedModel.getDeclaredConstantNames();
			val Set<String> undeclaredConstantNames = new HashSet(constantNames);
			undeclaredConstantNames.removeAll(declaredConstantNames);
			
			buildVariables(declaredConstantNames, constantTypes, variableGroups, variables, "common input");
			buildVariables(undeclaredConstantNames, constantTypes, variableGroups, variables, "unknown");
		} else {
			buildVariables(constantNames, constantTypes, variableGroups, variables, "common input");
		}
	}
	
	def private void buildFunctions(
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
			
			val JsonBuilder objectBuilder = new JsonBuilder();
			
			objectBuilder.addStringProperty("construct", "function");
			objectBuilder.addStringProperty("name", functionName);
			objectBuilder.addStringProperty("returnType", returnType.toString());
			objectBuilder.addArrayProperty("parameterNames", parameterNames);
			objectBuilder.addArrayProperty("parameterTypes", formattedParameterTypes);
			objectBuilder.addStringProperty("origin", functionOrigin);
			
			builder.addObjectValue(objectBuilder);
		}
	}
	
	def private void buildVariables(
		Collection<String> variableNames,
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
			
			
			val JsonBuilder objectBuilder = new JsonBuilder();
			
			objectBuilder.addStringProperty("construct", "variable");
			objectBuilder.addStringProperty("name", variableName);
			objectBuilder.addStringProperty("type", variableType.toString());	
			objectBuilder.addStringProperty("role", variableRole);
			
			if (variableGroups !== null && variableType !== Type.UNKNOWN && variableGroups.containsKey(variableName)) {
				val GroupType variableGroup = variableGroups.get(variableName);
				objectBuilder.addStringProperty("group", variableGroup.toString());	
			}
			
			builder.addObjectValue(objectBuilder);
		}
	}
}