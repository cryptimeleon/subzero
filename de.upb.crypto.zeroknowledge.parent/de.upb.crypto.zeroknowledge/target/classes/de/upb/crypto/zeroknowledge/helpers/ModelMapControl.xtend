package de.upb.crypto.zeroknowledge.helpers

class ModelMapControl {
	
	boolean breakCalled;
	boolean continueCalled;
	
	new() {
		this.breakCalled = false;
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
	
	def boolean triggerBreak() {
		return this.breakCalled;
	}
	
	def boolean triggerContinue() {
		val boolean tempValue = this.continueCalled;
		this.continueCalled = false;
		return tempValue;
	}
	
	
}