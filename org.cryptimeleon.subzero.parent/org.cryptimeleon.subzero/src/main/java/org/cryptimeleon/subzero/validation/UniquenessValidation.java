package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.FunctionSignature;
import org.cryptimeleon.subzero.model.TreeTraversals;
import org.cryptimeleon.subzero.predefined.PredefinedUtils;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Witness;
import org.eclipse.emf.common.util.EList;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Validate the uniqueness of identifiers
 */
public class UniquenessValidation {

    private final ValidationLogger logger;
    private final Map<String, FunctionSignature> predefinedFunctions;
    private final Set<String> witnessNames;
    private final Set<String> publicParameterNames;
    private final Set<String> declaredConstantNames;

    public UniquenessValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        predefinedFunctions = PredefinedUtils.getPredefinedFunctionSignatures();
        witnessNames = augmentedModel.getWitnessNames();
        publicParameterNames = augmentedModel.getPublicParameterNames();
        declaredConstantNames = augmentedModel.getDeclaredConstantNames();
    }

    // User defined function names must be unique
    public void checkFunctionNamesAreUnique(EList<FunctionDefinition> functions) {
        Set<String> functionNames = new HashSet<>();

        for (FunctionDefinition function : functions) {
            String name = function.getName();

            if (functionNames.contains(name)) {
                logger.error(function, "Function names must be unique");
            } else {
                functionNames.add(name);
            }
        }
    }

    // Subprotocol names must be unique
    public void checkSubprotocolNamesAreUnique(Model model) {
        Set<String> subprotocolNames = new HashSet<>();
        TreeTraversals.preorderTraversal(model.getProof(), (node) -> {
            if (node instanceof Comparison) {
                String subprotocolName = ((Comparison) node).getSubprotocolName();
                if (subprotocolNames.contains(subprotocolName)) {
                    logger.error(node, "Subprotocol names must be unique");
                } else if (subprotocolName != null && !subprotocolName.isEmpty()) {
                    subprotocolNames.add(subprotocolName);
                }
            }
        });
    }

    // User defined functions cannot have the same name as a predefined function
    public void checkFunctionNameIsNotPredefined(FunctionDefinition function) {
        if (predefinedFunctions.containsKey(function.getName())) {
            logger.error(function, "Function name is already used by a predefined function");
        }
    }

    // Public parameter names must be unique
    public void checkPublicParameterNamesAreUnique(EList<PublicParameter> publicParameterList) {
        Set<String> publicParameters = new HashSet<>();
        for (PublicParameter publicParameter : publicParameterList) {
            String name = publicParameter.getName();
            if (publicParameters.contains(name)) {
                logger.error(publicParameter, "Public parameter name conflicts with another public parameter name");
            }
            if (witnessNames.contains(name)) {
                logger.error(publicParameter, "Public parameter name conflicts with a witness name");
            }
            if (declaredConstantNames.contains(name)) {
                logger.error(publicParameter, "Public parameter name conflicts with a constant name");
            }

            publicParameters.add(name);
        }
    }

    // Witness names must be unique
    public void checkWitnessNamesAreUnique(EList<Witness> witnessList) {
        Set<String> witnesses = new HashSet<>();
        for (Witness witness : witnessList) {
            String name = witness.getName();
            if (witnesses.contains(name)) {
                logger.error(witness, "Witness name conflicts with another witness name");
            }
            if (publicParameterNames.contains(name)) {
                logger.error(witness, "Witness name conflicts with a public parameter name");
            }
            if (declaredConstantNames.contains(name)) {
                logger.error(witness, "Witness name conflicts with a constant name");
            }

            witnesses.add(name);
        }
    }

    // Constant names must be unique
    public void checkConstantNamesAreUnique(EList<Constant> constantList) {
        Set<String> constants = new HashSet<>();
        for (Constant constant : constantList) {
            String name = constant.getName();
            if (constants.contains(name)) {
                logger.error(constant, "Constant name conflicts with another constant name");
            }
            if (witnessNames.contains(name)) {
                logger.error(constant, "Constant name conflicts with a witness name");
            }
            if (publicParameterNames.contains(name)) {
                logger.error(constant, "Constant name conflicts with a public parameter name");
            }

            constants.add(name);
        }
    }

    // Function parameter names must be unique within a function signature
    public void checkFunctionParameterNamesAreUnique(EList<Parameter> parameterList) {
        Set<String> parameters = new HashSet<>();
        for (Parameter parameter : parameterList) {
            String name = parameter.getName();
            if (parameters.contains(name)) {
                logger.error(parameter, "Function parameters must be unique within a function's signature");
            } else {
                parameters.add(name);
            }
        }
    }
}
