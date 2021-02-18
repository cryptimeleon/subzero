package de.upb.crypto.zeroknowledge.model

import org.eclipse.emf.ecore.EObject

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

	def static void preorderWithControl(EObject node, (EObject, ModelMapController) => void function) {
		val ModelMapController controller = new ModelMapController();
		preorderWithControlHelper(node, controller, function);
	}
	
	def private static void preorderWithControlHelper(EObject node, ModelMapController controller, (EObject, ModelMapController) => void function) {
		
		// End all traversal if break was called in a function
		if (controller.triggerBreak()) {
			return;
		}
		
		// Apply function
		function.apply(node, controller);
		
		// End branch traversal if continue was called in function
		if (controller.triggerContinue()) {
			return;
		}
		
		// Recurse through child nodes
		for (EObject child : node.eContents()) {
			preorderWithControlHelper(child, controller, function);
		}	
	}
}