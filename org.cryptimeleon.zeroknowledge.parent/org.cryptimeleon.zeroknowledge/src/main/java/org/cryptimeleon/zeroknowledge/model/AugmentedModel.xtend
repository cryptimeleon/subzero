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
import org.cryptimeleon.zeroknowledge.zeroKnowledge.PublicParameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.PublicParameterList
import org.cryptimeleon.zeroknowledge.zeroKnowledge.StringLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import org.eclipse.emf.ecore.EObject
import java.util.Collections
import org.cryptimeleon.zeroknowledge.generator.GenerationHelper
import java.util.Map.Entry
import java.util.Comparator
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup
import org.cryptimeleon.math.structures.groups.Group
import org.eclipse.xtext.resource.XtextResource
import org.cryptimeleon.zeroknowledge.zeroKnowledge.PPVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ConstantVariable

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
	boolean containsOrProof;
	boolean containsOrDescendantOfAnd;
	boolean checkedForRangeProof;
	boolean checkedForPairing;
	boolean checkedForOrProof;
	boolean checkedForOrDescendantOfAnd;
	
	// Generated values
	// Must be accessed through their getters, even within class methods (to ensure that they are generated)
	String protocolName;
	
	TypeInference typeInference;
	SizeInference sizeInference;
	GroupInference groupInference;
	
	Map<String, Witness> witnesses;
	Set<String> witnessNames;
	List<String> sortedWitnessNames;
	Map<String, Type> witnessTypes;
	Set<String> constrainedWitnessNames;
	
	Set<String> publicParameterNames;
	List<String> sortedPublicParameterNames;
	
	Map<String, List<Variable>> variables;
	Set<String> variableNames;
	List<String> sortedVariableNames;
	Map<String, Type> variableTypes;
	Map<String, GroupType> variableGroups;
	
	Map<String, FunctionDefinition> userFunctions;
	Map<String, List<FunctionCall>> userFunctionCalls;
	
	
	Map<String, Map<String, List<LocalVariable>>> localVariables;
	Map<String, Map<String, Parameter>> localParameters;
	List<Tuple> tuples;
	Map<String, Map<String, List<Argument>>> arguments;
	Map<String, List<FunctionCall>> predefinedFunctionCalls;
	Map<String, FunctionSignature> userFunctionSignatures;
	Set<String> userFunctionWithConstant;
	Map<String, Set<String>> userFunctionWitnesses;
	Set<String> inlineFunctionNames;
	
	new(Model model) {
		this.model = model;
		this.functionsInlined = false;
		this.negativesNormalized = false;
		this.bracketsRemoved = false;
		this.specialVariablesIdentified = false;
		
		this.containsRangeProof = false;
		this.containsPairing = false;
		this.containsOrProof = false;
		this.containsOrDescendantOfAnd = false;
		this.checkedForRangeProof = false;
		this.checkedForPairing = false;
		this.checkedForOrProof = false;
		this.checkedForOrDescendantOfAnd = false;
		
		this.typeInference = null;
		this.sizeInference = null;
		this.groupInference = null;
		
		this.witnesses = null;
		this.witnessNames = null;
		this.sortedWitnessNames = null;
		this.witnessTypes = null;
		
		this.variables = null;
		this.variableNames = null;
		this.sortedVariableNames = null;
		this.variableTypes = null;
		this.variableGroups = null;
		
		this.userFunctions = null;
		this.userFunctionCalls = null;
		
		this.localVariables = null;
		this.localParameters = null;
		this.tuples = null;
		this.arguments = null;
		this.predefinedFunctionCalls = null;
		this.userFunctionSignatures = null;
		this.constrainedWitnessNames = null;
		this.publicParameterNames = null;
		this.userFunctionWithConstant = null;
		this.userFunctionWitnesses = null;
		this.inlineFunctionNames = null;
		
		trimStringsTransformation();
		comparisonTransformation();
		normalizeNegativesTransformation();
	}
	
	def Model getModel() {
		return model;
	}
	
	def String getProtocolName() {
		if (protocolName !== null) return protocolName;
		
		val String rawProtocolName = model.getProtocolName();
		if (rawProtocolName === null) {
			protocolName = GenerationHelper.DEFAULT_PROTOCOL_NAME;	
		} else {
			protocolName = GenerationHelper.convertToClassName(rawProtocolName.substring(1, rawProtocolName.length()-1));
		}
		return protocolName;
	}
	
	def String getPackageName() {
		return GenerationHelper.DEFAULT_PACKAGE_NAME;
	}
	
	// Returns the raw DSL code that produced the model
	def String getCode() {
		return (model.eResource() as XtextResource).getParseResult().getRootNode().getText();
	}
	
	def Map<EObject, Type> getTypes() {
		if (typeInference !== null) return typeInference.getTypes();
		
		typeInference = new TypeInference(this);
		
		return typeInference.getTypes();
	}

	
	def Map<EObject, Integer> getSizes() {
		if (sizeInference !== null) return sizeInference.getSizes();

		sizeInference = new SizeInference(this);

		return sizeInference.getSizes();		
	}
	
	def Map<EObject, GroupType> getGroups() {
		if (groupInference !== null) return groupInference.getGroups();
		
		groupInference = new GroupInference(this);
		
		return groupInference.getGroups();
	}
	
	def Map<String, GroupType> getGroupsByName() {
		if (groupInference !== null) return groupInference.getGroupsByName();
		
		groupInference = new GroupInference(this);
		
		return groupInference.getGroupsByName();
	}
	
	def boolean requiresPublicParametersClass() {
		return hasRangeProof() || hasOrDescendantOfAnd();
	}
	
	def Class<?> getGroupClass() {
		if (hasRangeProof() || hasPairing()) {
			return BilinearGroup;
		} else {
			return Group;
		}
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
	
	def boolean hasOrProof() {
		if (checkedForOrProof) return containsOrProof;
		
		checkedForOrProof = true;
		
		containsOrProof = ModelMap.preorderAny(model, [EObject node |
			if (node instanceof Disjunction) {
				System.out.println("---------------------------FOUND");
				return true;
			}
			
			return false;
		]);
	}
	
	def boolean hasOrDescendantOfAnd() {
		if (checkedForOrDescendantOfAnd) return containsOrDescendantOfAnd;
		
		checkedForOrDescendantOfAnd = true;
		
		containsOrDescendantOfAnd = ModelMap.preorderWithControl(model, [EObject node, ModelMap.Controller controller |
			if (node instanceof Conjunction) {
				
				val boolean hasDisjunction = ModelMap.preorderAny(node, [EObject newNode |
					if (newNode instanceof Disjunction) {
						return true;
					}
					
					return false;
				]);
				
				if (hasDisjunction) {
					controller.returnTrue();
				}
				
				controller.continueTraversal();
			}
		]);
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
	
	def private void trimStringsTransformation() {
		ModelMap.postorder(model, [EObject node |
			if (node instanceof StringLiteral) {
				val String value = node.getValue();
				if (value.charAt(0) == '"') {
					node.setValue(value.substring(1, value.length()-1));
				}
			} else if (node instanceof Comparison) {
				val String subprotocolName = node.getSubprotocolName();
				System.out.println(subprotocolName);
				if (subprotocolName !== null && subprotocolName.charAt(0) == '[') {
					node.setSubprotocolName(subprotocolName.substring(1, subprotocolName.length()-1));
				}
			}
		]);
	}
	
	def private void comparisonTransformation() {
		ModelMap.postorder(model, [EObject node | 
			if (node instanceof Comparison) {
				if (node.getOperation2() === null && node.getRight() === null) {
					node.setRight(node.getCenter());
					node.setCenter(null);
				}
			}
		]);
	}
	
	def void normalizeNegativesTransformation() {
		if (negativesNormalized) return;
		
		ModelMap.postorder(model, [EObject node |
			if (node instanceof Negative) {
				val EObject term = node.getTerm();
				
				if (term instanceof NumberLiteral) {
					term.setValue(-term.getValue());
					ModelHelper.replaceParentReferenceToSelf(node, term);
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
				val EObject contents = node.getContent();
				ModelHelper.replaceParentReferenceToSelf(node, contents);
			}
		]);
		
		bracketsRemoved = true;
	}
	
	// Changes any Variable node within a FunctionDefinition that references
	// a Parameter into a LocalVariable node
	// Changes any Variable node that references a Witness into a Witness node
	def void identifySpecialVariables() {
		if (specialVariablesIdentified) return;
		
		val Set<String> witnessNames = getWitnessNames();
		val Set<String> ppNames = getPublicParameterNames();

		for (FunctionDefinition function : model.getFunctions()) {
			val Set<String> parameters = new HashSet<String>;
			for (Parameter parameter : function.getParameterList().getParameters()) {
				parameters.add(parameter.getName());
			}

			ModelMap.preorder(function.getBody(), [ EObject node |
				if (node instanceof Variable) {
					if (parameters.contains(node.getName())) {
						val LocalVariable localVariable = ZeroKnowledgeFactory.eINSTANCE.createLocalVariable();
						localVariable.setName(node.getName());
						localVariable.setFunction(function.getName());
						ModelHelper.replaceParentReferenceToSelf(node, localVariable);
					} else {
						identifySpecialVariablesHelper(node, witnessNames, ppNames);
					}

				}
			]);

		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof Variable) {
				identifySpecialVariablesHelper(node, witnessNames, ppNames);
			}
		]);
		
		specialVariablesIdentified = true;	
	}
	
	def private void identifySpecialVariablesHelper(Variable variable, Set<String> witnessNames, Set<String> ppNames) {
		val String name = variable.getName();
		
		if (witnessNames.contains(name)) {
			val WitnessVariable witnessVariable = ZeroKnowledgeFactory.eINSTANCE.createWitnessVariable();
			witnessVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, witnessVariable);
		} else if (ppNames.contains(name)) {
			val PPVariable ppVariable = ZeroKnowledgeFactory.eINSTANCE.createPPVariable();
			ppVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, ppVariable);
		} else {
			val ConstantVariable constantVariable = ZeroKnowledgeFactory.eINSTANCE.createConstantVariable();
			constantVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, constantVariable);
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
		ModelMap.preorderWithControl(node, [EObject child, ModelMap.Controller controller |
			if (child instanceof Tuple) {
				tuples.add(child);
				getAllTuplesHelper2(tuples, child);				
				controller.continueTraversal();
			}
		]);
	}
	def private static void getAllTuplesHelper2(List<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMap.Controller controller |
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
		
		ModelMap.preorderWithControl(model, [EObject node, ModelMap.Controller controller |
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
	
	// Returns a set containing the names of all public parameters
	def Set<String> getPublicParameterNames() {
		if (publicParameterNames !== null) return publicParameterNames;
		
		publicParameterNames = new HashSet<String>();
		
		val PublicParameterList publicParameterList = model.getPublicParameterList();
		
		if (publicParameterList !== null) {
			for (PublicParameter publicParameter : publicParameterList.getPublicParameters()) {
				publicParameterNames.add(publicParameter.getName());
			}
		}
		
		return publicParameterNames;
	}
	
	def List<String> getSortedPublicParameterNames() {
		if (sortedPublicParameterNames !== null) return sortedPublicParameterNames;
		sortedPublicParameterNames = new ArrayList<String>(getPublicParameterNames());
		Collections.sort(sortedPublicParameterNames);
		return sortedPublicParameterNames;
	}
	
	def List<String> getSortedWitnessNames() {
		if (sortedWitnessNames !== null) return sortedWitnessNames;
		
		// TODO: change
//		val Set<String> witnessNames = getWitnessNames();
//		sortedWitnessNames = new ArrayList<String>(witnessNames);
//		Collections.sort(sortedWitnessNames);
		
		sortedWitnessNames = new ArrayList<String>();
		for (Witness witness : model.getWitnessList().getWitnesses()) {
			sortedWitnessNames.add(witness.getName());
		}
		
		return sortedWitnessNames;
	}
	
	def Map<String, Type> getWitnessTypes() {
		if (witnessTypes !== null) return witnessTypes;
		
		witnessTypes = new HashMap<String, Type>();
		
		for (Entry<String, Witness> entry : getAllWitnesses().entrySet()) {
			witnessTypes.put(entry.getKey(), types.get(entry.getValue()));
		}
		
		return witnessTypes;
	} 
	
	def Set<String> getVariableNames() {
		if (variableNames !== null) return variableNames;
		getVariablesHelper();
		return variableNames;
	}
	
	def List<String> getSortedVariableNames() {
		if (sortedVariableNames !== null) return sortedVariableNames;
		getVariablesHelper();
		return sortedVariableNames;
	}
	
	def Map<String, Type> getVariableTypes() {
		if (variableTypes !== null) return variableTypes;
		getVariablesHelper();
		return variableTypes;
	}
	
	def Map<String, GroupType> getVariableGroups() {
		if (variableGroups !== null) return variableGroups;
		getVariablesHelper();
		return variableGroups;
	}
	
	private def getVariablesHelper() {
		variableNames = new HashSet<String>();
		sortedVariableNames = new ArrayList<String>();
		variableTypes = new HashMap<String, Type>();
		variableGroups = new HashMap<String, GroupType>();
		
		for (Entry<String, List<Variable>> entry : getAllVariables().entrySet()) {
			val Variable variable = entry.getValue().get(0);
			val String variableName = entry.getKey();
			
			if (!(variable instanceof WitnessVariable) && !variableNames.contains(variableName)) {
				variableNames.add(variableName);
				sortedVariableNames.add(variableName);
				variableTypes.put(variableName, types.get(variable));
				if (groups.containsKey(variable)) variableGroups.put(variableName, groups.get(variable));
			}
		}
		
		// Sort first by variable type, and then by variable name
		Collections.sort(sortedVariableNames, new Comparator<String>() {
			override compare(String arg1, String arg2) {
				val Type argType1 = variableTypes.get(arg1);
				val Type argType2 = variableTypes.get(arg2);
				
				if (argType1 === argType2) {
					return arg1.compareTo(arg2);
				}
				return argType1.compareTo(argType2);
			}
		});
	}
	
	
	def boolean userFunctionHasConstant(String functionName) {
		if (userFunctionWithConstant !== null) return userFunctionWithConstant.contains(functionName);
		
		userFunctionWithConstant = new HashSet<String>();
		for (FunctionDefinition function : model.getFunctions()) {
			val hasConstant = ModelMap.preorderAny(function.getBody(), [EObject node |
				return node instanceof ConstantVariable;
			]);
			
			if (hasConstant) userFunctionWithConstant.add(function.getName());
		}
		
		return userFunctionWithConstant.contains(functionName);
	}
	
	def Set<String> getUserFunctionWitnesses(String functionName) {
		if (userFunctionWitnesses !== null) return userFunctionWitnesses.get(functionName);
		
		userFunctionWitnesses = new HashMap<String, Set<String>>();
		for (FunctionDefinition function : model.getFunctions()) {
			val witnessNames = new HashSet<String>();
			
			ModelMap.preorder(function.getBody(), [EObject node |
				if (node instanceof WitnessVariable) {
					witnessNames.add(node.getName());
				}
			]);
			
			userFunctionWitnesses.put(function.getName(), witnessNames);
		}
		
		return userFunctionWitnesses.get(functionName);
	}
	
	def boolean isInlineFunction(String functionName) {
		if (inlineFunctionNames !== null) return inlineFunctionNames.contains(functionName);
		
		inlineFunctionNames = new HashSet<String>();
		for (FunctionDefinition function : model.getFunctions()) {
			if (function.isInline()) inlineFunctionNames.add(function.getName());
		}
		
		return inlineFunctionNames.contains(functionName);
	}
	
	
	
	/*
	 * String representation
	 */
	override String toString() {
		val StringBuilder builder = new StringBuilder();
		
		ModelMap.preorderWithState(model, new BranchState(), [EObject node, BranchState state |
			for (var int i = 0; i < state.getDepth(); i++) {
				builder.append("---|");
			}
			
			var String className = node.getClass().getSimpleName();
			builder.append(className);
			
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