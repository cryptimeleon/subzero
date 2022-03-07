package org.cryptimeleon.subzero.predefined;

import org.cryptimeleon.subzero.builder.ImportBuilder;
import org.cryptimeleon.subzero.model.FunctionSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A helper class for working with predefined functions
 */
public class PredefinedUtils {

    private static final Map<String, FunctionSignature> functions = new HashMap<>();
    private static final Set<String> inlineFunctions = new HashSet<>();
    static {
        for (Method method : PredefinedFunctions.class.getDeclaredMethods()) {
            String name = method.getName();
            FunctionSignature signature = new FunctionSignature(method);
            functions.put(name, signature);

            if (method.isAnnotationPresent(Inline.class)) {
                inlineFunctions.add(name);
            }
        }
    }

    public static Map<String, FunctionSignature> getPredefinedFunctionSignatures() {
        return functions;
    }

    public static boolean isPredefinedFunction(String functionName) {
        return functions.containsKey(functionName);
    }

    public static boolean isInlineFunction(String functionName) {
        return inlineFunctions.contains(functionName);
    }

    public static ImportBuilder getPredefinedFunctionImports(String functionName) {
        return PredefinedFunctions.predefinedImports.get(functionName);
    }

    public static String getFunctionBody(String functionName) {
        try {
            // Returns the value of the static string field
            Field functionField = PredefinedFunctions.class.getDeclaredField(functionName);
            functionField.setAccessible(true);
            String functionBody = (String) functionField.get(null);
            return functionBody.trim();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

}
