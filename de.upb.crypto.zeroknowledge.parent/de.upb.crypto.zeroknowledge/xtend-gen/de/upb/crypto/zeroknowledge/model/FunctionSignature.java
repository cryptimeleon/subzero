package de.upb.crypto.zeroknowledge.model;

import de.upb.crypto.zeroknowledge.model.ReturnsTuple;
import de.upb.crypto.zeroknowledge.model.TupleParameters;
import de.upb.crypto.zeroknowledge.type.Type;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;

/**
 * Contains information about a function's name, return type, and parameters
 */
@SuppressWarnings("all")
public class FunctionSignature {
  private String name;
  
  private Type returnType;
  
  private int returnSize;
  
  private int parameterCount;
  
  private ArrayList<Type> parameterTypes;
  
  private ArrayList<Integer> parameterSizes;
  
  public FunctionSignature(final String name, final String returnType, final int returnSize, final String[] parameterTypes, final int[] parameterSizes) {
    this.name = name;
    this.returnType = Type.toType(returnType);
    this.returnSize = returnSize;
    this.parameterCount = parameterTypes.length;
    ArrayList<Type> _arrayList = new ArrayList<Type>();
    this.parameterTypes = _arrayList;
    for (final String parameterType : parameterTypes) {
      this.parameterTypes.add(Type.toType(parameterType));
    }
    ArrayList<Integer> _arrayList_1 = new ArrayList<Integer>((Collection<? extends Integer>)Conversions.doWrapArray(parameterSizes));
    this.parameterSizes = _arrayList_1;
  }
  
  public FunctionSignature(final String name, final Type returnType, final int returnSize, final Type[] parameterTypes, final int[] parameterSizes) {
    this.name = name;
    this.returnType = returnType;
    this.returnSize = returnSize;
    this.parameterCount = parameterTypes.length;
    ArrayList<Type> _arrayList = new ArrayList<Type>((Collection<? extends Type>)Conversions.doWrapArray(parameterTypes));
    this.parameterTypes = _arrayList;
    ArrayList<Integer> _arrayList_1 = new ArrayList<Integer>((Collection<? extends Integer>)Conversions.doWrapArray(parameterSizes));
    this.parameterSizes = _arrayList_1;
  }
  
  public FunctionSignature(final String name, final String returnType, final String[] parameterTypes) {
    this(name, returnType, 1, parameterTypes, ((int[])Conversions.unwrapArray(new ArrayList<Integer>(Collections.<Integer>nCopies(parameterTypes.length, Integer.valueOf(1))), int.class)));
  }
  
  public FunctionSignature(final String name, final Type returnType, final Type[] parameterTypes) {
    this(name, returnType, 1, parameterTypes, ((int[])Conversions.unwrapArray(new ArrayList<Integer>(Collections.<Integer>nCopies(parameterTypes.length, Integer.valueOf(1))), int.class)));
  }
  
  public FunctionSignature(final Method method) {
    this.name = method.getName();
    this.returnType = Type.toType(FunctionSignature.trimTypeName(method.getReturnType().getName()));
    final ReturnsTuple returnAnnotation = method.<ReturnsTuple>getAnnotation(ReturnsTuple.class);
    final TupleParameters parametersAnnotation = method.<TupleParameters>getAnnotation(TupleParameters.class);
    if ((returnAnnotation != null)) {
      this.returnSize = returnAnnotation.value();
    } else {
      this.returnSize = 1;
    }
    if ((parametersAnnotation != null)) {
      int[] _value = parametersAnnotation.value();
      ArrayList<Integer> _arrayList = new ArrayList<Integer>((Collection<? extends Integer>)Conversions.doWrapArray(_value));
      this.parameterSizes = _arrayList;
    } else {
      List<Integer> _nCopies = Collections.<Integer>nCopies(method.getParameterCount(), Integer.valueOf(1));
      ArrayList<Integer> _arrayList_1 = new ArrayList<Integer>(_nCopies);
      this.parameterSizes = _arrayList_1;
    }
    this.parameterCount = ((List<Class<?>>)Conversions.doWrapArray(method.getParameterTypes())).size();
    ArrayList<Type> _arrayList_2 = new ArrayList<Type>();
    this.parameterTypes = _arrayList_2;
    Class<?>[] _parameterTypes = method.getParameterTypes();
    for (final Class<?> classObject : _parameterTypes) {
      this.parameterTypes.add(Type.toType(FunctionSignature.trimTypeName(classObject.getName())));
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
  
  public int getReturnSize() {
    return this.returnSize;
  }
  
  public boolean returnsTuple() {
    return (this.returnSize > 1);
  }
  
  public ArrayList<Type> getParameterTypes() {
    return this.parameterTypes;
  }
  
  public ArrayList<Integer> getParameterSizes() {
    return this.parameterSizes;
  }
  
  private static String trimTypeName(final String type) {
    final int periodIndex = type.lastIndexOf(".");
    if ((periodIndex > 0)) {
      return type.substring((periodIndex + 1));
    }
    return type;
  }
}
