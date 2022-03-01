package org.cryptimeleon.subzero.environment;

import java.util.List;
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper;
import java.util.Map.Entry;
import java.util.Map;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.Variable;
import java.util.ArrayList;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import org.cryptimeleon.subzero.builder.JsonBuilder;
import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.FunctionSignature;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.model.GroupType;
import org.cryptimeleon.subzero.generator.CodeGenerator;

public class EnvironmentGenerator implements CodeGenerator {
    private final AugmentedModel augmentedModel;
    private JsonBuilder builder;

	public EnvironmentGenerator(AugmentedModel augmentedModel) {
        this.augmentedModel = augmentedModel;
    }

    @Override
    public String generate() {
        builder = new JsonBuilder(true);
        buildEnvironment();
        return builder.toString();
    }

    private void buildEnvironment() {
        Map<String, List<FunctionCall>> functionCalls = augmentedModel.getUserFunctionCallNodes();
        Map<String, Map<String, List<LocalVariable>>> localVariables = augmentedModel.getLocalVariableNodes();
        buildPredefinedFunctions(functionCalls, localVariables);
        buildUserFunctions(functionCalls, localVariables);

        Map<String, List<Variable>> variables = augmentedModel.getVariableNodes();
        Map<String, GroupType> variableGroups = null;
        if (augmentedModel.hasPairing()) {
            variableGroups = augmentedModel.getGroups();
        }

        buildWitnessVariables(variables, variableGroups);
        buildPublicParameterVariables(variables, variableGroups);
        buildConstantVariables(variables, variableGroups);
    }

    private void buildPredefinedFunctions(
            Map<String, List<FunctionCall>> functionCalls,
            Map<String, Map<String, List<LocalVariable>>> localVariables
    ) {
        Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
        buildFunctions(predefinedFunctions, functionCalls, localVariables, "built-in");
    }

    private void buildUserFunctions(
            Map<String, List<FunctionCall>> functionCalls,
            Map<String, Map<String, List<LocalVariable>>> localVariables
    ) {
        Map<String, FunctionSignature> userFunctions = augmentedModel.getUserFunctionSignatures();
        buildFunctions(userFunctions, functionCalls, localVariables, "user");
    }

    private void buildWitnessVariables(
            Map<String, List<Variable>> variables,
            Map<String, GroupType> variableGroups
    ) {
        List<String> witnessNames = augmentedModel.getSortedWitnessNames();
        Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
        buildVariables(witnessNames, witnessTypes, variableGroups, variables, "witness");
    }

    private void buildPublicParameterVariables(
            Map<String, List<Variable>> variables,
            Map<String, GroupType> variableGroups
    ) {
        List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
        Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
        buildVariables(publicParameterNames, publicParameterTypes, variableGroups, variables, "public parameter");
    }

    private void buildConstantVariables(
            Map<String, List<Variable>> variables,
            Map<String, GroupType> variableGroups
    ) {
        List<String> constantNames = augmentedModel.getSortedConstantVariableNames();
        Map<String, Type> constantTypes = augmentedModel.getConstantVariableTypes();

        if (augmentedModel.hasExplicitConstants()) {
            Set<String> declaredConstantNames = augmentedModel.getDeclaredConstantNames();
            Set<String> undeclaredConstantNames = new HashSet<>(constantNames);
            undeclaredConstantNames.removeAll(declaredConstantNames);

            buildVariables(declaredConstantNames, constantTypes, variableGroups, variables, "common input");
            buildVariables(undeclaredConstantNames, constantTypes, variableGroups, variables, "unknown");
        } else {
            buildVariables(constantNames, constantTypes, variableGroups, variables, "common input");
        }
    }

    private void buildFunctions(
            Map<String, FunctionSignature> signatures,
            Map<String, List<FunctionCall>> userFunctionCalls,
            Map<String, Map<String, List<LocalVariable>>> localVariableNodes,
            String functionOrigin
    ) {
        for (Entry<String, FunctionSignature> entry : signatures.entrySet()) {
            String functionName = entry.getKey();
            FunctionSignature signature = entry.getValue();

            Map<String, List<LocalVariable>> functionLocalVariables = localVariableNodes.get(functionName);
            boolean skipCheck = functionLocalVariables == null;

            Type returnType;
            if (skipCheck || userFunctionCalls.containsKey(functionName)) {
                returnType = signature.getReturnType();
            } else {
                returnType = Type.UNKNOWN;
            }

            List<String> parameterNames = signature.getParameterNames();
            List<Type> parameterTypes = signature.getParameterTypes();
            List<String> formattedParameterTypes = new ArrayList<>();

            for (int i = 0; i < parameterNames.size(); i++) {
                String parameterName = parameterNames.get(i);
                Type parameterType = parameterTypes.get(i);

                if (skipCheck || functionLocalVariables.get(parameterName).size() > 0) {
                    formattedParameterTypes.add(parameterType.toString());
                } else {
                    formattedParameterTypes.add(Type.UNKNOWN.toString());
                }
            }

            JsonBuilder objectBuilder = new JsonBuilder();

            objectBuilder.addStringProperty("construct", "function");
            objectBuilder.addStringProperty("name", functionName);
            objectBuilder.addStringProperty("returnType", returnType.toString());
            objectBuilder.addArrayProperty("parameterNames", parameterNames);
            objectBuilder.addArrayProperty("parameterTypes", formattedParameterTypes);
            objectBuilder.addStringProperty("origin", functionOrigin);

            builder.addObjectValue(objectBuilder);
        }
    }

    private void buildVariables(
            Collection<String> variableNames,
            Map<String, Type> variableTypes,
            Map<String, GroupType> variableGroups,
            Map<String, List<Variable>> variableNodes,
            String variableRole
    ) {
        for (String variableName : variableNames) {
            Type variableType;
            if (variableNodes.containsKey(variableName)) {
                variableType = variableTypes.get(variableName);
            } else {
                variableType = Type.UNKNOWN;
            }

            JsonBuilder objectBuilder = new JsonBuilder();

            objectBuilder.addStringProperty("construct", "variable");
            objectBuilder.addStringProperty("name", variableName);
            objectBuilder.addStringProperty("type", variableType.toString());
            objectBuilder.addStringProperty("role", variableRole);

            if (variableGroups != null && variableType != Type.UNKNOWN && variableGroups.containsKey(variableName)) {
                GroupType variableGroup = variableGroups.get(variableName);
                objectBuilder.addStringProperty("group", variableGroup.toString());
            }

            builder.addObjectValue(objectBuilder);
        }
    }
}