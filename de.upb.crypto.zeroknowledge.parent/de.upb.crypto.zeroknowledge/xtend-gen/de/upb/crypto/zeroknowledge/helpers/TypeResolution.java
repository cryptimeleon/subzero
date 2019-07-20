package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.helpers.ModelMapControl;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctionsHelper;
import de.upb.crypto.zeroknowledge.helpers.Type;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

@SuppressWarnings("all")
public class TypeResolution {
  private static HashMap<EObject, Type> types;
  
  private static HashMap<String, FunctionDefinition> userFunctions;
  
  private static Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
  
  public static HashMap<EObject, Type> getTypes() {
    return TypeResolution.types;
  }
  
  public static void fill(final FunctionCall call, final Type type) {
    final FunctionDefinition function = TypeResolution.userFunctions.get(call.getName());
    if (((function != null) && (TypeResolution.types.get(function) == null))) {
      TypeResolution.fill(function, type);
    }
  }
  
  public static void fill(final EObject node, final Type type) {
    TypeResolution.types.put(node, type);
  }
  
  protected static void _resolveTypes(final EObject object, final ModelMapControl controller) {
    return;
  }
  
  public static void resolveTypes(final Model model) {
    final Procedure2<EObject, ModelMapControl> _function = (EObject node, ModelMapControl controller) -> {
      TypeResolution.resolveTypes(node, controller);
    };
    ModelMap.preorderWithControl(model.getProof(), _function);
  }
  
  protected static void _resolveTypes(final Conjunction conjunction, final ModelMapControl controller) {
    TypeResolution.types.put(conjunction, Type.BOOLEAN);
  }
  
  protected static void _resolveTypes(final Disjunction disjunction, final ModelMapControl controller) {
    TypeResolution.types.put(disjunction, Type.BOOLEAN);
  }
  
  protected static void _resolveTypes(final Comparison comparison, final ModelMapControl controller) {
    TypeResolution.types.put(comparison, Type.BOOLEAN);
  }
  
  protected static void _resolveTypes(final Sum sum, final ModelMapControl controller) {
    TypeResolution.types.put(sum, Type.EXPONENT);
    controller.continueTraversal();
    final Procedure1<EObject> _function = (EObject node) -> {
      TypeResolution.types.put(node, Type.EXPONENT);
    };
    ModelMap.preorder(sum.getLeft(), _function);
    final Procedure1<EObject> _function_1 = (EObject node) -> {
      TypeResolution.types.put(node, Type.EXPONENT);
    };
    ModelMap.preorder(sum.getRight(), _function_1);
  }
  
  protected static void _resolveTypes(final Product product, final ModelMapControl controller) {
  }
  
  protected static void _resolveTypes(final Power power, final ModelMapControl controller) {
  }
  
  protected static void _resolveTypes(final StringLiteral stringLiteral, final ModelMapControl controller) {
    TypeResolution.types.put(stringLiteral, Type.STRING);
  }
  
  protected static void _resolveTypes(final Tuple tuple, final ModelMapControl controller) {
  }
  
  protected static void _resolveTypes(final Negative negative, final ModelMapControl controller) {
    TypeResolution.types.put(negative, Type.EXPONENT);
    controller.continueTraversal();
    final Procedure2<EObject, ModelMapControl> _function = (EObject node, ModelMapControl control) -> {
      TypeResolution.types.put(node, Type.EXPONENT);
    };
    ModelMap.preorderWithControl(negative.getTerm(), _function);
  }
  
  protected static void _resolveTypes(final NumberLiteral numberLiteral, final ModelMapControl controller) {
    TypeResolution.types.put(numberLiteral, Type.EXPONENT);
  }
  
  protected static void _resolveTypes(final FunctionCall call, final ModelMapControl controller) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method getType() is undefined for the type FunctionSignature");
  }
  
  protected static void _resolveTypes(final Variable variable, final ModelMapControl controller) {
  }
  
  public static void topdownInference(final Model model) {
    final Procedure2<EObject, ModelMapControl> _function = (EObject node, ModelMapControl controller) -> {
      if ((node instanceof Sum)) {
        controller.continueTraversal();
        final Procedure1<EObject> _function_1 = (EObject child) -> {
          TypeResolution.types.put(child, Type.EXPONENT);
        };
        ModelMap.preorder(node, _function_1);
      } else {
        if ((node instanceof Power)) {
          controller.continueTraversal();
          final Procedure1<EObject> _function_2 = (EObject child) -> {
            TypeResolution.types.put(child, Type.EXPONENT);
          };
          ModelMap.preorder(((Power)node).getRight(), _function_2);
        }
      }
    };
    ModelMap.preorderWithControl(model.getProof(), _function);
  }
  
  public static void bottomupInference(final Model model) {
  }
  
  public static void bracketsInference(final Model model) {
    final Procedure1<EObject> _function = (EObject node) -> {
      final EObject parent = node.eContainer();
      if ((((parent instanceof Brackets) && TypeResolution.types.containsKey(node)) && (!TypeResolution.types.containsKey(node.eContainer())))) {
        TypeResolution.types.put(parent, TypeResolution.types.get(node));
      }
    };
    ModelMap.postorder(model.getProof(), _function);
  }
  
  public static void resolveAllTypes(final Model model) {
    HashMap<EObject, Type> _hashMap = new HashMap<EObject, Type>();
    TypeResolution.types = _hashMap;
    TypeResolution.resolveTypes(model);
    TypeResolution.bracketsInference(model);
    TypeResolution.fillDefaultType(model);
  }
  
  public static void createVariableEnvironment(final Model model) {
    final HashMap<String, ArrayList<EObject>> environment = new HashMap<String, ArrayList<EObject>>();
  }
  
  public static HashMap<String, ArrayList<EObject>> getAllVariables(final Model model) {
    final HashMap<String, ArrayList<EObject>> variables = new HashMap<String, ArrayList<EObject>>();
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
  
  private static void _getAllVariablesHelper(final Variable variable, final HashMap<String, ArrayList<EObject>> variables) {
    if ((variable instanceof LocalVariable)) {
      return;
    }
    boolean _containsKey = variables.containsKey(variable.getName());
    if (_containsKey) {
      variables.get(variable.getName()).add(variable);
    } else {
      final ArrayList<EObject> list = new ArrayList<EObject>();
      list.add(variable);
      variables.put(variable.getName(), list);
    }
  }
  
  private static void _getAllVariablesHelper(final EObject node, final HashMap<String, ArrayList<EObject>> variables) {
    return;
  }
  
  public static HashMap<String, HashMap<String, ArrayList<EObject>>> getAllLocalVariables(final Model model) {
    final HashMap<String, HashMap<String, ArrayList<EObject>>> localVariables = new HashMap<String, HashMap<String, ArrayList<EObject>>>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        final HashMap<String, ArrayList<EObject>> functionVariables = new HashMap<String, ArrayList<EObject>>();
        final Procedure1<EObject> _function = (EObject node) -> {
          if ((node instanceof LocalVariable)) {
            boolean _containsKey = functionVariables.containsKey(((LocalVariable)node).getName());
            if (_containsKey) {
              functionVariables.get(((LocalVariable)node).getName()).add(node);
            } else {
              final ArrayList<EObject> list = new ArrayList<EObject>();
              list.add(node);
              functionVariables.put(((LocalVariable)node).getName(), list);
            }
          }
        };
        ModelMap.preorder(function.getBody(), _function);
      }
    }
    return localVariables;
  }
  
  public static void variableResolution(final Model model) {
  }
  
  public static Type topdownInferenceHelper(final EObject node) {
    boolean labeled = false;
    Type label = null;
    boolean _matched = false;
    if (node instanceof Conjunction) {
      _matched=true;
      labeled = true;
      label = Type.BOOLEAN;
      TypeResolution.types.put(node, label);
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof Disjunction) {
          _matched=true;
        }
      }
      if (_matched) {
        labeled = true;
        label = Type.BOOLEAN;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof Comparison) {
          _matched=true;
        }
      }
      if (_matched) {
        labeled = true;
        label = Type.BOOLEAN;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof NumberLiteral) {
          _matched=true;
        }
      }
      if (_matched) {
        labeled = true;
        label = Type.EXPONENT;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof StringLiteral) {
          _matched=true;
        }
      }
      if (_matched) {
        labeled = true;
        label = Type.STRING;
        TypeResolution.types.put(node, label);
      }
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof Sum) {
          _matched=true;
        }
      }
      if (_matched) {
        TypeResolution.types.put(node, Type.EXPONENT);
        TypeResolution.fillExponent(((Sum)node).getLeft());
        TypeResolution.fillExponent(((Sum)node).getRight());
      }
    }
    if (!_matched) {
      _matched=true;
      if (!_matched) {
        if (node instanceof Power) {
          _matched=true;
        }
      }
      if (_matched) {
      }
    }
    if ((node instanceof Brackets)) {
      final Type childLabel = TypeResolution.topdownInferenceHelper(((Brackets)node).getContent());
      if ((childLabel != null)) {
        TypeResolution.types.put(node, childLabel);
      }
    } else {
      if ((node instanceof Product)) {
        final Type leftChildLabel = TypeResolution.topdownInferenceHelper(((Product)node).getLeft());
        final Type rightChildLabel = TypeResolution.topdownInferenceHelper(((Product)node).getRight());
        if (((leftChildLabel == Type.EXPONENT) && (rightChildLabel == Type.EXPONENT))) {
          TypeResolution.types.put(node, Type.EXPONENT);
        } else {
          if (((leftChildLabel == Type.EXPONENT) && (rightChildLabel == Type.UNKNOWN))) {
            TypeResolution.types.put(node, Type.EXPONENT);
            TypeResolution.fillExponent(((Product)node).getRight());
          } else {
            if (((leftChildLabel == Type.UNKNOWN) && (rightChildLabel == Type.EXPONENT))) {
              TypeResolution.types.put(node, Type.EXPONENT);
              TypeResolution.fillExponent(((Product)node).getLeft());
            }
          }
        }
      } else {
        if ((node instanceof Comparison)) {
          final Type leftChildLabel_1 = TypeResolution.topdownInferenceHelper(((Comparison)node).getLeft());
          final Type rightChildLabel_1 = TypeResolution.topdownInferenceHelper(((Comparison)node).getRight());
          if (((leftChildLabel_1 == Type.EXPONENT) && (rightChildLabel_1 == Type.UNKNOWN))) {
            TypeResolution.types.put(node, Type.EXPONENT);
          } else {
            if (((leftChildLabel_1 == Type.UNKNOWN) && (rightChildLabel_1 == Type.EXPONENT))) {
              TypeResolution.types.put(node, Type.EXPONENT);
            }
          }
        } else {
          EList<EObject> _eContents = node.eContents();
          for (final EObject child : _eContents) {
            TypeResolution.topdownInferenceHelper(child);
          }
        }
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
    final Procedure2<EObject, ModelMapControl> _function = (EObject child, ModelMapControl controller) -> {
      TypeResolution.types.put(child, Type.EXPONENT);
      if ((node instanceof FunctionCall)) {
        boolean _containsKey = TypeResolution.userFunctions.containsKey(((FunctionCall)node).getName());
        if (_containsKey) {
          final FunctionDefinition function = TypeResolution.userFunctions.get(((FunctionCall)node).getName());
          boolean _containsKey_1 = TypeResolution.types.containsKey(function);
          boolean _not = (!_containsKey_1);
          if (_not) {
            final Procedure2<EObject, ModelMapControl> _function_1 = (EObject functionNode, ModelMapControl functionController) -> {
              TypeResolution.types.put(functionNode, Type.EXPONENT);
              if ((node instanceof FunctionCall)) {
                functionController.continueTraversal();
              }
            };
            ModelMap.preorderWithControl(function, _function_1);
          }
        }
        controller.continueTraversal();
      }
    };
    ModelMap.preorderWithControl(node, _function);
  }
  
  public static void fillExponentInFunction(final EObject node) {
  }
  
  public static void labelPredefinedFunctionCall(final FunctionCall call) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field PredefinedFunctionHelper is undefined"
      + "\ngetAllPredefinedFunctions cannot be resolved");
  }
  
  public static void fillDefaultType(final Model model) {
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      {
        TypeResolution.setDefaultType(function);
        EList<Parameter> _parameters = function.getParameterList().getParameters();
        for (final Parameter parameter : _parameters) {
          TypeResolution.setDefaultType(parameter);
        }
        final Procedure1<EObject> _function = (EObject node) -> {
          TypeResolution.setDefaultType(node);
        };
        ModelMap.preorder(function.getBody(), _function);
      }
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      TypeResolution.setDefaultType(node);
    };
    ModelMap.preorder(model.getProof(), _function);
  }
  
  public static void setDefaultType(final EObject node) {
    boolean _containsKey = TypeResolution.types.containsKey(node);
    boolean _not = (!_containsKey);
    if (_not) {
      TypeResolution.types.put(node, Type.GROUP_ELEMENT);
    }
  }
  
  public static void resolveTypes(final EObject comparison, final ModelMapControl controller) {
    if (comparison instanceof Comparison) {
      _resolveTypes((Comparison)comparison, controller);
      return;
    } else if (comparison instanceof Conjunction) {
      _resolveTypes((Conjunction)comparison, controller);
      return;
    } else if (comparison instanceof Disjunction) {
      _resolveTypes((Disjunction)comparison, controller);
      return;
    } else if (comparison instanceof FunctionCall) {
      _resolveTypes((FunctionCall)comparison, controller);
      return;
    } else if (comparison instanceof Negative) {
      _resolveTypes((Negative)comparison, controller);
      return;
    } else if (comparison instanceof NumberLiteral) {
      _resolveTypes((NumberLiteral)comparison, controller);
      return;
    } else if (comparison instanceof Power) {
      _resolveTypes((Power)comparison, controller);
      return;
    } else if (comparison instanceof Product) {
      _resolveTypes((Product)comparison, controller);
      return;
    } else if (comparison instanceof StringLiteral) {
      _resolveTypes((StringLiteral)comparison, controller);
      return;
    } else if (comparison instanceof Sum) {
      _resolveTypes((Sum)comparison, controller);
      return;
    } else if (comparison instanceof Tuple) {
      _resolveTypes((Tuple)comparison, controller);
      return;
    } else if (comparison instanceof Variable) {
      _resolveTypes((Variable)comparison, controller);
      return;
    } else if (comparison != null) {
      _resolveTypes(comparison, controller);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(comparison, controller).toString());
    }
  }
  
  private static void getAllVariablesHelper(final EObject variable, final HashMap<String, ArrayList<EObject>> variables) {
    if (variable instanceof Variable) {
      _getAllVariablesHelper((Variable)variable, variables);
      return;
    } else if (variable != null) {
      _getAllVariablesHelper(variable, variables);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(variable, variables).toString());
    }
  }
}
