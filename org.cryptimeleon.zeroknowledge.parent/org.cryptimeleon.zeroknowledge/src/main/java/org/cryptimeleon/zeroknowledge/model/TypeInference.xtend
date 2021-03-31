package org.cryptimeleon.zeroknowledge.model

import java.util.HashMap
import java.util.HashSet
import java.util.Iterator
import java.util.List
import java.util.Map
import java.util.Set
import org.cryptimeleon.zeroknowledge.predefined.PredefinedFunctionsHelper
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Brackets
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Conjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Disjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Expression
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.LocalVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Power
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Product
import org.cryptimeleon.zeroknowledge.zeroKnowledge.StringLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * Performs type inference on the parsed model tree
 */
 
/*
 * The type inference algorithm determines the type of every node in
 * the parse tree. The algorithm performs the following steps:
 * 1. A top-down traversal of the proof parse tree identifies all BOOLEAN nodes, all STRING nodes,
 *    and some EXPONENT nodes where possible
 * 2. A top-down traversal of each function body's parse tree performs the same thing
 * 
 * 
 * The size inference algorithm determines the multiplicity of every node
 * in the parse tree (where 1 is a scalar, >1 is a tuple, and 0 means that multiplicity is not
 * applicable). The algorithm performs the following steps:
 * 1. 
 * 
 * 
 * 
 * 3. A top-down traversal of the parse tree labels all unlabeled nodes as scalars
 */
package class TypeInference {
	
	// Stores the inferred Type for every node in the syntax tree that requires a type
	Map<EObject, Type> types;
	
	// Stores whether a node has been visited (inference has been attempted)
	// Used only during type inference
	Set<EObject> visited;
	
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
	
	def Map<EObject, Type> getTypes() {
		return types;
	}
	
	def Type getNodeType(EObject node) {
		return types.get(node);
	}
	
	// Labels nodes in the syntax tree in a topdown traversal
	def private Type topdownInference(EObject node) {
		if (node === null) return Type.UNKNOWN;
		
		var Type label;
		
		// If node is already labeled, ignore it and return its type
		if (types.containsKey(node)) {
			return types.get(node);
		}
		
		// Node has now been visited
		visited.contains(node)
		
		// Try to label the node
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
		
		// Try to label the child nodes, and label the original node if possible
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

				if (node.getOperation2() === null) {
					if (leftChildLabel === Type.EXPONENT || rightChildLabel === Type.EXPONENT) {
						fillExponent(node.getLeft());
						fillExponent(node.getRight());
					}
				} else {
					val Type extraChildLabel = topdownInference(node.getExtra());
					if (leftChildLabel === Type.EXPONENT || rightChildLabel == Type.EXPONENT || extraChildLabel === Type.EXPONENT) {
						fillExponent(node.getLeft());
						fillExponent(node.getRight());
						fillExponent(node.getExtra());
					}
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
		
		// Return the node's label, so that the parent node can be labeled if it is still unlabeled
		return label ?: Type.UNKNOWN;
	}
	
	// Labels a node and all its relevant descendants as EXPONENT
	def private void fillExponent(EObject node) {
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;
		
		// Node has now been visited
		visited.contains(node);
		
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
	def private void backpropagateType(EObject node) {
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;

		// Node has now been visited
		visited.contains(node);

		// Label the node		
		types.put(node, Type.EXPONENT);
		
		// If parent is root, or parent is labeled EXPONENT already stop
		val EObject parent = node.eContainer();
		if (parent instanceof Model) return;
		if (types.get(parent) === Type.EXPONENT) return;
		
		// If parent has not been visited, stop
		// Topdown inference will handle the propagation instead
		if (!visited.contains(parent)) return;
		
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
	def private void labelPredefinedFunctionCall(FunctionCall call) {
		val FunctionSignature signature = predefinedFunctionsMap.get(call.getName());
		
		visited.contains(call);
		types.put(call, signature.getReturnType());
		
		val Iterator<Type> parameterTypeIterator = signature.getParameterTypes().iterator();
		val Iterator<Expression> argumentIterator = call.getArguments().iterator();
		
		while (parameterTypeIterator.hasNext() && argumentIterator.hasNext()) {
			val Type parameterType= parameterTypeIterator.next();
			val EObject argument = argumentIterator.next();
			
			switch parameterType {
				case Type.STRING: {
					visited.contains(argument);
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
	def private void fillGroupElement(Model model) {
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
	def private void setGroupElement(EObject node) {
		if (!types.containsKey(node)) {
			visited.contains(node);
			types.put(node, Type.GROUP_ELEMENT);
		}
	}
	
	// Perform type inference on the syntax tree	
	def private void inferTypes(AugmentedModel augmentedModel) {
		val Model model = augmentedModel.getModel();
		
		// Perform topdown inference, labeling BOOLEAN and STRING nodes, and as many EXPONENT nodes as possible
		topdownInference(model);
		
		// Perform topdown inference on any user functions which were not called anywhere in the proof expression
		for (FunctionDefinition function : model.getFunctions()) {
			topdownInference(function.getBody());
		}

		// Label all remaining nodes which require a type as GROUP_ELEMENT
		fillGroupElement(model);
		
		return;
	}

	
	new(AugmentedModel augmentedModel) {
		this.types = new HashMap<EObject, Type>();
		this.visited = new HashSet<EObject>();
		
		// Model transformations to simplify the model
		augmentedModel.removeBrackets();
		augmentedModel.normalizeNegatives();
		
		// Replaces Variable nodes with LocalVariable nodes when the node references a function Parameter
		// Replaces Variable nodes with WitnessVariable nodes when the node references a Witness
		augmentedModel.identifySpecialVariables();

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
		
		inferTypes(augmentedModel);
	}
	
}