package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.GroupType;
import org.cryptimeleon.subzero.subzero.Variable;

import java.util.Map;

public class GroupTypeValidation {

    private final ValidationLogger logger;
    private final Map<String, GroupType> groups;

    public GroupTypeValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        groups = augmentedModel.getGroups();
    }

    public void checkGroupType(Variable variable) {
        if (groups.get(variable.getName()) == GroupType.UNKNOWN) {
            logger.error(variable, "Variable is used in conflicting group element contexts");
        }
    }
}
