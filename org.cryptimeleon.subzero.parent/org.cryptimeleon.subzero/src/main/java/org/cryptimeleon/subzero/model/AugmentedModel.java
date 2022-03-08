package org.cryptimeleon.subzero.model;

import com.google.common.base.Strings;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.inference.GroupInferenceProvider;
import org.cryptimeleon.subzero.inference.SizeInferenceProvider;
import org.cryptimeleon.subzero.inference.TypeInferenceProvider;
import org.cryptimeleon.subzero.predefined.PredefinedUtils;
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
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.SubzeroFactory;
import org.cryptimeleon.subzero.subzero.Sum;
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
import java.util.function.Predicate;

/**
 * A wrapper class for the parse tree Model class.
 * Augments the class by providing additional information about the model
 */

public class AugmentedModel {
    // All fields (except for model) must be accessed through their getters as they are lazily instantiated

    private final Model model;

    private String protocolName = null;

    // Using boxed type here so that we can check if they have been initialized
    private Boolean containsRangeProof = null;
    private Boolean containsPairing = null;
    private Boolean containsOrProof = null;
    private Boolean containsOrDescendantOfAnd = null;

    private Map<EObject, Type> types = null;
    private Map<EObject, Integer> sizes = null;
    private Map<String, GroupType> groups = null;

    private Map<String, Witness> witnesses = null;
    private Set<String> witnessNames = null;
    private List<String> declaredWitnessNames = null;
    private List<String> sortedWitnessNames = null;
    private Set<String> constrainedWitnessNames = null;
    private Map<String, Type> witnessTypes = null;

    private Map<String, PublicParameter> publicParameters = null;
    private Set<String> publicParameterNames = null;
    private List<String> sortedPublicParameterNames = null;
    private Map<String, Type> publicParameterTypes = null;

    private Set<String> declaredConstantNames = null;
    private Set<String> constantNames = null;
    private List<String> sortedConstantNames = null;
    private Map<String, Type> constantTypes = null;

    private Map<String, List<Variable>> globalVariables = null;

    private Map<String, FunctionDefinition> userFunctionDefinitions = null;
    private Set<String> userFunctionNames = null;
    private Set<String> inlineUserFunctionNames = null;
    private Map<String, FunctionSignature> userFunctionSignatures = null;
    private Set<String> userFunctionWithConstant = null;
    private Map<String, Set<String>> userFunctionWitnessNames = null;
    private Map<String, List<WitnessVariable>> userFunctionWitnessVariables = null;
    private Map<String, Map<String, List<LocalVariable>>> localVariables = null;
    private Map<String, List<FunctionCall>> userFunctionCalls = null;
    private Map<String, Map<String, Parameter>> userFunctionParameters = null;
    private Map<String, Map<String, List<Argument>>> userFunctionArguments = null;

    private Map<String, List<FunctionCall>> predefinedFunctionCalls = null;

    private List<Tuple> tuples = null;


    public AugmentedModel(Model model) {
        this.model = model;

        comparisonTransformation();
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

    // Returns the Subzero code that produced the model
    public String getCode() {
        return ((XtextResource) model.eResource()).getParseResult().getRootNode().getText();
    }


    /*
     * Getters for inference-based model information (types, sizes, and groups)
     */

    public Map<EObject, Type> getTypes() {
        if (types != null) return types;

        types = new TypeInferenceProvider().infer(this);

        types = Collections.unmodifiableMap(types);
        return types;
    }

    public Map<EObject, Integer> getSizes() {
        if (sizes != null) return sizes;

        sizes = new SizeInferenceProvider().infer(this);

        sizes = Collections.unmodifiableMap(sizes);
        return sizes;
    }

    public Map<String, GroupType> getGroups() {
        if (groups != null) return groups;

        groups = new GroupInferenceProvider().infer(this);

        groups = Collections.unmodifiableMap(groups);
        return groups;
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
        if (containsRangeProof != null) return containsRangeProof;

        // The model has a range proof if it contains any inequality comparison
        containsRangeProof = TreeTraversals.anyInPreorderTraversal(model, ModelUtils::isInequalityComparison);

        return containsRangeProof;
    }

    public boolean hasPairing() {
        if (containsPairing != null) return containsPairing;

        // The model has a pairing if it has a function call to the 'e' function
        containsPairing = TreeTraversals.anyInPreorderTraversal(model, (node) -> {
            if (node instanceof FunctionCall) {
                return ((FunctionCall) node).getName().equals("e");
            }

            return false;
        });

        return containsPairing;
    }

    public boolean hasOrProof() {
        if (containsOrProof != null) return containsOrProof;

        // The model has an OR proof if it contains a disjunction
        containsOrProof = TreeTraversals.anyInPreorderTraversal(model, node -> node instanceof Disjunction);

        return containsOrProof;
    }

    public boolean hasOrDescendantOfAnd() {
        if (containsOrDescendantOfAnd != null) return containsOrDescendantOfAnd;

        // The model has an OR descendant of an AND if it contains a disjunction nested within a conjunction
        containsOrDescendantOfAnd = TreeTraversals.preorderTraversalWithControl(model, (node, controller) -> {
            if (node instanceof Conjunction) {

                boolean hasDisjunction = TreeTraversals.anyInPreorderTraversal(node, (newNode) -> newNode instanceof Disjunction);

                if (hasDisjunction) {
                    controller.setReturnValue(true);
                    controller.endTraversal();
                } else {
                    controller.skipBranch();
                }
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
        TreeTraversals.postorderTraversal(model, (node) -> {
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

            TreeTraversals.preorderTraversal(function.getBody(), (node) -> {
                if (node instanceof Variable) {
                    Variable var = (Variable) node;
                    if (parameters.contains(var.getName())) {
                        LocalVariable localVar = SubzeroFactory.eINSTANCE.createLocalVariable();
                        localVar.setName(var.getName());
                        localVar.setFunction(function.getName());
                        ModelUtils.replaceParentReferenceToSelf(var, localVar);
                    } else {
                        variableRoleTransformationHelper(var, witnessNames, ppNames);
                    }

                }
            });
        }

        TreeTraversals.preorderTraversal(model.getProof(), (node) -> {
            if (node instanceof Variable) {
                variableRoleTransformationHelper((Variable) node, witnessNames, ppNames);
            }
        });

    }

    private void variableRoleTransformationHelper(Variable var, Set<String> witnessNames, Set<String> ppNames) {
        String name = var.getName();

        if (witnessNames.contains(name)) {
            WitnessVariable witnessVar = SubzeroFactory.eINSTANCE.createWitnessVariable();
            witnessVar.setName(name);
            ModelUtils.replaceParentReferenceToSelf(var, witnessVar);
        } else if (ppNames.contains(name)) {
            PPVariable ppVar = SubzeroFactory.eINSTANCE.createPPVariable();
            ppVar.setName(name);
            ModelUtils.replaceParentReferenceToSelf(var, ppVar);
        } else {
            ConstantVariable constantVar = SubzeroFactory.eINSTANCE.createConstantVariable();
            constantVar.setName(name);
            ModelUtils.replaceParentReferenceToSelf(var, constantVar);
        }
    }

    /*
     * Witness information
     */

    // Returns a map from witness names to witness variable nodes
    public Map<String, Witness> getWitnesses() {
        if (witnesses != null) return witnesses;

        witnesses = new LinkedHashMap<>(); // Uses insertion order for iteration order

        for (Witness witness : model.getWitnesses()) {
            witnesses.put(witness.getName(), witness);
        }

        witnesses = Collections.unmodifiableMap(witnesses);
        return witnesses;
    }

    // Returns the witness node associated with a witness name
    public Witness getWitness(String witnessName) {
        return getWitnesses().get(witnessName);
    }

    // Returns the set of witness variable names
    public Set<String> getWitnessNames() {
        if (witnessNames != null) return witnessNames;

        witnessNames = new LinkedHashSet<>(); // Uses insertion order for iteration order

        for (Witness witness : model.getWitnesses()) {
            witnessNames.add(witness.getName());
        }

        witnessNames = Collections.unmodifiableSet(witnessNames);
        return witnessNames;
    }

    // Returns true if the name is used for a witness variable
    public boolean isWitnessName(String witnessName) {
        return getWitnessNames().contains(witnessName);
    }

    // Returns a list of witness variable names, in declaration order
    public List<String> getDeclaredWitnessNames() {
        if (declaredWitnessNames != null) return declaredWitnessNames;

        declaredWitnessNames = new ArrayList<>();
        for (Witness witness : model.getWitnesses()) {
            declaredWitnessNames.add(witness.getName());
        }

        declaredWitnessNames = Collections.unmodifiableList(declaredWitnessNames);
        return declaredWitnessNames;
    }

    // Returns a list of witness variable names, in sorted order
    public List<String> getSortedWitnessNames() {
        if (sortedWitnessNames != null) return sortedWitnessNames;

		sortedWitnessNames = new ArrayList<>(getWitnessNames());
		Collections.sort(sortedWitnessNames);

        sortedWitnessNames = Collections.unmodifiableList(sortedWitnessNames);
        return sortedWitnessNames;
    }

    // Returns a set containing the names of all witnesses that are constrained by a range or linear exponent constraint
    public Set<String> getConstrainedWitnessNames() {
        if (constrainedWitnessNames != null) return constrainedWitnessNames;

        constrainedWitnessNames = new HashSet<>();

        Map<String, FunctionDefinition> userFunctions = getUserFunctionDefinitions();
        Set<String> checkedFunctions = new HashSet<>();

        getConstrainedWitnessNamesHelper(model.getProof(), userFunctions, checkedFunctions);

        constrainedWitnessNames = Collections.unmodifiableSet(constrainedWitnessNames);
        return constrainedWitnessNames;
    }

    private void getConstrainedWitnessNamesHelper(EObject root, Map<String, FunctionDefinition> userFunctions, Set<String> checkedFunctions) {
        TreeTraversals.preorderTraversalWithControl(root, (node, controller) -> {
            if (node instanceof Power) {
                // Witness variables in the exponent of a power expression are not constrained
                controller.skipBranch();

            } else if (node instanceof WitnessVariable) {
                WitnessVariable witnessVar = (WitnessVariable) node;
                constrainedWitnessNames.add(witnessVar.getName());

            } else if (node instanceof FunctionCall) {
                FunctionCall functionCall = (FunctionCall) node;
                String functionName = functionCall.getName();

                if (!checkedFunctions.contains(functionName) && userFunctions.containsKey(functionName)) {
                    FunctionDefinition functionDef = userFunctions.get(functionName);
                    checkedFunctions.add(functionName);
                    getConstrainedWitnessNamesHelper(functionDef.getBody(), userFunctions, checkedFunctions);
                }

                // controller.skipBranch() is NOT used here, because we want the witness names
                // in the function call arguments to also be added to the constrained set
            }
        });
    }

    // Returns true if the witness is constrained by a range or linear exponent constraint
    public boolean isConstrainedWitness(String witnessName) {
        return getConstrainedWitnessNames().contains(witnessName);
    }

    // Returns a map from witness names to their type
    public Map<String, Type> getWitnessTypes() {
        if (witnessTypes != null) return witnessTypes;

        witnessTypes = new HashMap<>();
        Map<EObject, Type> types = getTypes();

        for (Witness witness : model.getWitnesses()) {
            witnessTypes.put(witness.getName(), types.get(witness));
        }

        witnessTypes = Collections.unmodifiableMap(witnessTypes);
        return witnessTypes;
    }

    // Returns the type of a witness
    public Type getWitnessType(String witnessName) {
        return getWitnessTypes().get(witnessName);
    }

    /*
     * Public parameter information
     */

    // Returns a map from public parameter names to public parameter declaration nodes
    public Map<String, PublicParameter> getPublicParameters() {
        if (publicParameters != null) return publicParameters;

        publicParameters = new LinkedHashMap<>(); // Uses insertion order for iteration order

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            publicParameters.put(publicParameter.getName(), publicParameter);
        }

        publicParameters = Collections.unmodifiableMap(publicParameters);
        return publicParameters;
    }

    // Returns the public parameter node associated with a witness name
    public PublicParameter getPublicParameter(String ppName) {
        return getPublicParameters().get(ppName);
    }

    // Returns a set containing the names of all public parameters
    public Set<String> getPublicParameterNames() {
        if (publicParameterNames != null) return publicParameterNames;

        publicParameterNames = new LinkedHashSet<>(); // Uses insertion order for iteration order

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            publicParameterNames.add(publicParameter.getName());
        }

        publicParameterNames = Collections.unmodifiableSet(publicParameterNames);
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

        sortedPublicParameterNames = Collections.unmodifiableList(sortedPublicParameterNames);
        return sortedPublicParameterNames;
    }

    public Map<String, Type> getPublicParameterTypes() {
        if (publicParameterTypes != null) return publicParameterTypes;

        publicParameterTypes = new HashMap<>();
        Map<EObject, Type> types = getTypes();

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            publicParameterTypes.put(publicParameter.getName(), types.get(publicParameter));
        }

        publicParameterTypes = Collections.unmodifiableMap(publicParameterTypes);
        return publicParameterTypes;
    }

    public Type getPublicParameterType(String ppName) {
        return getPublicParameterTypes().get(ppName);
    }

    /*
     * Constant information
     */

    public Set<String> getDeclaredConstantNames() {
        if (declaredConstantNames != null) return declaredConstantNames;

        declaredConstantNames = new HashSet<>();

        for (Constant constant : model.getConstants()) {
            declaredConstantNames.add(constant.getName());
        }

        declaredConstantNames = Collections.unmodifiableSet(declaredConstantNames);
        return declaredConstantNames;
    }

    public Set<String> getConstantNames() {
        if (constantNames != null) return constantNames;

        getConstantsHelper();

        return constantNames;
    }

    public boolean isConstantName(String constantName) {
        return getConstantNames().contains(constantName);
    }

    public List<String> getSortedConstantNames() {
        if (sortedConstantNames != null) return sortedConstantNames;

        getConstantsHelper();

        return sortedConstantNames;
    }

    public Map<String, Type> getConstantTypes() {
        if (constantTypes != null) return constantTypes;

        getConstantsHelper();

        return constantTypes;
    }

    public Type getConstantType(String constantName) {
        return getConstantTypes().get(constantName);
    }

    private void getConstantsHelper() {
        constantNames = new HashSet<>();
        sortedConstantNames = new ArrayList<>();
        constantTypes = new HashMap<>();

        Map<EObject, Type> types = getTypes();

        for (Entry<String, List<Variable>> entry : getGlobalVariables().entrySet()) {
            Variable var = entry.getValue().get(0);
            String varName = entry.getKey();

            if (var instanceof ConstantVariable && !constantNames.contains(varName)) {
                constantNames.add(varName);
                sortedConstantNames.add(varName);
                constantTypes.put(varName, types.get(var));
            }
        }

        // Sort first by variable type, and then by variable name
        sortedConstantNames.sort((arg1, arg2) -> {
            Type argType1 = constantTypes.get(arg1);
            Type argType2 = constantTypes.get(arg2);

            if (argType1 == argType2) {
                return arg1.compareTo(arg2);
            }
            return argType1.compareTo(argType2);
        });

        constantNames = Collections.unmodifiableSet(constantNames);
        sortedConstantNames = Collections.unmodifiableList(sortedConstantNames);
        constantTypes = Collections.unmodifiableMap(constantTypes);
    }

    /*
     * Global variable information (includes witness, public parameter, and constant variables)
     */

    // Returns a map from variable names to a list of variable nodes
    // This includes witness variables, pp variables, and constant variables
    public Map<String, List<Variable>> getGlobalVariables() {
        if (globalVariables != null) return globalVariables;

        globalVariables = new HashMap<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            TreeTraversals.preorderTraversal(functionDef.getBody(), node -> getGlobalVariableNodesHelper(node, globalVariables));
        }

        TreeTraversals.preorderTraversal(model.getProof(), node -> getGlobalVariableNodesHelper(node, globalVariables));

        globalVariables.replaceAll((varName, varList) -> Collections.unmodifiableList(varList));
        globalVariables = Collections.unmodifiableMap(globalVariables);
        return globalVariables;
    }

    private void getGlobalVariableNodesHelper(EObject node, Map<String, List<Variable>> globalVariables) {
        if (node instanceof Variable) {
            if (node instanceof LocalVariable) return;

            Variable var = (Variable) node;
            List<Variable> vars = globalVariables.computeIfAbsent(var.getName(), k -> new ArrayList<>());
            vars.add(var);
        }
    }

    /*
     * User function information
     */

    // Returns a map from user function names to user function nodes
    public Map<String, FunctionDefinition> getUserFunctionDefinitions() {
        if (userFunctionDefinitions != null) return userFunctionDefinitions;

        userFunctionDefinitions = new HashMap<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            userFunctionDefinitions.put(functionDef.getName(), functionDef);
        }

        userFunctionDefinitions = Collections.unmodifiableMap(userFunctionDefinitions);
        return userFunctionDefinitions;
    }

    public FunctionDefinition getUserFunctionDefinition(String functionName) {
        return getUserFunctionDefinitions().get(functionName);
    }

    // Returns the set of user function names
    public Set<String> getUserFunctionNames() {
        if (userFunctionNames != null) return userFunctionNames;

        userFunctionNames = new HashSet<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            userFunctionNames.add(functionDef.getName());
        }

        userFunctionNames = Collections.unmodifiableSet(userFunctionNames);
        return userFunctionNames;
    }

    public boolean isUserFunctionName(String functionName) {
        return getUserFunctionNames().contains(functionName);
    }

    public Set<String> getInlineUserFunctionNames() {
        if (inlineUserFunctionNames != null) return inlineUserFunctionNames;

        inlineUserFunctionNames = new HashSet<>();
        for (FunctionDefinition function : model.getFunctions()) {
            if (function.isInline()) {
                inlineUserFunctionNames.add(function.getName());
            }
        }

        inlineUserFunctionNames = Collections.unmodifiableSet(inlineUserFunctionNames);
        return inlineUserFunctionNames;
    }

    public boolean isInlineUserFunctionName(String functionName) {
        return getInlineUserFunctionNames().contains(functionName);
    }

    // Returns a map from user function names to function signatures
    public Map<String, FunctionSignature> getUserFunctionSignatures() {
        if (userFunctionSignatures != null) return userFunctionSignatures;

        Map<EObject, Type> types = getTypes();
        Map<EObject, Integer> sizes = getSizes();

        userFunctionSignatures = new HashMap<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            List<String> parameterNames = new ArrayList<>();
            List<Type> parameterTypes = new ArrayList<>();
            List<Integer> parameterSizes = new ArrayList<>();

            for (Parameter parameter : functionDef.getParameters()) {
                parameterNames.add(parameter.getName());
                parameterTypes.add(types.get(parameter));
                parameterSizes.add(sizes.get(parameter));
            }

            FunctionSignature signature = new FunctionSignature(
                    functionDef.getName(), types.get(functionDef), sizes.get(functionDef),
                    parameterNames, parameterTypes, parameterSizes
            );

            userFunctionSignatures.put(functionDef.getName(), signature);
        }

        userFunctionSignatures = Collections.unmodifiableMap(userFunctionSignatures);
        return userFunctionSignatures;
    }

    public FunctionSignature getUserFunctionSignature(String functionName) {
        return getUserFunctionSignatures().get(functionName);
    }

    public Set<String> getUserFunctionsWithConstant() {
        if (userFunctionWithConstant != null) return userFunctionWithConstant;

        userFunctionWithConstant = new HashSet<>();
        for (FunctionDefinition function : model.getFunctions()) {
            boolean hasConstant = TreeTraversals.anyInPreorderTraversal(function.getBody(), node -> node instanceof ConstantVariable);

            if (hasConstant) {
                userFunctionWithConstant.add(function.getName());
            }
        }

        userFunctionWithConstant = Collections.unmodifiableSet(userFunctionWithConstant);
        return userFunctionWithConstant;
    }

    public boolean userFunctionHasConstant(String functionName) {
        return getUserFunctionsWithConstant().contains(functionName);
    }

    public Map<String, Set<String>> getUserFunctionWitnessNames() {
        if (userFunctionWitnessNames != null) return userFunctionWitnessNames;

        getUserFunctionWitnessesHelper();

        return userFunctionWitnessNames;
    }

    public Map<String, List<WitnessVariable>> getUserFunctionWitnessVariables() {
        if (userFunctionWitnessVariables != null) return userFunctionWitnessVariables;

        getUserFunctionWitnessesHelper();

        return userFunctionWitnessVariables;
    }

    private void getUserFunctionWitnessesHelper() {
        userFunctionWitnessNames = new HashMap<>();
        userFunctionWitnessVariables = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            String functionName = function.getName();
            Set<String> functionWitnessNames = new HashSet<>();
            List<WitnessVariable> functionWitnessVars = new ArrayList<>();

            TreeTraversals.preorderTraversal(function.getBody(), (node) -> {
                if (node instanceof WitnessVariable) {
                    WitnessVariable witnessVar = (WitnessVariable) node;
                    functionWitnessNames.add(witnessVar.getName());
                    functionWitnessVars.add(witnessVar);
                }
            });

            userFunctionWitnessNames.put(functionName, Collections.unmodifiableSet(functionWitnessNames));
            userFunctionWitnessVariables.put(functionName, Collections.unmodifiableList(functionWitnessVars));
        }

        userFunctionWitnessNames = Collections.unmodifiableMap(userFunctionWitnessNames);
        userFunctionWitnessVariables = Collections.unmodifiableMap(userFunctionWitnessVariables);
    }

    // Returns a map from user function names and local variable names to a list of local variable objects
    public Map<String, Map<String, List<LocalVariable>>> getLocalVariables() {
        if (localVariables != null) return localVariables;

        localVariables = new HashMap<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            Map<String, List<LocalVariable>> functionVariables = new HashMap<>();

            for (Parameter parameter : functionDef.getParameters()) {
                functionVariables.put(parameter.getName(), new ArrayList<>());
            }

            TreeTraversals.preorderTraversal(functionDef.getBody(), (node) -> {
                if (node instanceof LocalVariable) {
                    LocalVariable localVar = (LocalVariable) node;
                    functionVariables.get(localVar.getName()).add(localVar);
                }
            });

            functionVariables.replaceAll((varName, varList) -> Collections.unmodifiableList(varList));
            localVariables.put(functionDef.getName(), functionVariables);
        }

        localVariables.replaceAll((functionName, localVarMap) -> Collections.unmodifiableMap(localVarMap));
        localVariables = Collections.unmodifiableMap(localVariables);
        return localVariables;
    }

    // Returns a map from user function names to user function call nodes
    // Unused function definitions will not be stored
    public Map<String, List<FunctionCall>> getUserFunctionCalls() {
        if (userFunctionCalls != null) return userFunctionCalls;

        userFunctionCalls = getFunctionCallsHelper(this::isUserFunctionName);

        return userFunctionCalls;
    }

    private Map<String, List<FunctionCall>> getFunctionCallsHelper(Predicate<String> isFunctionCategory) {
        final Map<String, List<FunctionCall>> functionCalls = new HashMap<>();

        TreeTraversals.preorderTraversal(model.getProof(), (node) -> {
            if (node instanceof FunctionCall) {
                FunctionCall call = (FunctionCall) node;
                String functionName = call.getName();

                if (isFunctionCategory.test(functionName)) {
                    List<FunctionCall> calls = functionCalls.computeIfAbsent(functionName, k -> new ArrayList<>());
                    calls.add(call);
                }
            }
        });

        functionCalls.replaceAll((functionName, callList) -> Collections.unmodifiableList(callList));
        return Collections.unmodifiableMap(functionCalls);
    }

    /*
     * General function information (user defined and predefined functions)
     */

    // Returns a map from user function names and parameter names to parameter nodes
    public Map<String, Map<String, Parameter>> getUserFunctionParameters() {
        if (userFunctionParameters != null) return userFunctionParameters;

        userFunctionParameters = new HashMap<>();

        for (FunctionDefinition functionDef : model.getFunctions()) {
            Map<String, Parameter> parameters = new HashMap<>();

            for (Parameter parameter : functionDef.getParameters()) {
                parameters.put(parameter.getName(), parameter);
            }

            userFunctionParameters.put(functionDef.getName(), Collections.unmodifiableMap(parameters));
        }

        userFunctionParameters = Collections.unmodifiableMap(userFunctionParameters);
        return userFunctionParameters;
    }

    // Returns a map from user function names and parameter names to corresponding arguments in function calls
    public Map<String, Map<String, List<Argument>>> getUserFunctionArguments() {
        if (userFunctionArguments != null) return userFunctionArguments;

        userFunctionArguments = new HashMap<>();
        Map<String, List<String>> functionParameters = new HashMap<>();

        for (FunctionDefinition function : model.getFunctions()) {
            Map<String, List<Argument>> functionArguments = new HashMap<>();
            List<String> parameterNames = new ArrayList<>();

            for (Parameter parameter : function.getParameters()) {
                List<Argument> parameterArguments = new ArrayList<>();
                functionArguments.put(parameter.getName(), parameterArguments);
                parameterNames.add(parameter.getName());
            }

            userFunctionArguments.put(function.getName(), functionArguments);
            functionParameters.put(function.getName(), parameterNames);
        }

        TreeTraversals.preorderTraversal(model.getProof(), (node) -> {
            if (node instanceof FunctionCall) {
                FunctionCall call = (FunctionCall) node;
                String functionName = call.getName();
                Map<String, List<Argument>> specificUserFunctionArguments = userFunctionArguments.get(functionName);

                if (isUserFunctionName(functionName)) {
                    List<String> parameterNames = functionParameters.get(functionName);
                    Iterator<Expression> argumentIter = call.getArguments().iterator();
                    Iterator<String> parameterIter = parameterNames.iterator();

                    while (argumentIter.hasNext() && parameterIter.hasNext()) {
                        Argument argument = (Argument) argumentIter.next();
                        String parameterName = parameterIter.next();
                        specificUserFunctionArguments.get(parameterName).add(argument);
                    }
                }
            }
        });

        userFunctionArguments.replaceAll((functionName, parameterArgumentMap) -> {
            parameterArgumentMap.replaceAll((parameterName, argumentList) -> Collections.unmodifiableList(argumentList));
            return Collections.unmodifiableMap(parameterArgumentMap);
        });
        userFunctionArguments = Collections.unmodifiableMap(userFunctionArguments);
        return userFunctionArguments;
    }

    /*
     * Predefined function information
     */

    // Returns a map from predefined function names to predefined function call nodes
    public Map<String, List<FunctionCall>> getPredefinedFunctionCalls() {
        if (predefinedFunctionCalls != null) return predefinedFunctionCalls;

        predefinedFunctionCalls = getFunctionCallsHelper(PredefinedUtils::isPredefinedFunction);

        return predefinedFunctionCalls;
    }

    /*
     * Tuple information
     */

    // Returns a list of all tuple nodes in the model, except for tuples nested within
    // other tuples, unless they are first nested within a function call
    public List<Tuple> getTuples() {
        if (tuples != null) return tuples;

        tuples = new ArrayList<>();
        getTuplesHelper1(tuples, model);

        tuples = Collections.unmodifiableList(tuples);
        return tuples;
    }

    private void getTuplesHelper1(List<Tuple> tuples, EObject node) {
        TreeTraversals.preorderTraversalWithControl(node, (child, controller) -> {
            if (child instanceof Tuple) {
                Tuple tuple = (Tuple) child;
                tuples.add(tuple);
                getTuplesHelper2(tuples, child);
                controller.skipBranch();
            }
        });
    }

    private void getTuplesHelper2(List<Tuple> tuples, EObject node) {
        TreeTraversals.preorderTraversalWithControl(node, (child, controller) -> {
            if (child instanceof FunctionCall) {
                getTuplesHelper1(tuples, child);
                controller.skipBranch();
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
        Map<String, GroupType> groups = getGroups();

        toStringHelper(model, "", "", types, groups, builder);
        return builder.toString();
    }

    private void toStringHelper(
        EObject node, String indent, String childIndent,
        Map<EObject, Type> types, Map<String, GroupType> groups, StringBuilder builder
    ) {
        String separator = " - ";
        builder.append(indent);

        // Print out the node class name
        String className = node.getClass().getInterfaces()[0].getSimpleName();
        if (node instanceof Witness || node instanceof WitnessVariable) {
            className = Color.brightBlue(className);
        } else if (node instanceof PublicParameter || node instanceof PPVariable) {
            className = Color.brightBlue(className);
        } else if (node instanceof Constant || node instanceof ConstantVariable) {
            className = Color.brightGreen(className);
        }
        builder.append(className);

        // Print out the protocol name, if applicable
        if (node instanceof Model) {
            String protocol = ((Model) node).getProtocolName();
            if (!Strings.isNullOrEmpty(protocol)) {
                builder.append(separator);
                builder.append(Color.brightYellow(protocol));
            }
        }

        // Print out the node's name, if applicable
        String name = ModelUtils.getNodeName(node);
        if (!Strings.isNullOrEmpty(name)) {
            builder.append(separator);
            builder.append(Color.brightCyan(name));
        }

        // Print out the node's value, if applicable
        if (node instanceof NumberLiteral) {
            String value = String.valueOf(((NumberLiteral) node).getValue());
            builder.append(separator);
            builder.append(Color.brightCyan(value));
        }

        // Print out the node's operation, if applicable
        String operation = null;
        if (node instanceof Comparison) {
            Comparison comp = (Comparison) node;
            operation = comp.getOperation();
            String operation2 = comp.getOperation2();
            if (!Strings.isNullOrEmpty(operation2)) {
                operation += " " + operation2;
            }
        } else if (node instanceof Product) {
            operation = ((Product) node).getOperation();
        } else if (node instanceof Sum) {
            operation = ((Sum) node).getOperation();
        }

        if (!Strings.isNullOrEmpty(operation)) {
            builder.append(separator);
            builder.append(Color.brightCyan(operation));
        }

        // Print out the subprotocol name, if applicable
        if (node instanceof Comparison) {
            String subprotocol = ((Comparison) node).getSubprotocolName();
            if (!Strings.isNullOrEmpty(subprotocol)) {
                builder.append(separator);
                builder.append(Color.yellow(subprotocol));
            }
        }

        // Print out if a function definition is an inline function
        if (node instanceof FunctionDefinition) {
            boolean inline = ((FunctionDefinition) node).isInline();
            if (inline) {
                builder.append(separator);
                builder.append(Color.red("inline"));
            }
        }

        // Print out the node type, including its group type for applicable group element nodes
        Type type = types.get(node);
        if (type != null) {
            builder.append(separator);
            String typeName = type.toString();

            if (type == Type.GROUP_ELEMENT) {
                GroupType group = groups.get(name);
                if (group != null) {
                    typeName += " (" + group + ")";
                }

                typeName = Color.magenta(typeName);
            } else if (type == Type.EXPONENT) {
                typeName = Color.green(typeName);
            } else if (type == Type.BOOLEAN) {
                typeName = Color.yellow(typeName);
            }

            builder.append(typeName);
        }

        builder.append('\n');

        // Print out all child nodes
        for (Iterator<EObject> iter = node.eContents().iterator(); iter.hasNext();) {
            EObject child = iter.next();

            if (iter.hasNext()) {
                toStringHelper(child,  childIndent + "├── ", childIndent + "│   ", types, groups, builder);
            } else {
                toStringHelper(child, childIndent + "└── ", childIndent + "    ", types, groups, builder);
            }
        }
    }
}