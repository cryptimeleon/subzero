package org.cryptimeleon.subzero.model

import java.util.HashMap
import java.util.HashSet
import java.util.Iterator
import java.util.List
import java.util.Map
import java.util.Set
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
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
import org.cryptimeleon.subzero.subzero.NumberLiteral
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
import org.cryptimeleon.subzero.subzero.PublicParameter
import org.cryptimeleon.subzero.subzero.PPVariable
import org.cryptimeleon.subzero.subzero.ConstantVariable
import java.util.Map.Entry

/**
 * Performs type inference on the parsed model tree
 */
 
/*
 * The type inference algorithm determines the type of every node in
 * the parse tree. It works by identifying all BOOLEAN and STRING nodes (trivially), and then using
 * many inference rules to label nodes as EXPONENT based on the node's context. All remaining unlabeled
 * nodes after this are then labeled as GROUP_ELEMENT.
 * 
 * The following inference rules are used:
 * - All Disjunction, Conjunction, and Comparison nodes are labeled as BOOLEAN.
 * 
 * - All StringLiteral nodes are labeled as STRING.

 * - NumberLiteral nodes are EXPONENT.
 * 
 * - Negative nodes are EXPONENT, and all descendant nodes are EXPONENT.
 * 
 * - Sum nodes are EXPONENT, and all descendant nodes of left and right subtrees are EXPONENT.

 * - If one child of a Product node is EXPONENT, then all descendants of both subtrees, as well as
 *   the product node, are labeled as EXPONENT.
 * 
 * - All descendants of the right subtree of a Power node are labeled as EXPONENT.
 * 
 * - If the left child of a Power node is EXPONENT, then the Power node is labeled as EXPONENT.
 * 
 * - If a Comparison node is an inequality (uses the <, >, <=, >= operators) then all descendant nodes of
 *   left and right subtress are EXPONENT.
 * 
 * - If a Comparison node is an equality (= operator) and one child is EXPONENT, then all descendants of
 *   both subtrees are labeled as EXPONENT.
 * 
 * - If a non-local Variable node (WitnessVariable, PPVariable, ConstantVariable) is labeled as EXPONENT,
 *   then all Variable nodes with the same name are labeled as EXPONENT, and the EXPONENT label is backpropagated
 *   up the parse tree from each of these nodes.
 * 
 * - If a LocalVariable node is labeled as EXPONENT, then all LocalVariable nodes in the same function with
 *   the same name are labeled as EXPONENT, the EXPONENT label is backpropagated up the parse tree from all
 *   of these nodes, and the corresponding Parameter node is labeled as EXPONENT.
 * 
 * - If a Parameter node of a function is labeled as EXPONENT, then all LocalVariable nodes in that function with
 * 	 the same name are labeled as EXPONENT, and all corresponding Argument nodes in all function calls referencing
 * 	 the same function are labeled as EXPONENT.
 * 
 * - If an Argument node in a function call is labeled as EXPONENT, then all descendants are labeled EXPONENT, and
 *   the corresponding Parameter node of that function is labeled as EXPONENT.
 * 
 * - If a FunctionDefinition is labeled as EXPONENT, then the root body node and its descendants are labeled as EXPONENT, and all
 * 	 FunctionCall nodes that reference the function are labeled as EXPONENT
 * 
 * - If the root body node of a function is EXPONENT, then the FunctionDefinition node is labeled EXPONENT.
 * 
 * - If a FunctionCall is labeled EXPONENT, then the EXPONENT label is backpropagated up the parse tree,
 * 	 and the FunctionDefinition referencing the same function is labeled as EXPONENT
 * 
 * - If any child of a Tuple node is labeled as EXPONENT, then all children nodes, as well as the Tuple node,
 * 	 are labeled as EXPONENT.
 * 
 * - All unlabeled nodes after inference are labeled as GROUP_ELEMENT

 */
package class TypeInference {
	
	// Stores the inferred Type for every node in the syntax tree that requires a type
	Map<EObject, Type> types;
	
	// Stores whether a node has been visited (inference has been attempted)
	// Used only during type inference
	Set<EObject> visited;
	
	// Model information necessary for type inference
	Map<String, FunctionSignature> predefinedFunctionsMap;
	Map<String, List<FunctionCall>> predefinedFunctionCallsMap;
	Map<String, FunctionDefinition> userFunctionsMap;
	Map<String, List<FunctionCall>> userFunctionCallsMap;
	Map<String, List<Variable>> variablesMap;
	Map<String, Witness> witnessesMap;
	Map<String, PublicParameter> publicParametersMap;
	Map<String, Map<String, List<LocalVariable>>> localVariablesMap;
	Map<String, Map<String, Parameter>> parametersMap;	
	Map<String, Map<String, List<Argument>>> argumentsMap;
	
	def Map<EObject, Type> getTypes() {
		return types;
	}
	
	def Type getNodeType(EObject node) {
		return types.get(node);
	}
	
	// Labels nodes in the syntax tree in a topdown traversal
	// Returns the label of the node
	def private Type topdownInference(EObject node) {
		if (node === null) return Type.UNKNOWN;
		
		// If node is already labeled, immediately return its type
		if (types.containsKey(node)) {
			return types.get(node);
		}
		
		// Node has now been visited
		visited.add(node);
		
		var Type label;
		
		// Try to label the node
		switch node {
			Model: {
				label = topdownInference(node.getProof());
			}

			Disjunction: {
				label = Type.BOOLEAN;
				types.put(node, label);
				
				topdownInference(node.getLeft());
				topdownInference(node.getRight());
			}
			
			Conjunction: {
				label = Type.BOOLEAN;
				types.put(node, label);
				
				topdownInference(node.getLeft());
				topdownInference(node.getRight());
			}
			
			Comparison: {
				label = Type.BOOLEAN;
				types.put(node, label);
				
				val String operation = node.getOperation();
				if (operation == "=" || operation == "!=") {
					val leftChildLabel = topdownInference(node.getLeft());
					val centerChildLabel = topdownInference(node.getCenter());
					val rightChildLabel = topdownInference(node.getRight());
					
					if (leftChildLabel === Type.EXPONENT ||
						centerChildLabel === Type.EXPONENT ||
						rightChildLabel === Type.EXPONENT
					) {
						fillExponent(node.getLeft());
						fillExponent(node.getCenter());
						fillExponent(node.getRight());
					}
					
				} else {
					fillExponent(node.getLeft());
					fillExponent(node.getCenter());
					fillExponent(node.getRight());
				}
			}
			
			Sum: {
				label = Type.EXPONENT;
				types.put(node, label);
				fillExponent(node.getLeft());
				fillExponent(node.getRight());
			}
			
			Product: {
				val Type leftChildLabel = topdownInference(node.getLeft());
				val Type rightChildLabel = topdownInference(node.getRight());
				
				if (leftChildLabel === Type.EXPONENT || rightChildLabel === Type.EXPONENT) {
					label = Type.EXPONENT;
					types.put(node, label);
					fillExponent(node.getLeft());
					fillExponent(node.getRight());
				}
			}
			
			Power: {
				val Type leftChildLabel = topdownInference(node.getLeft());
				label = leftChildLabel;
				if (leftChildLabel !== Type.UNKNOWN) types.put(node, label);
				
				fillExponent(node.getRight());
			}
			
			Negative: {
				label = Type.EXPONENT;
				types.put(node, label);
				fillExponent(node.getTerm());
			}
			
			Brackets: {
				val Type childLabel = topdownInference(node.getContent());
				label = childLabel;
				if (childLabel !== Type.UNKNOWN) types.put(node, label);
			}
			
			
			NumberLiteral: {
				label = Type.EXPONENT;
				types.put(node, label);
			}
			
			StringLiteral: {
				label = Type.STRING;
				types.put(node, label);
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
							val EList<Parameter> parameters = userFunctionsMap.get(functionName).getParameters();
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
				if (label !== Type.UNKNOWN) types.put(node, label);
			}
			
			Tuple: {
				var boolean hasExponentElement = false;
				val Iterator<Expression> elementIterator = node.getElements().iterator();
				
				while (elementIterator.hasNext() && !hasExponentElement) {
					val EObject nextElement = elementIterator.next();
					val Type elementLabel = topdownInference(nextElement);
					
					if (elementLabel === Type.EXPONENT) {
						hasExponentElement = true;
						
						label = elementLabel;
						types.put(node, elementLabel);
					}
				}
				
				if (hasExponentElement) {
					for (EObject element : node.getElements()) {
						fillExponent(element);	
					}
				}
			}
			
			Variable: {}
			
			default: {
				throw new IllegalArgumentException("Unrecognized node type");
			}
		}
		
		// Return the node's label, so that the parent node can be labeled if it is still unlabeled
		return label ?: Type.UNKNOWN;
	}
	
	// Labels a node and all its relevant descendants as EXPONENT
	def private void fillExponent(EObject node) {
		if (node === null) return;
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;
		
		// Node has now been visited
		visited.add(node);
		
		// Label node as exponent
		types.put(node, Type.EXPONENT);
		
		switch node {
			WitnessVariable: {
				// Invariant: if the witness node is labeled, then all corresponding witness variables have been labeled
				// Thus if the witness node is already labeled, there is no need to try to label these
				fillExponent(witnessesMap.get(node.getName()));
			}
			
			PPVariable: {
				// Invariant: if the public parameter node is labeled, then all corresponding public parameter variables have been labeled
				// Thus if the public parameter node is already labeled, there is no need to try to label these
				fillExponent(publicParametersMap.get(node.getName()));
			}
			
			ConstantVariable: {
				// For every common input variable with the same name, perform backpropagation
				for (Variable variable : variablesMap.get(node.getName())) {
					backpropagateExponent(variable);
				}
			}
			
			LocalVariable: {
				val String functionName = node.getFunction();
				val String localName = node.getName();
				val Parameter parameter = parametersMap.get(functionName).get(localName);
				
				// Invariant: if the parameter node is labeled, then all corresponding local variables have been labeled
				// and all corresponding arguments in function calls have been labeled
				// Thus if the parameter node is already labeled, there is no need to try to label these
				fillExponent(parameter);
			}
			
			
			Witness: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable: variablesMap.get(node.getName())) {
					backpropagateExponent(variable);
				}
			}
			
			PublicParameter: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable: variablesMap.get(node.getName())) {
					backpropagateExponent(variable);
				}
			}
			
			Parameter: {
				val String functionName = ModelHelper.getParameterFunction(node);
				val String parameterName = node.getName();
				
				// For each local variable with the same name in the same function, perform backpropagation
				for (LocalVariable localVariable : localVariablesMap.get(functionName).get(parameterName)) {
					backpropagateExponent(localVariable);					
				}
				
				// For each argument in a corresponding function call that becomes the corresponding parameter, perform fill exponent
				for (EObject argument : argumentsMap.get(functionName).get(parameterName)) {
					fillExponent(argument);
				}
			}
			
			FunctionCall: {
				val FunctionDefinition function = userFunctionsMap.get(node.getName());
				if (function !== null) {
					// The node is a function call to a user function
					// Label the corresponding function definition
					fillExponent(function);
				}
				// Else the node is a call to a predefined function, and the type is already known			
			}
			
			FunctionDefinition: {
				// For every function call to this function, perform backpropagation
				val List<FunctionCall> functionCalls = userFunctionCallsMap.get(node.getName());
				if (functionCalls !== null) {
					for (FunctionCall call : functionCalls) {
						backpropagateExponent(call);
					}
				}
				
				fillExponent(node.getBody());
			}
			
			default: {
				// Label all descendants as exponent as well
				for (EObject child : node.eContents()) {
					fillExponent(child);
				}
			}
		}
	}
	
	// Labels a node as EXPONENT, and attempts to propagate this label upwards in the syntax tree
	def private void backpropagateExponent(EObject node) {
		if (node === null) return;
		
		// If node is labeled already, stop
		if (types.containsKey(node)) return;

		// Node has now been visited
		visited.add(node);

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
			// The parent cannot be a Disjunction or Conjunction, as the parent will first
			// be a Comparison, which does not backpropagate further
			// The parent cannot be a Sum or Negative, as this will already be labeled as exponent
			
			Comparison: {
				val String subtree = node.eContainmentFeature().getName();
				if (subtree == "left") {
					fillExponent(parent.getRight());
				} else if (subtree == "right") {
					fillExponent(parent.getLeft());
				}
			}
			
			Product: {
				if (!types.containsKey(parent)) {
					val String subtree = node.eContainmentFeature().getName();
					if (subtree == "left") {
						fillExponent(parent.getRight());
					} else if (subtree == "right") {
						fillExponent(parent.getLeft());
					}
					backpropagateExponent(parent); 
				}
			}
			
			Power: {
				if (!types.containsKey(parent)) {
					val String subtree = node.eContainmentFeature().getName();
					if (subtree == "left") {
						fillExponent(parent.getRight());	
						backpropagateExponent(parent);
					}
				}
			}
			
			FunctionDefinition: {
				val List<FunctionCall> functionCalls = userFunctionCallsMap.get(parent.getName());
				if (functionCalls !== null) {
					for (FunctionCall call : functionCalls) {
						backpropagateExponent(call);
					}
				}
			}
			
			FunctionCall: {
				if (userFunctionsMap.containsKey(parent.getName())) {
					val int index = parent.getArguments().indexOf(node);
					val FunctionDefinition function = userFunctionsMap.get(parent.getName());
					
					val EList<Parameter> parameters = function.getParameters();
					if (parameters.size() > index) {
						fillExponent(parameters.get(index));
					}
				}
			}
			
			Argument: {
				backpropagateExponent(parent);
			}
			
			Tuple: {
				if (!types.containsKey(parent)) {
					for (EObject element : parent.getElements()) {
						fillExponent(element);
					}
					backpropagateExponent(parent);
				}
			}
			
			Brackets: {
				backpropagateExponent(parent);
			}
		}
	}
	
	// Precondition: function call references a predefined function
	// Labels a predefined function call and all of its arguments
	def private void labelPredefinedFunctionCall(FunctionCall call) {
		val FunctionSignature signature = predefinedFunctionsMap.get(call.getName());
		
		visited.add(call);
		types.put(call, signature.getReturnType());
		
		val Iterator<Type> parameterTypeIterator = signature.getParameterTypes().iterator();
		val Iterator<Expression> argumentIterator = call.getArguments().iterator();
		
		while (parameterTypeIterator.hasNext() && argumentIterator.hasNext()) {
			val Type parameterType= parameterTypeIterator.next();
			val EObject argument = argumentIterator.next();
			
			switch parameterType {
				case Type.STRING: {
					visited.add(argument);
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
			
			for (Parameter parameter : function.getParameters()) {
				setGroupElement(parameter);
			}
			
			ModelMap.preorder(function.getBody(), [EObject node |
				setGroupElement(node);
			]);
		}
		
		for (Witness witness : model.getWitnesses()) {
			setGroupElement(witness);
		}
		
		for (PublicParameter publicParameter : model.getPublicParameters()) {
			setGroupElement(publicParameter);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			setGroupElement(node);
		]);
	}
	
	// Helper function for fillGroupElement
	def private void setGroupElement(EObject node) {
		if (!types.containsKey(node)) {
			visited.add(node);
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
		
		// Backpropagate EXPONENT from any function call to an exponent predefined function
		for (Entry<String, FunctionSignature> entry : predefinedFunctionsMap.entrySet()) {
			val String functionName = entry.getKey();
			val FunctionSignature signature = entry.getValue();
			
			if (signature.getReturnType === Type.EXPONENT) {
				for (FunctionCall call : predefinedFunctionCallsMap.get(functionName)) {
					backpropagateExponent(call);
				}
			}
		}

		// Label all remaining nodes which require a type as GROUP_ELEMENT
		fillGroupElement(model);
		
		return;
	}

	
	new(AugmentedModel augmentedModel) {
		this.types = new HashMap<EObject, Type>();
		this.visited = new HashSet<EObject>();

		// Get all maps needed to traverse the syntax tree easily
		this.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();	
		this.predefinedFunctionCallsMap = augmentedModel.getPredefinedFunctionCallNodes();
		this.userFunctionsMap = augmentedModel.getUserFunctionNodes();
		this.userFunctionCallsMap = augmentedModel.getUserFunctionCallNodes();
		this.variablesMap = augmentedModel.getVariableNodes();
		this.witnessesMap = augmentedModel.getWitnessNodes();
		this.publicParametersMap = augmentedModel.getPublicParameterNodes();
		this.localVariablesMap = augmentedModel.getLocalVariableNodes();
		this.parametersMap = augmentedModel.getParameterNodes();
		this.argumentsMap = augmentedModel.getArgumentNodes();
		
		inferTypes(augmentedModel);
	}
	
}