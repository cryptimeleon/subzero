package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.ConstantVariable;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.SubzeroPackage.Literals;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class VariableValidation {

    private final ValidationLogger logger;
    private final Set<String> declaredConstantNames;
    private final Map<String, List<Variable>> variables;
    private final boolean hasExplicitConstants;

    public VariableValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        declaredConstantNames = augmentedModel.getDeclaredConstantNames();
        variables = augmentedModel.getVariableNodes();
        hasExplicitConstants = augmentedModel.hasExplicitConstants();
    }

    // The protocol must declare at least one witness
    public void checkModelHasWitness(Model model) {
        if (model.getWitnesses().isEmpty()) {
            logger.error(model, Literals.MODEL__WITNESSES, "The protocol must declare at least one witness");
        }
    }

    // Each witness should be referenced at least once
    public void checkWitnessIsUsed(Witness witness) {
        String name = witness.getName();
        if (!variables.containsKey(name)) {
            logger.warning(witness, "The witness '%s' is not used in the proof expression or any function definition", name);
        }
    }

    // Each public parameter should be referenced at least once
    public void checkPublicParameterIsUsed(PublicParameter publicParameter) {
        String name = publicParameter.getName();
        if (!variables.containsKey(name)) {
            logger.warning(publicParameter, "The public parameter '%s' is not used in the proof expression or any function definition", name);
        }
    }

    // Each witness should be referenced at least once
    public void checkConstantIsUsed(Constant constant) {
        String name = constant.getName();
        if (!variables.containsKey(name)) {
            logger.warning(constant, "The common input '%s' is not used in the proof expression or any function definition", name);
        }
    }

    // If any constant variables are explicitly declared, then all variables must be explicitly declared
    public void checkVariableIsDeclared(Variable variable) {
        if (hasExplicitConstants && variable instanceof ConstantVariable && !declaredConstantNames.contains(variable.getName())) {
            logger.error(variable, "If any common input variable is explicitly declared, then no common input variable can be implicitly declared");
        }
    }

}
