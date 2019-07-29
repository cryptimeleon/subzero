package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import de.upb.crypto.zeroknowledge.helpers.ModelHelper;
import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctionsHelper;
import de.upb.crypto.zeroknowledge.helpers.Type;
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
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TypeResolution {
  private static HashMap<EObject, Type> types;
  
  private static HashMap<EObject, Boolean> visited;
  
  private static Map<String, FunctionSignature> predefinedFunctionsMap;
  
  private static HashMap<String, FunctionDefinition> userFunctionsMap;
  
  private static HashMap<String, ArrayList<FunctionCall>> userFunctionCallsMap;
  
  private static HashMap<String, ArrayList<Variable>> variablesMap;
  
  private static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariablesMap;
  
  private static HashMap<String, HashMap<String, Parameter>> parametersMap;
  
  private static HashMap<String, HashMap<String, ArrayList<EObject>>> argumentsMap;
  
  public static HashMap<EObject, Type> getTypes() {
    return TypeResolution.types;
  }
  
  public static HashMap<String, FunctionDefinition> getAllUserFunctions(final Model model) {
    final HashMap<String, FunctionDefinition> userFunctions = new HashMap<String, FunctionDefinition>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      userFunctions.put(function.getName(), function);
    }
    return userFunctions;
  }
  
  public static HashMap<String, ArrayList<FunctionCall>> getAllUserFunctionCalls(final Model model) {
    final HashMap<String, ArrayList<FunctionCall>> userFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof FunctionCall)) {
        final String functionName = ((FunctionCall)node).getName();
        boolean _containsKey = TypeResolution.userFunctionsMap.containsKey(functionName);
        if (_containsKey) {
          boolean _containsKey_1 = userFunctionCalls.containsKey(functionName);
          if (_containsKey_1) {
            userFunctionCalls.get(functionName).add(((FunctionCall)node));
          } else {
            final ArrayList<FunctionCall> list = new ArrayList<FunctionCall>();
            list.add(((FunctionCall)node));
            userFunctionCalls.put(functionName, list);
          }
        }
      }
    };
    ModelMap.preorder(model.getProof(), _function);
    return userFunctionCalls;
  }
  
  public static HashMap<String, ArrayList<Variable>> getAllVariables(final Model model) {
    final HashMap<String, ArrayList<Variable>> variables = new HashMap<String, ArrayList<Variable>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      final Procedure1<EObject> _function = (EObject node) -> {
        TypeResolution.getAllVariablesHelper(node, variables);
      };
      ModelMap.preorder(function.getBody(), _function);
    }
    final Procedure1<EObject> _function_1 = (EObject node) -> {
      TypeResolution.getAllVariablesHelper(node, variables);
    };
    ModelMap.preorder(model.getProof(), _function_1);
    return variables;
  }
  
  private static void getAllVariablesHelper(final EObject node, final HashMap<String, ArrayList<Variable>> variables) {
    if ((node instanceof Variable)) {
      if ((node instanceof LocalVariable)) {
        return;
      }
      boolean _containsKey = variables.containsKey(((Variable)node).getName());
      if (_containsKey) {
        variables.get(((Variable)node).getName()).add(((Variable)node));
      } else {
        final ArrayList<Variable> list = new ArrayList<Variable>();
        list.add(((Variable)node));
        variables.put(((Variable)node).getName(), list);
      }
    }
  }
  
  public static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> getAllLocalVariables(final Model model) {
    final HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariables = new HashMap<String, HashMap<String, ArrayList<LocalVariable>>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final HashMap<String, ArrayList<LocalVariable>> functionVariables = new HashMap<String, ArrayList<LocalVariable>>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          String _name = parameter.getName();
          ArrayList<LocalVariable> _arrayList = new ArrayList<LocalVariable>();
          functionVariables.put(_name, _arrayList);
        }
        final Procedure1<EObject> _function = (EObject node) -> {
          if ((node instanceof LocalVariable)) {
            functionVariables.get(((LocalVariable)node).getName()).add(((LocalVariable)node));
          }
        };
        ModelMap.preorder(function.getBody(), _function);
        localVariables.put(function.getName(), functionVariables);
      }
    }
    return localVariables;
  }
  
  public static HashMap<String, HashMap<String, Parameter>> getAllParameters(final Model model) {
    final HashMap<String, HashMap<String, Parameter>> localParameters = new HashMap<String, HashMap<String, Parameter>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final HashMap<String, Parameter> parameters = new HashMap<String, Parameter>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          parameters.put(parameter.getName(), parameter);
        }
        localParameters.put(function.getName(), parameters);
      }
    }
    return localParameters;
  }
  
  public static HashMap<String, HashMap<String, ArrayList<EObject>>> getAllArguments(final Model model) {
    final HashMap<String, HashMap<String, ArrayList<EObject>>> arguments = new HashMap<String, HashMap<String, ArrayList<EObject>>>();
    final HashMap<String, ArrayList<String>> functionParameters = new HashMap<String, ArrayList<String>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final HashMap<String, ArrayList<EObject>> local = new HashMap<String, ArrayList<EObject>>();
        final ArrayList<String> parameters = new ArrayList<String>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          {
            final ArrayList<EObject> list = new ArrayList<EObject>();
            local.put(parameter.getName(), list);
            parameters.add(parameter.getName());
          }
        }
        arguments.put(function.getName(), local);
        functionParameters.put(function.getName(), parameters);
      }
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof FunctionCall)) {
        final String functionName = ((FunctionCall)node).getName();
        boolean _containsKey = TypeResolution.userFunctionsMap.containsKey(functionName);
        if (_containsKey) {
          int index = 0;
          final int length = functionParameters.get(functionName).size();
          final Iterator<Expression> argumentIterator = ((FunctionCall)node).getArguments().iterator();
          while ((argumentIterator.hasNext() && (index < length))) {
            {
              final EObject argument = argumentIterator.next();
              final String parameterName = functionParameters.get(functionName).get(index);
              arguments.get(functionName).get(parameterName).add(argument);
              index++;
            }
          }
        }
      }
    };
    ModelMap.preorder(model.getProof(), _function);
    return arguments;
  }
  
  public static Type topdownInference(final EObject node) {
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
          if ((bodyLabel == Type.EXPONENT)) {
            label = Type.EXPONENT;
            TypeResolution.types.put(node, label);
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
  
  public static void fillExponent(final EObject node) {
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
        TypeResolution.backpropagate(localVariable);
      }
      ArrayList<EObject> _get_1 = TypeResolution.argumentsMap.get(functionName).get(parameterName);
      for (final EObject argument : _get_1) {
        TypeResolution.fillExponent(argument);
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
      if (node instanceof Variable) {
        _matched=true;
        ArrayList<Variable> _get = TypeResolution.variablesMap.get(((Variable)node).getName());
        for (final Variable variable : _get) {
          TypeResolution.backpropagate(variable);
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
          TypeResolution.backpropagate(call);
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
  
  public static void backpropagate(final EObject node) {
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
          TypeResolution.backpropagate(parent);
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
            TypeResolution.backpropagate(parent);
          }
        }
      }
    }
    if (!_matched) {
      if (parent instanceof Brackets) {
        _matched=true;
        TypeResolution.backpropagate(parent);
      }
    }
    if (!_matched) {
      if (parent instanceof FunctionDefinition) {
        _matched=true;
        ArrayList<FunctionCall> _get_1 = TypeResolution.userFunctionCallsMap.get(((FunctionDefinition)parent).getName());
        for (final FunctionCall call : _get_1) {
          TypeResolution.backpropagate(call);
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
  }
  
  public static void labelPredefinedFunctionCall(final FunctionCall call) {
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
  
  public static void fillGroupElement(final Model model) {
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
    final Procedure1<EObject> _function = (EObject node) -> {
      TypeResolution.setGroupElement(node);
    };
    ModelMap.preorder(model.getProof(), _function);
  }
  
  public static void setGroupElement(final EObject node) {
    boolean _containsKey = TypeResolution.types.containsKey(node);
    boolean _not = (!_containsKey);
    if (_not) {
      TypeResolution.visited.put(node, Boolean.valueOf(true));
      TypeResolution.types.put(node, Type.GROUP_ELEMENT);
    }
  }
  
  public static HashMap<EObject, Type> resolveTypes(final Model model) {
    HashMap<EObject, Type> _hashMap = new HashMap<EObject, Type>();
    TypeResolution.types = _hashMap;
    HashMap<EObject, Boolean> _hashMap_1 = new HashMap<EObject, Boolean>();
    TypeResolution.visited = _hashMap_1;
    ModelHelper.identifyLocalVariables(model);
    TypeResolution.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();
    TypeResolution.userFunctionsMap = TypeResolution.getAllUserFunctions(model);
    TypeResolution.userFunctionCallsMap = TypeResolution.getAllUserFunctionCalls(model);
    TypeResolution.variablesMap = TypeResolution.getAllVariables(model);
    TypeResolution.localVariablesMap = TypeResolution.getAllLocalVariables(model);
    TypeResolution.parametersMap = TypeResolution.getAllParameters(model);
    TypeResolution.argumentsMap = TypeResolution.getAllArguments(model);
    TypeResolution.topdownInference(model);
    TypeResolution.fillGroupElement(model);
    return TypeResolution.types;
  }
}
