package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.VariableIdentifier;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.eclipse.emf.ecore.EObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Validate the format of identifier names
 */
public class FormatValidation {

    private final ValidationLogger logger;

    public FormatValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
    }

    // User defined function names must start with a letter, and contain only letters and numbers
    public void checkFunctionNameFormat(FunctionDefinition function) {
        String name = function.getName();

        if (name.contains("_")) {
            logger.error(function, "Function names cannot contain underscores");
        }

        if (name.contains("'")) {
            logger.error(function, "Function names cannot contain single quotes");
        }

        if (name.contains("~")) {
            logger.error(function, "Function names cannot contain tildes");
        }
    }

    public void checkPublicParameterNameFormat(PublicParameter publicParameter) {
        nameFormatErrors(publicParameter, publicParameter.getName(), "Public parameter");
    }

    public void checkWitnessNameFormat(Witness witness) {
        nameFormatErrors(witness, witness.getName(), "Witness");
    }

    public void checkVariableNameFormat(Variable variable) {
        nameFormatErrors(variable, variable.getName(), "Variable");
    }

    public void checkParameterNameFormat(Parameter parameter) {
        nameFormatErrors(parameter, parameter.getName(), "Parameter");
    }

    public void checkConstantNameFormat(Constant constant) {
        nameFormatErrors(constant, constant.getName(), "Constant");
    }

    // Helper method for checking the name format of identifiers
    private void nameFormatErrors(EObject node, String identifier, String type) {
        List<String> nameErrors = new ArrayList<>();

        VariableIdentifier variableIdentifier = new VariableIdentifier(identifier);

        if (!variableIdentifier.isValid()) {
            if (variableIdentifier.hasInvalidUnderscore()) {
                nameErrors.add(type + " name has an invalid underscore");
            }

            if (variableIdentifier.hasInvalidTilde()) {
                nameErrors.add(type + " name has an invalid tilde");
            }

            if (variableIdentifier.hasInvalidQuote()) {
                nameErrors.add(type + " name can only contain single quotes at the end of the name");
            }
        }

        for (String errorMessage : nameErrors) {
            logger.error(node, errorMessage);
        }
    }

}
