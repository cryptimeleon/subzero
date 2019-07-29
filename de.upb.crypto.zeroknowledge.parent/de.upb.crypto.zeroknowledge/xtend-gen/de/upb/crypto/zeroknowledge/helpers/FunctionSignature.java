package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.Type;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class FunctionSignature {
  private String name;
  
  private Type returnType;
  
  private ArrayList<Type> parameterTypes;
  
  private int parameterCount;
  
  public FunctionSignature(final String name, final String returnType, final int parameterCount, final String[] parameterTypes) {
    this.name = name;
    this.returnType = Type.convert(returnType);
    this.parameterCount = parameterCount;
    ArrayList<Type> _arrayList = new ArrayList<Type>();
    this.parameterTypes = _arrayList;
    for (final String parameterType : parameterTypes) {
      this.parameterTypes.add(Type.convert(parameterType));
    }
  }
  
  public FunctionSignature(final String name, final Type returnType, final int parameterCount, final Type[] parameterTypes) {
    this.name = name;
    this.returnType = returnType;
    this.parameterCount = parameterCount;
    ArrayList<Type> _arrayList = new ArrayList<Type>((Collection<? extends Type>)Conversions.doWrapArray(parameterTypes));
    this.parameterTypes = _arrayList;
  }
  
  public FunctionSignature(final Method method) {
    this.name = method.getName();
    this.returnType = Type.convert(FunctionSignature.trimTypeName(method.getReturnType().getName()));
    this.parameterCount = ((List<Class<?>>)Conversions.doWrapArray(method.getParameterTypes())).size();
    ArrayList<Type> _arrayList = new ArrayList<Type>();
    this.parameterTypes = _arrayList;
    Class<?>[] _parameterTypes = method.getParameterTypes();
    for (final Class<?> classObject : _parameterTypes) {
      this.parameterTypes.add(Type.convert(FunctionSignature.trimTypeName(classObject.getName())));
    }
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getParameterCount() {
    return this.parameterCount;
  }
  
  public Type getReturnType() {
    return this.returnType;
  }
  
  public ArrayList<Type> getParameterTypes() {
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
  
  private static String trimTypeName(final String type) {
    final int periodIndex = type.lastIndexOf(".");
    if ((periodIndex > 0)) {
      return type.substring((periodIndex + 1));
    }
    return type;
  }
}
