package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.Sum;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.eclipse.emf.ecore.EObject;

import java.util.Map;

public class SizeValidation {
    
    private final ValidationLogger logger;
    private final Map<EObject, Type> types;
    private final Map<EObject, Integer> sizes;

    public SizeValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        types = augmentedModel.getTypes();
        sizes = augmentedModel.getSizes();
    }

    public void checkTupleElementsAreSameType(Tuple tuple) {
        Type tupleType = types.get(tuple);

        for (EObject element : tuple.getElements()) {
            Type elementType = types.get(element);

            if (tupleType != elementType) {
                logger.error(element, "Tuple elements must be the same type as the tuple. The tuple has type %s, but the element has type %s", tupleType, elementType);
            }
        }
    }

    public void checkIsScalar(EObject node) {
        if (!sizes.containsKey(node)) {
            logger.error(node, "%s must be a scalar", ValidationUtils.getCapitalizedNodeName(node));
        } else if (sizes.get(node) != 1) {
            logger.error(node, "%s must be a scalar, not a tuple of size %s", ValidationUtils.getCapitalizedNodeName(node), sizes.get(node));
        }
    }

    public void checkIsTuple(EObject node) {
        if (!sizes.containsKey(node)) {
            logger.error(node, "%s must be a tuple", ValidationUtils.getCapitalizedNodeName(node));
        } else if (sizes.get(node) <= 1) {
            logger.error(node, "%s must be a tuple, not a scalar", ValidationUtils.getCapitalizedNodeName(node));
        }
    }

    public void checkTupleSize(Tuple tuple) {
        int currentSize = sizes.get(tuple);
        int correctSize = tuple.getElements().size();

        if (currentSize != correctSize) {
            logger.error(tuple, "The operands of operations between tuples must have the same size. This tuple of size %s is in an operation with a tuple of size %s", correctSize, currentSize);
        }
    }
    
    public void checkComparisonOperandSizes(Comparison comparison) {
        int leftSize = sizes.get(comparison.getLeft());
        int rightSize = sizes.get(comparison.getRight());

        if (comparison.getOperation2() == null) {
            if (leftSize != rightSize) {
                logger.error(comparison, "The operands of a comparison expression must be the same size. The left operand is of size %s but the right operand is of size %s", leftSize, rightSize);
            }
        } else {
            int centerSize = sizes.get(comparison.getCenter());

            if (leftSize != centerSize || centerSize != rightSize) {
                logger.error(comparison, "The operands of a comparison expression must be the same size. The left operand is of size %s, the middle operand is of size %s, and the right operand is of size %s", leftSize, centerSize, rightSize);
            }
        }
    }

    public void checkSumOperandSizes(Sum sum) {
        int leftSize = sizes.get(sum.getLeft());
        int rightSize = sizes.get(sum.getRight());

        if (leftSize != rightSize) {
            logger.error(sum, "The operands of a sum expression must be the same size. The left operand is of size %s but the right operand is of size %s", leftSize, rightSize);
        }
    }

    public void checkProductOperandSizes(Product product) {
        Type leftType = types.get(product.getLeft());
        Type rightType = types.get(product.getRight());
        int leftSize = sizes.get(product.getLeft());
        int rightSize = sizes.get(product.getRight());

        if (leftType == Type.GROUP_ELEMENT && rightType == Type.GROUP_ELEMENT && leftSize != rightSize) {
            logger.error(product, "The operands of a group element product expression must be the same size. The left operand is of size %s but the right operand is of size %s", leftSize, rightSize);
        }
    }

    public void checkPowerOperandSizes(Power power) {
        int rightTuple = sizes.get(power.getRight());

        if (rightTuple > 1) {
            logger.error(power, "The right operand of an exponentiation expression cannot be a tuple");
        }
    }
}
