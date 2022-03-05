package org.cryptimeleon.subzero.predefined;

import org.cryptimeleon.subzero.builder.ImportBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to track imports required by predefined functions
 */
public class PredefinedImports {

    private static final Map<String, ImportBuilder> importBuilders = new HashMap<>();

    public PredefinedImports() {
        // Intentionally blank
    }

    // Get the import builder for a specific predefined function
    public ImportBuilder get(String functionName) {
        return importBuilders.get(functionName);
    }

    // Add a class to import for a predefined function
    // The specific predefined function that the import class is used in is determined automatically with reflection
    public String use(Class<?> importClass) {
        String functionName = getFunctionName();

        if (functionName == null) {
            System.err.println("Error: could not get function name");
            return "";
        }

        ImportBuilder importBuilder = importBuilders.get(functionName);
        if (importBuilder == null) {
            importBuilder = new ImportBuilder();
            importBuilders.put(functionName, importBuilder);
        }

        return importBuilder.use(importClass);
    }

    // Get the predefined function name, which is the name of the static field in PredefinedFunctions that is
    // currently being initialized to a template string that calls the 'use' function for a specific class.
    // Since static fields are initialized in the order they are declared, we can find what static field is
    // currently being initialized by iterating over the fields in PredefinedFunctions, and getting the name of
    // the first field whose value is still null (i.e. it is uninitialized).
    private String getFunctionName() {
        for (Field field : PredefinedFunctions.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(null);
                if (fieldValue == null) {
                    return field.getName();
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Intentionally blank
            }
        }
        return null;
    }

}
