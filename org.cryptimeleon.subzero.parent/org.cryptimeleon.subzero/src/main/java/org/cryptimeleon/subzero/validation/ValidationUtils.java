package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.ConstantVariable;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Negative;
import org.cryptimeleon.subzero.subzero.NumberLiteral;
import org.cryptimeleon.subzero.subzero.PPVariable;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.StringLiteral;
import org.cryptimeleon.subzero.subzero.SubzeroPackage.Literals;
import org.cryptimeleon.subzero.subzero.Sum;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.cryptimeleon.subzero.subzero.WitnessVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidationUtils {
    
    public static final Set<Class<?>> NODE_CLASSES = Set.of(
        Model.class,
        FunctionDefinition.class,
        Parameter.class,
        Witness.class,
        PublicParameter.class,
        Constant.class,
        Disjunction.class,
        Conjunction.class,
        Comparison.class,
        Sum.class,
        Product.class,
        Power.class,
        StringLiteral.class,
        Tuple.class,
        Negative.class,
        FunctionCall.class,
        LocalVariable.class,
        ConstantVariable.class,
        PPVariable.class,
        WitnessVariable.class,
        Variable.class,
        NumberLiteral.class
    );

    // Capitalizes the first letter of the string
    public static String capitalize(String string) {
        if (string.isEmpty()) return "";
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    // Merges two maps of lists into the first map of lists
    public static <K, T> void mergeMapsOfLists(Map<K, List<T>> finalMap, Map<K, List<T>> mergedMap) {
        for (Map.Entry<K, List<T>> entry : mergedMap.entrySet()) {
            K name = entry.getKey();
            List<T> list = entry.getValue();

            if (finalMap.containsKey(name)) {
                finalMap.get(name).addAll(list);
            } else {
                finalMap.put(name, list);
            }
        }
    }

    // Helper map for getNodeName
    private static final Map<Class<?>, String> nodeNames = NODE_CLASSES.stream()
        .collect(
            Collectors.toMap(
                Function.identity(),
                ValidationUtils::generateNodeName
            )
        );

    private static String generateNodeName(Class<?> nodeClass) {
        String className = nodeClass.getSimpleName();
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(Character.toLowerCase(className.charAt(0)));

        String impl = "Impl";
        if (className.endsWith(impl)) {
            className = className.substring(0, className.length() - impl.length());
        }

        for (int i = 1; i < className.length(); i++) {
            char letter = className.charAt(i);
            if (Character.isUpperCase(letter)) {
                nameBuilder.append(" " + Character.toLowerCase(letter));
            } else {
                nameBuilder.append(letter);
            }
        }

        return nameBuilder.toString();
    }

    // Returns the name of the type of node
    public static String getNodeName(EObject node) {
        return nodeNames.get(node.getClass().getInterfaces()[0]);
    }


    // Helper map for getCapitalizedNodeName
    private static final Map<Class<?>, String> capitalizedNodeNames = nodeNames.entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                e -> capitalize(e.getValue())
            )
        );

    // Returns the name of the type of node, with the first letter capitalized
    public static String getCapitalizedNodeName(EObject node) {
        return capitalizedNodeNames.get(node.getClass().getInterfaces()[0]);
    }

    // Helper map for getDefaultFeature
    private static final Map<Class<?>, EStructuralFeature> nodeDefaultFeatures = Map.ofEntries(
        Map.entry(Model.class, Literals.MODEL__PROOF),
        Map.entry(FunctionDefinition.class, Literals.FUNCTION_DEFINITION__NAME),
        Map.entry(Parameter.class, Literals.PARAMETER__NAME),
        Map.entry(Witness.class, Literals.WITNESS__NAME),
        Map.entry(PublicParameter.class, Literals.PUBLIC_PARAMETER__NAME),
        Map.entry(Constant.class, Literals.CONSTANT__NAME),
        Map.entry(Disjunction.class, Literals.DISJUNCTION__OPERATION),
        Map.entry(Conjunction.class, Literals.CONJUNCTION__OPERATION),
        Map.entry(Comparison.class, Literals.COMPARISON__OPERATION),
        Map.entry(Sum.class, Literals.SUM__OPERATION),
        Map.entry(Product.class, Literals.PRODUCT__OPERATION),
        Map.entry(Power.class, Literals.POWER__OPERATION),
        Map.entry(StringLiteral.class, Literals.STRING_LITERAL__VALUE),
        Map.entry(Tuple.class, Literals.TUPLE__ELEMENTS),
        Map.entry(Negative.class, Literals.NEGATIVE__OPERATION),
        Map.entry(FunctionCall.class, Literals.FUNCTION_CALL__NAME),
        Map.entry(LocalVariable.class, Literals.VARIABLE__NAME),
        Map.entry(ConstantVariable.class, Literals.VARIABLE__NAME),
        Map.entry(PPVariable.class, Literals.VARIABLE__NAME),
        Map.entry(WitnessVariable.class, Literals.VARIABLE__NAME),
        Map.entry(Variable.class, Literals.VARIABLE__NAME),
        Map.entry(NumberLiteral.class, Literals.NUMBER_LITERAL__VALUE)
    );

    // Returns the default structural feature for a node
    public static EStructuralFeature getDefaultFeature(EObject node) {
        Class<?>[] nodeInterfaces = node.getClass().getInterfaces();
        if (nodeInterfaces.length == 0) return null;
        return nodeDefaultFeatures.get(nodeInterfaces[0]);
    }
}
