package de.upb.crypto.zeroknowledge.helpers;

import com.google.common.base.Objects;
import de.upb.crypto.zeroknowledge.helpers.FunctionSignature;
import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.helpers.PredefinedFunctionsHelper;
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
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
  
  public static void identifyLocalVariables(final Model model) {
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
            }
          }
        };
        ModelMap.preorder(function.getBody(), _function);
      }
    }
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
  
  public static void typeResolution(final Model model) {
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
