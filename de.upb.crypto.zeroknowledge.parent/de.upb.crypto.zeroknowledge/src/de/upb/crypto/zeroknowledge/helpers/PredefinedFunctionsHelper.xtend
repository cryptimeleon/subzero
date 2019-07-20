package de.upb.crypto.zeroknowledge.helpers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;

class PredefinedFunctionsHelper {

  // Converts internal class name to simpler form
  // Possibly remove and simply use fully qualified name?
  def private static String convertType(String type) {
    val int periodIndex = type.lastIndexOf('.');
    if (periodIndex > 0) {
      return type.substring(periodIndex + 1);
    }
    return type;
  }

  // Returns a map between the function names and signatures of all predefined functions
  def static Map<String, FunctionSignature> getAllPredefinedFunctions() {
    val Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
	val Class<PredefinedFunctions> predefinedClass = PredefinedFunctions;

    for (Method method : predefinedClass.getDeclaredMethods) {
      val String name = method.getName();
      val String type = convertType(method.getReturnType().getName());
      val int parameters = method.getParameterCount();
      val FunctionSignature signature = new FunctionSignature(name, type, parameters);
      functions.put(name,  signature);
    }

    return functions;
  }

}
