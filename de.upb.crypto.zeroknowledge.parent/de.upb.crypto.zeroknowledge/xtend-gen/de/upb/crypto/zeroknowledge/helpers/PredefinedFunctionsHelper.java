package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class PredefinedFunctionsHelper {
  public static Map<String, FunctionSignature> getAllPredefinedFunctions() {
    final Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
    final Class<PredefinedFunctions> predefinedClass = PredefinedFunctions.class;
    Method[] _declaredMethods = predefinedClass.getDeclaredMethods();
    for (final Method method : _declaredMethods) {
      {
        final String name = method.getName();
        final FunctionSignature signature = new FunctionSignature(method);
        functions.put(name, signature);
      }
    }
    return functions;
  }
}
