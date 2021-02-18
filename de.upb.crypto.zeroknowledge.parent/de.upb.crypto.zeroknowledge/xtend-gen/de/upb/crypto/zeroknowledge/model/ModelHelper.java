package de.upb.crypto.zeroknowledge.model;

import de.upb.crypto.zeroknowledge.model.FunctionSignature;
import de.upb.crypto.zeroknowledge.model.ModelMap;
import de.upb.crypto.zeroknowledge.model.ModelMapController;
import de.upb.crypto.zeroknowledge.type.Type;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness;
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList;
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessVariable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

/**
 * A helper class providing functions for working with a model
 */
@SuppressWarnings("all")
public class ModelHelper {
  public static Model getRoot(final EObject node) {
    EObject _rootContainer = EcoreUtil.getRootContainer(node);
    return ((Model) _rootContainer);
  }
  
  public static String convertToJavaName(final String name) {
    return name.replace("\'", "_prime");
  }
  
  public static void replaceParentReferenceToSelf(final EObject child_node, final EObject new_child_node) {
    final EObject parent = child_node.eContainer();
    final EStructuralFeature feature = child_node.eContainingFeature();
    Object _eGet = parent.eGet(feature);
    if ((_eGet instanceof EList)) {
      Object _eGet_1 = parent.eGet(feature);
      final EList<EObject> list = ((EList<EObject>) _eGet_1);
      list.set(list.indexOf(child_node), new_child_node);
    } else {
      parent.eSet(feature, new_child_node);
    }
  }
  
  public static void inlineFunctions(final Model model) {
    final HashMap<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      functions.put(function.getName(), function);
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      ModelHelper.replaceFunctionCallWithDefinition(node, functions);
    };
    ModelMap.postorder(model.getProof(), _function);
  }
  
  private static void _replaceFunctionCallWithDefinition(final EObject node, final HashMap<String, FunctionDefinition> functions) {
    return;
  }
  
  private static void _replaceFunctionCallWithDefinition(final FunctionCall call, final HashMap<String, FunctionDefinition> functions) {
    final FunctionDefinition definition = EcoreUtil.<FunctionDefinition>copy(functions.get(call.getName()));
    final HashMap<String, Expression> mapping = new HashMap<String, Expression>();
    final Iterator<Expression> argumentIterator = call.getArguments().iterator();
    final Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameterList().getParameters().iterator();
    while ((argumentIterator.hasNext() && parameterIterator.hasNext())) {
      {
        Expression _next = argumentIterator.next();
        final Argument argument = ((Argument) _next);
        final String parameter = parameterIterator.next().getName();
        mapping.put(parameter, argument.getExpression());
      }
    }
    final Procedure1<EObject> _function = (EObject bodyNode) -> {
      if ((bodyNode instanceof Variable)) {
        final Expression expression = EcoreUtil.<Expression>copy(mapping.get(((Variable) bodyNode).getName()));
        if ((expression != null)) {
          ModelHelper.replaceParentReferenceToSelf(bodyNode, expression);
        }
      }
    };
    ModelMap.preorder(definition.getBody(), _function);
    ModelHelper.replaceParentReferenceToSelf(call, definition.getBody());
  }
  
  public static void normalizeNegatives(final Model model) {
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof Sum)) {
        final Sum sum = ((Sum) node);
        String _operation = sum.getOperation();
        boolean _tripleEquals = (_operation == "-");
        if (_tripleEquals) {
          final Expression rightSide = sum.getRight();
          final Negative negative = ZeroKnowledgeFactory.eINSTANCE.createNegative();
          ModelHelper.replaceParentReferenceToSelf(rightSide, negative);
          negative.setTerm(rightSide);
          sum.setOperation("+");
        }
      }
    };
    ModelMap.postorder(model, _function);
  }
  
  public static void removeBrackets(final Model model) {
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof Brackets)) {
        final Brackets brackets = ((Brackets) node);
        final EObject contents = brackets.getContent();
        ModelHelper.replaceParentReferenceToSelf(brackets, contents);
      }
    };
    ModelMap.postorder(model, _function);
  }
  
  public static void identifySpecialVariables(final Model model) {
    final HashMap<String, Witness> witnesses = ModelHelper.getAllWitnesses(model);
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final ArrayList<String> parameters = new ArrayList<String>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          parameters.add(parameter.getName());
        }
        final Procedure1<EObject> _function = (EObject node) -> {
          if ((node instanceof Variable)) {
            boolean _contains = parameters.contains(((Variable)node).getName());
            if (_contains) {
              final LocalVariable local = ZeroKnowledgeFactory.eINSTANCE.createLocalVariable();
              local.setName(((Variable)node).getName());
              local.setFunction(function.getName());
              ModelHelper.replaceParentReferenceToSelf(node, local);
            } else {
              ModelHelper.identifySpecialVariablesHelper(((Variable)node), witnesses);
            }
          }
        };
        ModelMap.preorder(function.getBody(), _function);
      }
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof Variable)) {
        ModelHelper.identifySpecialVariablesHelper(((Variable)node), witnesses);
      }
    };
    ModelMap.preorder(model.getProof(), _function);
  }
  
  public static void identifySpecialVariablesHelper(final Variable variable, final HashMap<String, Witness> witnesses) {
    boolean _containsKey = witnesses.containsKey(variable.getName());
    if (_containsKey) {
      final WitnessVariable witness = ZeroKnowledgeFactory.eINSTANCE.createWitnessVariable();
      witness.setName(variable.getName());
      ModelHelper.replaceParentReferenceToSelf(variable, witness);
    }
  }
  
  public static String getArgumentFunction(final Argument argument) {
    EObject _eContainer = argument.eContainer();
    return ((FunctionCall) _eContainer).getName();
  }
  
  public static int getArgumentIndex(final Argument argument) {
    EObject _eContainer = argument.eContainer();
    return ((FunctionCall) _eContainer).getArguments().indexOf(argument);
  }
  
  public static HashMap<String, FunctionSignature> getUserFunctionSignatures(final Model model, final HashMap<EObject, Type> types, final HashMap<EObject, Integer> tuples) {
    final HashMap<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final ArrayList<Type> parameterTypes = new ArrayList<Type>();
        final ArrayList<Integer> parameterSizes = new ArrayList<Integer>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          {
            parameterTypes.add(types.get(parameter));
            parameterSizes.add(tuples.get(parameter));
          }
        }
        String _name = function.getName();
        Type _get = types.get(function);
        Integer _get_1 = tuples.get(function);
        final FunctionSignature signature = new FunctionSignature(_name, _get, (_get_1).intValue(), ((Type[])Conversions.unwrapArray(parameterTypes, Type.class)), ((int[])Conversions.unwrapArray(parameterSizes, int.class)));
        functions.put(function.getName(), signature);
      }
    }
    return functions;
  }
  
  public static HashSet<String> getWitnessNames(final Model model) {
    final HashSet<String> witnesses = new HashSet<String>();
    EList<Witness> _witnesses = model.getWitnessList().getWitnesses();
    for (final Witness witness : _witnesses) {
      witnesses.add(witness.getName());
    }
    return witnesses;
  }
  
  public static HashMap<String, FunctionDefinition> getAllUserFunctions(final Model model) {
    final HashMap<String, FunctionDefinition> userFunctions = new HashMap<String, FunctionDefinition>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      userFunctions.put(function.getName(), function);
    }
    return userFunctions;
  }
  
  public static HashMap<String, ArrayList<FunctionCall>> getAllUserFunctionCalls(final Model model, final HashMap<String, FunctionDefinition> userFunctionsMap) {
    final HashMap<String, ArrayList<FunctionCall>> userFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof FunctionCall)) {
        final String functionName = ((FunctionCall)node).getName();
        boolean _containsKey = userFunctionsMap.containsKey(functionName);
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
        ModelHelper.getAllVariablesHelper(node, variables);
      };
      ModelMap.preorder(function.getBody(), _function);
    }
    final Procedure1<EObject> _function_1 = (EObject node) -> {
      ModelHelper.getAllVariablesHelper(node, variables);
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
  
  public static HashMap<String, Witness> getAllWitnesses(final Model model) {
    final HashMap<String, Witness> witnesses = new HashMap<String, Witness>();
    WitnessList _witnessList = model.getWitnessList();
    boolean _tripleNotEquals = (_witnessList != null);
    if (_tripleNotEquals) {
      EList<Witness> _witnesses = model.getWitnessList().getWitnesses();
      for (final Witness witness : _witnesses) {
        witnesses.put(witness.getName(), witness);
      }
    }
    return witnesses;
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
  
  public static HashMap<String, HashMap<String, ArrayList<Argument>>> getAllArguments(final Model model, final HashMap<String, FunctionDefinition> userFunctionsMap) {
    final HashMap<String, HashMap<String, ArrayList<Argument>>> arguments = new HashMap<String, HashMap<String, ArrayList<Argument>>>();
    final HashMap<String, ArrayList<String>> functionParameters = new HashMap<String, ArrayList<String>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final HashMap<String, ArrayList<Argument>> local = new HashMap<String, ArrayList<Argument>>();
        final ArrayList<String> parameters = new ArrayList<String>();
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          {
            final ArrayList<Argument> list = new ArrayList<Argument>();
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
        boolean _containsKey = userFunctionsMap.containsKey(functionName);
        if (_containsKey) {
          int index = 0;
          final int length = functionParameters.get(functionName).size();
          final Iterator<Expression> argumentIterator = ((FunctionCall)node).getArguments().iterator();
          while ((argumentIterator.hasNext() && (index < length))) {
            {
              Expression _next = argumentIterator.next();
              final Argument argument = ((Argument) _next);
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
  
  public static ArrayList<Tuple> getAllTuples(final Model model) {
    final ArrayList<Tuple> tuples = new ArrayList<Tuple>();
    ModelHelper.getAllTuplesHelper1(tuples, model);
    return tuples;
  }
  
  private static void getAllTuplesHelper1(final ArrayList<Tuple> tuples, final EObject node) {
    final Procedure2<EObject, ModelMapController> _function = (EObject child, ModelMapController controller) -> {
      if ((child instanceof Tuple)) {
        tuples.add(((Tuple)child));
        ModelHelper.getAllTuplesHelper2(tuples, child);
        controller.continueTraversal();
      }
    };
    ModelMap.preorderWithControl(node, _function);
  }
  
  private static void getAllTuplesHelper2(final ArrayList<Tuple> tuples, final EObject node) {
    final Procedure2<EObject, ModelMapController> _function = (EObject child, ModelMapController controller) -> {
      if ((child instanceof FunctionCall)) {
        ModelHelper.getAllTuplesHelper1(tuples, child);
        controller.continueTraversal();
      }
    };
    ModelMap.preorderWithControl(node, _function);
  }
  
  public static HashMap<String, ArrayList<FunctionCall>> getAllPredefinedFunctionCalls(final Model model, final HashMap<String, FunctionSignature> predefinedFunctionsMap) {
    final HashMap<String, ArrayList<FunctionCall>> predefinedFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();
    final Procedure1<EObject> _function = (EObject node) -> {
      if ((node instanceof FunctionCall)) {
        final String functionName = ((FunctionCall)node).getName();
        boolean _containsKey = predefinedFunctionsMap.containsKey(functionName);
        if (_containsKey) {
          boolean _containsKey_1 = predefinedFunctionCalls.containsKey(functionName);
          if (_containsKey_1) {
            predefinedFunctionCalls.get(functionName).add(((FunctionCall)node));
          } else {
            final ArrayList<FunctionCall> list = new ArrayList<FunctionCall>();
            list.add(((FunctionCall)node));
            predefinedFunctionCalls.put(functionName, list);
          }
        }
      }
    };
    ModelMap.preorder(model.getProof(), _function);
    return predefinedFunctionCalls;
  }
  
  private static void replaceFunctionCallWithDefinition(final EObject call, final HashMap<String, FunctionDefinition> functions) {
    if (call instanceof FunctionCall) {
      _replaceFunctionCallWithDefinition((FunctionCall)call, functions);
      return;
    } else if (call != null) {
      _replaceFunctionCallWithDefinition(call, functions);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(call, functions).toString());
    }
  }
}
