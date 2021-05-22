package org.cryptimeleon.zeroknowledge.model

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList

/**
 * A helper class for applying general functions to each node of the model
 * through various tree traversals
 */

class ModelMap {
	
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies the function to the node, and then to its subtrees	
	def static void preorder(EObject node, (EObject) => void function) {
		
		// Apply function
		function.apply(node);
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			preorder(child, function);
		}
	}
		
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies to all of a node's subtrees before applying the function to the node
	def static void postorder(EObject node, (EObject) => void function) {
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			postorder(child, function);
		}
		
		// Apply function
		function.apply(node);
	}
	
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies the function to the node's left subtree, then to the node, then to the right subtree
	// For nodes with >2 children, it applies the function to the leftmost subtree, then to the
	// node, then to all remaining subtrees from left to right
	def static void inorder(EObject node, (EObject) => void function) {
		val EList<EObject> contents = node.eContents();
		val int size = contents.size();
		
		if (size == 0) {
			function.apply(node);
		} else {
			for (var int i = 0; i < size; i++) {
				val EObject child = contents.get(i);
				inorder(child, function);
				if (i == 0) function.apply(node);
			}
		}
		
		
	}
	
	
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies the function to the node, and then to its subtrees
	// If for any node the function returns true, then the original call returns true
	// The traversal ends as soon as the function returns true for any node, so the 
	// function should not have any side effects
	def static boolean preorderAny(EObject node, (EObject) => boolean function) {
		
		if (function.apply(node)) {
			return true;
		}
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			if (preorderAny(child, function)) {
				return true;
			}
		}
		
	}
	
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies to all of a node's subtrees before applying the function to the node
	// If for any node the function returns true, then the original call returns true
	// The traversal ends as soon as the function returns true for any node, so the 
	// function should not have any side effects
	def static boolean postorderAny(EObject node, (EObject) => boolean function) {
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			if (postorderAny(child, function)) {
				return true;
			}
		}
		
		return function.apply(node);
	}
	
	// Recurses through the abstract syntax tree and applies the function to each node
	// Applies to all of a node's subtrees before applying the function to the node
	// A BranchState instance is also passed in each recursive call
	def static void preorderWithState(EObject node, BranchState state, (EObject, BranchState) => void function) {
				
		// Apply function
		function.apply(node, state);
		
		state.updateState(node);
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			preorderWithState(child, new BranchState(state), function);
		}
	}

	def static boolean preorderWithControl(EObject node, (EObject, ModelMap.Controller) => void function) {
		val ModelMap.Controller controller = new ModelMap.Controller();
		preorderWithControlHelper(node, controller, function);
		return controller.getReturnValue();
	}
	
	def private static void preorderWithControlHelper(EObject node, ModelMap.Controller controller, (EObject, ModelMap.Controller) => void function) {
		
		// End all traversal if break was called in a function
		if (controller.breakIsTriggered()) {
			return;
		}
		
		// Apply function
		function.apply(node, controller);
		
		// End branch traversal if continue was called in function
		if (controller.continueIsTriggered()) {
			controller.resetContinue();
			return;
		}
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			preorderWithControlHelper(child, controller, function);
		}	
	}
	
	def static boolean preorderWithStateAndControl(EObject node, BranchState state, (EObject, BranchState, ModelMap.Controller) => void function) {
		val ModelMap.Controller controller = new ModelMap.Controller();
		preorderWithStateAndControlHelper(node, state, controller, function);
		return controller.getReturnValue();
	}
	
	def private static void preorderWithStateAndControlHelper(EObject node, BranchState state, ModelMap.Controller controller, (EObject, BranchState, ModelMap.Controller) => void function) {
		// End all traversal if break was called in a function
		if (controller.breakIsTriggered()) {
			return;
		}
		
		// Apply function
		function.apply(node, state, controller);
		
		state.updateState(node);
		
		// End branch traversal if continue was called in function
		if (controller.continueIsTriggered()) {
			controller.resetContinue();
			return;
		}
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			preorderWithStateAndControlHelper(child, state, controller, function);
		}	
	}
	
	static class Controller {
		
		boolean breakCalled;
		boolean continueCalled;
		boolean returnValue;
		
		new() {
			this.breakCalled = false;
			this.continueCalled = false;
			this.returnValue = false;
		}
		
		def private boolean breakIsTriggered() {
			return this.breakCalled;
		}
		
		def private boolean continueIsTriggered() {
			return this.continueCalled;
		}
		
		def private resetContinue() {
			this.continueCalled = false;
		}
		
		// Stops traversal of the tree
		def void breakMap() {
			this.breakCalled = true;
		}
		
		// Stops traversal of the current branch, but continues traversal elsewhere
		def void continueTraversal() {
			this.continueCalled = true;
		}
		
		def void returnTrue() {
			this.returnValue = true;
		}
		
		def void returnFalse() {
			this.returnValue = false;
		}
		
		def boolean getReturnValue() {
			return this.returnValue;
		}
	}
	
	
}