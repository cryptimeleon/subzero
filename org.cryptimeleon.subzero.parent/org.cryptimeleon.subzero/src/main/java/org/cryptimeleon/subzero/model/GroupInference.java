package org.cryptimeleon.subzero.model;

import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Expression;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.LocalVariable;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import java.util.HashMap;
import java.util.Map;

import static org.cryptimeleon.subzero.model.LanguageConstants.OPERATOR_EQUAL;
import static org.cryptimeleon.subzero.model.LanguageConstants.OPERATOR_INEQUAL;

/*
 * Determines what group each group element is from.
 * Necessary when there is a pairing in the protocol.
 */
public class GroupInference {

    private static final String PAIRING_FUNCTION = "e";
    private static final int PAIRING_FUNCTION_ARGS = 2;

    private final Map<EObject, Type> types;
    private final Map<String, GroupType> groups;
    private final Map<String, FunctionDefinition> userFunctions;

	public GroupInference(AugmentedModel augmentedModel) {
        groups = new HashMap<>();

        types = augmentedModel.getTypes();
        userFunctions = augmentedModel.getUserFunctionDefinitions();

        inferGroups(augmentedModel);
    }

    public Map<String, GroupType> getGroups() {
        return groups;
    }

    public GroupType getGroup(String name) {
        return groups.get(name);
    }

    private void setGroup(EObject node, GroupType type) {
        if (types.get(node) != Type.GROUP_ELEMENT) return;

        String name = ModelUtils.getNodeName(node);
        GroupType existingType = groups.get(name);

        if (existingType != null && existingType != type) {
            groups.put(name, GroupType.UNKNOWN);
        } else {
            groups.put(name, type);
        }
    }

    // Label all group elements within the second argument of an e call as G2 elements
    private void fillG2(EObject node) {
        TreeTraversals.postorderTraversal(node, (childNode) -> {
            if (childNode instanceof Variable && !(childNode instanceof LocalVariable) && types.get(childNode) == Type.GROUP_ELEMENT) {
                setGroup(childNode, GroupType.G2);
            } else if (childNode instanceof FunctionCall) {
                FunctionDefinition function = userFunctions.get(((FunctionCall) childNode).getName());
                if (function != null) {
                    fillG2(function.getBody());
                }
            }
        });
    }

    // Label all group elements within an equality that contains an e call as GT elements
    private void fillGT(EObject node) {
        TreeTraversals.preorderTraversalWithControl(node, (childNode, controller) -> {
            if (childNode instanceof Variable && !(childNode instanceof LocalVariable) && types.get(childNode) == Type.GROUP_ELEMENT) {
                setGroup(childNode, GroupType.GT);
            } else if (childNode instanceof FunctionCall) {
                String functionName = ((FunctionCall) childNode).getName();
                if (functionName.equals(PAIRING_FUNCTION)) {
                    controller.skipBranch();
                } else if (userFunctions.containsKey(functionName)) {
                    fillGT(userFunctions.get(functionName).getBody());
                    // Model map will also label all function arguments as GT
                }

            }
        });
    }

    // Label all remaining unlabeled group elements as G1 elements
    private void fillG1(Model model) {
        TreeTraversals.postorderTraversal(model, (node) -> {
            if (node instanceof Variable && !(node instanceof LocalVariable)) {
                setG1(node);
            }
        });

        for (Witness witness : model.getWitnesses()) {
            setG1(witness);
        }

        for (PublicParameter publicParameter : model.getPublicParameters()) {
            setG1(publicParameter);
        }
    }

    private void setG1(EObject node) {
        String name = ModelUtils.getNodeName(node);

        if (types.get(node) == Type.GROUP_ELEMENT && !groups.containsKey(name)) {
            groups.put(name, GroupType.G1);
        }
    }

    private void inferGroups(AugmentedModel augmentedModel) {
        Model model = augmentedModel.getModel();

        TreeTraversals.preorderTraversalWithControl(model, (node, controller) -> {
            if (node instanceof Comparison) {
                String operation = ((Comparison) node).getOperation();
                if (operation.equals(OPERATOR_EQUAL) || operation.equals(OPERATOR_INEQUAL)) {

                    boolean containsECall = TreeTraversals.preorderTraversalWithControl(node, (newNode, newController) -> {
                        if (newNode instanceof FunctionCall) {
                            FunctionCall call = (FunctionCall) newNode;
                            EList<Expression> arguments = call.getArguments();

                            if (call.getName().equals(PAIRING_FUNCTION) && arguments.size() == PAIRING_FUNCTION_ARGS) {
                                newController.setReturnValue(true);
                                fillG2(arguments.get(1));
                            }

                            newController.skipBranch();
                        }
                    });

                    if (containsECall) {
                        fillGT(node);
                    }
                }

                controller.skipBranch();
            }
        });

        fillG1(model);
    }
}