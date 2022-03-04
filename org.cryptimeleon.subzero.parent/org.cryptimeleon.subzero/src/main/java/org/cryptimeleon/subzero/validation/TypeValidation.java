package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.FunctionSignature;
import org.cryptimeleon.subzero.model.ModelUtils;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.Sum;
import org.eclipse.emf.ecore.EObject;

import java.util.Map;

/*
 * Validate that nodes with a predetermined type are correctly labelled as this type, and 
 * that the operands of an operation are compatible types
 */
public class TypeValidation {
    private final ValidationLogger logger;
    private final Map<EObject, Type> types;
    private final Map<String, FunctionSignature> predefinedFunctions;

    public TypeValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        types = augmentedModel.getTypes();
        predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
    }

    public void checkDisjunctionOperandTypes(Disjunction disjunction) {
        Type leftType = types.get(disjunction.getLeft());
        Type rightType = types.get(disjunction.getRight());

        if (leftType != Type.BOOLEAN || rightType != Type.BOOLEAN) {
            logger.error(disjunction, "Disjunction operands must both have type boolean. The left operand is of type %s and the right operand is of type %s", leftType, rightType);
        }
    }

    public void checkConjunctionOperandTypes(Conjunction conjunction) {
        Type leftType = types.get(conjunction.getLeft());
        Type rightType = types.get(conjunction.getRight());

        if (leftType != Type.BOOLEAN || rightType != Type.BOOLEAN) {
            logger.error(conjunction, "Conjunction operands must both have type boolean. The left operand is of type %s and the right operand is of type %s", leftType, rightType);
        }
    }

    public void checkComparisonOperandTypes(Comparison comparison) {
        Type leftType = types.get(comparison.getLeft());
        Type rightType = types.get(comparison.getRight());

        if (comparison.getOperation2() == null) {
            if (leftType != rightType) {
                logger.error(comparison, "The operands of a comparison expression must be the same type. The left operand is of type %s but the right operand is of type %s", leftType, rightType);
            }
        } else {
            Type centerType = types.get(comparison.getCenter());

            if (leftType != centerType || centerType != rightType) {
                logger.error(comparison, "The operands of a comparison expression must be the same type. The left operand is of type %s, the middle operand is of type %s, and the right operand is of type %s", leftType, centerType, rightType);
            }
        }
    }

    public void checkSumOperandTypes(Sum sum) {
        Type leftType = types.get(sum.getLeft());
        Type rightType = types.get(sum.getRight());

        if (leftType != Type.EXPONENT || rightType != Type.EXPONENT) {
            logger.error(sum, "Sum operands must both have type exponent. The left operand is of type %s and the right operand is of type %s", leftType, rightType);
        }
    }

    public void checkProductOperandTypes(Product product) {
        Type leftType = types.get(product.getLeft());
        Type rightType = types.get(product.getRight());

        if (leftType != rightType) {
            logger.error(product, "The operands of a product expression must be the same type. The left operand is of type %s but the right operand is of type %s", leftType, rightType);
        }
    }

    public void checkPowerOperandTypes(Power power) {
        Type type = types.get(power);
        Type leftType = types.get(power.getLeft());
        Type rightType = types.get(power.getRight());

        if (!(leftType == Type.EXPONENT || leftType == Type.GROUP_ELEMENT)) {
            logger.error(power, "The left operand of an exponentiation expression must be of type exponent or group element, not type %s", leftType);
        }

        if (rightType != Type.EXPONENT) {
            logger.error(power, "The right operand of an exponentiation expression must be of type exponent, not type %s", rightType);
        }

        if (type != leftType) {
            logger.error(power, "The type of an exponentiation expression must be the same as the type of the left operand. The exponentiation expression is of type %s but the left operand is of type %s", type, leftType);
        }
    }

    public void checkPredefinedFunctionCallType(FunctionCall call) {
        if (predefinedFunctions.containsKey(call.getName())) {
            Type currentType = types.get(call);
            Type correctType = predefinedFunctions.get(call.getName()).getReturnType();

            if (currentType != correctType) {
                logger.error(call, "Predefined function call should have type %s, not type %s", correctType, currentType);
            }
        }
    }

    public void checkIsBoolean(EObject node) {
        checkIsType(node, Type.BOOLEAN);
    }

    public void checkIsExponent(EObject node) {
        checkIsType(node, Type.EXPONENT);
    }

    public void checkIsGroupElement(EObject node) {
        checkIsType(node, Type.GROUP_ELEMENT);
    }

    public void checkIsExponentOrGroupElement(EObject node) {
        String nodeName = ValidationUtils.getCapitalizedNodeName(node);
        if (!types.containsKey(node)) {
            logger.error(node, "%s must be of type exponent or group element", nodeName);
        } else {
            logger.error(node, "%s must be of type exponent or group element, not type %s", nodeName, types.get(node));
        }
    }

    public void checkIsString(EObject node) {
        checkIsType(node, Type.STRING);
    }

    private void checkIsType(EObject node, Type type) {
        String nodeName = ValidationUtils.getCapitalizedNodeName(node);
        if (!types.containsKey(node)) {
            logger.error(node, "%s must be of type %s", nodeName, type);
        } else if (types.get(node) != type) {
            logger.error(node, "%s must be of type %s, not type %s", nodeName, type, types.get(node));
        }
    }
}
