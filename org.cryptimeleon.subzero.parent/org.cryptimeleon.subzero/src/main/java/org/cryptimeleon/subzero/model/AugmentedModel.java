package org.cryptimeleon.subzero.model;

import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper;
import org.cryptimeleon.subzero.subzero.Argument;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.ConstantVariable;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.NumberLiteral;
import org.cryptimeleon.subzero.subzero.PPVariable;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.SubzeroFactory;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.cryptimeleon.subzero.subzero.WitnessVariable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A wrapper class for the parse tree Model class.
 * Augments the class by providing additional information about the model
 */

public class AugmentedModel {

    private final Model model;

    private boolean containsRangeProof = false;
    private boolean containsPairing = false;
    private boolean containsOrProof = false;
    private boolean containsOrDescendantOfAnd = false;

    private boolean checkedForRangeProof = false;
    private boolean checkedForPairing = false;
    private boolean checkedForOrProof = false;
    private boolean checkedForOrDescendantOfAnd = false;

    // Generated values
    // Must be accessed through their getters, even within class methods (to ensure that they are generated)
    private String protocolName;

    private TypeInference typeInference = null;
    private SizeInference sizeInference = null;
    private GroupInference groupInference = null;

    private Map<String, Witness> witnessNodes = null;
    private Set<String> witnessNames = null;
    private List<String> sortedWitnessNames = null;
    private Map<String, Type> witnessTypes = null;
    private Set<String> constrainedWitnessNames = null;

    private Map<String, PublicParameter> publicParameters = null;
    private Set<String> publicParameterNames = null;
    private List<String> sortedPublicParameterNames = null;
    private Map<String, Type> publicParameterTypes = null;
    private Map<String, GroupType> publicParameterGroups = null;

    private Map<String, List<Variable>> variables = null;
    private Set<String> declaredConstantNames = null;
    private Set<String> constantVariableNames = null;
    private List<String> sortedConstantVariableNames = null;
    private Map<String, Type> constantVariableTypes = null;
    private Map<String, GroupType> constantVariableGroups = null;

    private Map<String, FunctionDefinition> userFunctions = null;
    private Map<String, List<FunctionCall>> userFunctionCalls = null;

    private Map<String, List<WitnessVariable>> userFunctionWitnessNodes = null;

    private Map<String, Map<String, List<LocalVariable>>> localVariables = null;
    private Map<String, Map<String, Parameter>> localParameters = null;
    private List<Tuple> tuples = null;
    private Map<String, Map<String, List<Argument>>> arguments = null;
    private Map<String, List<FunctionCall>> predefinedFunctionCalls = null;
    private Map<String, FunctionSignature> userFunctionSignatures = null;
    private Set<String> userFunctionWithConstant = null;
    private Map<String, Set<String>> userFunctionWitnesses = null;
    private Set<String> inlineFunctionNames = null;

	public AugmentedModel(Model model) {
        this.model = model;

        comparisonTransformation();
//		removeBracketsTransformation(); // Likely unnecessary
        variableRoleTransformation();
    }

    /*
     * Getters for basic model information
     */

    // Returns the model representing the syntax tree
    public Model getModel() {
        return model;
    }

    // Returns the model errors
    public EList<Diagnostic> getErrors() {
        return model.eResource().getErrors();
    }

    public boolean hasErrors() {
        return getErrors().size() > 0;
    }

    // Returns the protocol name, if specified, or the default protocol name
    public String getProtocolName() {
        if (protocolName != null) return protocolName;

        String rawProtocolName = model.getProtocolName();
        if (rawProtocolName == null) {
            protocolName = GenerationUtils.DEFAULT_PROTOCOL_NAME;
        } else {
            protocolName = GenerationUtils.convertToClassName(rawProtocolName.substring(1, rawProtocolName.length()-1));
        }
        return protocolName;
    }

    // Returns the default package name for now
    public String getPackageName() {
        return GenerationUtils.DEFAULT_PACKAGE_NAME;
    }

    // Returns the raw DSL code that produced the model
    public String getCode() {
        return ((XtextResource) model.eResource()).getParseResult().getRootNode().getText();
    }


    /*
     * Getters for inference-based model information (types, sizes, and groups)
     */

    public Map<EObject, Type> getTypes() {
        if (typeInference != null) return typeInference.getTypes();

        typeInference = new TypeInference(this);

        return typeInference.getTypes();
    }


    public Map<EObject, Integer> getSizes() {
        if (sizeInference != null) return sizeInference.getSizes();

        sizeInference = new SizeInference(this);

        return sizeInference.getSizes();
    }

    public Map<String, GroupType> getGroups() {
        if (groupInference != null) return groupInference.getGroups();

        groupInference = new GroupInference(this);

        return groupInference.getGroups();
    }

    /*
     * Methods for getting high-level model structure information
     */
    public boolean hasExplicitConstants() {
        return model.getConstants().size() > 0;
    }

    public boolean requiresPublicParametersClass() {
        return hasRangeProof() || hasOrDescendantOfAnd();
    }

    public Class<?> getGroupClass() {
        if (hasRangeProof() || hasPairing()) {
            return BilinearGroup.class;
        } else {
            return Group.class;
        }
    }

    public boolean hasRangeProof() {
        if (checkedForRangeProof) return containsRangeProof;

        checkedForRangeProof = true;

        // The model has a range proof if it contain any inequality comparison
        containsRangeProof = ModelMap.preorderAny(model, ModelHelper::isInequalityComparison);

        return containsRangeProof;
    }

    public boolean hasPairing() {
        if (checkedForPairing) return containsPairing;

        checkedForPairing = true;

        // The model has a pairing if it has a function call to the 'e' function
        containsPairing = ModelMap.preorderAny(model, (node) -> {
            if (node instanceof FunctionCall) {
                return ((FunctionCall) node).getName().equals("e");
            }

            return false;
        });

        return containsPairing;
    }

    public boolean hasOrProof() {
        if (checkedForOrProof) return containsOrProof;

        checkedForOrProof = true;

        // The model has an OR proof if it contains a disjunction
        containsOrProof = ModelMap.preorderAny(model, node -> node instanceof Disjunction);

        return containsOrProof;
    }

    public boolean hasOrDescendantOfAnd() {
        if (checkedForOrDescendantOfAnd) return containsOrDescendantOfAnd;

        checkedForOrDescendantOfAnd = true;

        // The model has an OR descendant of an AND if it contains a disjunction nested within a conjunction
        containsOrDescendantOfAnd = ModelMap.preorderWithControl(model, (node, controller) -> {
            if (node instanceof Conjunction) {

                boolean hasDisjunction = ModelMap.preorderAny(node, (newNode) -> newNode instanceof Disjunction);

                if (hasDisjunction) {
                    controller.returnTrue();
                }

                controller.continueTraversal();
            }
        });

        return containsOrDescendantOfAnd;
    }

    /*
     * Model transformations that change the structure and node attributes of the syntax tree
     * All transformations are applied to the model when an augmented model object is instantiated
     */

    // Fixes single comparisons to have left and right subtrees, instead of left and center subtrees
    // This is needed due to how the grammar is written
    private void comparisonTransformation() {
        ModelMap.postorder(model, (node) -> {
            if (node instanceof Comparison) {
                Comparison comparison = (Comparison) node;
                if (comparison.getOperation2() == null && comparison.getRight() == null) {
                    comparison.setRight(comparison.getCenter());
                    comparison.setCenter(null);
                }
            }
        });
    }

    // Changes any Variable node within a FunctionDefinition that references
    // a parameter into a LocalVariable node
    // Changes any Variable node that references a witness into a WitnessVariable node,
    // that references a public parameter into a PPVariable node, and all other Variable nodes
    // into ConstantVariable nodes
    private void variableRoleTransformation() {
        Set<String> witnessNames = getWitnessNames();
        Set<String> ppNames = getPublicParameterNames();

        for (FunctionDefinition function : model.getFunctions()) {
            Set<String> parameters = new HashSet<>();
            for (Parameter parameter : function.getParameters()) {
                parameters.add(parameter.getName());
            }

            ModelMap.preorder(function.getBody(), (node) -> {
                if (node instanceof Variable) {
                    Variable var = (Variable) node;
                    if (parameters.contains(var.getName())) {
                        LocalVariable localVariable = SubzeroFactory.eINSTANCE.createLocalVariable();
                        localVariable.setName(var.getName());
                        localVariable.setFunction(function.getName());
                        ModelHelper.replaceParentReferenceToSelf(var, localVariable);
                    } else {
                        variableRoleTransformationHelper(var, witnessNames, ppNames);
                    }

                }
            });
        }

        ModelMap.preorder(model.getProof(), (node) -> {
            if (node instanceof Variable) {
                variableRoleTransformationHelper((Variable) node, witnessNames, ppNames);
            }
        });

    }

    private void variableRoleTransformationHelper(Variable variable, Set<String> witnessNames, Set<String> ppNames) {
        String name = variable.getName();

        if (witnessNames.contains(name)) {
            WitnessVariable witnessVariable = SubzeroFactory.eINSTANCE.createWitnessVariable();
            witnessVariable.setName(name);
            ModelHelper.replaceParentReferenceToSelf(variable, witnessVariable);
        } else if (ppNames.contains(name)) {
            PPVariable ppVariable = SubzeroFactory.eINSTANCE.createPPVariable();
            ppVariable.setName(name);
            ModelHelper.replaceParentReferenceToSelf(variable, ppVariable);
        } else {
            ConstantVariable constantVariable = SubzeroFactory.eINSTANCE.createConstantVariable();
            constantVariable.setName(name);
            ModelHelper.replaceParentReferenceToSelf(variable, constantVariable);
        }
    }


    /*
     * Witness variable information
     */

    // Returns a map from witness names to witness variable nodes
    public Map<String, Witness> getWitnessNodes() {
        if (witnessNodes != null) return witnessNodes;

        witnessNodes = new LinkedHashMap<>(); // Uses insertion order for iteration order

        for (Witness witness : model.getWitnesses()) {
            witnessNodes.put(witness.getName(), witness);
        }

        return witnessNodes;
    }

    // Returns the witness node associated with a witness name
    public Witness getWitnessNode(String witnessName) {
        return getWitnessNodes().get(witnessName);
    }

    // Returns the set of witness variable names
    public Set<String> getWitnessNames() {
        if (witnessNames != null) return witnessNames;

        witnessNames = new LinkedHashSet<>(); // Uses insertion order for iteration order

        for (Witness witness : model.getWitnesses()) {
            witnessNames.add(witness.getName());
        }

        return witnessNames;
    }

    // Returns true if the name is used for a witness variable
    public boolean isWitnessName(String witnessName) {
        return getWitnessNames().contains(witnessName);
    }

    // Returns a list of witness variable names, in sorted order
    public List<String> getSortedWitnessNames() {
        if (sortedWitnessNames != null) return sortedWitnessNames;

        // TODO: change
//		val Set<String> witnessNames = getWitnessNames();
//		sortedWitnessNames = new ArrayList<String>(witnessNames);
//		Collections.sort(sortedWitnessNames);

        sortedWitnessNames = new ArrayList<>();
        for (Witness witness : model.getWitnesses()) {
            sortedWitnessNames.add(witness.getName());
        }

        return sortedWitnessNames;
    }

    // Returns a map from witness variable names to their type
    public Map<String, Type> getWitnessTypes() {
        if (witnessTypes != null) return witnessTypes;

        witnessTypes = new HashMap<>();
        Map<EObject, Type> types = getTypes();

        for (Entry<String, Witness> entry : getWitnessNodes().entrySet()) {
            witnessTypes.put(entry.getKey(), types.get(entry.getValue()));
        }

        return witnessTypes;
    }

    // Returns the type of a witness
    public Type getWitnessType(String witnessName) {
        return getWitnessTypes().get(witnessName);
    }

    // Returns a set containing the names of all witnesses that are constrained by a range or linear exponent constraint
    public Set<String> getConstrainedWitnessNames() {
        if (constrainedWitnessNames != null) return constrainedWitnessNames;

        constrainedWitnessNames = new HashSet<>();

        Map<String, FunctionDefinition> userFunctions = getUserFunctionNodes();
        Set<String> checkedFunctions = new HashSet<>();

        getConstrainedWitnessNamesHelper(model.getProof(), userFunctions, checkedFunctions);

        return constrainedWitnessNames;
    }

    private void getConstrainedWitnessNamesHelper(EObject root, Map<String, FunctionDefinition> userFunctions, Set<String> checkedFunctions) {
        ModelMap.preorderWithControl(root, (node, controller) -> {
            if (node instanceof Power) {
                // Witness variables in the exponent of a power expression are not constrained
                controller.continueTraversal();

            } else if (node instanceof WitnessVariable) {
                WitnessVariable witnessVar = (WitnessVariable) node;
                constrainedWitnessNames.add(witnessVar.getName());

            } else if (node instanceof FunctionCall) {
                FunctionCall functionCall = (FunctionCall) node;
                String functionName = functionCall.getName();

                if (!checkedFunctions.contains(functionName) && userFunctions.containsKey(functionName)) {
                    FunctionDefinition function = userFunctions.get(functionName);
                    checkedFunctions.add(functionName);
                    getConstrainedWitnessNamesHelper(function.getBody(), userFunctions, checkedFunctions);
                }

                // controller.continueTraversal() is NOT used here, because we want the witness names
                // in the function call arguments to also be added to the constrained set
            }
        });
    }

    // Returns true if the witness is constrained by a range or linear exponent constraint
    public boolean isConstrainedWitness(String witnessName) {
        return getConstrainedWitnessNames().contains(witnessName);
    }

    /*
     * Public parameter information
     */

    // Returns a map from public parameter names to public parameter nodes
    public Map<String, PublicParameter> getPublicParameterNodes() {
        if (publicParameters != null) return publicParameters;

        publicParameters = new LinkedHashMap<>(); // Uses insertion order for iteration order

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            publicParameters.put(publicParameter.getName(), publicParameter);
        }

        return publicParameters;
    }

    // Returns the public parameter node associated with a witness name
    public PublicParameter getPublicParameterNode(String ppName) {
        return getPublicParameterNodes().get(ppName);
    }

    // Returns a set containing the names of all public parameters
    public Set<String> getPublicParameterNames() {
        if (publicParameterNames != null) return publicParameterNames;

        publicParameterNames = new LinkedHashSet<>(); // Uses insertion order for iteration order

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            publicParameterNames.add(publicParameter.getName());
        }

        return publicParameterNames;
    }

    // Returns true if the name is used for a public parameter variable
    public boolean isPublicParameterName(String ppName) {
        return getPublicParameterNames().contains(ppName);
    }

    public List<String> getSortedPublicParameterNames() {
        if (sortedPublicParameterNames != null) return sortedPublicParameterNames;

        sortedPublicParameterNames = new ArrayList<>(getPublicParameterNames());
        Collections.sort(sortedPublicParameterNames);

        return sortedPublicParameterNames;
    }

    public Map<String, Type> getPublicParameterTypes() {
        if (publicParameterTypes != null) return publicParameterTypes;

        publicParameterTypes = new HashMap<>();
        Map<EObject, Type> types = getTypes();

        for (Entry<String, PublicParameter> entry : getPublicParameterNodes().entrySet()) {
            publicParameterTypes.put(entry.getKey(), types.get(entry.getValue()));
        }

        return publicParameterTypes;
    }

    public Type getPublicParameterType(String ppName) {
        return getPublicParameterTypes().get(ppName);
    }

    /*
     * Constant variable information
     */
    public Set<String> getDeclaredConstantNames() {
        if (declaredConstantNames != null) return declaredConstantNames;

        declaredConstantNames = new HashSet<>();

        for (Constant constant : model.getConstants()) {
            declaredConstantNames.add(constant.getName());
        }

        return declaredConstantNames;
    }

    public Set<String> getConstantVariableNames() {
        if (constantVariableNames != null) return constantVariableNames;
        getConstantVariablesHelper();
        return constantVariableNames;
    }

    public boolean isConstantVariableName(String constantName) {
        return getConstantVariableNames().contains(constantName);
    }

    public List<String> getSortedConstantVariableNames() {
        if (sortedConstantVariableNames != null) return sortedConstantVariableNames;
        getConstantVariablesHelper();
        return sortedConstantVariableNames;
    }

    public Map<String, Type> getConstantVariableTypes() {
        if (constantVariableTypes != null) return constantVariableTypes;
        getConstantVariablesHelper();
        return constantVariableTypes;
    }

    public Type getConstantVariableType(String constantName) {
        return getConstantVariableTypes().get(constantName);
    }

    private void getConstantVariablesHelper() {
        constantVariableNames = new HashSet<>();
        sortedConstantVariableNames = new ArrayList<>();
        constantVariableTypes = new HashMap<>();

        Map<EObject, Type> types = getTypes();

        for (Entry<String, List<Variable>> entry : getVariableNodes().entrySet()) {
            Variable variable = entry.getValue().get(0);
            String variableName = entry.getKey();

            if (variable instanceof ConstantVariable && !constantVariableNames.contains(variableName)) {
                constantVariableNames.add(variableName);
                sortedConstantVariableNames.add(variableName);
                constantVariableTypes.put(variableName, types.get(variable));
            }
        }

        // Sort first by variable type, and then by variable name
        sortedConstantVariableNames.sort((arg1, arg2) -> {
            Type argType1 = constantVariableTypes.get(arg1);
            Type argType2 = constantVariableTypes.get(arg2);

            if (argType1 == argType2) {
                return arg1.compareTo(arg2);
            }
            return argType1.compareTo(argType2);
        });
    }

    /*
     * General variable information (witness, public parameter, and constant variables)
     */

    // Returns a map from variable names to a list of variable nodes
    // This includes witness variables, pp variables, and constant variables
    public Map<String, List<Variable>> getVariableNodes() {
        if (variables != null) return variables;

        variables = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            ModelMap.preorder(function.getBody(), node -> getVariableNodesHelper(node, variables));
        }

        ModelMap.preorder(model.getProof(), node -> getVariableNodesHelper(node, variables));

        return variables;
    }

    private void getVariableNodesHelper(EObject node, Map<String, List<Variable>> variables) {
        if (node instanceof Variable) {
            if (node instanceof LocalVariable) return;

            Variable var = (Variable) node;
            if (variables.containsKey(var.getName())) {
                variables.get(var.getName()).add(var);
            } else {
                List<Variable> list = new ArrayList<>();
                list.add(var);
                variables.put(var.getName(), list);
            }
        }
    }

    /*
     * User function information
     */

    // Returns a map from user function names to user function nodes
    public Map<String, FunctionDefinition> getUserFunctionNodes() {
        if (userFunctions != null) return userFunctions;

        userFunctions = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            userFunctions.put(function.getName(), function);
        }

        return userFunctions;
    }

    public FunctionDefinition getUserFunctionNode(String functionName) {
        return getUserFunctionNodes().get(functionName);
    }

    // Returns a map from user function names to user function call nodes
    // Unused function definitions will not be stored
    public Map<String, List<FunctionCall>> getUserFunctionCallNodes() {
        if (userFunctionCalls != null) return userFunctionCalls;

        Map<String, FunctionDefinition> userFunctionsMap = getUserFunctionNodes();

        userFunctionCalls = new HashMap<>();

        ModelMap.preorder(model.getProof(), (node) -> {
            if (node instanceof FunctionCall) {
                FunctionCall functionCall = (FunctionCall) node;
                String functionName = functionCall.getName();
                if (userFunctionsMap.containsKey(functionName)) {
                    if (userFunctionCalls.containsKey(functionName)) {
                        userFunctionCalls.get(functionName).add(functionCall);
                    } else {
                        List<FunctionCall> list = new ArrayList<>();
                        list.add(functionCall);
                        userFunctionCalls.put(functionName, list);
                    }
                }
            }
        });

        return userFunctionCalls;
    }

    // Returns a map from user function names to function signatures
    public Map<String, FunctionSignature> getUserFunctionSignatures() {
        if (userFunctionSignatures != null) return userFunctionSignatures;

        Map<EObject, Type> types = getTypes();
        Map<EObject, Integer> sizes = getSizes();

        userFunctionSignatures = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            List<String> parameterNames = new ArrayList<>();
            List<Type> parameterTypes = new ArrayList<>();
            List<Integer> parameterSizes = new ArrayList<>();

            for (Parameter parameter : function.getParameters()) {
                parameterNames.add(parameter.getName());
                parameterTypes.add(types.get(parameter));
                parameterSizes.add(sizes.get(parameter));
            }

            FunctionSignature signature = new FunctionSignature(function.getName(), types.get(function), sizes.get(function), parameterNames, parameterTypes, parameterSizes);
            userFunctionSignatures.put(function.getName(), signature);
        }

        return userFunctionSignatures;
    }

    public FunctionSignature getUserFunctionSignature(String functionName) {
        return getUserFunctionSignatures().get(functionName);
    }

    // Returns a map from user function names and local variable names to a list of local variable objects
    public Map<String, Map<String, List<LocalVariable>>> getLocalVariableNodes() {
        if (localVariables != null) return localVariables;

        localVariables = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            Map<String, List<LocalVariable>> functionVariables = new HashMap<>();

            for (Parameter parameter : function.getParameters()) {
                functionVariables.put(parameter.getName(), new ArrayList<>());
            }

            ModelMap.preorder(function.getBody(), (node) -> {
                if (node instanceof LocalVariable) {
                    LocalVariable localVar = (LocalVariable) node;
                    functionVariables.get(localVar.getName()).add(localVar);
                }
            });

            localVariables.put(function.getName(), functionVariables);
        }

        return localVariables;
    }

    public Set<String> getUserFunctionsWithConstant() {
        if (userFunctionWithConstant != null) return userFunctionWithConstant;

        userFunctionWithConstant = new HashSet<>();
        for (FunctionDefinition function : model.getFunctions()) {
            boolean hasConstant = ModelMap.preorderAny(function.getBody(), node -> node instanceof ConstantVariable);

            if (hasConstant) {
                userFunctionWithConstant.add(function.getName());
            }
        }

        return userFunctionWithConstant;
    }

    public boolean userFunctionHasConstant(String functionName) {
        return getUserFunctionsWithConstant().contains(functionName);
    }


    public Set<String> getUserFunctionWitnessNames(String functionName) {
        if (userFunctionWitnesses != null) return userFunctionWitnesses.get(functionName);
        getUserFunctionWitnessesHelper();
        return userFunctionWitnesses.get(functionName);
    }

    public Map<String, List<WitnessVariable>> getUserFunctionWitnessNodes() {
        if (userFunctionWitnessNodes != null) return userFunctionWitnessNodes;
        getUserFunctionWitnessesHelper();
        return userFunctionWitnessNodes;
    }

    public void getUserFunctionWitnessesHelper() {
        userFunctionWitnesses = new HashMap<>();
        userFunctionWitnessNodes = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            String functionName = function.getName();
            Set<String> functionWitnessNames = new HashSet<>();
            List<WitnessVariable> functionWitnessNodes = new ArrayList<>();

            ModelMap.preorder(function.getBody(), (node) -> {
                if (node instanceof WitnessVariable) {
                    WitnessVariable witnessVar = (WitnessVariable) node;
                    functionWitnessNames.add(witnessVar.getName());
                    functionWitnessNodes.add(witnessVar);
                }
            });

            userFunctionWitnesses.put(functionName, functionWitnessNames);
            userFunctionWitnessNodes.put(functionName, functionWitnessNodes);
        }
    }

    public Set<String> getInlineFunctionNames() {
        if (inlineFunctionNames != null) return inlineFunctionNames;

        inlineFunctionNames = new HashSet<>();
        for (FunctionDefinition function : model.getFunctions()) {
            if (function.isInline()) inlineFunctionNames.add(function.getName());
        }

        return inlineFunctionNames;
    }

    public boolean isInlineFunctionName(String functionName) {
        return getInlineFunctionNames().contains(functionName);
    }

    /*
     * Predefined function call nodes
     */

    // Returns a map from predefined function names to predefined function call nodes
    public Map<String, List<FunctionCall>> getPredefinedFunctionCallNodes() {
        if (predefinedFunctionCalls != null) return predefinedFunctionCalls;

        Map<String, FunctionSignature> predefinedFunctionsMap = PredefinedFunctionsHelper.getAllPredefinedFunctions();

        predefinedFunctionCalls = new HashMap<>();

        ModelMap.preorder(model.getProof(), (node) -> {
            if (node instanceof FunctionCall) {
                FunctionCall functionCall = (FunctionCall) node;
                String functionName = functionCall.getName();
                if (predefinedFunctionsMap.containsKey(functionName)) {
                    if (predefinedFunctionCalls.containsKey(functionName)) {
                        predefinedFunctionCalls.get(functionName).add(functionCall);
                    } else {
                        List<FunctionCall> calls = new ArrayList<>();
                        calls.add(functionCall);
                        predefinedFunctionCalls.put(functionName, calls);
                    }
                }
            }
        });

        return predefinedFunctionCalls;
    }

    /*
     * General function information (user defined and predefined functions)
     */

    // Returns a map from user function names and parameter names to parameter nodes
    public Map<String, Map<String, Parameter>> getParameterNodes() {
        if (localParameters != null) return localParameters;

        localParameters = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            Map<String, Parameter> parameters = new HashMap<>();

            for (Parameter parameter : function.getParameters()) {
                parameters.put(parameter.getName(), parameter);
            }

            localParameters.put(function.getName(), parameters);
        }

        return localParameters;
    }

    // Returns a map from user function names and parameter names to corresponding arguments in function calls
    public Map<String, Map<String, List<Argument>>> getArgumentNodes() {
        if (arguments != null) return arguments;


        Map<String, FunctionDefinition> userFunctionsMap = this.getUserFunctionNodes();

        arguments = new HashMap<>();
        Map<String, List<String>> functionParameters = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            Map<String, List<Argument>> local = new HashMap<>();
            List<String> parameters = new ArrayList<>();

            for (Parameter parameter : function.getParameters()) {
                List<Argument> list = new ArrayList<>();
                local.put(parameter.getName(), list);
                parameters.add(parameter.getName());
            }

            arguments.put(function.getName(), local);
            functionParameters.put(function.getName(), parameters);

        }

        ModelMap.preorder(model.getProof(), (node) -> {
            if (node instanceof FunctionCall) {
                FunctionCall functionCall = (FunctionCall) node;
                String functionName = functionCall.getName();

                if (userFunctionsMap.containsKey(functionName)) {
                    int index = 0;
                    int length = functionParameters.get(functionName).size();
                    Iterator<Expression> argumentIterator = functionCall.getArguments().iterator();

                    while (argumentIterator.hasNext() && index < length) {
                        Argument argument = (Argument) argumentIterator.next();
                        String parameterName = functionParameters.get(functionName).get(index);
                        arguments.get(functionName).get(parameterName).add(argument);
                        index++;
                    }
                }
            }
        });

        return arguments;
    }

    /*
     * Tuple information
     */

    // Returns a list of all tuple nodes in the model, except for tuples nested within
    // other tuples, unless they are first nested within a function call
    public List<Tuple> getTupleNodes() {
        if (tuples != null) return tuples;

        tuples = new ArrayList<>();
        getTupleNodesHelper1(tuples, model);

        return tuples;
    }

    private void getTupleNodesHelper1(List<Tuple> tuples, EObject node) {
        ModelMap.preorderWithControl(node, (child, controller) -> {
            if (child instanceof Tuple) {
                Tuple tuple = (Tuple) child;
                tuples.add(tuple);
                getTupleNodesHelper2(tuples, child);
                controller.continueTraversal();
            }
        });
    }

    private void getTupleNodesHelper2(List<Tuple> tuples, EObject node) {
        ModelMap.preorderWithControl(node, (child, controller) -> {
            if (child instanceof FunctionCall) {
                getTupleNodesHelper1(tuples, child);
                controller.continueTraversal();
            }
        });
    }

    /*
     * String representation
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Map<EObject, Type> types = getTypes();
        Map<EObject, Integer> sizes = getSizes();
        Map<String, GroupType> groups = getGroups();

        ModelMap.preorderWithState(model, new BranchState(), (node, state) -> {
            builder.append("---|".repeat(state.getDepth()));

            String className = node.getClass().getSimpleName();
            builder.append(className);

            if (node instanceof Witness) {
                builder.append(" - " + ((Witness) node).getName());
            } else if (node instanceof FunctionCall) {
                builder.append(" - " + ((FunctionCall) node).getName());
            } else if (node instanceof FunctionDefinition) {
                builder.append(" - " + ((FunctionDefinition) node).getName());
            } else if (node instanceof Parameter) {
                builder.append(" - " + ((Parameter) node).getName());
            } else if (node instanceof Variable) {
                builder.append(" - " + ((Variable) node).getName());
            } else if (node instanceof NumberLiteral) {
                builder.append(" - " + ((NumberLiteral) node).getValue());
            }

            if (types != null && types.containsKey(node)) {
                builder.append(" - " + types.get(node).toString());
            }

            if (sizes != null && sizes.containsKey(node)) {
                builder.append(" (" + sizes.get(node).toString() + ")");
            }

            String name = ModelHelper.getNodeName(node);
            if (groups != null && groups.containsKey(name)) {
                builder.append(" (" + groups.get(name).toString() + ")");
            }

            builder.append("\n");
        });

        return builder.toString();
    }
}