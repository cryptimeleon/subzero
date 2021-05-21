package org.cryptimeleon.zeroknowledge.predefined

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.cryptimeleon.zeroknowledge.model.FunctionSignature;

/**
 * A helper class to get the function signatures of all predefined functions
 * using reflection
 */
class PredefinedFunctionsHelper {

  private static Map<String, FunctionSignature> functions;

  // Returns a map between the function names and signatures of all predefined functions
  // The map is cached after the first call for subsequent calls
  def static Map<String, FunctionSignature> getAllPredefinedFunctions() {
  	if (functions !== null) return functions;
  	
    functions = new HashMap<String, FunctionSignature>();
	val Class<PredefinedFunctions> predefinedClass = PredefinedFunctions;

    for (Method method : predefinedClass.getDeclaredMethods()) {
      val String name = method.getName();
      val FunctionSignature signature = new FunctionSignature(method);    
      functions.put(name, signature);
    }
  	
    return functions;
  }
  
  def static boolean isPredefinedFunction(String functionName) {
  	return getAllPredefinedFunctions().containsKey(functionName);
  }
  
  def static String generateFunction(String functionName) {
  	try {
  		val String generatedCode = PredefinedFunctions.getDeclaredField(functionName).get("") as String;
  		return generatedCode;
  	} catch (Exception e) {
  		throw new IllegalArgumentException();
  	}
  }
}
