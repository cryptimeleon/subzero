package org.cryptimeleon.subzero.model;

import org.cryptimeleon.subzero.subzero.Argument;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.cryptimeleon.subzero.subzero.WitnessVariable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/*
 * A helper class providing functions for working with a model
 */
public class ModelUtils {

    public static final String OPERATOR_ADDITION = "+";
    public static final String OPERATOR_SUBTRACTION = "-";
    public static final String OPERATOR_MULTIPLICATION = "*";
    public static final String OPERATOR_DIVISION = "/";
    public static final String OPERATOR_EQUAL = "=";
    public static final String OPERATOR_INEQUAL = "!=";
    public static final String OPERATOR_LESS = "<";
    public static final String OPERATOR_GREATER = ">";
    public static final String OPERATOR_LESSEQUAL = "<=";
    public static final String OPERATOR_GREATEREQUAL = ">=";

    // Helper function to get the root Model object
    public static Model getRoot(EObject node) {
        return (Model) EcoreUtil.getRootContainer(node);
    }

    // Sets a child node's parent's reference to the child node to reference a new child node
    // Precondition: node cannot be the root Model node
    public static void replaceParentReferenceToSelf(EObject childNode, EObject newChildNode) {
        EObject parent = childNode.eContainer();
        EStructuralFeature feature = childNode.eContainingFeature();
        if (parent.eGet(feature) instanceof EList) {
            // It is guaranteed that the containing feature is an EList of EObjects, so this cast is safe
            @SuppressWarnings("unchecked")
            EList<EObject> list = (EList<EObject>) parent.eGet(feature);

            list.set(list.indexOf(childNode), newChildNode);
        } else {
            parent.eSet(feature, newChildNode);
        }
    }

    public static void replaceFunctionCallWithDefinition(FunctionCall call, Map<String, FunctionDefinition> functions) {
        // Precondition: validation ensures that function call is valid
        FunctionDefinition definition = EcoreUtil.copy(functions.get(call.getName()));
        Map<String, Expression> mapping = new HashMap<>();
        Iterator<Expression> argumentIterator = call.getArguments().iterator();
        Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameters().
                iterator();

        while (argumentIterator.hasNext() && parameterIterator.hasNext()) {
            Argument argument = (Argument) argumentIterator.next();
            String parameter = parameterIterator.next().getName();
            mapping.put(parameter, argument.getExpression());
        }

        ModelMap.preorder(definition.getBody(), (bodyNode) -> {
            if (bodyNode instanceof Variable) {
                Expression expression = EcoreUtil.copy(mapping.get(((Variable) bodyNode).getName()));
                if (expression != null) {
                    replaceParentReferenceToSelf(bodyNode, expression);
                }
            }
        });

        replaceParentReferenceToSelf(call, definition.getBody());
    }

    // For a parameter in a function definition, get the function name
    public static String getParameterFunction(Parameter parameter) {
        return ((FunctionDefinition) parameter.eContainer()).getName();
    }

    // For an argument in a function call, get the function name
    public static String getArgumentFunction(Argument argument) {
        return ((FunctionCall) argument.eContainer()).getName();
    }

    // For an argument in a function call, get the position (0-based) of the argument in the call
    public static int getArgumentIndex(Argument argument) {
        return ((FunctionCall) argument.eContainer()).getArguments().indexOf(argument);
    }

    public static boolean containsWitnessVariable(EObject node) {
        return ModelMap.preorderWithControl(node, (childNode, controller) -> {
            if (childNode instanceof FunctionCall) {
                controller.continueTraversal();
            } else if (childNode instanceof WitnessVariable) {
                controller.returnTrue();
                controller.breakMap();
            }
        });
    }

    // Helper map for getNodeName
    private static final Map<Class<?>, Function<EObject, String>> nodeNameFunctions = Map.ofEntries(
        Map.entry(Constant.class, node -> ((Constant) node).getName()),
        Map.entry(FunctionCall.class, node -> ((FunctionCall) node).getName()),
        Map.entry(FunctionDefinition.class, node -> ((FunctionDefinition) node).getName()),
        Map.entry(Parameter.class, node -> ((Parameter) node).getName()),
        Map.entry(PublicParameter.class, node -> ((PublicParameter) node).getName()),
        Map.entry(Variable.class, node -> ((Variable) node).getName()),
        Map.entry(Witness.class, node -> ((Witness) node).getName())
    );

    public static String getNodeName(EObject node) {
        Function<EObject, String> nodeNameFunction = getFromNodeClassMap(nodeNameFunctions, node.getClass());
        return (nodeNameFunction == null) ? "" : nodeNameFunction.apply(node);
    }

    private static <T> T getFromNodeClassMap(Map<Class<?>, T> classMap, Class<?> nodeClass) {
        Class<?> nodeInterface = nodeClass.getInterfaces()[0];
        T result = classMap.get(nodeInterface);

        if (result == null) {
            Class<?>[] superInterfaces = nodeInterface.getInterfaces();

            if (superInterfaces.length == 0) {
                return null;
            }

            Class<?> superInterface = superInterfaces[0];
            result = classMap.get(superInterface);
        }

        return result;
    }

    public static boolean isEqualityComparison(EObject node) {
        if (node instanceof Comparison) {
            String operator = ((Comparison) node).getOperation();
            return operator.equals(OPERATOR_EQUAL) || operator.equals(OPERATOR_INEQUAL);
        }

        return false;
    }

    public static boolean isInequalityComparison(EObject node) {
        if (node instanceof Comparison) {
            String operator = ((Comparison) node).getOperation();

            return operator.equals(OPERATOR_LESS)
                || operator.equals(OPERATOR_LESSEQUAL)
                || operator.equals(OPERATOR_GREATER)
                || operator.equals(OPERATOR_GREATEREQUAL);
        }

        return false;
    }

    public static boolean isStrictComparison(String operator) {
        return operator.equals(OPERATOR_LESS) || operator.equals(OPERATOR_GREATER);
    }

    public static boolean isLessComparison(String operator) {
        return operator.equals(OPERATOR_LESS) || operator.equals(OPERATOR_LESSEQUAL);
    }

    public static String swapComparisonDirection(String operator) {
        switch (operator) {
            case OPERATOR_LESS: return OPERATOR_GREATER;
            case OPERATOR_LESSEQUAL: return OPERATOR_GREATEREQUAL;
            case OPERATOR_GREATER: return OPERATOR_LESS;
            case OPERATOR_GREATEREQUAL: return OPERATOR_LESSEQUAL;
        }
        return null;
    }
}
