package org.cryptimeleon.zeroknowledge.model

import java.util.ArrayList
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
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Expression
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.LocalVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Power
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import org.eclipse.emf.ecore.EObject

/**
 * A wrapper class for the parse tree Model class.
 * Augments the class by providing additional information about the model
 */

class AugmentedModel {
	
	Model model;

	boolean functionsInlined;
	boolean negativesNormalized;
	boolean bracketsRemoved;
	boolean specialVariablesIdentified;
	
	boolean containsRangeProof;
	boolean containsPairing;
	boolean checkedForRangeProof;
	boolean checkedForPairing;
	
	// Generated values
	// Must be accessed through their getters, even within class methods (to ensure that they are generated)
	Map<EObject, Type> types;
	Map<EObject, Integer> sizes;
	Set<String> witnessNames;
	Map<String, FunctionDefinition> userFunctions;
	Map<String, List<FunctionCall>> userFunctionCalls;
	Map<String, List<Variable>> variables;
	Map<String, Witness> witnesses;
	Map<String, Map<String, List<LocalVariable>>> localVariables;
	Map<String, Map<String, Parameter>> localParameters;
	List<Tuple> tuples;
	Map<String, Map<String, List<Argument>>> arguments;
	Map<String, List<FunctionCall>> predefinedFunctionCalls;
	Map<String, FunctionSignature> userFunctionSignatures;
	Set<String> constrainedWitnessNames;
	
	new(Model model) {
		this.model = model;
		this.functionsInlined = false;
		this.negativesNormalized = false;
		this.bracketsRemoved = false;
		this.specialVariablesIdentified = false;
		
		this.containsRangeProof = false;
		this.containsPairing = false;
		this.checkedForRangeProof = false;
		this.checkedForPairing = false;
		
		this.types = null;
		this.sizes = null;
		this.witnessNames = null;
		this.userFunctions = null;
		this.userFunctionCalls = null;
		this.variables = null;
		this.witnesses = null;
		this.localVariables = null;
		this.localParameters = null;
		this.tuples = null;
		this.arguments = null;
		this.predefinedFunctionCalls = null;
		this.userFunctionSignatures = null;
		this.constrainedWitnessNames = null;
	}
	
	def Model getModel() {
		return model;
	}
	
	def Map<EObject, Type> getTypes() {
		if (types !== null) return types;
		
		val TypeInference typeInference = new TypeInference(this);
		types = typeInference.getTypes();
		
		return types;
	}

	
	def Map<EObject, Integer> getSizes() {
		if (sizes !== null) return sizes;

		val SizeInference sizeInference = new SizeInference(this);
		sizes = sizeInference.getSizes();

		return sizes;		
	}
	
	def boolean hasRangeProof() {
		if (checkedForRangeProof) return containsRangeProof;
		
		checkedForRangeProof = true;
		
		containsRangeProof = ModelMap.preorderAny(model, [EObject node |
			return node instanceof Comparison && ModelHelper.isInequalityComparison(node as Comparison);
		]);
		
		return containsRangeProof;
	}
	
	def boolean hasPairing() {
		if (checkedForPairing) return containsPairing;
		
		checkedForPairing = true;
		
		containsPairing = ModelMap.preorderAny(model, [EObject node |
			if (node instanceof FunctionCall) {
				return (node as FunctionCall).getName() == "e";
			}
			
			return false;
		]);
		
		return containsPairing;
	}
	
	
	// Takes the user functions of the syntax tree and inlines them for each corresponding function call
	// Precondition: model has passed all validation rules
	def void inlineFunctions() {
		if (functionsInlined) return;
		
		val Map<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();
		for (FunctionDefinition function : model.getFunctions()) {
			functions.put(function.getName(), function);
		}

		ModelMap.postorder(model.getProof(), [ EObject node |
			ModelHelper.replaceFunctionCallWithDefinition(node, functions);
		]);
		
		functionsInlined = true;
	}
	
	
	// Replace all occurrences of Sum nodes that have the subtraction operation with a Sum 
	// node with the addition operation, where the right operand is now a Negative node
	def void normalizeNegatives() {
		if (negativesNormalized) return;
		
		ModelMap.postorder(model, [ EObject node |
			if (node instanceof Sum) {
				val Sum sum = node as Sum;
				if (sum.getOperation() === "-") {
					val Expression rightSide = sum.getRight();
					val Negative negative = ZeroKnowledgeFactory.eINSTANCE.createNegative();
					ModelHelper.replaceParentReferenceToSelf(rightSide, negative);
					negative.setTerm(rightSide);
					sum.setOperation("+");
				}
			}
		]);
		
		negativesNormalized = true;
	}
	
	// Simplifies the model by removing all bracket nodes
	def void removeBrackets() {
		if (bracketsRemoved) return;
		
		ModelMap.postorder(model, [ EObject node |
			if (node instanceof Brackets) {
				val Brackets brackets = node as Brackets;
				val EObject contents = brackets.getContent();
				ModelHelper.replaceParentReferenceToSelf(brackets, contents);
			}
		]);
		
		bracketsRemoved = true;
	}
	
	// Changes any Variable node within a FunctionDefinition that references
	// a Parameter into a LocalVariable node
	// Changes any Variable node that references a Witness into a Witness node
	def void identifySpecialVariables() {
		if (specialVariablesIdentified) return;
		
		val Map<String, Witness> witnesses = getAllWitnesses();

		for (FunctionDefinition function : model.getFunctions()) {
			val List<String> parameters = new ArrayList<String>;
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
		
		specialVariablesIdentified = true;	
	}
	
	def private void identifySpecialVariablesHelper(Variable variable, Map<String, Witness> witnesses) {
		if (witnesses.containsKey(variable.getName())) {
			val WitnessVariable witness = ZeroKnowledgeFactory.eINSTANCE.createWitnessVariable();
			witness.setName(variable.getName());
			ModelHelper.replaceParentReferenceToSelf(variable, witness);
		}
	}
	
	
	// Returns the set of witness names
	def Set<String> getWitnessNames() {
		if (witnessNames !== null) return witnessNames;
			
		witnessNames = new HashSet<String>();

		for (Witness witness : model.getWitnessList().getWitnesses()) {
			witnessNames.add(witness.getName());
		}
	
		return witnessNames;	
	}

	// Returns a map from user function names to user function nodes
	def Map<String, FunctionDefinition> getAllUserFunctions() {
		if (userFunctions !== null) return userFunctions;
		
		userFunctions = new HashMap<String, FunctionDefinition>();

		for (FunctionDefinition function : model.getFunctions()) {
			userFunctions.put(function.getName(), function);
		}

		return userFunctions;
	}

	// Returns a map from user function names to user function call nodes
	def Map<String, List<FunctionCall>> getAllUserFunctionCalls() {
		if (userFunctionCalls !== null) return userFunctionCalls;
		
		val Map<String, FunctionDefinition> userFunctionsMap = getAllUserFunctions();
		
		userFunctionCalls = new HashMap<String, List<FunctionCall>>();

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (userFunctionsMap.containsKey(functionName)) {
					if (userFunctionCalls.containsKey(functionName)) {
						userFunctionCalls.get(functionName).add(node);
					} else {
						val List<FunctionCall> list = new ArrayList<FunctionCall>;
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
	// Returns a map from variable names to a list of variable nodes, including witness variables
	def Map<String, List<Variable>> getAllVariables() {
		if (variables !== null) return variables;
		
		variables = new HashMap<String, List<Variable>>();

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

	def private void getAllVariablesHelper(EObject node, Map<String, List<Variable>> variables) {
		if (node instanceof Variable) {
			if(node instanceof LocalVariable) return;
			if (variables.containsKey(node.getName())) {
				variables.get(node.getName()).add(node);
			} else {
				val List<Variable> list = new ArrayList<Variable>();
				list.add(node);
				variables.put(node.getName(), list);
			}
		}
	}

	// Returns a map from witness names to witness nodes
	def Map<String, Witness> getAllWitnesses() {
		if (witnesses !== null) return witnesses;
		
		witnesses = new HashMap<String, Witness>();

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
	def Map<String, Map<String, List<LocalVariable>>> getAllLocalVariables() {
		if (localVariables !== null) return localVariables;
		
		localVariables = new HashMap<String, Map<String, List<LocalVariable>>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val Map<String, List<LocalVariable>> functionVariables = new HashMap<String, List<LocalVariable>>();

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
	def Map<String, Map<String, Parameter>> getAllParameters() {
		if (localParameters !== null) return localParameters;
		
		localParameters = new HashMap<String, Map<String, Parameter>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val Map<String, Parameter> parameters = new HashMap<String, Parameter>();

			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameters.put(parameter.getName(), parameter);
			}

			localParameters.put(function.getName(), parameters);
		}

		return localParameters;
	}

	// Returns a map from user function names and parameter names to corresponding arguments in function calls
	def Map<String, Map<String, List<Argument>>> getAllArguments() {
		if (arguments !== null) return arguments;
		
		
		val Map<String, FunctionDefinition> userFunctionsMap = this.getAllUserFunctions();
			
		arguments = new HashMap<String, Map<String, List<Argument>>>();
		val Map<String, List<String>> functionParameters = new HashMap<String, List<String>>();

		for (FunctionDefinition function : model.getFunctions()) {
			val Map<String, List<Argument>> local = new HashMap<String, List<Argument>>();
			val List<String> parameters = new ArrayList<String>();

			for (Parameter parameter : function.getParameterList.getParameters()) {
				val List<Argument> list = new ArrayList<Argument>;
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
	def List<Tuple> getAllTuples() {
		if (tuples !== null) return tuples;
		
		tuples = new ArrayList<Tuple>();
		getAllTuplesHelper1(tuples, model);
		
		return tuples;
	}
	def private static void getAllTuplesHelper1(List<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMapController controller |
			if (child instanceof Tuple) {
				tuples.add(child);
				getAllTuplesHelper2(tuples, child);				
				controller.continueTraversal();
			}
		]);
	}
	def private static void getAllTuplesHelper2(List<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMapController controller |
			if (child instanceof FunctionCall) {
				getAllTuplesHelper1(tuples, child);
				controller.continueTraversal();
			}
		]);
	}
	
	// Returns a map from predefined function names to predefined function call nodes
	def Map<String, List<FunctionCall>> getAllPredefinedFunctionCalls() {
		if (predefinedFunctionCalls !== null) return predefinedFunctionCalls;
		
		val Map<String, FunctionSignature> predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();
		
		predefinedFunctionCalls = new HashMap<String, List<FunctionCall>>();

		ModelMap.preorder(model.getProof(), [EObject node |
			if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				if (predefinedFunctionsMap.containsKey(functionName)) {
					if (predefinedFunctionCalls.containsKey(functionName)) {
						predefinedFunctionCalls.get(functionName).add(node);
					} else {
						val List<FunctionCall> list = new ArrayList<FunctionCall>;
						list.add(node);
						predefinedFunctionCalls.put(functionName, list);
					}
				}
			}

		]);

		return predefinedFunctionCalls;
	}
	
	
	
	
	// Returns a map from user function names to function signatures
	def Map<String, FunctionSignature> getUserFunctionSignatures() {
		if (userFunctionSignatures !== null) return userFunctionSignatures;
		
		val Map<EObject, Type> types = this.getTypes();
		val Map<EObject, Integer> tuples = this.getSizes(); // Change to sizes
		
		userFunctionSignatures = new HashMap<String, FunctionSignature>();

		for (FunctionDefinition function : model.getFunctions()) {
			val List<Type> parameterTypes = new ArrayList<Type>();
			val List<Integer> parameterSizes = new ArrayList<Integer>();

			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameterTypes.add(types.get(parameter));
				parameterSizes.add(tuples.get(parameter));
			}

			val FunctionSignature signature = new FunctionSignature(function.getName(), types.get(function), tuples.get(function), parameterTypes, parameterSizes);
			userFunctionSignatures.put(function.getName(), signature);
		}

		return userFunctionSignatures;
	}
	
	// Returns a set containing the names of all witnesses that are constrained by a range or linear exponent constraint
	def Set<String> getConstrainedWitnessNames() {
		if (constrainedWitnessNames !== null) return constrainedWitnessNames;
		
		constrainedWitnessNames = new HashSet<String>();
		
		ModelMap.preorderWithControl(model, [EObject node, ModelMapController controller |
			if (node instanceof Power) {
				controller.continueTraversal();
				return;
			}
			
			if (node instanceof WitnessVariable) {
				constrainedWitnessNames.add(node.getName());
			}
		]);
		
		return constrainedWitnessNames;
	}
	
	
	override String toString() {
		val StringBuilder builder = new StringBuilder();
		
		ModelMap.preorderWithState(model, new BranchState(), [EObject node, BranchState state |
			for (var int i = 0; i < state.getDepth(); i++) {
				builder.append("---|");
			}
			
			var String className = node.getClass().toString();
			val int periodIndex = className.lastIndexOf('.');
			if (periodIndex > 0) {
				className = className.substring(periodIndex + 1);
			}
			
			builder.append(className.substring(0, className.length() - 4));
			
			switch node {
				Witness: builder.append(" - " + node.getName().toString())
				FunctionCall: builder.append(" - " + node.getName().toString())
				FunctionDefinition: builder.append(" - " + node.getName().toString())
				Parameter: builder.append(" - " + node.getName().toString())
				Variable: builder.append(" - " + node.getName().toString())
				NumberLiteral: builder.append(" - " + node.getValue().toString())
				default: builder.append("")
			}
			
			if (types !== null && types.containsKey(node)) {
				builder.append(" - " + types.get(node).toString());
			} else {
				builder.append("");
			}
			
			if (sizes !== null && sizes.containsKey(node)) {
				builder.append(" (" + sizes.get(node).toString() + ")");
			} else {
				builder.append("");
			}
			
			builder.append("\n");
		]);
		
		
		
		return builder.toString();
	}
	
	
	
}