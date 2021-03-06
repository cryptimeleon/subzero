package org.cryptimeleon.subzero.inference;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.FunctionSignature;
import org.cryptimeleon.subzero.model.ModelUtils;
import org.cryptimeleon.subzero.model.TreeTraversals;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.predefined.PredefinedUtils;
import org.cryptimeleon.subzero.subzero.Argument;
import org.cryptimeleon.subzero.subzero.Brackets;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.ConstantVariable;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Negative;
import org.cryptimeleon.subzero.subzero.PPVariable;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Sum;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.cryptimeleon.subzero.subzero.WitnessVariable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * The size inference algorithm determines the multiplicity of every node
 * in the parse tree (where 1 is a scalar, >1 is a tuple, and 0 means that multiplicity is not
 * applicable). The algorithm performs the following steps:
 * 1. 
 * 
 * 
 * 
 * 3. A top-down traversal of the parse tree labels all unlabeled nodes as scalars
 */
public class SizeInferenceProvider implements InferenceProvider<EObject, Integer> {

	private Map<EObject, Integer> sizes;

	private Map<EObject, Type> types;

	private Map<String, Witness> witnesses;
	private Map<String, PublicParameter> publicParameters;
	private Map<String, List<Variable>> variables;

	private Map<String, FunctionDefinition> userFunctionDefinitions;
	private Map<String, List<FunctionCall>> userFunctionCalls;
	private Map<String, Map<String, List<LocalVariable>>> localVariables;
	private Map<String, Map<String, Parameter>> parameters;
	private Map<String, Map<String, List<Argument>>> arguments;

	private Map<String, FunctionSignature> predefinedFunctionSignatures;
	private Map<String, List<FunctionCall>> predefinedFunctionCalls;
	
	public new() {
		// Intentionally blank
	}

	override public Map<EObject, Integer> infer(AugmentedModel augmentedModel) {
		sizes = new HashMap<EObject, Integer>();
		
		// Get type information
		types = augmentedModel.getTypes();
		
		// Get all maps needed to traverse the syntax tree easily
		predefinedFunctionSignatures = PredefinedUtils.getPredefinedFunctionSignatures();
		predefinedFunctionCalls = augmentedModel.getPredefinedFunctionCalls();
		userFunctionDefinitions = augmentedModel.getUserFunctionDefinitions();
		userFunctionCalls = augmentedModel.getUserFunctionCalls();
		variables = augmentedModel.getGlobalVariables();
		witnesses = augmentedModel.getWitnesses();
		publicParameters = augmentedModel.getPublicParameters();
		localVariables = augmentedModel.getLocalVariables();
		parameters = augmentedModel.getUserFunctionParameters();
		arguments = augmentedModel.getUserFunctionArguments();
		
		inferSizes(augmentedModel);
		return sizes;
	}

		/*
	 * For every tuple, get the tuple's size and propagate that size upwards through the parse tree,
	 * until it reaches a node that does not have a multiplicity
	 * 
	 * 
	 * 
	 * Finally, perform a top-down traversal of the parse tree, labeling all unlabeled nodes as scalars (size 1)
	 */
	def private void inferSizes(AugmentedModel augmentedModel) {
		val Model model = augmentedModel.getModel();
		
		val List<Tuple> tuples = augmentedModel.getTuples();
		
		// Backpropagate from every tuple node (except illegal nested tuples)
		for (Tuple tuple : tuples) {
			backpropagateSize(tuple, tuple.getElements().size());
		}
		
		for (Entry<String, List<FunctionCall>> entry : predefinedFunctionCalls.entrySet()) {
			val String functionName = entry.getKey();
			val List<FunctionCall> functionCalls = entry.getValue();
			
			val FunctionSignature signature = predefinedFunctionSignatures.get(functionName);
			val int returnSize = signature.getReturnSize();
			val List<Integer> parameterSizes = signature.getParameterSizes();
			
			// Backpropagate from every predefined function call node that returns a tuple
			if (returnSize > 1) {
				for (FunctionCall call : functionCalls) {
					backpropagateSize(call, returnSize);
				}
			}
			
			// Fill size for every argument in a predefined function call node
			for (FunctionCall call : functionCalls) {
				val Iterator<Expression> argumentsIter = call.getArguments().iterator();
				val Iterator<Integer> parameterSizesIter = parameterSizes.iterator();
				
				while (argumentsIter.hasNext() && parameterSizesIter.hasNext()) {
					val EObject argument = argumentsIter.next();
					val int parameterSize = parameterSizesIter.next();
					fillSize(argument, parameterSize);
				}
			}
		}
		
		// Label all remaining nodes with the default size (1)
		fillDefaultSize(model);
	}
	
	def private void backpropagateSize(EObject node, int size) {
		if (node === null) return;

		// If the node has already been labeled, return
		if (sizes.containsKey(node)) return;
		
		sizes.put(node, size);
		
		// Stop backpropagating when root, disjunction, or conjunction is reached
		val EObject parent = node.eContainer();
		if (
			parent instanceof Model ||
			parent instanceof Disjunction ||
			parent instanceof Conjunction
		) return;
		
		switch parent {
			Comparison: {
				fillSize(parent.getLeft(), size);
				fillSize(parent.getRight(), size);
				// Do not backpropagate further
			}
			
			Sum: {
				fillSize(parent.getLeft(), size);
				fillSize(parent.getRight(), size);
				backpropagateSize(parent, size);
			}
			
			Product: {
				if (types.get(parent) === Type.GROUP_ELEMENT) {
					fillSize(parent.getLeft(), size);
					fillSize(parent.getRight(), size);
				}
				backpropagateSize(parent, size);
			}
			
			Power: {
				val String subtree = node.eContainmentFeature().getName();
				if (subtree == "left") {
					backpropagateSize(parent, size);
				}
			}
			
			Negative: {
				backpropagateSize(parent, size);
			}
			
			FunctionDefinition: {
				sizes.put(parent, size);
				
				val List<FunctionCall> functionCalls = userFunctionCalls.get(parent.getName());
				if (functionCalls !== null) {
					for (FunctionCall call : functionCalls) {
						backpropagateSize(call, size);
					}
				}
			}
			
			Argument: {
				val String functionName = ModelUtils.getArgumentFunction(parent);
				
				sizes.put(parent, size);
				
				val FunctionDefinition functionDef = userFunctionDefinitions.get(functionName);
				if (functionDef !== null) {
					val EList<Parameter> parameters = functionDef.getParameters();
					val int index = ModelUtils.getArgumentIndex(parent);
					
					if (index < parameters.size()) {
						fillSize(parameters.get(index), size);
					}
				}
			}
			
			Brackets: {
				backpropagateSize(parent, size);
			}
		}
	}
	
	// Sets the size of a node and all of its descendants to a given size
	def private void fillSize(EObject node, int size) {
		if (node === null) return;
		
		// If the node is already labeled, all of its descendants are also labeled, so return
		if (sizes.containsKey(node)) return;
		
		sizes.put(node, size);
		
		if (node instanceof Tuple) return;
		
		switch node {
			Product: {
				if (types.get(node) === Type.GROUP_ELEMENT) {
					fillSize(node.getLeft(), size);
					fillSize(node.getRight(), size);
				}
			}
			
			Power: {
				fillSize(node.getLeft(), size);
			}
			
			WitnessVariable: {
				fillSize(witnesses.get(node.getName()), size);
			}
			
			PPVariable: {
				fillSize(publicParameters.get(node.getName()), size);
			}
			
			ConstantVariable: {
				for (Variable variable : variables.get(node.getName())) {
					backpropagateSize(variable, size);
				}
			}
			
			Witness: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable: variables.get(node.getName())) {
					backpropagateSize(variable, size);
				}
			}
			
			PublicParameter: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable : variables.get(node.getName())) {
					backpropagateSize(variable, size);
				}
			}
			
			FunctionDefinition: {
				// For every function call to this function, perform backpropagation
				val List<FunctionCall> functionCalls = userFunctionCalls.get(node.getName());
				if (functionCalls !== null) {
					for (FunctionCall call : functionCalls) {
						backpropagateSize(call, size);
					}
				}

				fillSize(node.getBody(), size);
			}
			
			LocalVariable: {
				fillSize(parameters.get(node.getFunction()).get(node.getName()), size);
			}
			
			FunctionCall: {
				val FunctionDefinition function = userFunctionDefinitions.get(node.getName());
				if (function !== null) {
					fillSize(function, size);
				}
			}
			
			Parameter: {
				val String functionName = ModelUtils.getParameterFunction(node);
				val String parameterName = node.getName();

				// For each local variable with the same name in the same function, perform backpropagation
				for (LocalVariable localVariable : localVariables.get(functionName).get(parameterName)) {
					backpropagateSize(localVariable, size);
				}
				
				// For each argument in a corresponding function call that becomes the corresponding parameter, perform fill
				for (Argument argument : arguments.get(functionName).get(parameterName)) {
					fillSize(argument, size);					
				}
			}
			
			default: {
				for (EObject child : node.eContents()) {
					fillSize(child, size);
				}
			}
		}
	}
	
	// Labels all remaining nodes with size 1 (scalar)
	def private void fillDefaultSize(Model model) {
		TreeTraversals.preorderTraversal(model.getProof(), [EObject node |
			sizes.putIfAbsent(node, 1);
		]);
		
		for (FunctionDefinition function : model.getFunctions()) {
			sizes.putIfAbsent(function, 1);
			
			TreeTraversals.preorderTraversal(function.getBody(), [EObject node |
				sizes.putIfAbsent(node, 1);
			]);
			
			for (Parameter parameter : function.getParameters()) {
				sizes.putIfAbsent(parameter, 1);
			}
		}
		
		for (Witness witness : model.getWitnesses()) {
			sizes.putIfAbsent(witness, 1);
		}
		
		for (PublicParameter publicParameter : model.getPublicParameters()) {
			sizes.putIfAbsent(publicParameter, 1);
		}
	}
	
}