package de.upb.crypto.zeroknowledge.generator;

import java.util.HashSet;

/**
 * A class to collect import statements required for
 * the generated code
 */
@SuppressWarnings("all")
public class ImportBuilder {
  private StringBuilder importBuilder;
  
  private HashSet<String> importedClasses;
  
  public ImportBuilder() {
    StringBuilder _stringBuilder = new StringBuilder();
    this.importBuilder = _stringBuilder;
  }
  
  public StringBuilder addImport() {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method append(Object) is not applicable without arguments");
  }
  
  public StringBuilder getBuilder() {
    return this.importBuilder;
  }
  
  public String getCode() {
    return this.importBuilder.toString();
  }
}
