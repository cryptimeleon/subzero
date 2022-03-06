package org.cryptimeleon.subzero.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * A helper class for applying general functions to each node of the model
 * through various tree traversals
 */
public class TreeTraversals {

    @FunctionalInterface
    public interface NodeVisitor {
        void visit(EObject node);
    }

    @FunctionalInterface
    public interface BooleanNodeVisitor {
        boolean visit(EObject node);
    }

    @FunctionalInterface
    public interface StatefulNodeVisitor {
        void visit(EObject node, BranchState state);
    }

    @FunctionalInterface
    public interface ControllerNodeVisitor {
        void visit(EObject node, Controller controller);
    }

    @FunctionalInterface
    public interface StatefulControllerNodeVisitor {
        void visit(EObject node, BranchState state, Controller controller);
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    public static void preorderTraversal(AugmentedModel augmentedModel, NodeVisitor visitor) {
        preorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static void preorderTraversal(EObject root, NodeVisitor visitor) {
        preorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    // A BranchState instance is also passed in each recursive call to provide info about the current branch.
    public static void preorderTraversalWithState(AugmentedModel augmentedModel, StatefulNodeVisitor visitor) {
        preorderTraversalWithState(augmentedModel.getModel(), visitor);
    }
    public static void preorderTraversalWithState(EObject root, StatefulNodeVisitor visitor) {
        preorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node, state));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    // A Controller instance is also passed in each recursive call that allows for ending the
    // traversal or skipping the current tree branch.
    public static boolean preorderTraversalWithControl(AugmentedModel augmentedModel, ControllerNodeVisitor visitor) {
        return preorderTraversalWithControl(augmentedModel.getModel(), visitor);
    }
    public static boolean preorderTraversalWithControl(EObject root, ControllerNodeVisitor visitor) {
        return preorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node, controller));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    // A BranchState instance is also passed in each recursive call to provide info about the current branch.
    // A Controller instance is also passed in each recursive call that allows for ending the
    // traversal or skipping the current tree branch.
    public static boolean preorderTraversalWithStateAndControl(AugmentedModel augmentedModel, StatefulControllerNodeVisitor visitor) {
        return preorderTraversalWithStateAndControl(augmentedModel.getModel(), visitor);
    }
    public static boolean preorderTraversalWithStateAndControl(EObject root, StatefulControllerNodeVisitor visitor) {
        Controller controller = new Controller();
        BranchState state = new BranchState();

        preorderTraversalHelper(root, state, controller, visitor);

        return controller.getReturnValue();
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    // If for any node the function returns true, then the original call returns true, else it returns false.
    // The traversal ends as soon as the function returns true for any node, so the 
    // function should not have any side effects.
    public static boolean anyInPreorderTraversal(AugmentedModel augmentedModel, BooleanNodeVisitor visitor) {
        return anyInPreorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static boolean anyInPreorderTraversal(EObject root, BooleanNodeVisitor visitor) {
        return preorderTraversalWithStateAndControl(root, (node, state, controller) -> {
            if (visitor.visit(node)) {
                controller.setReturnValue(true);
                controller.traversalEnded = true;
            }
        });
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node, and then to its subtrees.
    // If for any node the function returns false, then the original call returns false, else it returns true.
    // The traversal ends as soon as the function returns false for any node, so the 
    // function should not have any side effects.
    public static boolean allInPreorderTraversal(AugmentedModel augmentedModel, BooleanNodeVisitor visitor) {
        return allInPreorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static boolean allInPreorderTraversal(EObject root, BooleanNodeVisitor visitor) {
        return !anyInPreorderTraversal(root, node -> !visitor.visit(node));
    }

    // Helper method for all variations of preorder traversal
    private static void preorderTraversalHelper(EObject node, BranchState state, Controller controller, StatefulControllerNodeVisitor visitor) {
        // End model tree traversal if endTraversal was called in a function
        if (controller.traversalEnded) {
            return;
        }

        // Apply function
        visitor.visit(node, state, controller);

        // End branch traversal if skipBranch was called in function
        if (controller.branchSkipped) {
            controller.branchSkipped = false;
            return;
        }

        BranchState newState = new BranchState(state, node);

        // Recurse through child nodes
        for (EObject child : node.eContents()) {
            preorderTraversalHelper(child, newState, controller, visitor);
        }
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    public static void postorderTraversal(AugmentedModel augmentedModel, NodeVisitor visitor) {
        postorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static void postorderTraversal(EObject root, NodeVisitor visitor) {
        postorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    // A BranchState instance is also passed in each recursive call to provide info about the current branch.
    public static void postorderTraversalWithState(AugmentedModel augmentedModel, StatefulNodeVisitor visitor) {
        postorderTraversalWithState(augmentedModel.getModel(), visitor);
    }
    public static void postorderTraversalWithState(EObject root, StatefulNodeVisitor visitor) {
        postorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node, state));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    // A Controller instance is also passed in each recursive call that allows for ending the
    // traversal (skipping the current tree branch does not make sense here).
    public static boolean postorderTraversalWithControl(AugmentedModel augmentedModel, ControllerNodeVisitor visitor) {
        return postorderTraversalWithControl(augmentedModel.getModel(), visitor);
    }
    public static boolean postorderTraversalWithControl(EObject root, ControllerNodeVisitor visitor) {
        return postorderTraversalWithStateAndControl(root, (node, state, controller) -> visitor.visit(node, controller));
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    // A BranchState instance is also passed in each recursive call to provide info about the current branch.
    // A Controller instance is also passed in each recursive call that allows for ending the
    // traversal (skipping the current tree branch does not make sense here).
    public static boolean postorderTraversalWithStateAndControl(AugmentedModel augmentedModel, StatefulControllerNodeVisitor visitor) {
        return postorderTraversalWithStateAndControl(augmentedModel.getModel(), visitor);
    }
    public static boolean postorderTraversalWithStateAndControl(EObject root, StatefulControllerNodeVisitor visitor) {
        Controller controller = new Controller();
        BranchState state = new BranchState();

        postorderTraversalHelper(root, state, controller, visitor);

        return controller.getReturnValue();
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    // If for any node the function returns true, then the original call returns true, else it returns false.
    // The traversal ends as soon as the function returns true for any node, so the 
    // function should not have any side effects.
    public static boolean anyInPostorderTraversal(AugmentedModel augmentedModel, BooleanNodeVisitor visitor) {
        return anyInPostorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static boolean anyInPostorderTraversal(EObject root, BooleanNodeVisitor visitor) {
        return postorderTraversalWithStateAndControl(root, (node, state, controller) -> {
            if (visitor.visit(node)) {
                controller.setReturnValue(true);
                controller.traversalEnded = true;
            }
        });
    }

    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies to all of a node's subtrees before applying the function to the node
    // If for any node the function returns false, then the original call returns false, else it returns true.
    // The traversal ends as soon as the function returns false for any node, so the 
    // function should not have any side effects.
    public static boolean allInPostorderTraversal(AugmentedModel augmentedModel, BooleanNodeVisitor visitor) {
        return allInPostorderTraversal(augmentedModel.getModel(), visitor);
    }
    public static boolean allInPostorderTraversal(EObject root, BooleanNodeVisitor visitor) {
        return !anyInPostorderTraversal(root, node -> !visitor.visit(node));
    }

    // Helper method for all variations of postorder traversal
    private static void postorderTraversalHelper(EObject node, BranchState state, Controller controller, StatefulControllerNodeVisitor visitor) {
        // End model tree traversal if endTraversal was called in a function
        if (controller.traversalEnded) {
            return;
        }

        BranchState newState = new BranchState(state, node);

        // Recurse through child nodes
        for (EObject child : node.eContents()) {
            postorderTraversalHelper(child, newState, controller, visitor);
        }

        // Apply function
        visitor.visit(node, state, controller);
    }

    // TODO: implement inorder traversal methods if needed
    // Recurses through the abstract syntax tree and applies the function to each node.
    // Applies the function to the node's left subtree, then to the node, then to the right subtree
    // For nodes with >2 children, it applies the function to the leftmost subtree, then to the
    // node, then to all remaining subtrees from left to right
    private static void inorderTraversalHelper(EObject node, BranchState state, Controller controller, StatefulControllerNodeVisitor visitor) {
        EList<EObject> contents = node.eContents();
        int size = contents.size();

        if (size == 0) {
            visitor.visit(node, state, controller);
        } else {
            BranchState newState = new BranchState(state, node);

            for (int i = 0; i < size; i++) {
                EObject child = contents.get(i);
                inorderTraversalHelper(child, newState, controller, visitor);

                if (i == 0) visitor.visit(node, state, controller);
            }
        }
    }

    public static class Controller {

        private boolean branchSkipped = false;
        private boolean traversalEnded = false;
        private boolean returnValue = false;
        
        private Controller() {
        }

        // Stops traversal of the tree
        public void endTraversal() {
            traversalEnded = true;
        }

        // Stops traversal of the current branch, but continues traversal elsewhere
        public void skipBranch() {
            branchSkipped = true;
        }

        public void setReturnValue(boolean returnValue) {
            this.returnValue = returnValue;
        }

        public boolean getReturnValue() {
            return returnValue;
        }
    }
    
}
