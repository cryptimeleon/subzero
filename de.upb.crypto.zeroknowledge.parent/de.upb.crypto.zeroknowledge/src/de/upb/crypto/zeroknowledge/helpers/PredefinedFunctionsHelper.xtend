package de.upb.crypto.zeroknowledge.helpers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;

class PredefinedFunctionsHelper {

  // Returns a map between the function names and signatures of all predefined functions
  def static Map<String, FunctionSignature> getAllPredefinedFunctions() {
    val Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
	val Class<PredefinedFunctions> predefinedClass = PredefinedFunctions;

    for (Method method : predefinedClass.getDeclaredMethods) {
      val String name = method.getName();
      val FunctionSignature signature = new FunctionSignature(method);    
      functions.put(name,  signature);
    }

    return functions;
  }

}
