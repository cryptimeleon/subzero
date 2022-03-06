package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.BranchState;
import org.cryptimeleon.subzero.model.ModelUtils;
import org.cryptimeleon.subzero.model.TreeTraversals;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.subzero.Brackets;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.StringLiteral;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.WitnessVariable;
import org.eclipse.emf.ecore.EObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrammarValidation {

    private final ValidationLogger logger;
    private final Map<EObject, Type> types;
    private final Map<String, List<WitnessVariable>> userFunctionWitnessNodes;

    public GrammarValidation(AugmentedModel augmentedModel, ValidationLogger logger) {
        this.logger = logger;
        types = augmentedModel.getTypes();
        userFunctionWitnessNodes = augmentedModel.getUserFunctionWitnessNodes();
    }

    // Protocol must have a proof expression
    public void checkHasProof(Model model) {
        if (model.getProof() == null) {
            logger.error(model, "Must have a proof statement");
        }
    }

    // String literals must be directly nested within a tuple, a comparison, or a function call
    public void checkStringLiteralPositionIsValid(StringLiteral stringLiteral, BranchState state) {
        EObject parent = state.getParent();

        if (!(parent instanceof FunctionCall)) {
            logger.error(stringLiteral, "String literals can only be used as arguments in calls to predefined functions");
        }

    }

    // Disjunctions cannot be nested within algebraic expressions or comparison expressions
    public void checkDisjunctionPositionIsValid(Disjunction disjunction, BranchState state) {
        if (!isValidBooleanPosition(disjunction, state)) {
            logger.error(disjunction, "Disjunctions cannot be nested within algebraic expressions, comparison expressions, or function calls");
        }
    }

    // Conjunctions cannot be nested within algebraic expressions or comparison expressions
    public void checkConjunctionPositionIsValid(Conjunction conjunction, BranchState state) {
        if (!isValidBooleanPosition(conjunction, state)) {
            logger.error(conjunction, "Conjunctions cannot be nested within algebraic expressions, comparison expressions, or function calls");
        }
    }

    // Comparisons cannot be nested within algebraic expressions or comparison expressions
    public void checkValidComparisonPosition(Comparison comparison, BranchState state) {
        if (!isValidBooleanPosition(comparison, state)) {
            logger.error(comparison, "Comparisons cannot be nested within algebraic expressions, other comparison expressions, or function calls");
        }
    }

    // Double comparisons cannot use equals or not equals, and inequality operators must be the same direction
    public void checkComparisonOperations(Comparison comparison) {
        String operation1 = comparison.getOperation();
        String operation2 = comparison.getOperation2();

        // Check if the comparison is not equals (not yet supported)
        if (operation1.equals("!=")) {
            logger.error(comparison, "The != operator is currently not supported");
        }

        // Check if the comparison is a double comparison
        if (operation2 != null) {

            if (operation1.equals("=") || operation1.equals("!=") || operation2.equals("=") || operation2.equals("!=")) {
                logger.error(comparison, "Cannot use = or != in a double comparison");
            } else if (operation1.charAt(0) != operation2.charAt(0)) {
                logger.error(comparison, "Comparison operators in a double comparison must be in the same direction");
            }
        }
    }

    public void checkComparisonOperandWitnesses(Comparison comparison) {
        boolean leftHasWitness = ModelUtils.containsWitnessVariable(comparison.getLeft());
        boolean rightHasWitness = ModelUtils.containsWitnessVariable(comparison.getRight());

        if (comparison.getOperation2() == null) {
            if (ModelUtils.isInequalityComparison(comparison)) {
                if (!leftHasWitness && !rightHasWitness) {
                    logger.error(comparison, "One side of a comparison must be dependent on a witness variable");
                }

                if (leftHasWitness && rightHasWitness) {
                    logger.error(comparison, "Only one side of a comparison can be dependent on a witness variable");
                }
            }

        } else {
            boolean centerHasWitness = ModelUtils.containsWitnessVariable(comparison.getCenter());

            if (!centerHasWitness) {
                logger.error(comparison, "The middle of a double comparison must be dependent on a witness variable");
            }

            if (leftHasWitness || rightHasWitness) {
                logger.error(comparison, "Only the middle of a double comparison can be dependent on a witness variable");
            }
        }
    }

    // Helper function for checkValidDisjunctionPosition, checkValidConjunctionPosition, and checkValidComparisonPosition
    private boolean isValidBooleanPosition(EObject node, BranchState state) {
        EObject parent = state.getParent();
        return parent instanceof Model
            || parent instanceof FunctionDefinition
            || parent instanceof Disjunction
            || parent instanceof Conjunction
            || parent instanceof Brackets;
    }

    // Algebraic expressions must be nested within a comparison expression before being nested within a logical expression
    public void checkValidAlgebraicPosition(EObject node, BranchState state) {
        if (state.hasFunctionDefinitionAncestor() || state.hasFunctionCallAncestor()) return;

        if (state.hasLogicalBeforeComparison()) {
            logger.error(node, "Algebraic expression must be nested within a comparison expression before being nested within a logical expression");
        }
    }

    // Algebraic expressions in the proof expression must be nested within a comparison expression or function call
    public void checkProofAlgebraicPosition(EObject object, BranchState state) {
        if (state.hasFunctionDefinitionAncestor()) return;

        if (!(state.hasComparisonAncestor() || state.hasFunctionCallAncestor())) {
            logger.error(object, "Algebraic expressions in the proof expression must be nested within a comparison expression or function call");
        }
    }

    // Function calls to boolean functions cannot be nested within algebraic expressions, comparison expressions, or other function calls
    public void checkFunctionCallPositionIsValid(FunctionCall call, BranchState state) {
        if (types.get(call) == Type.BOOLEAN) {
            if (!isValidBooleanPosition(call, state)) {
                logger.error(call, "Function calls to boolean functions cannot be nested within algebraic expressions, comparison expressions, or other function calls");
            }
        } else {
            checkProofAlgebraicPosition(call, state);
        }
    }

    public void checkWitnessIsNotInExponentOfWitness(Power power) {
        EObject left = power.getLeft();
        EObject right = power.getRight();

        if (left instanceof WitnessVariable) {
            TreeTraversals.preorderTraversal(right, (node) -> {
                if (node instanceof WitnessVariable) {
                    createWitnessIsNotInExponentOfWitnessError((WitnessVariable) node);
                } else if (node instanceof FunctionCall) {
                    String functionName = ((FunctionCall) node).getName();
                    if (userFunctionWitnessNodes.containsKey(functionName)) {
                        List<WitnessVariable> witnessNodes = userFunctionWitnessNodes.get(functionName);
                        for (WitnessVariable witnessNode : witnessNodes) {
                            createWitnessIsNotInExponentOfWitnessError(witnessNode);
                        }
                    }
                }
            });
        }
    }

    private void createWitnessIsNotInExponentOfWitnessError(WitnessVariable witness) {
        logger.error(witness, "A witness cannot be in the exponent of another witness");
    }

    // A group element witness cannot be in both subtrees of a disjunction
    // that has a conjunction ancestor
    public void checkOrWithAndAncestorGroupElementWitnesses(Model model) {
        TreeTraversals.preorderTraversalWithStateAndControl(model.getProof(), (node, state, controller) -> {
            if (node instanceof Disjunction && state.hasConjunctionAncestor()) {
                controller.skipBranch();

                Map<String, List<WitnessVariable>> witnessNodes = new HashMap<>();
                orTreeHelper(node, witnessNodes);
            }
        });
    }

    private void orTreeHelper(EObject node, Map<String, List<WitnessVariable>> witnessNodes) {
        if (node instanceof WitnessVariable) {
            orTreeWitnessNodeHelper((WitnessVariable) node, witnessNodes);

        } else if (node instanceof FunctionCall) {
            FunctionCall call = (FunctionCall) node;
            String functionName = call.getName();
            if (userFunctionWitnessNodes.containsKey(functionName)) {
                List<WitnessVariable> functionWitnessNodes = userFunctionWitnessNodes.get(functionName);
                for (WitnessVariable witnessNode : functionWitnessNodes) {
                    orTreeWitnessNodeHelper(witnessNode, witnessNodes);
                }
            }
            for (Expression argument : call.getArguments()) {
                orTreeHelper(argument, witnessNodes);
            }

        } else if (node instanceof Disjunction) {
            Disjunction disjunction = (Disjunction) node;
            Map<String, List<WitnessVariable>> leftWitnessNodes = new HashMap<>();
            Map<String, List<WitnessVariable>> rightWitnessNodes = new HashMap<>();
            orTreeHelper(disjunction.getLeft(), leftWitnessNodes);
            orTreeHelper(disjunction.getRight(), rightWitnessNodes);

            Set<String> sharedNames = new HashSet<>(leftWitnessNodes.keySet());
            sharedNames.retainAll(rightWitnessNodes.keySet());

            for (String name : sharedNames) {
                createOrErrors(leftWitnessNodes.get(name));
                createOrErrors(rightWitnessNodes.get(name));
            }

            ValidationUtils.mergeMapsOfLists(witnessNodes, leftWitnessNodes);
            ValidationUtils.mergeMapsOfLists(witnessNodes, rightWitnessNodes);

        } else {
            for (EObject child : node.eContents()) {
                orTreeHelper(child, witnessNodes);
            }
        }
    }

    private void orTreeWitnessNodeHelper(WitnessVariable witness, Map<String, List<WitnessVariable>> witnessNodes) {
        if (types.get(witness) == Type.GROUP_ELEMENT) {
            String name = witness.getName();

            if (witnessNodes.containsKey(name)) {
                witnessNodes.get(name).add(witness);
            } else {
                List<WitnessVariable> variables = new ArrayList<>();
                variables.add(witness);
                witnessNodes.put(name, variables);
            }
        }
    }

    private void createOrErrors(List<WitnessVariable> witnessVariables) {
        for (WitnessVariable variable : witnessVariables) {
            logger.error(variable, "A group element witness cannot be in both operands of a disjunction that is within a conjunction");
        }
    }

    // Tuples must be nested within a function call before being nested within another tuple
    public void checkTuplePositionIsValid(Tuple tuple, BranchState state) {
        if (state.hasTupleBeforeFunctionCall()) {
            logger.error(tuple, "Tuples must be nested within a function call before being nested within another tuple");
        }
    }

}
