package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class PredefinedFunctionsHelper {
  private static String convertType(final String type) {
    final int periodIndex = type.lastIndexOf(".");
    if ((periodIndex > 0)) {
      return type.substring((periodIndex + 1));
    }
    return type;
  }
  
  public static Map<String, FunctionSignature> getAllPredefinedFunctions() {
    final Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
    final Class<PredefinedFunctions> predefinedClass = PredefinedFunctions.class;
    Method[] _declaredMethods = predefinedClass.getDeclaredMethods();
    for (final Method method : _declaredMethods) {
      {
        final String name = method.getName();
        final String type = PredefinedFunctionsHelper.convertType(method.getReturnType().getName());
        final int parameters = method.getParameterCount();
        final FunctionSignature signature = new FunctionSignature(name, type, parameters);
        functions.put(name, signature);
      }
    }
    return functions;
  }
}
