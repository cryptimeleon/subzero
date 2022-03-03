package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.FunctionSignature;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.eclipse.emf.ecore.EObject;

import java.util.Iterator;
import java.util.Map;

/*
 * Validate function calls
 */
public class FunctionCallValidation {
    
    private final ValidationLogger logger;
    private final Map<EObject, Type> types;
    private final Map<EObject, Integer> sizes;
    private final Map<String, FunctionSignature> userFunctions;
    private final Map<String, FunctionSignature> predefinedFunctions;

    public FunctionCallValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        types = augmentedModel.getTypes();
        sizes = augmentedModel.getSizes();
        userFunctions = augmentedModel.getUserFunctionSignatures();
        predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
    }

    // Function calls must reference either a user defined function or a predefined function
    // The number of arguments in a function call must match the number of parameters in the function definition
    public void checkFunctionCallIsValid(FunctionCall call) {
        String name = call.getName();

        if (userFunctions.containsKey(name)) {
            FunctionSignature signature = userFunctions.get(name);
            functionCallIsValidHelper(call, signature);
            return;
        }

        if (predefinedFunctions.containsKey(name)) {
            FunctionSignature signature = predefinedFunctions.get(name);
            functionCallIsValidHelper(call, signature);
            return;
        }

        logger.error(call, "Function call references a function that does not exist");
    }

    private void functionCallIsValidHelper(FunctionCall call, FunctionSignature signature) {
        if (signature.getParameterCount() != call.getArguments().size()) {
            logger.error(call, "The number of arguments in the function call (%s) must match the number of parameters in the function definition (%s)", call.getArguments().size(), signature.getParameterCount());
            return;
        }

        Iterator<Type> parameterTypesIterator = signature.getParameterTypes().iterator();
        Iterator<Integer> parameterSizesIterator = signature.getParameterSizes().iterator();
        Iterator<Expression> argumentsIterator = call.getArguments().iterator();

        while (parameterTypesIterator.hasNext() && parameterSizesIterator.hasNext() && argumentsIterator.hasNext()) {
            Type parameterType = parameterTypesIterator.next();
            int parameterSize = parameterSizesIterator.next();
            EObject argument = argumentsIterator.next();

            if (types.get(argument) != parameterType) {
                logger.error(argument, "The argument type (%s) does not match the function parameter type (%s)", types.get(argument), parameterType);
            }

            if (sizes.get(argument) != parameterSize) {
                logger.error(argument, "The argument size (%s) does not match the function parameter size (%s)", sizes.get(argument), parameterSize);
            }
        }
    }

}
