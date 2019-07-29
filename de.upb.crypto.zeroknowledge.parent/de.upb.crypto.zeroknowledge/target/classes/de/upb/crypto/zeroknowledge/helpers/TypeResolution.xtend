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
import java.util.Iterator
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression

class TypeResolution {
	
	// Stores the resolved Type for every node in the syntax tree that requires a type
	static HashMap<EObject, Type> types;
	
	// Stores whether a node has been visited (inference has been attempted)
	static HashMap<EObject, Boolean> visited;
	
	// A map from predefined function names to the function signature
	static Map<String, FunctionSignature> predefinedFunctionsMap;
	
	// A map from function names to user function definition nodes
	static HashMap<String, FunctionDefinition> userFunctionsMap;
	
	// A map from function names to user function calls
	static HashMap<String, ArrayList<FunctionCall>> userFunctionCallsMap;
	
	// A map from variable names to variable nodes
	static HashMap<String, ArrayList<Variable>> variablesMap;
	
	// A map from function names and parameter names to local variable nodes
	static HashMap<String, HashMap<String, ArrayList<LocalVariable>>> localVariablesMap;
	
	// A map from user function names and local variable names to the corresponding Parameter node in the function definition
	static HashMap<String, HashMap<String, Parameter>> parametersMap;	
	
	// A map from user function names and parameter names to corresponding arguments in function calls
	static HashMap<String, HashMap<String, ArrayList<EObject>>> argumentsMap;
	
	def static HashMap<EObject, Type> getTypes() {
		return types;
	}
	
	// Returns a map from user function names to user function nodes
	def static HashMap<String, FunctionDefinition> getAllUserFunctions(Model model) {
		val HashMap<String, FunctionDefinition> userFunctions = new HashMap<String, FunctionDefinition>();
		
		for (FunctionDefinition function : model.getFunctions()) {
			userFunctions.put(function.getName(), function);
		}
		
		return userFunctions;
	}
	
	// Precondition: userFunctionsMap must be defined
	// Returns a map from user function names to user function call nodes
	def static HashMap<String, ArrayList<FunctionCall>> getAllUserFunctionCalls(Model model) {
		val HashMap<String, ArrayList<FunctionCall>> userFunctionCalls = new HashMap<String, ArrayList<FunctionCall>>();
		
		ModelMap.preorder(model.getProof(), [EObject node |
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
			ModelMap.preorder(function.getBody(), [EObject node |
				getAllVariablesHelper(node, variables);
			]);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			getAllVariablesHelper(node, variables);
		]);
		
		return variables;
	}
	
	def static private void getAllVariablesHelper(EObject node, HashMap<String, ArrayList<Variable>> variables) {
		if (node instanceof Variable) {
			if (node instanceof LocalVariable) return;
			if (variables.containsKey(node.getName())) {
				variables.get(node.getName()).add(node);
			} else {
				val ArrayList<Variable> list = new ArrayList<Variable>();
				list.add(node);
				variables.put(node.getName(), list);
			}
		}
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
						
			ModelMap.preorder(function.getBody(), [EObject node |
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
	
	// Precondition: userFunctionsMap must be defined
	// Returns a map from user function names and parameter names to corresponding arguments in function calls
	def static HashMap<String, HashMap<String, ArrayList<EObject>>> getAllArguments(Model model) {
		val HashMap<String, HashMap<String, ArrayList<EObject>>> arguments = new HashMap<String, HashMap<String, ArrayList<EObject>>>();
		val HashMap<String, ArrayList<String>> functionParameters = new HashMap<String, ArrayList<String>>();
		
		for (FunctionDefinition function : model.getFunctions()) {
			val HashMap<String, ArrayList<EObject>> local = new HashMap<String, ArrayList<EObject>>();
			val ArrayList<String> parameters = new ArrayList<String>();
			
			for (Parameter parameter : function.getParameterList.getParameters()) {
				val ArrayList<EObject> list = new ArrayList<EObject>;
				local.put(parameter.getName(), list);
				parameters.add(parameter.getName());
			}
			
			arguments.put(function.getName(), local);
			functionParameters.put(function.getName(), parameters);
			
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (userFunctionsMap.containsKey(functionName)) {
					
					var int index = 0;
					val int length = functionParameters.get(functionName).size();
					val Iterator<Expression> argumentIterator = node.getArguments().iterator();
					
					while (argumentIterator.hasNext() && index < length) {
						val EObject argument = argumentIterator.next();
						val String parameterName = functionParameters.get(functionName).get(index);
						arguments.get(functionName).get(parameterName).add(argument);
						index++;
					}
				}
			}
		]);
		
		return arguments;
	}
	
	// Labels nodes in the syntax tree in a topdown traversal
	def static Type topdownInference(EObject node) {
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
					if (bodyLabel === Type.EXPONENT) {
						label = Type.EXPONENT;
						types.put(node, label);
					}
				} else if (predefinedFunctionsMap.containsKey(functionName)) {
					labelPredefinedFunctionCall(node);
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
	def static void fillExponent(EObject node) {
		
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
					backpropagate(localVariable);					
				}
				
				// For each argument in a corresponding function call that becomes the corresponding parameter, perform fill
				for (EObject argument : argumentsMap.get(functionName).get(parameterName)) {
					fillExponent(argument);
				}
			}
			LocalVariable: {
				val String functionName = node.getFunction();
				val String localName = node.getName()
				val Parameter parameter = parametersMap.get(functionName).get(localName);
				
				// Invariant: if parameter is labeled, then all corresponding local variables have been labeled
				// and all corresponding arguments in function calls have been labeled
				// Thus if the parameter is already labeled, there is no need to try to label these
				fillExponent(parameter);
			}
			Variable: {
				// For every global variable with the same name, perform backpropagation
				for (Variable variable : variablesMap.get(node.getName())) {
					backpropagate(variable);
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
					backpropagate(call);
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
	def static void backpropagate(EObject node) {
		
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
					backpropagate(parent); 
				}
			}
			Power: {
				if (!types.containsKey(parent)) {
					val String feature = node.eContainmentFeature().getName();
					if (feature === "left") {
						fillExponent(parent.getRight());	
						backpropagate(parent);
					}
				}
			}
			Brackets: {
				backpropagate(parent);
			}
			FunctionDefinition: {
				for (FunctionCall call : userFunctionCallsMap.get(parent.getName())) {
					backpropagate(call);
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
		}		
	}
	
	// Precondition: function call references a predefined function
	def static void labelPredefinedFunctionCall(FunctionCall call) {
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
		
//		PredefinedFunctionHelper.getAllPredefinedFunctions
	}
	
	// All unlabeled nodes that require a type label
	// are labeled with GROUP_ELEMENT as the default
	def static void fillGroupElement(Model model) {
		for (FunctionDefinition function : model.getFunctions()) {
			setGroupElement(function);
			
			for (Parameter parameter : function.getParameterList().getParameters()) {
				setGroupElement(parameter);
			}
			
			ModelMap.preorder(function.getBody(), [EObject node |
				setGroupElement(node);
			]);
		}
		
		ModelMap.preorder(model.getProof(), [EObject node |
			setGroupElement(node);
		]);
	}
	def static void setGroupElement(EObject node) {
		if (!types.containsKey(node)) {
			visited.put(node, true);
			types.put(node, Type.GROUP_ELEMENT);
		}
	}
	
	
	def static HashMap<EObject, Type> resolveTypes(Model model) {
		TypeResolution.types = new HashMap<EObject, Type>();
		TypeResolution.visited = new HashMap<EObject, Boolean>();
		
		// Replaces Variable nodes with LocalVariable nodes when
		// the node references a function Parameter
		ModelHelper.identifyLocalVariables(model);

		// Create all HashMaps needed to traversal the syntax tree easily
		TypeResolution.predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		TypeResolution.userFunctionsMap = TypeResolution.getAllUserFunctions(model);
		TypeResolution.userFunctionCallsMap = TypeResolution.getAllUserFunctionCalls(model);
		TypeResolution.variablesMap = TypeResolution.getAllVariables(model);
		TypeResolution.localVariablesMap = TypeResolution.getAllLocalVariables(model);
		TypeResolution.parametersMap = TypeResolution.getAllParameters(model);
		TypeResolution.argumentsMap = TypeResolution.getAllArguments(model);
		
		// Perform topdown inference, labeling BOOLEAN and STRING nodes, and as many EXPONENT nodes as possible
		topdownInference(model);

		// Label all remaining nodes which require a type as GROUP_ELEMENT
		fillGroupElement(model);
		
		return TypeResolution.types;
	}
	
}