package de.upb.crypto.zeroknowledge.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class FunctionSignature {
  private String name;
  
  private String type;
  
  private int parameter_count;
  
  public FunctionSignature(final String name, final String type, final int parameter_count) {
    this.name = name;
    this.type = type;
    this.parameter_count = parameter_count;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getParameterCount() {
    return this.parameter_count;
  }
  
  public String getType() {
    return this.type;
  }
  
  public static List<String> getAllNames(final Map<String, FunctionSignature> map) {
    final List<String> names = new ArrayList<String>();
    Set<Map.Entry<String, FunctionSignature>> _entrySet = map.entrySet();
    for (final Map.Entry<String, FunctionSignature> entry : _entrySet) {
      names.add(entry.getValue().getName());
    }
    return names;
  }
}
