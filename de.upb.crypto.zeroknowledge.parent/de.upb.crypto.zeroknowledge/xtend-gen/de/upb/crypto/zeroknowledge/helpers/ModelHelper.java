package de.upb.crypto.zeroknowledge.helpers;

import com.google.common.base.Objects;
import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
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
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ModelHelper {
  public static Map<String, FunctionSignature> predefined_functions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
  
  public static Model getRoot(final EObject node) {
    EObject _rootContainer = EcoreUtil.getRootContainer(node);
    return ((Model) _rootContainer);
  }
  
  public static String convertToJavaName(final String name) {
    return name.replace("\'", "_prime");
  }
  
  public static void replaceParentReferenceToSelf(final EObject child_node, final EObject new_child_node) {
    child_node.eContainer().eSet(child_node.eContainingFeature(), new_child_node);
  }
  
  public static void inlineFunctions(final Model model) {
    final Map<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      functions.put(function.getName(), function);
    }
    final Procedure1<EObject> _function = (EObject node) -> {
      ModelHelper.replaceFunctionCallWithDefinition(node, functions);
    };
    ModelMap.postorder(model.getProof(), _function);
  }
  
  private static void _replaceFunctionCallWithDefinition(final EObject node, final Map<String, FunctionDefinition> functions) {
    return;
  }
  
  private static void _replaceFunctionCallWithDefinition(final FunctionCall call, final Map<String, FunctionDefinition> functions) {
    final FunctionDefinition definition = EcoreUtil.<FunctionDefinition>copy(functions.get(call.getName()));
    final Map<String, Expression> mapping = new HashMap<String, Expression>();
    final Iterator<Expression> expressionIterator = call.getArguments().iterator();
    final Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameterList().getParameters().iterator();
    while ((expressionIterator.hasNext() && parameterIterator.hasNext())) {
      {
        final Expression expression = expressionIterator.next();
        final String parameter = parameterIterator.next().getName();
        mapping.put(parameter, expression);
      }
    }
    final Procedure1<EObject> _function = (EObject body_node) -> {
      if ((body_node instanceof Variable)) {
        final Expression expression = EcoreUtil.<Expression>copy(mapping.get(((Variable) body_node).getName()));
        if ((expression != null)) {
          ModelHelper.replaceParentReferenceToSelf(body_node, expression);
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
        boolean _equals = Objects.equal(_operation, "-");
        if (_equals) {
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
  
  public static boolean isAlgebraic(final EObject node) {
    return ((((((((((node instanceof FunctionCall) || (node instanceof Brackets)) || (node instanceof Negative)) || (node instanceof NumberLiteral)) || (node instanceof Power)) || (node instanceof Product)) || (node instanceof StringLiteral)) || (node instanceof Sum)) || (node instanceof Tuple)) || (node instanceof Variable));
  }
  
  public static boolean isComparison(final EObject node) {
    return (node instanceof Comparison);
  }
  
  public static boolean isPropositional(final EObject node) {
    return ((node instanceof Conjunction) || (node instanceof Disjunction));
  }
  
  public static boolean isLogical(final EObject node) {
    return (ModelHelper.isComparison(node) || ModelHelper.isPropositional(node));
  }
  
  public static boolean isBinary(final EObject node) {
    return ((((((node instanceof Conjunction) || 
      (node instanceof Disjunction)) || 
      (node instanceof Comparison)) || 
      (node instanceof Sum)) || 
      (node instanceof Product)) || 
      (node instanceof Power));
  }
  
  public static boolean isBooleanFunction(final FunctionCall call) {
    Type _functionType = ModelHelper.functionType(call);
    return Objects.equal(_functionType, Type.BOOLEAN);
  }
  
  public static boolean isBooleanFunction(final FunctionDefinition function) {
    Type _functionType = ModelHelper.functionType(function);
    return Objects.equal(_functionType, Type.BOOLEAN);
  }
  
  public static boolean isGroupElementFunction(final FunctionCall call) {
    Type _functionType = ModelHelper.functionType(call);
    return Objects.equal(_functionType, Type.GROUP_ELEMENT);
  }
  
  public static boolean isGroupElementFunction(final FunctionDefinition function) {
    Type _functionType = ModelHelper.functionType(function);
    return Objects.equal(_functionType, Type.GROUP_ELEMENT);
  }
  
  public static boolean isExponentFunction(final FunctionCall call) {
    Type _functionType = ModelHelper.functionType(call);
    return Objects.equal(_functionType, Type.EXPONENT);
  }
  
  public static boolean isExponentFunction(final FunctionDefinition function) {
    Type _functionType = ModelHelper.functionType(function);
    return Objects.equal(_functionType, Type.EXPONENT);
  }
  
  public static Type functionType(final FunctionCall call) {
    final String function_name = call.getName();
    final FunctionSignature value = ModelHelper.predefined_functions.get(call.getName());
    if ((value != null)) {
      return Type.convert(value.getType());
    }
    final Model root = ModelHelper.getRoot(call);
    EList<FunctionDefinition> _functions = root.getFunctions();
    for (final FunctionDefinition function : _functions) {
      String _name = function.getName();
      boolean _equals = Objects.equal(function_name, _name);
      if (_equals) {
        return ModelHelper.functionType(function);
      }
    }
    return null;
  }
  
  public static Type functionType(final FunctionDefinition function) {
    final EObject body = function.getBody();
    if ((((body instanceof Conjunction) || (body instanceof Disjunction)) || (body instanceof Comparison))) {
      return Type.BOOLEAN;
    }
    if (((body instanceof Sum) || (body instanceof NumberLiteral))) {
      return Type.EXPONENT;
    }
    if ((body instanceof FunctionCall)) {
      return ModelHelper.functionType(((FunctionCall)body));
    }
    final Model model = ModelHelper.getRoot(function);
    final String function_name = function.getName();
    final Function1<EObject, Boolean> _function = (EObject node) -> {
      if ((node instanceof FunctionCall)) {
        if ((Objects.equal(((FunctionCall)node).getName(), function_name) && ModelHelper.hasSumOrPowerAncestor(node))) {
          return true;
        }
      }
      return false;
    };
    boolean _postorderAny = ModelMap.postorderAny(model.getProof(), _function);
    if (_postorderAny) {
      return Type.EXPONENT;
    } else {
      return Type.GROUP_ELEMENT;
    }
  }
  
  public static boolean inFunctionDefinition(final EObject node) {
    if ((node instanceof Model)) {
      return false;
    }
    if ((node instanceof FunctionDefinition)) {
      return true;
    }
    return ModelHelper.inFunctionDefinition(node.eContainer());
  }
  
  public static boolean hasSumOrPowerAncestor(final EObject node) {
    if ((node instanceof Model)) {
      return false;
    }
    EObject parent = node.eContainer();
    if ((parent instanceof Sum)) {
      return true;
    } else {
      if ((parent instanceof Power)) {
        Expression _right = ((Power)parent).getRight();
        boolean _tripleEquals = (_right == node);
        if (_tripleEquals) {
          return true;
        }
      }
    }
    return ModelHelper.hasSumOrPowerAncestor(parent);
  }
  
  private static void replaceFunctionCallWithDefinition(final EObject call, final Map<String, FunctionDefinition> functions) {
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
