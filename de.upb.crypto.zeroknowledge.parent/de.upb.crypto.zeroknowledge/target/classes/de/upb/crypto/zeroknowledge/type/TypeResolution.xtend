package de.upb.crypto.zeroknowledge.type

import java.util.HashMap
import java.util.ArrayList
import java.util.Iterator

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList

import de.upb.crypto.zeroknowledge.predefined.PredefinedFunctionsHelper

import de.upb.crypto.zeroknowledge.model.FunctionSignature
import de.upb.crypto.zeroknowledge.model.ModelHelper
import de.upb.crypto.zeroknowledge.model.ModelMap

import de.upb.crypto.zeroknowledge.zeroKnowledge.Model
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument

class TypeResolution {
	
	// Stores the resolved Type for every node in the syntax tree that requires a type
	static HashMap<EObject, Type> types;
	
	// Stores the resolved multiplicity for every node in the syntax tree that requires a multiplicity
	// Multiplicity of 1 is a scalar, multiplicity greater than 1 is a tuple
	static HashMap<EObject, Integer> sizes;
	
	// Stores whether a node has been visited (inference has been attempted)
	// Used only during Type resolution
	static HashMap<EObject, Boolean> visited;
	
	// A map from predefined function names to the function signature
	static HashMap<String, FunctionSignature> predefinedFunctionsMap;
	
	// A map from function names to user function calls
	static HashMap<String, ArrayList<FunctionCall>> predefinedFunctionCallsMap;
	
	// A map from function names to user function definition nodes
	static HashMap<String, FunctionDefinition> userFunctionsMap;
	
	// A map from function names to user function calls
	static HashMap<String, ArrayList<FunctionCall>> userFunctionCallsMap;
	
	// A map from variable names to variable nodes
	static HashMap<String, ArrayList<Variable>> variablesMap;
	
	// A map from witness names to witness nodes
	static HashMap<String, Witness> witnessesMap;
	
	// A map from function names and parameter names to local variable nodes
	static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariablesMap;
	
	// A map from user function names and local variable names to the corresponding Parameter node in the function definition
	static HashMap<String, HashMap<String, Parameter>> parametersMap;	
	
	// A map from user function names and parameter names to corresponding arguments in function calls
	static HashMap<String, HashMap<String, ArrayList<Argument>>> argumentsMap;
	
	def static HashMap<EObject, Type> getTypes() {
		return types;
	}
	
	def static HashMap<EObject, Integer> getSizes() {
		return sizes;
	}
	
	// Labels nodes in the syntax tree in a topdown traversal
	def private static Type topdownInference(EObject node) {
		var Type label;
		
		// If node is already labeled, ignore it and return its type
		if (types.containsKey(node)) {
			return types.get(node);
		}
		
		// Node has now been visited
		visited.put(node, true)
		
		// Try to label the parent node
		switch node {
			Model: {
				return topdownInference(node.getProof());
			}
			Conjunction: {
				label = Type.BOOLEAN;
				types.put(node, label);
			}
			Disjunction: {
				label = Type.BOOLEAN;
				types.put(node, label);
			}
			Comparison: {
				label = Type.BOOLEAN;
				types.put(node, label);
			}
			NumberLiteral: {
				label = Type.EXPONENT;
				types.put(node, label);
			}
			StringLiteral: {
				label = Type.STRING;
				types.put(node, label);
			}
			Sum: {
				label = Type.EXPONENT;
				types.put(node, label);
			}
			Negative: {
				label = Type.EXPONENT;
				types.put(node, label);
			}
			default: {}
		}
		
		// If possible, label all descendants at once
		switch node {
			Sum: {
				fillExponent(node.getLeft());
				fillExponent(node.getRight());
			}
			Power: {
				fillExponent(node.getRight());
			}
			Negative: {
				fillExponent(node.getTerm());
			}
			default: {}
		}
		
		// Try to label the child nodes, and label the parent if possible
		switch node {
			Power: {
				val Type leftChildLabel = topdownInference(node.getLeft());
				label = leftChildLabel;
				if (leftChildLabel !== Type.UNKNOWN) {
					types.put(node, label);
				}
			}
			Brackets: {
				val Type childLabel = topdownInference(node.getContent());
				label = childLabel;
				if (childLabel !== Type.UNKNOWN) {
					types.put(node, label);
				}
			}
			Product: {
				val Type leftChildLabel = topdownInference(node.getLeft());
				val Type rightChildLabel = topdownInference(node.getRight());
				if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.EXPONENT) {
					label = Type.EXPONENT;
					types.put(node, label);
				} else if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.UNKNOWN) {
					label = Type.EXPONENT;
					types.put(node, label);
					fillExponent(node.getRight());
				} else if (leftChildLabel === Type.UNKNOWN && rightChildLabel === Type.EXPONENT) {
					label = Type.EXPONENT
					types.put(node, label);
					fillExponent(node.getLeft());
				}
			}
			Comparison: {
				val Type leftChildLabel = topdownInference(node.getLeft());
				val Type rightChildLabel = topdownInference(node.getRight());

				if (leftChildLabel === Type.EXPONENT && rightChildLabel === Type.UNKNOWN) {
					fillExponent(node.getRight());
				} else if (leftChildLabel === Type.UNKNOWN && rightChildLabel === Type.EXPONENT) {
					fillExponent(node.getLeft());
				}
			}
			FunctionCall: {
				val String functionName = node.getName();
				if (userFunctionsMap.containsKey(functionName)) {
					val FunctionDefinition function = userFunctionsMap.get(functionName);
					val Type bodyLabel = topdownInference(function);
					if (bodyLabel !== Type.UNKNOWN) {
						label = bodyLabel;
						types.put(node, label);
					}
					
					for (Expression argument : node.getArguments()) {
						topdownInference(argument);
					}
				} else if (predefinedFunctionsMap.containsKey(functionName)) {
					labelPredefinedFunctionCall(node);
				}
			}
			Argument: {
				val Type childLabel = topdownInference(node.getExpression());
				label = childLabel;
				if (childLabel !== Type.UNKNOWN) {
					types.put(node, childLabel);
					if (childLabel === Type.EXPONENT) {
						val String functionName = ModelHelper.getArgumentFunction(node);
						if (userFunctionsMap.containsKey(functionName)) {
							val EList<Parameter> parameters = userFunctionsMap.get(functionName).getParameterList().getParameters();
							val int index = ModelHelper.getArgumentIndex(node);
							
							if (index < parameters.size()) {
								fillExponent(parameters.get(index));
							}
						}
					}
				}
			}
			FunctionDefinition: {
				label = topdownInference(node.getBody());
				if (label !== Type.UNKNOWN) {
					types.put(node, label);
				}
			}
			Tuple: {
				var boolean labeled = false;
				val Iterator<Expression> elementIterator = node.getElements().iterator();
				
				while (elementIterator.hasNext() && !labeled) {
					val EObject nextElement = elementIterator.next();
					val Type elementLabel = topdownInference(nextElement);
					if (elementLabel === Type.EXPONENT) {
						label = elementLabel;
						types.put(node, elementLabel);
						labeled = true;
						for (EObject element : node.getElements()) {
							fillExponent(element);	
						}
					}
				}
			}
			default: {
				for (EObject child : node.eContents()) {
					topdownInference(child);
				}
			}
		}
		
		// If still unlabeled, try to label the parent node
		return label ?: Type.UNKNOWN;
	}
	
	// Labels a node and all its relevant descendants as EXPONENT
	def private static void fillExponent(EObject node) {
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;
		
		// Node has now been visited
		visited.put(node, true);
		
		// Label node as exponent
		types.put(node, Type.EXPONENT);
		
		switch node {
			Parameter: {
				val String functionName = (node.eContainer().eContainer() as FunctionDefinition).getName();
				val String parameterName = node.getName();
				// For each local variable with the same name in the same function, perform backpropagation
				for (LocalVariable localVariable : localVariablesMap.get(functionName).get(parameterName)) {
					backpropagateType(localVariable);					
				}
				
				// For each argument in a corresponding function call that becomes the corresponding parameter, perform fill
				for (EObject argument : argumentsMap.get(functionName).get(parameterName)) {
					fillExponent(argument);
				}
			}
			Witness: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable: variablesMap.get(node.getName())) {
					backpropagateType(variable);
				}
			}
			LocalVariable: {
				val String functionName = node.getFunction();
				val String localName = node.getName();
				val Parameter parameter = parametersMap.get(functionName).get(localName);
				
				// Invariant: if parameter is labeled, then all corresponding local variables have been labeled
				// and all corresponding arguments in function calls have been labeled
				// Thus if the parameter is already labeled, there is no need to try to label these
				fillExponent(parameter);
			}
			WitnessVariable: {
				// Invariant: if witness is labeled, then all corresponding witness variables have been labeled
				// Thus if the witness is already labeled, there is no need to try to label these
				fillExponent(witnessesMap.get(node.getName()));
			}
			Variable: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable : variablesMap.get(node.getName())) {
					backpropagateType(variable);
				}
			}
			FunctionCall: {
				if (userFunctionsMap.containsKey(node.getName())) {
					// The node is a function call to a user function
					// Label the corresponding function definition
					fillExponent(userFunctionsMap.get(node.getName()));
				}				
			}
			FunctionDefinition: {
				// For every function call to this function, perform backpropagation
				for (FunctionCall call : userFunctionCallsMap.get(node.getName())) {
					backpropagateType(call);
				}
				
				fillExponent(node.getBody());
			}
			default: {
				for (EObject child : node.eContents()) {
					fillExponent(child);
				}
			}
		}
	}
	
	// Labels a node as EXPONENT, and attempts to propagate this label upwards in the syntax tree
	def private static void backpropagateType(EObject node) {
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;

		// Node has now been visited
		visited.put(node, true);

		// Label the node		
		types.put(node, Type.EXPONENT);
		
		// If parent is root, or parent is labeled EXPONENT already stop
		val EObject parent = node.eContainer();
		if (parent instanceof Model) return;
		if (types.get(parent) === Type.EXPONENT) return;
		
		// If parent has not been visited, stop
		// Topdown inference will handle the propagation instead
		if (!visited.containsKey(parent)) return;
		
		switch parent {
			Comparison: {
				val String feature = node.eContainmentFeature().getName();
				if (feature === "left") {
					fillExponent(parent.getRight());
				} else if (feature === "right") {
					fillExponent(parent.getLeft());
				}
			}
			Product: {
				if (!types.containsKey(parent)) {
					val String feature = node.eContainmentFeature().getName();
					if (feature === "left") {
						fillExponent(parent.getRight());
					} else if (feature === "right") {
						fillExponent(parent.getLeft());
					}
					backpropagateType(parent); 
				}
			}
			Power: {
				if (!types.containsKey(parent)) {
					val String feature = node.eContainmentFeature().getName();
					if (feature === "left") {
						fillExponent(parent.getRight());	
						backpropagateType(parent);
					}
				}
			}
			Brackets: {
				backpropagateType(parent);
			}
			FunctionDefinition: {
				for (FunctionCall call : userFunctionCallsMap.get(parent.getName())) {
					backpropagateType(call);
				}
			}
			FunctionCall: {
				if (userFunctionsMap.containsKey(parent.getName())) {
					val int index = parent.getArguments().indexOf(node);
					val FunctionDefinition function = userFunctionsMap.get(parent.getName());
					
					if (function.getParameterList().getParameters().size() > index) {
						fillExponent(function.getParameterList().getParameters().get(index));
					}
				}
			}
			Argument: {
				backpropagateType(parent);
			}
			Tuple: {
				if (!types.containsKey(parent)) {
					for (EObject element : parent.getElements()) {
						fillExponent(element);
					}
					backpropagateType(parent);
				}
			}
		}
	}
	
	// Precondition: function call references a predefined function
	// Labels a predefined function call and all of its arguments
	def private static void labelPredefinedFunctionCall(FunctionCall call) {
		val FunctionSignature signature = predefinedFunctionsMap.get(call.getName());
		
		visited.put(call, true);
		types.put(call, signature.getReturnType());
		
		val Iterator<Type> parameterTypeIterator = signature.getParameterTypes().iterator();
		val Iterator<Expression> argumentIterator = call.getArguments().iterator();
		
		while (parameterTypeIterator.hasNext() && argumentIterator.hasNext()) {
			val Type parameterType= parameterTypeIterator.next();
			val EObject argument = argumentIterator.next();
			
			switch parameterType {
				case Type.STRING: {
					visited.put(argument, true);
					types.put(argument, Type.STRING);
				}
				case Type.EXPONENT: {
					fillExponent(argument);
				}
				case Type.GROUP_ELEMENT: {
					topdownInference(argument);
					// fillGroupElement will be called on this node eventually to label it GROUP_ELEMENT
				}
				default: {}
			}
		}
	}
	
	// All unlabeled nodes that require a type label are labeled with GROUP_ELEMENT as the default
	def private static void fillGroupElement(Model model) {
		for (FunctionDefinition function : model.getFunctions()) {
			setGroupElement(function);
			
			for (Parameter parameter : function.getParameterList().getParameters()) {
				setGroupElement(parameter);
			}
			
			ModelMap.preorder(function.getBody(), [EObject node |
				setGroupElement(node);
			]);
		}
		
		for (Witness witness : model.getWitnessList().getWitnesses()) {
			setGroupElement(witness);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			setGroupElement(node);
		]);
	}
	// Helper function for fillGroupElement
	def private static void setGroupElement(EObject node) {
		if (!types.containsKey(node)) {
			visited.put(node, true);
			types.put(node, Type.GROUP_ELEMENT);
		}
	}

	
	def private static void backpropagateSize(EObject node, int size) {
		
		if (sizes.containsKey(node)) return;
		
		sizes.put(node, size);
		
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
	
	def private static void fillSize(EObject node, int size) {
		
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
	def private static void fillDefaults(Model model) {
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
	def static void fillDefaultsHelper(EObject node) {
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

	// Perform Type resolution on the syntax tree	
	def static void resolveTypes(Model model) {
		TypeResolution.types = new HashMap<EObject, Type>();
		TypeResolution.sizes = new HashMap<EObject, Integer>();
		TypeResolution.visited = new HashMap<EObject, Boolean>();
		
		// Model transformations to simplify the model
		ModelHelper.removeBrackets(model);
		ModelHelper.normalizeNegatives(model);
		// Replaces Variable nodes with LocalVariable nodes when the node references a function Parameter
		// Replaces Variable nodes with WitnessVariable nodes when the node references a Witness
		ModelHelper.identifySpecialVariables(model);

		// Create all HashMaps needed to traversal the syntax tree easily
		TypeResolution.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		TypeResolution.predefinedFunctionCallsMap = ModelHelper.getAllPredefinedFunctionCalls(model, predefinedFunctionsMap);
		TypeResolution.userFunctionsMap = ModelHelper.getAllUserFunctions(model);
		TypeResolution.userFunctionCallsMap = ModelHelper.getAllUserFunctionCalls(model, userFunctionsMap);
		TypeResolution.variablesMap = ModelHelper.getAllVariables(model);
		TypeResolution.witnessesMap = ModelHelper.getAllWitnesses(model);
		TypeResolution.localVariablesMap = ModelHelper.getAllLocalVariables(model);
		TypeResolution.parametersMap = ModelHelper.getAllParameters(model);
		TypeResolution.argumentsMap = ModelHelper.getAllArguments(model, userFunctionsMap);
		
		val ArrayList<Tuple> tupleNodes = ModelHelper.getAllTuples(model);
		
		// Perform topdown inference, labeling BOOLEAN and STRING nodes, and as many EXPONENT nodes as possible
		topdownInference(model);
		
		// Perform topdown inference on any user functions which were not called anywhere in the proof expression
		for (FunctionDefinition function : model.getFunctions()) {
			topdownInference(function.getBody());
		}

		// Label all remaining nodes which require a type as GROUP_ELEMENT
		fillGroupElement(model);
		
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
		fillDefaults(model);
		
		return;
	}
	
}