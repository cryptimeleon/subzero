package de.upb.crypto.zeroknowledge.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EStructuralFeature

import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet
import java.util.ArrayList;

import de.upb.crypto.zeroknowledge.type.Type;

import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage
import de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product
import de.upb.crypto.zeroknowledge.predefined.PredefinedFunctionsHelper
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral

class ModelHelper {

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
		val EObject parent = child_node.eContainer();
		val EStructuralFeature feature = child_node.eContainingFeature();
		if (parent.eGet(feature) instanceof EList) {
			val EList<EObject> list = parent.eGet(feature) as EList<EObject>;
			list.set(list.indexOf(child_node), new_child_node);
		} else {
			parent.eSet(feature, new_child_node);
		}
	}

	// Takes the user functions of a syntax tree and inlines them for each corresponding function call
	def static void inlineFunctions(Model model) {
		val HashMap<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
		for (FunctionDefinition function : model.getFunctions()) {
			functions.put(function.getName(), function);
		}

		ModelMap.postorder(model.getProof(), [ EObject node |
			replaceFunctionCallWithDefinition(node, functions);
		]);

	// This loop causes a crash
//		for (FunctionDefinition function : new ArrayList(model.getFunctions())) {
//			EcoreUtil.remove(function);
//		}
	}

	def private static dispatch void replaceFunctionCallWithDefinition(EObject node, HashMap<String, FunctionDefinition> functions) {
		return;
	}

	def private static dispatch void replaceFunctionCallWithDefinition(FunctionCall call, HashMap<String, FunctionDefinition> functions) {
		// Precondition: validation ensures that function call is valid
		val FunctionDefinition definition = EcoreUtil.copy(functions.get(call.getName()));
		val HashMap<String, Expression> mapping = new HashMap<String, Expression>();
		val Iterator<Expression> argumentIterator = call.getArguments().iterator();
		val Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameterList().getParameters().
			iterator();

		while (argumentIterator.hasNext() && parameterIterator.hasNext()) {
			val Argument argument = argumentIterator.next() as Argument;
			val String parameter = parameterIterator.next().getName();
			mapping.put(parameter, argument.getExpression());
		}

		ModelMap.preorder(definition.getBody(), [ EObject bodyNode |
			if (bodyNode instanceof Variable) {
				val Expression expression = EcoreUtil.copy(mapping.get((bodyNode as Variable).getName()));
				if (expression !== null) {
					replaceParentReferenceToSelf(bodyNode, expression);
				}
			}
		]);

		replaceParentReferenceToSelf(call, definition.getBody());
	}

	// Replace all occurrences of Sum nodes that have the subtraction operation
	// with a Sum node with the addition operation, where the right operand
	// is now a Negative node
	def static void normalizeNegatives(Model model) {
		ModelMap.postorder(model, [ EObject node |
			if (node instanceof Sum) {
				val Sum sum = node as Sum;
				if (sum.getOperation() === "-") {
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
		ModelMap.postorder(model, [ EObject node |
			if (node instanceof Brackets) {
				val Brackets brackets = node as Brackets;
				val EObject contents = brackets.getContent();
				ModelHelper.replaceParentReferenceToSelf(brackets, contents);
			}
		]);
	}

	// Changes any Variable node within a FunctionDefinition that references
	// a Parameter into a LocalVariable node
	// Changes any Variable node that references a Witness into a Witness node
	def static void identifySpecialVariables(Model model) {

		val HashMap<String, Witness> witnesses = getAllWitnesses(model);

		for (FunctionDefinition function : model.getFunctions()) {
			val ArrayList<String> parameters = new ArrayList<String>;
			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameters.add(parameter.getName());
			}

			ModelMap.preorder(function.getBody(), [ EObject node |
				if (node instanceof Variable) {
					if (parameters.contains(node.getName())) {
						val LocalVariable local = ZeroKnowledgeFactory.eINSTANCE.createLocalVariable();
						local.setName(node.getName());
						local.setFunction(function.getName());
						ModelHelper.replaceParentReferenceToSelf(node, local);
					} else {
						identifySpecialVariablesHelper(node, witnesses);
					}

				}
			]);

		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof Variable) {
				identifySpecialVariablesHelper(node, witnesses);
			}
		]);
	}

	def static void identifySpecialVariablesHelper(Variable variable, HashMap<String, Witness> witnesses) {
		if (witnesses.containsKey(variable.getName())) {
			val WitnessVariable witness = ZeroKnowledgeFactory.eINSTANCE.createWitnessVariable();
			witness.setName(variable.getName());
			ModelHelper.replaceParentReferenceToSelf(variable, witness);
		}
	}
	
	
	def static String getArgumentFunction(Argument argument) {
		return (argument.eContainer() as FunctionCall).getName();
	}
	
	def static int getArgumentIndex(Argument argument) {
		return (argument.eContainer() as FunctionCall).getArguments().indexOf(argument);
	}

	// Returns a map from user function names to function signatures
	def static HashMap<String, FunctionSignature> getUserFunctionSignatures(Model model, HashMap<EObject, Type> types, HashMap<EObject, Integer> tuples) {
		val HashMap<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();

		for (FunctionDefinition function : model.getFunctions()) {
			val ArrayList<Type> parameterTypes = new ArrayList<Type>();
			val ArrayList<Integer> parameterSizes = new ArrayList<Integer>();

			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameterTypes.add(types.get(parameter));
				parameterSizes.add(tuples.get(parameter));
			}

			val FunctionSignature signature = new FunctionSignature(function.getName(), types.get(function), tuples.get(function), parameterTypes, parameterSizes);
			functions.put(function.getName(), signature);
		}

		return functions;
	}

	// Returns the set of witness names
	def static HashSet<String> getWitnessNames(Model model) {
		val HashSet<String> witnesses = new HashSet<String>();

		for (Witness witness : model.getWitnessList().getWitnesses()) {
			witnesses.add(witness.getName());
		}

		return witnesses;
	}

	// Returns a map from user function names to user function nodes
	def static HashMap<String, FunctionDefinition> getAllUserFunctions(Model model) {
		val HashMap<String, FunctionDefinition> userFunctions = new HashMap<String, FunctionDefinition>();

		for (FunctionDefinition function : model.getFunctions()) {
			userFunctions.put(function.getName(), function);
		}

		return userFunctions;
	}

	// Returns a map from user function names to user function call nodes
	def static HashMap<String, ArrayList<FunctionCall>> getAllUserFunctionCalls(Model model, HashMap<String, FunctionDefinition> userFunctionsMap) {
		val HashMap<String, ArrayList<FunctionCall>> userFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (userFunctionsMap.containsKey(functionName)) {
					if (userFunctionCalls.containsKey(functionName)) {
						userFunctionCalls.get(functionName).add(node);
					} else {
						val ArrayList<FunctionCall> list = new ArrayList<FunctionCall>;
						list.add(node);
						userFunctionCalls.put(functionName, list);
					}
				}
			}

		]);

		return userFunctionCalls;
	}

	// Precondition: requires that all local variables have been identified, and that the
	// corresponding Variable objects have been replaced with LocalVariable objects
	// Returns a map from variable names to a list of variable nodes
	def static HashMap<String, ArrayList<Variable>> getAllVariables(Model model) {
		val HashMap<String, ArrayList<Variable>> variables = new HashMap<String, ArrayList<Variable>>();

		for (FunctionDefinition function : model.getFunctions()) {
			ModelMap.preorder(function.getBody(), [ EObject node |
				getAllVariablesHelper(node, variables);
			]);
		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			getAllVariablesHelper(node, variables);
		]);

		return variables;
	}

	def static private void getAllVariablesHelper(EObject node, HashMap<String, ArrayList<Variable>> variables) {
		if (node instanceof Variable) {
			if(node instanceof LocalVariable) return;
			if (variables.containsKey(node.getName())) {
				variables.get(node.getName()).add(node);
			} else {
				val ArrayList<Variable> list = new ArrayList<Variable>();
				list.add(node);
				variables.put(node.getName(), list);
			}
		}
	}

	// Returns a map from witness names to witness nodes
	def static HashMap<String, Witness> getAllWitnesses(Model model) {
		val HashMap<String, Witness> witnesses = new HashMap<String, Witness>();

		if (model.getWitnessList() !== null) {
			for (Witness witness : model.getWitnessList().getWitnesses()) {
				witnesses.put(witness.getName(), witness);
			}
		}

		return witnesses;
	}

	// Precondition: requires that all local variables have been identified, and that the
	// corresponding Variable objects have been replaced with LocalVariable objects
	// Returns a map from user function names and local variable names to a list of local variable objects	
	def static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> getAllLocalVariables(Model model) {
		val HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariables = new HashMap<String, HashMap<String, ArrayList<LocalVariable>>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val HashMap<String, ArrayList<LocalVariable>> functionVariables = new HashMap<String, ArrayList<LocalVariable>>();

			for (Parameter parameter : function.getParameterList().getParameters()) {
				functionVariables.put(parameter.getName(), new ArrayList<LocalVariable>());
			}

			ModelMap.preorder(function.getBody(), [ EObject node |
				if (node instanceof LocalVariable) {
					functionVariables.get(node.getName()).add(node);
				}
			]);

			localVariables.put(function.getName(), functionVariables);
		}

		return localVariables;
	}

	// Returns a map from user function names and parameter names to parameter nodes
	def static HashMap<String, HashMap<String, Parameter>> getAllParameters(Model model) {
		val HashMap<String, HashMap<String, Parameter>> localParameters = new HashMap<String, HashMap<String, Parameter>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val HashMap<String, Parameter> parameters = new HashMap<String, Parameter>();

			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameters.put(parameter.getName(), parameter);
			}

			localParameters.put(function.getName(), parameters);
		}

		return localParameters;
	}

	// Returns a map from user function names and parameter names to corresponding arguments in function calls
	def static HashMap<String, HashMap<String, ArrayList<Argument>>> getAllArguments(Model model,
		HashMap<String, FunctionDefinition> userFunctionsMap) {
		val HashMap<String, HashMap<String, ArrayList<Argument>>> arguments = new HashMap<String, HashMap<String, ArrayList<Argument>>>();
		val HashMap<String, ArrayList<String>> functionParameters = new HashMap<String, ArrayList<String>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val HashMap<String, ArrayList<Argument>> local = new HashMap<String, ArrayList<Argument>>();
			val ArrayList<String> parameters = new ArrayList<String>();

			for (Parameter parameter : function.getParameterList.getParameters()) {
				val ArrayList<Argument> list = new ArrayList<Argument>;
				local.put(parameter.getName(), list);
				parameters.add(parameter.getName());
			}

			arguments.put(function.getName(), local);
			functionParameters.put(function.getName(), parameters);

		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (userFunctionsMap.containsKey(functionName)) {

					var int index = 0;
					val int length = functionParameters.get(functionName).size();
					val Iterator<Expression> argumentIterator = node.getArguments().iterator();

					while (argumentIterator.hasNext() && index < length) {
						val Argument argument = argumentIterator.next() as Argument;
						val String parameterName = functionParameters.get(functionName).get(index);
						arguments.get(functionName).get(parameterName).add(argument);
						index++;
					}
				}
			}
		]);

		return arguments;
	}
	
	
	
	// Returns a list of all tuple nodes in the model, except for tuples nested within
	// other tuples, unless they are first nested within a function call
	def static ArrayList<Tuple> getAllTuples(Model model) {
		val ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		getAllTuplesHelper1(tuples, model);
		return tuples;
	}
	def private static void getAllTuplesHelper1(ArrayList<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMapControl controller |
			if (child instanceof Tuple) {
				tuples.add(child);
				getAllTuplesHelper2(tuples, child);				
				controller.continueTraversal();
			}
		]);
	}
	def private static void getAllTuplesHelper2(ArrayList<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMapControl controller |
			if (child instanceof FunctionCall) {
				getAllTuplesHelper1(tuples, child);
				controller.continueTraversal();
			}
		]);
	}
		
//	def static ArrayList<Tuple> getAllTuples(Model model) {
//		val ArrayList<Tuple> tuples = new ArrayList<Tuple>();
//	
//		ModelMap.preorderWithControl(model, [EObject node, ModelMapControl controller |
//			if (node instanceof Tuple) {
//				tuples.add(node);
//				controller.continueTraversal();
//			}
//		]);
//		return tuples;
//	}

	
	
	
	// Returns a map from predefined function names to predefined function call nodes
	def static HashMap<String, ArrayList<FunctionCall>> getAllPredefinedFunctionCalls(Model model, HashMap<String, FunctionSignature> predefinedFunctionsMap) {
		val HashMap<String, ArrayList<FunctionCall>> predefinedFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();

		ModelMap.preorder(model.getProof(), [EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (predefinedFunctionsMap.containsKey(functionName)) {
					if (predefinedFunctionCalls.containsKey(functionName)) {
						predefinedFunctionCalls.get(functionName).add(node);
					} else {
						val ArrayList<FunctionCall> list = new ArrayList<FunctionCall>;
						list.add(node);
						predefinedFunctionCalls.put(functionName, list);
					}
				}
			}

		]);

		return predefinedFunctionCalls;
	}

}
