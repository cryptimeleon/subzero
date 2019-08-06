package de.upb.crypto.zeroknowledge.type;

import de.upb.crypto.zeroknowledge.model.FunctionSignature;
import de.upb.crypto.zeroknowledge.model.ModelHelper;
import de.upb.crypto.zeroknowledge.model.ModelMap;
import de.upb.crypto.zeroknowledge.predefined.PredefinedFunctionsHelper;
import de.upb.crypto.zeroknowledge.type.Type;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product;
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness;
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TypeResolution {
  private static HashMap<EObject, Type> types;
  
  private static HashMap<EObject, Integer> sizes;
  
  private static HashMap<EObject, Boolean> visited;
  
  private static HashMap<String, FunctionSignature> predefinedFunctionsMap;
  
  private static HashMap<String, ArrayList<FunctionCall>> predefinedFunctionCallsMap;
  
  private static HashMap<String, FunctionDefinition> userFunctionsMap;
  
  private static HashMap<String, ArrayList<FunctionCall>> userFunctionCallsMap;
  
  private static HashMap<String, ArrayList<Variable>> variablesMap;
  
  private static HashMap<String, Witness> witnessesMap;
  
  private static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariablesMap;
  
  private static HashMap<String, HashMap<String, Parameter>> parametersMap;
  
  private static HashMap<String, HashMap<String, ArrayList<Argument>>> argumentsMap;
  
  public static HashMap<EObject, Type> getTypes() {
    return TypeResolution.types;
  }
  
  public static HashMap<EObject, Integer> getSizes() {
    return TypeResolution.sizes;
  }
  
  private static Type topdownInference(final EObject node) {
    Type label = null;
    boolean _containsKey = TypeResolution.types.containsKey(node);
    if (_containsKey) {
      return TypeResolution.types.get(node);
    }
    TypeResolution.visited.put(node, Boolean.valueOf(true));
    boolean _matched = false;
    if (node instanceof Model) {
      _matched=true;
      return TypeResolution.topdownInference(((Model)node).getProof());
    }
    if (!_matched) {
      if (node instanceof Conjunction) {
        _matched=true;
        label = Type.BOOLEAN;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof Disjunction) {
        _matched=true;
        label = Type.BOOLEAN;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof Comparison) {
        _matched=true;
        label = Type.BOOLEAN;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof NumberLiteral) {
        _matched=true;
        label = Type.EXPONENT;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof StringLiteral) {
        _matched=true;
        label = Type.STRING;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof Sum) {
        _matched=true;
        label = Type.EXPONENT;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      if (node instanceof Negative) {
        _matched=true;
        label = Type.EXPONENT;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
    }
    boolean _matched_1 = false;
    if (node instanceof Sum) {
      _matched_1=true;
      TypeResolution.fillExponent(((Sum)node).getLeft());
      TypeResolution.fillExponent(((Sum)node).getRight());
    }
    if (!_matched_1) {
      if (node instanceof Power) {
        _matched_1=true;
        TypeResolution.fillExponent(((Power)node).getRight());
      }
    }
    if (!_matched_1) {
      if (node instanceof Negative) {
        _matched_1=true;
        TypeResolution.fillExponent(((Negative)node).getTerm());
      }
    }
    if (!_matched_1) {
    }
    boolean _matched_2 = false;
    if (node instanceof Power) {
      _matched_2=true;
      final Type leftChildLabel = TypeResolution.topdownInference(((Power)node).getLeft());
      label = leftChildLabel;
      if ((leftChildLabel != Type.UNKNOWN)) {
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched_2) {
      if (node instanceof Brackets) {
        _matched_2=true;
        final Type childLabel = TypeResolution.topdownInference(((Brackets)node).getContent());
        label = childLabel;
        if ((childLabel != Type.UNKNOWN)) {
          TypeResolution.types.put(node, label);
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof Product) {
        _matched_2=true;
        final Type leftChildLabel = TypeResolution.topdownInference(((Product)node).getLeft());
        final Type rightChildLabel = TypeResolution.topdownInference(((Product)node).getRight());
        if (((leftChildLabel == Type.EXPONENT) && (rightChildLabel == Type.EXPONENT))) {
          label = Type.EXPONENT;
          TypeResolution.types.put(node, label);
        } else {
          if (((leftChildLabel == Type.EXPONENT) && (rightChildLabel == Type.UNKNOWN))) {
            label = Type.EXPONENT;
            TypeResolution.types.put(node, label);
            TypeResolution.fillExponent(((Product)node).getRight());
          } else {
            if (((leftChildLabel == Type.UNKNOWN) && (rightChildLabel == Type.EXPONENT))) {
              label = Type.EXPONENT;
              TypeResolution.types.put(node, label);
              TypeResolution.fillExponent(((Product)node).getLeft());
            }
          }
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof Comparison) {
        _matched_2=true;
        final Type leftChildLabel = TypeResolution.topdownInference(((Comparison)node).getLeft());
        final Type rightChildLabel = TypeResolution.topdownInference(((Comparison)node).getRight());
        if (((leftChildLabel == Type.EXPONENT) && (rightChildLabel == Type.UNKNOWN))) {
          TypeResolution.fillExponent(((Comparison)node).getRight());
        } else {
          if (((leftChildLabel == Type.UNKNOWN) && (rightChildLabel == Type.EXPONENT))) {
            TypeResolution.fillExponent(((Comparison)node).getLeft());
          }
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof FunctionCall) {
        _matched_2=true;
        final String functionName = ((FunctionCall)node).getName();
        boolean _containsKey_1 = TypeResolution.userFunctionsMap.containsKey(functionName);
        if (_containsKey_1) {
          final FunctionDefinition function = TypeResolution.userFunctionsMap.get(functionName);
          final Type bodyLabel = TypeResolution.topdownInference(function);
          if ((bodyLabel != Type.UNKNOWN)) {
            label = bodyLabel;
            TypeResolution.types.put(node, label);
          }
          EList<Expression> _arguments = ((FunctionCall)node).getArguments();
          for (final Expression argument : _arguments) {
            TypeResolution.topdownInference(argument);
          }
        } else {
          boolean _containsKey_2 = TypeResolution.predefinedFunctionsMap.containsKey(functionName);
          if (_containsKey_2) {
            TypeResolution.labelPredefinedFunctionCall(((FunctionCall)node));
          }
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof Argument) {
        _matched_2=true;
        final Type childLabel = TypeResolution.topdownInference(((Argument)node).getExpression());
        label = childLabel;
        if ((childLabel != Type.UNKNOWN)) {
          TypeResolution.types.put(node, childLabel);
          if ((childLabel == Type.EXPONENT)) {
            final String functionName = ModelHelper.getArgumentFunction(((Argument)node));
            boolean _containsKey_1 = TypeResolution.userFunctionsMap.containsKey(functionName);
            if (_containsKey_1) {
              final EList<Parameter> parameters = TypeResolution.userFunctionsMap.get(functionName).getParameterList().getParameters();
              final int index = ModelHelper.getArgumentIndex(((Argument)node));
              int _size = parameters.size();
              boolean _lessThan = (index < _size);
              if (_lessThan) {
                TypeResolution.fillExponent(parameters.get(index));
              }
            }
          }
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof FunctionDefinition) {
        _matched_2=true;
        label = TypeResolution.topdownInference(((FunctionDefinition)node).getBody());
        if ((label != Type.UNKNOWN)) {
          TypeResolution.types.put(node, label);
        }
      }
    }
    if (!_matched_2) {
      if (node instanceof Tuple) {
        _matched_2=true;
        boolean labeled = false;
        final Iterator<Expression> elementIterator = ((Tuple)node).getElements().iterator();
        while ((elementIterator.hasNext() && (!labeled))) {
          {
            final EObject nextElement = elementIterator.next();
            final Type elementLabel = TypeResolution.topdownInference(nextElement);
            if ((elementLabel == Type.EXPONENT)) {
              label = elementLabel;
              TypeResolution.types.put(node, elementLabel);
              labeled = true;
              EList<Expression> _elements = ((Tuple)node).getElements();
              for (final EObject element : _elements) {
                TypeResolution.fillExponent(element);
              }
            }
          }
        }
      }
    }
    if (!_matched_2) {
      EList<EObject> _eContents = node.eContents();
      for (final EObject child : _eContents) {
        TypeResolution.topdownInference(child);
      }
    }
    Type _elvis = null;
    if (label != null) {
      _elvis = label;
    } else {
      _elvis = Type.UNKNOWN;
    }
    return _elvis;
  }
  
  private static void fillExponent(final EObject node) {
    boolean _containsKey = TypeResolution.types.containsKey(node);
    if (_containsKey) {
      return;
    }
    TypeResolution.visited.put(node, Boolean.valueOf(true));
    TypeResolution.types.put(node, Type.EXPONENT);
    boolean _matched = false;
    if (node instanceof Parameter) {
      _matched=true;
      EObject _eContainer = ((Parameter)node).eContainer().eContainer();
      final String functionName = ((FunctionDefinition) _eContainer).getName();
      final String parameterName = ((Parameter)node).getName();
      ArrayList<LocalVariable> _get = TypeResolution.localVariablesMap.get(functionName).get(parameterName);
      for (final LocalVariable localVariable : _get) {
        TypeResolution.backpropagateType(localVariable);
      }
      ArrayList<Argument> _get_1 = TypeResolution.argumentsMap.get(functionName).get(parameterName);
      for (final EObject argument : _get_1) {
        TypeResolution.fillExponent(argument);
      }
    }
    if (!_matched) {
      if (node instanceof Witness) {
        _matched=true;
        ArrayList<Variable> _get = TypeResolution.variablesMap.get(((Witness)node).getName());
        for (final Variable variable : _get) {
          TypeResolution.backpropagateType(variable);
        }
      }
    }
    if (!_matched) {
      if (node instanceof LocalVariable) {
        _matched=true;
        final String functionName = ((LocalVariable)node).getFunction();
        final String localName = ((LocalVariable)node).getName();
        final Parameter parameter = TypeResolution.parametersMap.get(functionName).get(localName);
        TypeResolution.fillExponent(parameter);
      }
    }
    if (!_matched) {
      if (node instanceof WitnessVariable) {
        _matched=true;
        TypeResolution.fillExponent(TypeResolution.witnessesMap.get(((WitnessVariable)node).getName()));
      }
    }
    if (!_matched) {
      if (node instanceof Variable) {
        _matched=true;
        ArrayList<Variable> _get = TypeResolution.variablesMap.get(((Variable)node).getName());
        for (final Variable variable : _get) {
          TypeResolution.backpropagateType(variable);
        }
      }
    }
    if (!_matched) {
      if (node instanceof FunctionCall) {
        _matched=true;
        boolean _containsKey_1 = TypeResolution.userFunctionsMap.containsKey(((FunctionCall)node).getName());
        if (_containsKey_1) {
          TypeResolution.fillExponent(TypeResolution.userFunctionsMap.get(((FunctionCall)node).getName()));
        }
      }
    }
    if (!_matched) {
      if (node instanceof FunctionDefinition) {
        _matched=true;
        ArrayList<FunctionCall> _get = TypeResolution.userFunctionCallsMap.get(((FunctionDefinition)node).getName());
        for (final FunctionCall call : _get) {
          TypeResolution.backpropagateType(call);
        }
        TypeResolution.fillExponent(((FunctionDefinition)node).getBody());
      }
    }
    if (!_matched) {
      EList<EObject> _eContents = node.eContents();
      for (final EObject child : _eContents) {
        TypeResolution.fillExponent(child);
      }
    }
  }
  
  private static void backpropagateType(final EObject node) {
    boolean _containsKey = TypeResolution.types.containsKey(node);
    if (_containsKey) {
      return;
    }
    TypeResolution.visited.put(node, Boolean.valueOf(true));
    TypeResolution.types.put(node, Type.EXPONENT);
    final EObject parent = node.eContainer();
    if ((parent instanceof Model)) {
      return;
    }
    Type _get = TypeResolution.types.get(parent);
    boolean _tripleEquals = (_get == Type.EXPONENT);
    if (_tripleEquals) {
      return;
    }
    boolean _containsKey_1 = TypeResolution.visited.containsKey(parent);
    boolean _not = (!_containsKey_1);
    if (_not) {
      return;
    }
    boolean _matched = false;
    if (parent instanceof Comparison) {
      _matched=true;
      final String feature = node.eContainmentFeature().getName();
      if ((feature == "left")) {
        TypeResolution.fillExponent(((Comparison)parent).getRight());
      } else {
        if ((feature == "right")) {
          TypeResolution.fillExponent(((Comparison)parent).getLeft());
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Product) {
        _matched=true;
        boolean _containsKey_2 = TypeResolution.types.containsKey(parent);
        boolean _not_1 = (!_containsKey_2);
        if (_not_1) {
          final String feature = node.eContainmentFeature().getName();
          if ((feature == "left")) {
            TypeResolution.fillExponent(((Product)parent).getRight());
          } else {
            if ((feature == "right")) {
              TypeResolution.fillExponent(((Product)parent).getLeft());
            }
          }
          TypeResolution.backpropagateType(parent);
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Power) {
        _matched=true;
        boolean _containsKey_2 = TypeResolution.types.containsKey(parent);
        boolean _not_1 = (!_containsKey_2);
        if (_not_1) {
          final String feature = node.eContainmentFeature().getName();
          if ((feature == "left")) {
            TypeResolution.fillExponent(((Power)parent).getRight());
            TypeResolution.backpropagateType(parent);
          }
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Brackets) {
        _matched=true;
        TypeResolution.backpropagateType(parent);
      }
    }
    if (!_matched) {
      if (parent instanceof FunctionDefinition) {
        _matched=true;
        ArrayList<FunctionCall> _get_1 = TypeResolution.userFunctionCallsMap.get(((FunctionDefinition)parent).getName());
        for (final FunctionCall call : _get_1) {
          TypeResolution.backpropagateType(call);
        }
      }
    }
    if (!_matched) {
      if (parent instanceof FunctionCall) {
        _matched=true;
        boolean _containsKey_2 = TypeResolution.userFunctionsMap.containsKey(((FunctionCall)parent).getName());
        if (_containsKey_2) {
          final int index = ((FunctionCall)parent).getArguments().indexOf(node);
          final FunctionDefinition function = TypeResolution.userFunctionsMap.get(((FunctionCall)parent).getName());
          int _size = function.getParameterList().getParameters().size();
          boolean _greaterThan = (_size > index);
          if (_greaterThan) {
            TypeResolution.fillExponent(function.getParameterList().getParameters().get(index));
          }
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Argument) {
        _matched=true;
        TypeResolution.backpropagateType(parent);
      }
    }
    if (!_matched) {
      if (parent instanceof Tuple) {
        _matched=true;
        boolean _containsKey_2 = TypeResolution.types.containsKey(parent);
        boolean _not_1 = (!_containsKey_2);
        if (_not_1) {
          EList<Expression> _elements = ((Tuple)parent).getElements();
          for (final EObject element : _elements) {
            TypeResolution.fillExponent(element);
          }
          TypeResolution.backpropagateType(parent);
        }
      }
    }
  }
  
  private static void labelPredefinedFunctionCall(final FunctionCall call) {
    final FunctionSignature signature = TypeResolution.predefinedFunctionsMap.get(call.getName());
    TypeResolution.visited.put(call, Boolean.valueOf(true));
    TypeResolution.types.put(call, signature.getReturnType());
    final Iterator<Type> parameterTypeIterator = signature.getParameterTypes().iterator();
    final Iterator<Expression> argumentIterator = call.getArguments().iterator();
    while ((parameterTypeIterator.hasNext() && argumentIterator.hasNext())) {
      {
        final Type parameterType = parameterTypeIterator.next();
        final EObject argument = argumentIterator.next();
        if (parameterType != null) {
          switch (parameterType) {
            case STRING:
              TypeResolution.visited.put(argument, Boolean.valueOf(true));
              TypeResolution.types.put(argument, Type.STRING);
              break;
            case EXPONENT:
              TypeResolution.fillExponent(argument);
              break;
            case GROUP_ELEMENT:
              TypeResolution.topdownInference(argument);
              break;
            default:
              break;
          }
        } else {
        }
      }
    }
  }
  
  private static void fillGroupElement(final Model model) {
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        TypeResolution.setGroupElement(function);
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          TypeResolution.setGroupElement(parameter);
        }
        final Procedure1<EObject> _function = (EObject node) -> {
          TypeResolution.setGroupElement(node);
        };
        ModelMap.preorder(function.getBody(), _function);
      }
    }
    EList<Witness> _witnesses = model.getWitnessList().getWitnesses();
    for (final Witness witness : _witnesses) {
      TypeResolution.setGroupElement(witness);
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      TypeResolution.setGroupElement(node);
    };
    ModelMap.preorder(model.getProof(), _function);
  }
  
  private static void setGroupElement(final EObject node) {
    boolean _containsKey = TypeResolution.types.containsKey(node);
    boolean _not = (!_containsKey);
    if (_not) {
      TypeResolution.visited.put(node, Boolean.valueOf(true));
      TypeResolution.types.put(node, Type.GROUP_ELEMENT);
    }
  }
  
  private static void backpropagateSize(final EObject node, final int size) {
    boolean _containsKey = TypeResolution.sizes.containsKey(node);
    if (_containsKey) {
      return;
    }
    TypeResolution.sizes.put(node, Integer.valueOf(size));
    final EObject parent = node.eContainer();
    if ((((parent instanceof Model) || 
      (parent instanceof Conjunction)) || 
      (parent instanceof Disjunction))) {
      return;
    }
    boolean _matched = false;
    if (parent instanceof Comparison) {
      _matched=true;
      boolean _containsKey_1 = TypeResolution.sizes.containsKey(((Comparison)parent).getLeft());
      boolean _not = (!_containsKey_1);
      if (_not) {
        TypeResolution.fillSize(((Comparison)parent).getLeft(), size);
      }
      boolean _containsKey_2 = TypeResolution.sizes.containsKey(((Comparison)parent).getRight());
      boolean _not_1 = (!_containsKey_2);
      if (_not_1) {
        TypeResolution.fillSize(((Comparison)parent).getRight(), size);
      }
    }
    if (!_matched) {
      if (parent instanceof Brackets) {
        _matched=true;
        TypeResolution.backpropagateSize(parent, size);
      }
    }
    if (!_matched) {
      if (parent instanceof Negative) {
        _matched=true;
        TypeResolution.backpropagateSize(parent, size);
      }
    }
    if (!_matched) {
      if (parent instanceof Sum) {
        _matched=true;
        boolean _containsKey_1 = TypeResolution.sizes.containsKey(((Sum)parent).getLeft());
        boolean _not = (!_containsKey_1);
        if (_not) {
          TypeResolution.fillSize(((Sum)parent).getLeft(), size);
        }
        boolean _containsKey_2 = TypeResolution.sizes.containsKey(((Sum)parent).getRight());
        boolean _not_1 = (!_containsKey_2);
        if (_not_1) {
          TypeResolution.fillSize(((Sum)parent).getRight(), size);
        }
        TypeResolution.backpropagateSize(parent, size);
      }
    }
    if (!_matched) {
      if (parent instanceof Product) {
        _matched=true;
        Type _get = TypeResolution.types.get(parent);
        boolean _tripleEquals = (_get == Type.GROUP_ELEMENT);
        if (_tripleEquals) {
          boolean _containsKey_1 = TypeResolution.sizes.containsKey(((Product)parent).getLeft());
          boolean _not = (!_containsKey_1);
          if (_not) {
            TypeResolution.fillSize(((Product)parent).getLeft(), size);
          }
          boolean _containsKey_2 = TypeResolution.sizes.containsKey(((Product)parent).getRight());
          boolean _not_1 = (!_containsKey_2);
          if (_not_1) {
            TypeResolution.fillSize(((Product)parent).getRight(), size);
          }
        }
        TypeResolution.backpropagateSize(parent, size);
      }
    }
    if (!_matched) {
      if (parent instanceof Power) {
        _matched=true;
        String _name = node.eContainmentFeature().getName();
        boolean _tripleEquals = (_name == "left");
        if (_tripleEquals) {
          TypeResolution.backpropagateSize(parent, size);
        }
      }
    }
    if (!_matched) {
      if (parent instanceof FunctionDefinition) {
        _matched=true;
        TypeResolution.sizes.put(parent, Integer.valueOf(size));
        ArrayList<FunctionCall> _get = TypeResolution.userFunctionCallsMap.get(((FunctionDefinition)parent).getName());
        for (final FunctionCall call : _get) {
          TypeResolution.backpropagateSize(call, size);
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Argument) {
        _matched=true;
        final String functionName = ModelHelper.getArgumentFunction(((Argument)parent));
        TypeResolution.sizes.put(parent, Integer.valueOf(size));
        boolean _containsKey_1 = TypeResolution.userFunctionsMap.containsKey(functionName);
        if (_containsKey_1) {
          final EList<Parameter> parameters = TypeResolution.userFunctionsMap.get(functionName).getParameterList().getParameters();
          final int index = ModelHelper.getArgumentIndex(((Argument)parent));
          int _size = parameters.size();
          boolean _lessThan = (index < _size);
          if (_lessThan) {
            TypeResolution.fillSize(parameters.get(index), size);
          }
        }
      }
    }
  }
  
  private static void fillSize(final EObject node, final int size) {
    boolean _containsKey = TypeResolution.sizes.containsKey(node);
    if (_containsKey) {
      return;
    }
    TypeResolution.sizes.put(node, Integer.valueOf(size));
    if ((node instanceof Tuple)) {
      return;
    }
    boolean _matched = false;
    if (node instanceof Parameter) {
      _matched=true;
      EObject _eContainer = ((Parameter)node).eContainer().eContainer();
      final String functionName = ((FunctionDefinition) _eContainer).getName();
      final String parameterName = ((Parameter)node).getName();
      ArrayList<LocalVariable> _get = TypeResolution.localVariablesMap.get(functionName).get(parameterName);
      for (final LocalVariable localVariable : _get) {
        TypeResolution.backpropagateSize(localVariable, size);
      }
      ArrayList<Argument> _get_1 = TypeResolution.argumentsMap.get(functionName).get(parameterName);
      for (final Argument argument : _get_1) {
        TypeResolution.fillSize(argument, size);
      }
    }
    if (!_matched) {
      if (node instanceof Witness) {
        _matched=true;
        ArrayList<Variable> _get = TypeResolution.variablesMap.get(((Witness)node).getName());
        for (final Variable variable : _get) {
          TypeResolution.backpropagateSize(variable, size);
        }
      }
    }
    if (!_matched) {
      if (node instanceof WitnessVariable) {
        _matched=true;
        TypeResolution.fillSize(TypeResolution.witnessesMap.get(((WitnessVariable)node).getName()), size);
      }
    }
    if (!_matched) {
      if (node instanceof LocalVariable) {
        _matched=true;
        TypeResolution.fillSize(TypeResolution.parametersMap.get(((LocalVariable)node).getFunction()).get(((LocalVariable)node).getName()), size);
      }
    }
    if (!_matched) {
      if (node instanceof Variable) {
        _matched=true;
        ArrayList<Variable> _get = TypeResolution.variablesMap.get(((Variable)node).getName());
        for (final Variable variable : _get) {
          TypeResolution.backpropagateSize(variable, size);
        }
      }
    }
    if (!_matched) {
      if (node instanceof Product) {
        _matched=true;
        Type _get = TypeResolution.types.get(node);
        boolean _tripleEquals = (_get == Type.GROUP_ELEMENT);
        if (_tripleEquals) {
          TypeResolution.fillSize(((Product)node).getLeft(), size);
          TypeResolution.fillSize(((Product)node).getRight(), size);
        }
      }
    }
    if (!_matched) {
      if (node instanceof Power) {
        _matched=true;
        TypeResolution.fillSize(((Power)node).getLeft(), size);
      }
    }
    if (!_matched) {
      if (node instanceof FunctionCall) {
        _matched=true;
        boolean _containsKey_1 = TypeResolution.userFunctionsMap.containsKey(((FunctionCall)node).getName());
        if (_containsKey_1) {
          TypeResolution.fillSize(TypeResolution.userFunctionsMap.get(((FunctionCall)node).getName()), size);
        }
      }
    }
    if (!_matched) {
      if (node instanceof FunctionDefinition) {
        _matched=true;
        ArrayList<FunctionCall> _get = TypeResolution.userFunctionCallsMap.get(((FunctionDefinition)node).getName());
        for (final FunctionCall call : _get) {
          TypeResolution.backpropagateSize(call, size);
        }
        TypeResolution.fillSize(((FunctionDefinition)node).getBody(), size);
      }
    }
    if (!_matched) {
      EList<EObject> _eContents = node.eContents();
      for (final EObject child : _eContents) {
        TypeResolution.fillSize(child, size);
      }
    }
  }
  
  private static void fillDefaults(final Model model) {
    final Procedure1<EObject> _function = (EObject node) -> {
      TypeResolution.fillDefaultsHelper(node);
    };
    ModelMap.preorder(model.getProof(), _function);
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        TypeResolution.sizes.putIfAbsent(function, Integer.valueOf(1));
        final Procedure1<EObject> _function_1 = (EObject node) -> {
          TypeResolution.fillDefaultsHelper(node);
        };
        ModelMap.preorder(function.getBody(), _function_1);
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          TypeResolution.sizes.putIfAbsent(parameter, Integer.valueOf(1));
        }
      }
    }
    EList<Witness> _witnesses = model.getWitnessList().getWitnesses();
    for (final Witness witness : _witnesses) {
      TypeResolution.sizes.putIfAbsent(witness, Integer.valueOf(1));
    }
  }
  
  public static void fillDefaultsHelper(final EObject node) {
    if (((((node instanceof Conjunction) || 
      (node instanceof Disjunction)) || 
      (node instanceof Comparison)) || 
      (node instanceof StringLiteral))) {
      TypeResolution.sizes.putIfAbsent(node, Integer.valueOf(0));
    } else {
      TypeResolution.sizes.putIfAbsent(node, Integer.valueOf(1));
    }
  }
  
  public static void resolveTypes(final Model model) {
    HashMap<EObject, Type> _hashMap = new HashMap<EObject, Type>();
    TypeResolution.types = _hashMap;
    HashMap<EObject, Integer> _hashMap_1 = new HashMap<EObject, Integer>();
    TypeResolution.sizes = _hashMap_1;
    HashMap<EObject, Boolean> _hashMap_2 = new HashMap<EObject, Boolean>();
    TypeResolution.visited = _hashMap_2;
    ModelHelper.removeBrackets(model);
    ModelHelper.normalizeNegatives(model);
    ModelHelper.identifySpecialVariables(model);
    TypeResolution.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();
    TypeResolution.predefinedFunctionCallsMap = ModelHelper.getAllPredefinedFunctionCalls(model, TypeResolution.predefinedFunctionsMap);
    TypeResolution.userFunctionsMap = ModelHelper.getAllUserFunctions(model);
    TypeResolution.userFunctionCallsMap = ModelHelper.getAllUserFunctionCalls(model, TypeResolution.userFunctionsMap);
    TypeResolution.variablesMap = ModelHelper.getAllVariables(model);
    TypeResolution.witnessesMap = ModelHelper.getAllWitnesses(model);
    TypeResolution.localVariablesMap = ModelHelper.getAllLocalVariables(model);
    TypeResolution.parametersMap = ModelHelper.getAllParameters(model);
    TypeResolution.argumentsMap = ModelHelper.getAllArguments(model, TypeResolution.userFunctionsMap);
    final ArrayList<Tuple> tupleNodes = ModelHelper.getAllTuples(model);
    TypeResolution.topdownInference(model);
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      TypeResolution.topdownInference(function.getBody());
    }
    TypeResolution.fillGroupElement(model);
    for (final Tuple tuple : tupleNodes) {
      TypeResolution.backpropagateSize(tuple, tuple.getElements().size());
    }
    Set<String> _keySet = TypeResolution.predefinedFunctionCallsMap.keySet();
    for (final String predefinedFunctionName : _keySet) {
      {
        final FunctionSignature signature = TypeResolution.predefinedFunctionsMap.get(predefinedFunctionName);
        final int returnSize = signature.getReturnSize();
        final ArrayList<Integer> parameterSizes = signature.getParameterSizes();
        if ((returnSize > 1)) {
          ArrayList<FunctionCall> _get = TypeResolution.predefinedFunctionCallsMap.get(predefinedFunctionName);
          for (final FunctionCall call : _get) {
            TypeResolution.backpropagateSize(call, returnSize);
          }
        }
        ArrayList<FunctionCall> _get_1 = TypeResolution.predefinedFunctionCallsMap.get(predefinedFunctionName);
        for (final FunctionCall call_1 : _get_1) {
          {
            final Iterator<Expression> argumentsIterator = call_1.getArguments().iterator();
            final Iterator<Integer> parameterSizesIterator = parameterSizes.iterator();
            while ((argumentsIterator.hasNext() && parameterSizesIterator.hasNext())) {
              {
                final EObject argument = argumentsIterator.next();
                final int parameterSize = (parameterSizesIterator.next()).intValue();
                TypeResolution.fillSize(argument, parameterSize);
              }
            }
          }
        }
      }
    }
    TypeResolution.fillDefaults(model);
    return;
  }
}
