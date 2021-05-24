package org.cryptimeleon.subzero.model

import java.util.ArrayList
import java.util.HashMap
import java.util.Iterator
import java.util.List
import java.util.Map
import org.cryptimeleon.subzero.subzero.Argument
import org.cryptimeleon.subzero.subzero.Brackets
import org.cryptimeleon.subzero.subzero.Comparison
import org.cryptimeleon.subzero.subzero.Conjunction
import org.cryptimeleon.subzero.subzero.Disjunction
import org.cryptimeleon.subzero.subzero.Expression
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.FunctionDefinition
import org.cryptimeleon.subzero.subzero.LocalVariable
import org.cryptimeleon.subzero.subzero.Model
import org.cryptimeleon.subzero.subzero.Negative
import org.cryptimeleon.subzero.subzero.Parameter
import org.cryptimeleon.subzero.subzero.Power
import org.cryptimeleon.subzero.subzero.Product
import org.cryptimeleon.subzero.subzero.StringLiteral
import org.cryptimeleon.subzero.subzero.Sum
import org.cryptimeleon.subzero.subzero.Tuple
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.Witness
import org.cryptimeleon.subzero.subzero.WitnessVariable
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper

class SizeInference {
	// Stores the inferred multiplicity for every node in the syntax tree that requires a multiplicity
	// Multiplicity of 1 is a scalar, multiplicity greater than 1 is a tuple
	Map<EObject, Integer> sizes;
	
	// Stores the inferred Type for every node in the syntax tree that requires a type
	Map<EObject, Type> types;
	
	// A map from predefined function names to the function signature
	Map<String, FunctionSignature> predefinedFunctionsMap;
	
	// A map from function names to user function calls
	Map<String, List<FunctionCall>> predefinedFunctionCallsMap;
	
	// A map from function names to user function definition nodes
	Map<String, FunctionDefinition> userFunctionsMap;
	
	// A map from function names to user function calls
	Map<String, List<FunctionCall>> userFunctionCallsMap;
	
	// A map from variable names to variable nodes
	Map<String, List<Variable>> variablesMap;
	
	// A map from witness names to witness nodes
	Map<String, Witness> witnessesMap;
	
	// A map from function names and parameter names to local variable nodes
	Map<String, Map<String, List<LocalVariable>>> localVariablesMap;
	
	// A map from user function names and local variable names to the corresponding Parameter node in the function definition
	Map<String, Map<String, Parameter>> parametersMap;	
	
	// A map from user function names and parameter names to corresponding arguments in function calls
	Map<String, Map<String, List<Argument>>> argumentsMap;
	
	def Map<EObject, Integer> getSizes() {
		return sizes;
	}
	
	def int getNodeType(EObject node) {
		return sizes.get(node);
	}
	
	def private void backpropagateSize(EObject node, int size) {
		// If the node has already been labeled, return
		if (sizes.containsKey(node)) return;
		
		sizes.put(node, size);
		
		// Stop backpropagating when
		val EObject parent = node.eContainer();
		if (
			parent instanceof Model ||
			parent instanceof Conjunction ||
			parent instanceof Disjunction
		) return;
		
		switch parent {
			Comparison: {
				if (!sizes.containsKey(parent.getLeft())) {
					fillSize(parent.getLeft(), size);
				}
				if (!sizes.containsKey(parent.getRight())) {
					fillSize(parent.getRight(), size);
				}
			}
			Brackets: {
				backpropagateSize(parent, size);
			}
			Negative: {
				backpropagateSize(parent, size);
			}
			Sum: {
				if (!sizes.containsKey(parent.getLeft())) {
					fillSize(parent.getLeft(), size);
				}
				if (!sizes.containsKey(parent.getRight())) {
					fillSize(parent.getRight(), size);
				}
				backpropagateSize(parent, size);
			}
			Product: {
				if (types.get(parent) === Type.GROUP_ELEMENT) {
					if (!sizes.containsKey(parent.getLeft())) {
						fillSize(parent.getLeft(), size);
					}
					if (!sizes.containsKey(parent.getRight())) {
						fillSize(parent.getRight(), size);
					}
				}
				backpropagateSize(parent, size);
			}
			Power: {
				if (node.eContainmentFeature().getName() === "left") {
					backpropagateSize(parent, size);
				}
			}
			FunctionDefinition: {
				sizes.put(parent, size);
				for (FunctionCall call : userFunctionCallsMap.get(parent.getName())) {
					backpropagateSize(call, size);
				}
			}
			Argument: {
				val String functionName = ModelHelper.getArgumentFunction(parent);
				
				sizes.put(parent, size);
				
				if (userFunctionsMap.containsKey(functionName)) {
					val EList<Parameter> parameters = userFunctionsMap.get(functionName).getParameterList().getParameters();
					val int index = ModelHelper.getArgumentIndex(parent);
					
					if (index < parameters.size()) {
						fillSize(parameters.get(index), size);
					}
				}
			}
		}
	}
	
	// Sets the size of a node and all of its descendants to a given size
	def private void fillSize(EObject node, int size) {
		// If the node is already labeled, all of its descendants are also labeled, so return
		if (sizes.containsKey(node)) return;
		
		sizes.put(node, size);
		
		if (node instanceof Tuple) return;
		
		switch node {
			Parameter: {
				val String functionName = (node.eContainer().eContainer() as FunctionDefinition).getName();
				val String parameterName = node.getName();

				// For each local variable with the same name in the same function, perform backpropagation
				for (LocalVariable localVariable : localVariablesMap.get(functionName).get(parameterName)) {
					backpropagateSize(localVariable, size);
				}
				
				// For each argument in a corresponding function call that becomes the corresponding parameter, perform fill
				for (Argument argument : argumentsMap.get(functionName).get(parameterName)) {
					fillSize(argument, size);					
				}
			}
			Witness: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable: variablesMap.get(node.getName())) {
					backpropagateSize(variable, size);
				}
			}
			WitnessVariable: {
				fillSize(witnessesMap.get(node.getName()), size);
			}			
			LocalVariable: {
				fillSize(parametersMap.get(node.getFunction()).get(node.getName()), size);
			}
			Variable: {
				for (Variable variable : variablesMap.get(node.getName())) {
					backpropagateSize(variable, size);
				}
			}
			Product: {
				if (types.get(node) === Type.GROUP_ELEMENT) {
					fillSize(node.getLeft(), size);
					fillSize(node.getRight(), size);
				}
			}
			Power: {
				fillSize(node.getLeft(), size);
			}
			FunctionCall: {
				if (userFunctionsMap.containsKey(node.getName())) {
					fillSize(userFunctionsMap.get(node.getName()), size);
				}
			}
			FunctionDefinition: {
				// For every function call to this function, perform backpropagation
				for (FunctionCall call : userFunctionCallsMap.get(node.getName())) {
					backpropagateSize(call, size);
				}

				fillSize(node.getBody(), size);
			}
			default: {
				for (EObject child : node.eContents()) {
					fillSize(child, size);
				}
			}
		}
		
	}
	
	// Labels all remaining nodes with size 1 (scalar)
	def private void fillDefaultSizes(Model model) {
		ModelMap.preorder(model.getProof(), [EObject node |
			sizes.putIfAbsent(node, 1);
		]);
		
		for (FunctionDefinition function : model.getFunctions()) {
			sizes.putIfAbsent(function, 1);
			
			ModelMap.preorder(function.getBody(), [EObject node |
				sizes.putIfAbsent(node, 1);
			]);
			
			for (Parameter parameter : function.getParameterList.getParameters()) {
				sizes.putIfAbsent(parameter, 1);
			}
		}
		
		for (Witness witness : model.getWitnessList().getWitnesses()) {
			sizes.putIfAbsent(witness, 1);
		}
	}
	
	// Conjunctions, disjunctions, comparisons, string literals get size 0
	// Model, parameter list, witness list, have no assigned size
	// All remaining unlabeled nodes get size 1
	def private void fillDefaultsHelper(EObject node) {
		if (
			node instanceof Conjunction ||
			node instanceof Disjunction ||
			node instanceof Comparison ||
			node instanceof StringLiteral
		) {
			sizes.putIfAbsent(node, 0);
		} else {
			sizes.putIfAbsent(node, 1);
		}
	}

	
	/*
	 * For every tuple, get the tuple's size and propagate that size upwards through the parse tree,
	 * until it reaches a node that does not have a multiplicity
	 * 
	 * 
	 * 
	 * Perform a top-down traversal of the parse tree, labeling all unlabeled nodes as scalars (size 1),
	 * or size 0 if multiplicity is not applicable
	 * 
	 */
	
	
	
	def private void inferSizes(AugmentedModel augmentedModel) {
		val Model model = augmentedModel.getModel();
		val List<Tuple> tupleNodes = augmentedModel.getAllTuples();
		
		// Backpropagate from every tuple node (except illegal nested tuples)
		for (Tuple tuple : tupleNodes) {
			backpropagateSize(tuple, tuple.getElements().size());
		}
		
		for (String predefinedFunctionName : predefinedFunctionCallsMap.keySet()) {
			val FunctionSignature signature = predefinedFunctionsMap.get(predefinedFunctionName);
			val int returnSize = signature.getReturnSize();
			val ArrayList<Integer> parameterSizes = signature.getParameterSizes();
			
			// Backpropagate from every predefined function call node that returns a tuple
			if (returnSize > 1) {
				for (FunctionCall call : predefinedFunctionCallsMap.get(predefinedFunctionName)) {
					backpropagateSize(call, returnSize);
				}
			}
			
			// Fill size for every argument in a predefined function call node
			for (FunctionCall call : predefinedFunctionCallsMap.get(predefinedFunctionName)) {
				
				val Iterator<Expression> argumentsIterator = call.getArguments().iterator();
				val Iterator<Integer> parameterSizesIterator = parameterSizes.iterator();
				
				while (argumentsIterator.hasNext() && parameterSizesIterator.hasNext()) {
					val EObject argument = argumentsIterator.next();
					val int parameterSize = parameterSizesIterator.next();
					fillSize(argument, parameterSize);
				}
				
			}
			
		}
		
		// Label all remaining nodes with their corresponding default size
		fillDefaultSizes(model);
	}
	
	new(AugmentedModel augmentedModel) {
		this.types = augmentedModel.getTypes();
		this.sizes = new HashMap<EObject, Integer>();
		
		// Get all maps needed to traverse the syntax tree easily
		this.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();	
		this.predefinedFunctionCallsMap = augmentedModel.getAllPredefinedFunctionCalls();
		this.userFunctionsMap = augmentedModel.getAllUserFunctions();
		this.userFunctionCallsMap = augmentedModel.getAllUserFunctionCalls();
		this.variablesMap = augmentedModel.getAllVariables();
		this.witnessesMap = augmentedModel.getAllWitnesses();
		this.localVariablesMap = augmentedModel.getAllLocalVariables();
		this.parametersMap = augmentedModel.getAllParameters();
		this.argumentsMap = augmentedModel.getAllArguments();
		
		inferSizes(augmentedModel);
	}
	
}