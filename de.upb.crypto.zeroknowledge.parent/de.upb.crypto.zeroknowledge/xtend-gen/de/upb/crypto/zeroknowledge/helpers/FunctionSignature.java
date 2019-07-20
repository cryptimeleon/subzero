package de.upb.crypto.zeroknowledge.helpers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class FunctionSignature {
  private String name;
  
  private String returnType;
  
  private String[] parameterTypes;
  
  private int parameter_count;
  
  public FunctionSignature(final String name, final String type, final int parameter_count) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method type(String) is undefined for the type FunctionSignature");
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getParameterCount() {
    return this.parameter_count;
  }
  
  public String getReturnType() {
    return this.returnType;
  }
  
  public String[] getParameterTypes() {
    return this.parameterTypes;
  }
  
  public static ArrayList<String> getAllNames(final Map<String, FunctionSignature> map) {
    final ArrayList<String> names = new ArrayList<String>();
    Set<Map.Entry<String, FunctionSignature>> _entrySet = map.entrySet();
    for (final Map.Entry<String, FunctionSignature> entry : _entrySet) {
      names.add(entry.getValue().getName());
    }
    return names;
  }
}
