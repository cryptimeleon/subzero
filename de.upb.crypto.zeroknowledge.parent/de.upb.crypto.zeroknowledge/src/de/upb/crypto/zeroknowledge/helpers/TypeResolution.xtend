package de.upb.crypto.zeroknowledge.helpers

import de.upb.crypto.zeroknowledge.zeroKnowledge.Model
import java.util.HashMap
import org.eclipse.emf.ecore.EObject
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction
import java.util.Map
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets
import java.util.ArrayList
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter

class TypeResolution {
	
	private static HashMap<EObject, Type> types;
	private static HashMap<String, FunctionDefinition> userFunctions;
	private static Map<String, FunctionSignature> predefinedFunctions = PredefinedFunctionsHelper.getAllPredefinedFunctions();
	
	def static HashMap<EObject, Type> getTypes() {
		return types;
	}
	
	
	def static void fill(FunctionCall call, Type type) {
		val FunctionDefinition function = userFunctions.get(call.getName());
		if (function !== null && types.get(function) === null) {
			fill(function, type);	
		}
	}
	
	def static void fill(EObject node, Type type) {
		types.put(node, type);
	}
	
	// Catches all nodes that do not need a type (other than Model)
	// FunctionDefinition, ParameterList, Parameter, WitnessList, Witness 
	def static dispatch void resolveTypes(EObject object, ModelMapControl controller) {
		return;
	}
	
	def static void resolveTypes(Model model) {
		ModelMap.preorderWithControl(model.getProof(), [EObject node, ModelMapControl controller |
			resolveTypes(node, controller);
		]);
	}
	
	def static dispatch void resolveTypes(Conjunction conjunction, ModelMapControl controller) {
		types.put(conjunction, Type.BOOLEAN);
	}
	
	def static dispatch void resolveTypes(Disjunction disjunction, ModelMapControl controller) {
		types.put(disjunction, Type.BOOLEAN);	
	}
	
	def static dispatch void resolveTypes(Comparison comparison, ModelMapControl controller) {
		types.put(comparison, Type.BOOLEAN);
	}
	
	def static dispatch void resolveTypes(Sum sum, ModelMapControl controller) {
		types.put(sum, Type.EXPONENT);
		controller.continueTraversal();
		ModelMap.preorder(sum.getLeft(), [EObject node | 
			types.put(node, Type.EXPONENT);
		]);
		ModelMap.preorder(sum.getRight(), [EObject node |
			types.put(node, Type.EXPONENT);
		]);
	}
	
	def static dispatch void resolveTypes(Product product, ModelMapControl controller) {
		//??
	}
	
	def static dispatch void resolveTypes(Power power, ModelMapControl controller) {
		
	}
	
	def static dispatch void resolveTypes(StringLiteral stringLiteral, ModelMapControl controller) {
		types.put(stringLiteral, Type.STRING);
	}
	
	def static dispatch void resolveTypes(Tuple tuple, ModelMapControl controller) {
		
	}
	
	def static dispatch void resolveTypes(Negative negative, ModelMapControl controller) {
		types.put(negative, Type.EXPONENT);
		controller.continueTraversal();
		ModelMap.preorderWithControl(negative.getTerm(), [EObject node, ModelMapControl control |
			types.put(node, Type.EXPONENT)
		]);
	}
	
	
	def static dispatch void resolveTypes(NumberLiteral numberLiteral, ModelMapControl controller) {
		types.put(numberLiteral, Type.EXPONENT);
	}
	

	def static dispatch void resolveTypes(FunctionCall call, ModelMapControl controller) {
		val FunctionSignature function = predefinedFunctions.get(call.getName());
		if (function !== null) {
			types.put(call, Type.convert(function.getType()));
			return;
		}
		
	}
	
	def static dispatch void resolveTypes(Variable variable, ModelMapControl controller) {
		
	}
	
	
	
	def static void topdownInference(Model model) {
		ModelMap.preorderWithControl(model.getProof(), [EObject node, ModelMapControl controller |
			if (node instanceof Sum) {
				controller.continueTraversal();
				ModelMap.preorder(node, [EObject child |
					types.put(child, Type.EXPONENT);
				]);
			} else if (node instanceof Power) {
				controller.continueTraversal();
				ModelMap.preorder(node.getRight(), [EObject child |
					types.put(child, Type.EXPONENT);
				]);
			}
			
		]);
	}
	
	
	
	def static void bottomupInference(Model model) {
		
	}
	
	
	
	def static void bracketsInference(Model model) {
		ModelMap.postorder(model.getProof(), [EObject node |
			val parent = node.eContainer();
			if (parent instanceof Brackets && types.containsKey(node) && !types.containsKey(node.eContainer())) {
				types.put(parent, types.get(node));
			}
		]);
	} 
	
	
	
	
	def static void resolveAllTypes(Model model) {
		types = new HashMap<EObject, Type>();
		resolveTypes(model);
		bracketsInference(model);
		fillDefaultType(model);
	}
	
	
	def static void createVariableEnvironment(Model model) {
		val HashMap<String, ArrayList<EObject>> environment = new HashMap<String, ArrayList<EObject>>;
		
	}
	
	// Precondition: requires that all local variables have been identified, and that the
	// corresponding Variable objects have been replaced with LocalVariable objects
	def static HashMap<String, ArrayList<EObject>> getAllVariables(Model model) {
		val HashMap<String, ArrayList<EObject>> variables = new HashMap<String, ArrayList<EObject>>();
		
		for (FunctionDefinition function : model.getFunctions()) {
			ModelMap.preorder(function.getBody(), [EObject node |
				getAllVariablesHelper(node, variables);
			]);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			getAllVariablesHelper(node, variables);
		]);
		
		return variables;
	}
	
	def static private dispatch void getAllVariablesHelper(Variable variable, HashMap<String, ArrayList<EObject>> variables) {
		if (variable instanceof LocalVariable) return;
		if (variables.containsKey(variable.getName())) {
			variables.get(variable.getName()).add(variable);
		} else {
			val ArrayList<EObject> list = new ArrayList<EObject>();
			list.add(variable);
			variables.put(variable.getName(), list);
		}
	}
	def static private dispatch void getAllVariablesHelper(EObject node, HashMap<String, ArrayList<EObject>> variables) {
		return;
	}
	
	def static HashMap<String, HashMap<String, ArrayList<EObject>>> getAllLocalVariables(Model model) {
		val HashMap<String, HashMap<String, ArrayList<EObject>>> localVariables = new HashMap<String, HashMap<String, ArrayList<EObject>>>();
		
		for (FunctionDefinition function : model.getFunctions()) {
			val HashMap<String, ArrayList<EObject>> functionVariables = new HashMap<String, ArrayList<EObject>>();
			ModelMap.preorder(function.getBody(), [EObject node |
				if (node instanceof LocalVariable) {
					if (functionVariables.containsKey(node.getName())) {
						functionVariables.get(node.getName()).add(node);
					} else {
						val ArrayList<EObject> list = new ArrayList<EObject>;
						list.add(node);
						functionVariables.put(node.getName(), list);
					}
				}
			]);			
		}
		
		return localVariables;
	}
	
	def static void variableResolution(Model model) {
		
	}
	
	
//	def static void topdownInference(Model model) {
//		
//		topdownInferenceHelper(model.getProof());
//		
//	}
	
	
	def static Type topdownInferenceHelper(EObject node) {
		
		var boolean labeled = false;
		var Type label;
		
		// Try to label the parent node
		switch node {
			Conjunction: {
				labeled = true;	
				label = Type.BOOLEAN;
				types.put(node, label);
			},
			Disjunction: {
				labeled = true;
				label = Type.BOOLEAN;
				types.put(node, label);
			},
			Comparison: {
				labeled = true;
				label = Type.BOOLEAN;
				types.put(node, label);
			},
			NumberLiteral: {
				labeled = true;
				label = Type.EXPONENT;
				types.put(node, label);
			},
			StringLiteral: {
				labeled = true;
				label = Type.STRING;
				types.put(node, label);
			},
			Sum: {
				types.put(node, Type.EXPONENT);
				fillExponent(node.getLeft());
				fillExponent(node.getRight());
			},
			Power: {
				
			}
		}
		
		
		
		
		
		
		
		
		// Try to label the child nodes, and label the parent if possible
		if (node instanceof Brackets) {
			val childLabel = topdownInferenceHelper(node.getContent());
			if (childLabel !== null) {
				types.put(node, childLabel);
			}
		} else if (node instanceof Product) {
			val leftChildLabel = topdownInferenceHelper(node.getLeft());
			val rightChildLabel = topdownInferenceHelper(node.getRight());
			if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.EXPONENT) {
				types.put(node, Type.EXPONENT);
			} else if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.UNKNOWN) {
				types.put(node, Type.EXPONENT);
				fillExponent(node.getRight());
			} else if (leftChildLabel === Type.UNKNOWN && rightChildLabel === Type.EXPONENT) {
				types.put(node, Type.EXPONENT);
				fillExponent(node.getLeft());
			}
		} else if (node instanceof Comparison) {
			val leftChildLabel = topdownInferenceHelper(node.getLeft());
			val rightChildLabel = topdownInferenceHelper(node.getRight());
			if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.UNKNOWN) {
				types.put(node, Type.EXPONENT);
			} else if (leftChildLabel === Type.UNKNOWN && rightChildLabel === Type.EXPONENT) {
				types.put(node, Type.EXPONENT);
			}
		} else {
			for (EObject child : node.eContents()) {
				topdownInferenceHelper(child);
			}
		}
		
		
		
		// If still unlabeled, try to label the parent node
		
		return label ?: Type.UNKNOWN;


	}
	
	// Labels each node in an entire subtree as Exponent, ignoring arguments in FunctionCall nodes
	def static void fillExponent(EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMapControl controller |
			types.put(child, Type.EXPONENT);
			
			if (node instanceof FunctionCall) {
				if (userFunctions.containsKey(node.getName())) {
					val FunctionDefinition function = userFunctions.get(node.getName());
					if (!types.containsKey(function)) {
						ModelMap.preorderWithControl(function, [EObject functionNode, ModelMapControl functionController |
							types.put(functionNode, Type.EXPONENT);
							if (node instanceof FunctionCall) functionController.continueTraversal();
						]);						
					}
				}
				controller.continueTraversal();
			}
			
			
		]);
	}
	
	def static void fillExponentInFunction(EObject node) {
		
	}
	
	// Precondition: function call references a predefined function
	def static void labelPredefinedFunctionCall(FunctionCall call) {

		PredefinedFunctionHelper.getAllPredefinedFunctions
	}
	
	
	
	// All unlabeled nodes that require a type label
	// are labeled with GROUP_ELEMENT as the default
	def static void fillDefaultType(Model model) {
		for (FunctionDefinition function : model.getFunctions()) {
			setDefaultType(function);
			
			for (Parameter parameter : function.getParameterList().getParameters()) {
				setDefaultType(parameter);
			}
			
			ModelMap.preorder(function.getBody(), [EObject node |
				setDefaultType(node);
			]);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			setDefaultType(node);
		]);
	}
	def static void setDefaultType(EObject node) {
		if (!types.containsKey(node)) {
			types.put(node, Type.GROUP_ELEMENT);
		}
	}
}