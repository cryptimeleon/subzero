package de.upb.crypto.zeroknowledge.helpers;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class VariableEnvironment {
  private Set<String> variables;
  
  public VariableEnvironment() {
    HashSet<String> _hashSet = new HashSet<String>();
    this.variables = _hashSet;
  }
  
  public VariableEnvironment(final Set<String> variableSet) {
    this.variables = variableSet;
  }
  
  public boolean contains(final String name) {
    return this.variables.contains(name);
  }
  
  public boolean add(final String name) {
    return this.variables.add(name);
  }
}
