package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.BranchState;
import org.cryptimeleon.subzero.model.TreeTraversals;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import org.cryptimeleon.subzero.subzero.Parameter;

import java.util.List;
import java.util.Map;

/*
 * Validate user function definitions
 */
public class FunctionDefinitionValidation {

    private final ValidationLogger logger;
    private final Map<String, List<FunctionCall>> userFunctionCalls;
    private final Map<String, Map<String, List<LocalVariable>>> localVariables;

    public FunctionDefinitionValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        userFunctionCalls = augmentedModel.getUserFunctionCalls();
        localVariables = augmentedModel.getLocalVariables();
    }

    // Function definitions cannot contain function calls to other user functions
    public void checkFunctionHasNoFunctionCalls(FunctionCall call, BranchState state) {
        if (state.hasFunctionDefinitionAncestor()) {
            logger.error(call, "Cannot call functions from within a user function");
        }
    }

    // Every function parameter should be used at least once in the function definition
    public void checkFunctionParametersAreUsed(FunctionDefinition function) {
        Map<String, List<LocalVariable>> functionLocalVariables = localVariables.get(function.getName());

        for (Parameter parameter: function.getParameters()) {
            if (functionLocalVariables.get(parameter.getName()).size() == 0) {
                logger.warning(parameter, "Parameter '%s' should be used within the function definition", parameter.getName());
            }
        }
    }

    // User defined functions should be called at least once in the proof expression
    public void checkFunctionIsCalled(FunctionDefinition function) {
        String functionName = function.getName();

        if (!userFunctionCalls.containsKey(functionName)) {
            logger.warning(function, "Function is never used in the proof expression, and will not be included in the generated Java code");
        }
    }

    // User defined functions cannot currently contain a disjunction
    public void checkFunctionHasNoDisjunction(FunctionDefinition function) {
        boolean hasDisjunction = TreeTraversals.anyInPostorderTraversal(function.getBody(), (node) -> node instanceof Disjunction);

        if (hasDisjunction) {
            logger.error(function, "Disjunctions are currently not supported in user functions");
        }
    }

}
