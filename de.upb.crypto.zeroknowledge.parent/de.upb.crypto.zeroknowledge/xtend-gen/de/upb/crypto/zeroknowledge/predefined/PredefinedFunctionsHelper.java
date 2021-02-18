package de.upb.crypto.zeroknowledge.predefined;

import de.upb.crypto.zeroknowledge.model.FunctionSignature;
import de.upb.crypto.zeroknowledge.predefined.PredefinedFunctions;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * A helper class to get the function signatures of all predefined functions
 * using reflection
 */
@SuppressWarnings("all")
public class PredefinedFunctionsHelper {
  private static HashMap<String, FunctionSignature> functions;
  
  public static HashMap<String, FunctionSignature> getAllPredefinedFunctions() {
    if ((PredefinedFunctionsHelper.functions == null)) {
      HashMap<String, FunctionSignature> _hashMap = new HashMap<String, FunctionSignature>();
      PredefinedFunctionsHelper.functions = _hashMap;
      final Class<PredefinedFunctions> predefinedClass = PredefinedFunctions.class;
      Method[] _declaredMethods = predefinedClass.getDeclaredMethods();
      for (final Method method : _declaredMethods) {
        {
          final String name = method.getName();
          final FunctionSignature signature = new FunctionSignature(method);
          PredefinedFunctionsHelper.functions.put(name, signature);
        }
      }
    }
    return PredefinedFunctionsHelper.functions;
  }
}
