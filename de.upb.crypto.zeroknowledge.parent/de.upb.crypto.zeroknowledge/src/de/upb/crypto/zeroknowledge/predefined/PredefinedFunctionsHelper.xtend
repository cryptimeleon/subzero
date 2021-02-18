package de.upb.crypto.zeroknowledge.predefined;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import de.upb.crypto.zeroknowledge.model.FunctionSignature;

/**
 * A helper class to get the function signatures of all predefined functions
 * using reflection
 */
class PredefinedFunctionsHelper {

  private static HashMap<String, FunctionSignature> functions;

  // Returns a map between the function names and signatures of all predefined functions
  // The map is cached after the first call for subsequent calls
  def static HashMap<String, FunctionSignature> getAllPredefinedFunctions() {
  	if (functions === null) {
	    functions = new HashMap<String, FunctionSignature>();
		val Class<PredefinedFunctions> predefinedClass = PredefinedFunctions;
	
	    for (Method method : predefinedClass.getDeclaredMethods) {
	      val String name = method.getName();
	      val FunctionSignature signature = new FunctionSignature(method);    
	      functions.put(name, signature);
	    }
  	}
  	
    return functions;
  }
}
