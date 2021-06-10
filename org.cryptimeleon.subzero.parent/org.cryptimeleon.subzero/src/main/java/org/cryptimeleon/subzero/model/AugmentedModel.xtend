package org.cryptimeleon.subzero.model

import java.util.ArrayList
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
import org.cryptimeleon.subzero.subzero.NumberLiteral
import org.cryptimeleon.subzero.subzero.Parameter
import org.cryptimeleon.subzero.subzero.Power
import org.cryptimeleon.subzero.subzero.PublicParameter
import org.cryptimeleon.subzero.subzero.PublicParameterList
import org.cryptimeleon.subzero.subzero.Tuple
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.Witness
import org.cryptimeleon.subzero.subzero.WitnessVariable
import org.cryptimeleon.subzero.subzero.SubzeroFactory
import org.eclipse.emf.ecore.EObject
import java.util.Collections
import org.cryptimeleon.subzero.generator.GenerationHelper
import java.util.Map.Entry
import java.util.Comparator
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup
import org.cryptimeleon.math.structures.groups.Group
import org.eclipse.xtext.resource.XtextResource
import org.cryptimeleon.subzero.subzero.PPVariable
import org.cryptimeleon.subzero.subzero.ConstantVariable
import org.cryptimeleon.subzero.subzero.WitnessList

/**
 * A wrapper class for the parse tree Model class.
 * Augments the class by providing additional information about the model
 */

class AugmentedModel {
	
	Model model;

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
	
	Map<String, Witness> witnessNodes;
	Set<String> witnessNames;
	List<String> sortedWitnessNames;
	Map<String, Type> witnessTypes;
	Set<String> constrainedWitnessNames;
	
	Map<String, PublicParameter> publicParameters;
	Set<String> publicParameterNames;
	List<String> sortedPublicParameterNames;
	Map<String, Type> publicParameterTypes;
	
	Map<String, List<Variable>> variables;
	Set<String> constantVariableNames;
	List<String> sortedConstantVariableNames;
	Map<String, Type> constantVariableTypes;
	Map<String, GroupType> constantVariableGroups;
	
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
		
		this.witnessNodes = null;
		this.witnessNames = null;
		this.sortedWitnessNames = null;
		this.witnessTypes = null;
		
		this.publicParameters = null;
		this.publicParameterNames = null;
		this.sortedPublicParameterNames = null;
		this.publicParameterTypes = null;
		
		this.variables = null;
		this.constantVariableNames = null;
		this.sortedConstantVariableNames = null;
		this.constantVariableTypes = null;
		this.constantVariableGroups = null;
		
		this.userFunctions = null;
		this.userFunctionCalls = null;
		
		this.localVariables = null;
		this.localParameters = null;
		this.tuples = null;
		this.arguments = null;
		this.predefinedFunctionCalls = null;
		this.userFunctionSignatures = null;
		this.constrainedWitnessNames = null;
		this.userFunctionWithConstant = null;
		this.userFunctionWitnesses = null;
		this.inlineFunctionNames = null;
		
		comparisonTransformation();
		removeBracketsTransformation();
		variableRoleTransformation();
	}
	
	/*
	 * Getters for basic model information
	 */
	
	// Returns the model representing the syntax tree
	def Model getModel() {
		return model;
	}
	
	// Returns the protocol name, if specified, or the default protocol name
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
	
	// Returns the default package name for now
	def String getPackageName() {
		return GenerationHelper.DEFAULT_PACKAGE_NAME;
	}
	
	// Returns the raw DSL code that produced the model
	def String getCode() {
		return (model.eResource() as XtextResource).getParseResult().getRootNode().getText();
	}
	
	
	/*
	 * Getters for inference-based model information (types, sizes, and groups)
	 */
	
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
	
	/*
	 * Methods for getting high-level model structure information
	 */
	
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
		
		// The model has a range proof if it contain any inequality comparison
		containsRangeProof = ModelMap.preorderAny(model, [EObject node |
			return node instanceof Comparison && ModelHelper.isInequalityComparison(node as Comparison);
		]);
		
		return containsRangeProof;
	}
	
	def boolean hasPairing() {
		if (checkedForPairing) return containsPairing;
		
		checkedForPairing = true;
		
		// The model has a pairing if it has a function call to the 'e' function
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
		
		// The model has an OR proof if it contains a disjunction
		containsOrProof = ModelMap.preorderAny(model, [EObject node |
			if (node instanceof Disjunction) {
				return true;
			}
			
			return false;
		]);
	}
	
	def boolean hasOrDescendantOfAnd() {
		if (checkedForOrDescendantOfAnd) return containsOrDescendantOfAnd;
		
		checkedForOrDescendantOfAnd = true;
		
		// The model has an OR descedant of an AND if it contains a disjunction nested within a conjunction
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
	
	/*
	 * Model transformations that change the structure and node attributes of the syntax tree
	 * All transformations are applied to the model when an augmented model object is instantiated
	 */	
	
	// Fixes single comparisons to have left and right subtrees, instead of left and center subtrees
	// This is needed due to how the grammar is written
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
	
	// Simplifies the model by removing all bracket nodes
	def private void removeBracketsTransformation() {
		ModelMap.postorder(model, [ EObject node |
			if (node instanceof Brackets) {
				val EObject contents = node.getContent();
				ModelHelper.replaceParentReferenceToSelf(node, contents);
			}
		]);
	}
	
	// Changes any Variable node within a FunctionDefinition that references
	// a parameter into a LocalVariable node
	// Changes any Variable node that references a witness into a WitnessVariable node,
	// that references a public parameter into a PPVariable node, and all other Variable nodes
	// into ConstantVariable nodes
	def private void variableRoleTransformation() {
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
						val LocalVariable localVariable = SubzeroFactory.eINSTANCE.createLocalVariable();
						localVariable.setName(node.getName());
						localVariable.setFunction(function.getName());
						ModelHelper.replaceParentReferenceToSelf(node, localVariable);
					} else {
						variableRoleTransformationHelper(node, witnessNames, ppNames);
					}

				}
			]);
		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			if (node instanceof Variable) {
				variableRoleTransformationHelper(node, witnessNames, ppNames);
			}
		]);
		
	}
	
	def private void variableRoleTransformationHelper(Variable variable, Set<String> witnessNames, Set<String> ppNames) {
		val String name = variable.getName();
		
		if (witnessNames.contains(name)) {
			val WitnessVariable witnessVariable = SubzeroFactory.eINSTANCE.createWitnessVariable();
			witnessVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, witnessVariable);
		} else if (ppNames.contains(name)) {
			val PPVariable ppVariable = SubzeroFactory.eINSTANCE.createPPVariable();
			ppVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, ppVariable);
		} else {
			val ConstantVariable constantVariable = SubzeroFactory.eINSTANCE.createConstantVariable();
			constantVariable.setName(name);
			ModelHelper.replaceParentReferenceToSelf(variable, constantVariable);
		}
	}
	
	
	/*
	 * Witness variable information
	 */
	 
	// Returns a map from witness names to witness variable nodes
	def Map<String, Witness> getWitnessNodes() {
		if (witnessNodes !== null) return witnessNodes;
		
		witnessNodes = new HashMap<String, Witness>();

		val WitnessList witnessList = model.getWitnessList();
		if (witnessList !== null) {
			for (Witness witness : witnessList.getWitnesses()) {
				witnessNodes.put(witness.getName(), witness);
			}
		}

		return witnessNodes;
	}
	
	// Returns the set of witness variable names
	def Set<String> getWitnessNames() {
		if (witnessNames !== null) return witnessNames;
			
		witnessNames = new HashSet<String>();

		val WitnessList witnessList = model.getWitnessList();
		if (witnessList !== null) {
			for (Witness witness : witnessList.getWitnesses()) {
				witnessNames.add(witness.getName());
			}
		}
		
		return witnessNames;	
	}
	
	// Returns a list of witness variable names, in sorted order
	def List<String> getSortedWitnessNames() {
		if (sortedWitnessNames !== null) return sortedWitnessNames;
		
		// TODO: change
//		val Set<String> witnessNames = getWitnessNames();
//		sortedWitnessNames = new ArrayList<String>(witnessNames);
//		Collections.sort(sortedWitnessNames);
		
		sortedWitnessNames = new ArrayList<String>();
		val WitnessList witnessList = model.getWitnessList();
		if (witnessList !== null) {
			for (Witness witness : witnessList.getWitnesses()) {
				sortedWitnessNames.add(witness.getName());
			}
		}
		
		return sortedWitnessNames;
	}
	
	// Returns a map from witness variable names to their type
	def Map<String, Type> getWitnessTypes() {
		if (witnessTypes !== null) return witnessTypes;
		
		witnessTypes = new HashMap<String, Type>();
		
		for (Entry<String, Witness> entry : getWitnessNodes().entrySet()) {
			witnessTypes.put(entry.getKey(), types.get(entry.getValue()));
		}
		
		return witnessTypes;
	}
	
	// Returns a set containing the names of all witnesses that are constrained by a range or linear exponent constraint
	def Set<String> getConstrainedWitnessNames() {
		if (constrainedWitnessNames !== null) return constrainedWitnessNames;
		
		constrainedWitnessNames = new HashSet<String>();
		
		val Map<String, FunctionDefinition> userFunctions = getUserFunctionNodes();
		val Set<String> checkedFunctions = new HashSet<String>();
		
		getConstrainedWitnessNamesHelper(model.getProof(), userFunctions, checkedFunctions);
		
		return constrainedWitnessNames;
	}
	
	def private void getConstrainedWitnessNamesHelper(EObject root, Map<String, FunctionDefinition> userFunctions, Set<String> checkedFunctions) {
		ModelMap.preorderWithControl(root, [EObject node, ModelMap.Controller controller |
			if (node instanceof Power) {
				// Witness variables in the exponent of a power expression are not constrained
				controller.continueTraversal();
				
			} else if (node instanceof WitnessVariable) {
				constrainedWitnessNames.add(node.getName());
				
			} else if (node instanceof FunctionCall) {
				val String functionName = node.getName();
				
				if (!checkedFunctions.contains(functionName) && userFunctions.containsKey(functionName)) {
					val FunctionDefinition function = userFunctions.get(functionName);
					checkedFunctions.add(functionName);
					getConstrainedWitnessNamesHelper(function.getBody(), userFunctions, checkedFunctions);
				}
				
				// controller.continueTraversal() is NOT used here, because we want the witness names
				// in the function call arguments to also be added to the constrained set
			}
		]);
	}
	
	/*
	 * Public parameter information
	 */
	 
	// Returns a map from public parameter names to public parameter nodes
	def Map<String, PublicParameter> getPublicParameterNodes() {
		if (publicParameters !== null) return publicParameters;
		
		publicParameters = new HashMap<String, PublicParameter>();

		if (model.getPublicParameterList() !== null) {
			for (PublicParameter publicParameter : model.getPublicParameterList().getPublicParameters()) {
				publicParameters.put(publicParameter.getName(), publicParameter);
			}
		}

		return publicParameters;
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
	
	def Map<String, Type> getPublicParameterTypes() {
		if (publicParameterTypes !== null) return publicParameterTypes;
		
		publicParameterTypes = new HashMap<String, Type>();
		
		for (Entry<String, PublicParameter> entry : getPublicParameterNodes().entrySet()) {
			publicParameterTypes.put(entry.getKey(), types.get(entry.getValue()));
		}
		
		return publicParameterTypes;
	}
	
	/*
	 * Constant variable information
	 */
	 
	def Set<String> getConstantVariableNames() {
		if (constantVariableNames !== null) return constantVariableNames;
		getConstantVariablesHelper();
		return constantVariableNames;
	}
	
	def List<String> getSortedConstantVariableNames() {
		if (sortedConstantVariableNames !== null) return sortedConstantVariableNames;
		getConstantVariablesHelper();
		return sortedConstantVariableNames;
	}
	
	def Map<String, Type> getConstantVariableTypes() {
		if (constantVariableTypes !== null) return constantVariableTypes;
		getConstantVariablesHelper();
		return constantVariableTypes;
	}
	
	def Map<String, GroupType> getConstantVariableGroups() {
		if (constantVariableGroups !== null) return constantVariableGroups;
		getConstantVariablesHelper();
		return constantVariableGroups;
	}
	
	private def getConstantVariablesHelper() {
		constantVariableNames = new HashSet<String>();
		sortedConstantVariableNames = new ArrayList<String>();
		constantVariableTypes = new HashMap<String, Type>();
		constantVariableGroups = new HashMap<String, GroupType>();
		
		for (Entry<String, List<Variable>> entry : getVariableNodes().entrySet()) {
			val Variable variable = entry.getValue().get(0);
			val String variableName = entry.getKey();
			
			if (variable instanceof ConstantVariable && !constantVariableNames.contains(variableName)) {
				constantVariableNames.add(variableName);
				sortedConstantVariableNames.add(variableName);
				constantVariableTypes.put(variableName, types.get(variable));
				if (groups.containsKey(variable)) constantVariableGroups.put(variableName, groups.get(variable));
			}
		}
		
		// Sort first by variable type, and then by variable name
		Collections.sort(sortedConstantVariableNames, new Comparator<String>() {
			override compare(String arg1, String arg2) {
				val Type argType1 = constantVariableTypes.get(arg1);
				val Type argType2 = constantVariableTypes.get(arg2);
				
				if (argType1 === argType2) {
					return arg1.compareTo(arg2);
				}
				return argType1.compareTo(argType2);
			}
		});
	}
	
	/*
	 * General variable information (witness, public parameter, and constant variables)
	 */
	
	// Returns a map from variable names to a list of variable nodes
	// This includes witness variables, pp variables, and constant variables
	def Map<String, List<Variable>> getVariableNodes() {
		if (variables !== null) return variables;
		
		variables = new HashMap<String, List<Variable>>();

		for (FunctionDefinition function : model.getFunctions()) {
			ModelMap.preorder(function.getBody(), [ EObject node |
				getVariableNodesHelper(node, variables);
			]);
		}

		ModelMap.preorder(model.getProof(), [ EObject node |
			getVariableNodesHelper(node, variables);
		]);

		return variables;
	}

	def private void getVariableNodesHelper(EObject node, Map<String, List<Variable>> variables) {
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
	
	/*
	 * User function information
	 */

	// Returns a map from user function names to user function nodes
	def Map<String, FunctionDefinition> getUserFunctionNodes() {
		if (userFunctions !== null) return userFunctions;
		
		userFunctions = new HashMap<String, FunctionDefinition>();

		for (FunctionDefinition function : model.getFunctions()) {
			userFunctions.put(function.getName(), function);
		}

		return userFunctions;
	}

	// Returns a map from user function names to user function call nodes
	def Map<String, List<FunctionCall>> getUserFunctionCallNodes() {
		if (userFunctionCalls !== null) return userFunctionCalls;
		
		val Map<String, FunctionDefinition> userFunctionsMap = getUserFunctionNodes();
		
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
	
	// Returns a map from user function names and local variable names to a list of local variable objects	
	def Map<String, Map<String, List<LocalVariable>>> getLocalVariableNodes() {
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
	
	def Set<String> getUserFunctionWitnessNames(String functionName) {
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
	 * Predefined function call nodes
	 */
	
	// Returns a map from predefined function names to predefined function call nodes
	def Map<String, List<FunctionCall>> getPredefinedFunctionCallNodes() {
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

	/*
	 * General function information (user defined and predefined functions) 
	 */

	// Returns a map from user function names and parameter names to parameter nodes
	def Map<String, Map<String, Parameter>> getParameterNodes() {
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
	def Map<String, Map<String, List<Argument>>> getArgumentNodes() {
		if (arguments !== null) return arguments;
		
		
		val Map<String, FunctionDefinition> userFunctionsMap = this.getUserFunctionNodes();
			
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
	
	/*
	 * Tuple information
	 */
	
	// Returns a list of all tuple nodes in the model, except for tuples nested within
	// other tuples, unless they are first nested within a function call
	def List<Tuple> getTupleNodes() {
		if (tuples !== null) return tuples;
		
		tuples = new ArrayList<Tuple>();
		getTupleNodesHelper1(tuples, model);
		
		return tuples;
	}
	
	def private void getTupleNodesHelper1(List<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMap.Controller controller |
			if (child instanceof Tuple) {
				tuples.add(child);
				getTupleNodesHelper2(tuples, child);				
				controller.continueTraversal();
			}
		]);
	}
	
	def private void getTupleNodesHelper2(List<Tuple> tuples, EObject node) {
		ModelMap.preorderWithControl(node, [EObject child, ModelMap.Controller controller |
			if (child instanceof FunctionCall) {
				getTupleNodesHelper1(tuples, child);
				controller.continueTraversal();
			}
		]);
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
			}
			
			if (sizes !== null && sizes.containsKey(node)) {
				builder.append(" (" + sizes.get(node).toString() + ")");
			}
			
			if (groups !== null && groups.containsKey(node)) {
				builder.append(" (" + groups.get(node).toString() + ")");
			}
			
			builder.append("\n");
		]);
		
		return builder.toString();
	}
}