package de.upb.crypto.zeroknowledge.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product;
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;

import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import java.util.ArrayList

class ModelHelper {
	
	var public static Map<String, FunctionSignature> predefined_functions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
	
	// Helper function to get the root Model object
	def static Model getRoot(EObject node) {
		return EcoreUtil.getRootContainer(node) as Model;
	}	
	
	// Converts variable names and witness names to Java variable names
	def static String convertToJavaName(String name) {
		return name.replace("'", "_prime");
	}
	
	// Sets a child node's parent's reference to the child node to reference a new child node
	// Precondition: node cannot be the root Model node
	def static void replaceParentReferenceToSelf(EObject child_node, EObject new_child_node) {
		child_node.eContainer().eSet(child_node.eContainingFeature(), new_child_node);
	}

	// Takes the user functions of a syntax tree and inlines
	// them for each corresponding function call
	def static void inlineFunctions(Model model) {
		val Map<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
		for (FunctionDefinition function : model.getFunctions()) {
			functions.put(function.getName(), function);
		}		
		
		ModelMap.postorder(model.getProof(), [EObject node | 
			replaceFunctionCallWithDefinition(node, functions);
		]);
		
		// This loop causes a crash
//		for (FunctionDefinition function : new ArrayList(model.getFunctions())) {
//			EcoreUtil.remove(function);
//		}
	}
	
	def private static dispatch void replaceFunctionCallWithDefinition(EObject node, Map<String, FunctionDefinition> functions) {
		return;
	}
	def private static dispatch void replaceFunctionCallWithDefinition(FunctionCall call, Map<String, FunctionDefinition> functions) {
		// Precondition: validation ensures that function call is valid
		val FunctionDefinition definition = EcoreUtil.copy(functions.get(call.getName()));
		val Map<String, Expression> mapping = new HashMap<String, Expression>();
		val Iterator<Expression> expressionIterator = call.getArguments().iterator();
		val Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameterList().getParameters().iterator();
		
		while(expressionIterator.hasNext() && parameterIterator.hasNext()) {
			val Expression expression = expressionIterator.next();
			val String parameter = parameterIterator.next().getName();
			mapping.put(parameter, expression);
		}
		
		ModelMap.preorder(definition.getBody(), [EObject body_node |
			if (body_node instanceof Variable) {
				val Expression expression = EcoreUtil.copy(mapping.get((body_node as Variable).getName()));
				if (expression !== null) {
					replaceParentReferenceToSelf(body_node, expression);
				}
			}
		]);
		
		replaceParentReferenceToSelf(call, definition.getBody());
	}
	
	// Replace all occurrences of Sum nodes that have the subtraction operation
	// with a Sum node with the addition operation, where the right branch
	// is now a Negative node
	def static void normalizeNegatives(Model model) {
		ModelMap.postorder(model, [EObject node |
			if (node instanceof Sum) {
				val Sum sum = node as Sum;
				if (sum.getOperation() == '-') {
					val Expression rightSide = sum.getRight();
					val Negative negative = ZeroKnowledgeFactory.eINSTANCE.createNegative();
					ModelHelper.replaceParentReferenceToSelf(rightSide, negative);
					negative.setTerm(rightSide);
					sum.setOperation('+');
				}
			}
			
		]);
	}
	
	// Simplifies the model by removing all bracket nodes
	def static void removeBrackets(Model model) {
		ModelMap.postorder(model, [EObject node |
			if (node instanceof Brackets) {
				val Brackets brackets = node as Brackets;
				val EObject contents = brackets.getContent();
				ModelHelper.replaceParentReferenceToSelf(brackets, contents);
			}
			
		]);
	}
	
	// True if the object will evaluate to an algebraic value
	// This includes string literals
	// ? should this include function call and group?
	def static boolean isAlgebraic(EObject node) {
		return node instanceof FunctionCall
				|| node instanceof Brackets
				|| node instanceof Negative
				|| node instanceof NumberLiteral
				|| node instanceof Power
				|| node instanceof Product
				|| node instanceof StringLiteral
				|| node instanceof Sum
				|| node instanceof Tuple
				|| node instanceof Variable;
	}
	
	// True if the object is a comparison node
	def static boolean isComparison(EObject node) {
		return node instanceof Comparison;
	}
	
	// True if the object is a conjunction or disjunction
	def static boolean isPropositional(EObject node) {
		return node instanceof Conjunction || node instanceof Disjunction;
	}
	
	// True if the object is a comparison, conjunction, or disjunction
	def static boolean isLogical(EObject node) {
		return isComparison(node) || isPropositional(node);
	}
	
	// True if the object performs a binary operation or is a binary connective
	def static boolean isBinary(EObject node) {
		return node instanceof Conjunction ||
				node instanceof Disjunction ||
				node instanceof Comparison ||
				node instanceof Sum ||
				node instanceof Product ||
				node instanceof Power;
	}
	
	def static boolean isBooleanFunction(FunctionCall call) {
		return functionType(call) == Type.BOOLEAN;
	}
	def static boolean isBooleanFunction(FunctionDefinition function) {
		return functionType(function) == Type.BOOLEAN;	
	}
	
	def static boolean isGroupElementFunction(FunctionCall call) {
		return functionType(call) == Type.GROUP_ELEMENT;
	}
	def static boolean isGroupElementFunction(FunctionDefinition function) {
		return functionType(function) == Type.GROUP_ELEMENT;
	}
	
	def static boolean isExponentFunction(FunctionCall call) {
		return functionType(call) == Type.EXPONENT;
	}
	def static boolean isExponentFunction(FunctionDefinition function) {
		return functionType(function) == Type.EXPONENT;
	}
	
	def static Type functionType(FunctionCall call) {
		// Precondition: function call must reference a valid user function or predefined function
		val String function_name = call.getName();
		
		// If function call references a predefined function, just return its type
		val FunctionSignature value = predefined_functions.get(call.getName());
		if (value !== null) {
			return Type.convert(value.getType());
		}
		
		// If function call references a user function, determine the type and return it
		val Model root = ModelHelper.getRoot(call);
		for (FunctionDefinition function : root.getFunctions()) {
			if (function_name == function.getName()) {
				return functionType(function);
			}
		}
		
	}
	
	def static Type functionType(FunctionDefinition function) {
		val EObject body = function.getBody();
		
		if (body instanceof Conjunction || body instanceof Disjunction || body instanceof Comparison) {
			return Type.BOOLEAN;
		}
		
		if (body instanceof Sum || body instanceof NumberLiteral) {
			return Type.EXPONENT;
		}
		
		if (body instanceof FunctionCall) {
			return functionType(body);
		}
		
		// body instanceof StringLiteral is not possible
		// body instanceof Tuple is not possible
		// Validation guarantees that StringLiterals and Tuples are never the top node of a function body

		val Model model = ModelHelper.getRoot(function);
		val String function_name = function.getName();
		
		if (ModelMap.postorderAny(model.getProof(), [EObject node | 
			if (node instanceof FunctionCall) {
				if (node.getName() == function_name && ModelHelper.hasSumOrPowerAncestor(node)) {
					return true;
				}
			}
			return false;
		])) {
			return Type.EXPONENT;
		} else {
			return Type.GROUP_ELEMENT;
		}
		
	}
	
	// Returns true if the node is contained within a function definition
	def static boolean inFunctionDefinition(EObject node) {
		if (node instanceof Model) {
			return false;
		}
		if (node instanceof FunctionDefinition) {
			return true;
		}
		return inFunctionDefinition(node.eContainer());
	}
	
	// Returns true if the node is a descendant of a sum node
	// or a descendant of the right branch of a power node
	def static boolean hasSumOrPowerAncestor(EObject node) {
		if (node instanceof Model) {
			return false;
		}
		var EObject parent = node.eContainer();
		if (parent instanceof Sum) {
			return true;
		} else if (parent instanceof Power) {
			if (parent.getRight() === node) {
				return true;
			}
		}
		return hasSumOrPowerAncestor(parent);
	}
	
}
