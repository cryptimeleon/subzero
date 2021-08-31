package org.cryptimeleon.subzero.model

import org.cryptimeleon.subzero.subzero.Argument
import org.cryptimeleon.subzero.subzero.Brackets
import org.cryptimeleon.subzero.subzero.Comparison
import org.cryptimeleon.subzero.subzero.Conjunction
import org.cryptimeleon.subzero.subzero.Disjunction
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.FunctionDefinition
import org.cryptimeleon.subzero.subzero.Model
import org.cryptimeleon.subzero.subzero.Negative
import org.cryptimeleon.subzero.subzero.NumberLiteral
import org.cryptimeleon.subzero.subzero.Parameter
import org.cryptimeleon.subzero.subzero.ParameterList
import org.cryptimeleon.subzero.subzero.Power
import org.cryptimeleon.subzero.subzero.Product
import org.cryptimeleon.subzero.subzero.PublicParameter
import org.cryptimeleon.subzero.subzero.PublicParameterList
import org.cryptimeleon.subzero.subzero.StringLiteral
import org.cryptimeleon.subzero.subzero.Sum
import org.cryptimeleon.subzero.subzero.Tuple
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.Witness
import org.cryptimeleon.subzero.subzero.WitnessList
import org.eclipse.emf.ecore.EObject
import org.cryptimeleon.subzero.subzero.ConstantList
import org.cryptimeleon.subzero.subzero.Constant

/**
 * Contains information about the current branch of the abstract syntax tree
 * and all ancestor nodes of the node which receives the BranchState object
 */
class BranchState {
	
	int depth;
	EObject parent;
	
	boolean inProof;
	boolean inFunctionDefinition;
	boolean inDisjunction;
	boolean inConjunction;
	boolean inComparison;
	boolean inSum;
	boolean inProduct;
	boolean inPower;
	boolean inNegative;
	boolean inFunctionCall;
	boolean inTuple;
	
	boolean logicalBeforeComparison;
	boolean tupleBeforeFunctionCall;
	
	new() {
		this.depth = 0;
		this.parent = null;
		
		this.inProof = false;
		this.inFunctionDefinition = false;
		this.inDisjunction = false;
		this.inConjunction = false;
		this.inComparison = false;
		this.inSum = false;
		this.inProduct = false;
		this.inPower = false;
		this.inNegative = false;
		this.inFunctionCall = false;
		this.inTuple = false;
		
		this.logicalBeforeComparison = false;
		this.tupleBeforeFunctionCall = false;
	}

	
	new(BranchState state) {
		this.depth = state.depth;
		this.parent = state.parent;
		
		this.inProof = state.inProof
		this.inFunctionDefinition = state.inFunctionDefinition;
		this.inDisjunction = state.inDisjunction;
		this.inConjunction = state.inConjunction;
		this.inComparison = state.inComparison;
		this.inSum = state.inSum;
		this.inProduct = state.inProduct;
		this.inPower = state.inPower;
		this.inNegative = state.inNegative;
		this.inFunctionCall = state.inFunctionCall;
		this.inTuple = state.inTuple;
		
		this.logicalBeforeComparison = state.logicalBeforeComparison;
		this.tupleBeforeFunctionCall = state.tupleBeforeFunctionCall;
	}
	
	def int getDepth() {return depth;}
	def void setDepth(int depth) {this.depth = depth;}
	
	def EObject getParent() {return parent;}
	def void setParent(EObject parent) {this.parent = parent;}
	
	def boolean hasProofAncestor() {return this.inProof;}
	def boolean hasFunctionDefinitionAncestor() {return this.inFunctionDefinition;}
	def boolean hasDisjunctionAncestor() {return this.inDisjunction;}
	def boolean hasConjunctionAncestor() {return this.inConjunction;}
	def boolean hasComparisonAncestor() {return this.inComparison;}
	def boolean hasSumAncestor() {return this.inSum;}
	def boolean hasProductAncestor() {return this.inProduct;}
	def boolean hasPowerAncestor() {return this.inPower;}
	def boolean hasNegativeAncestor() {return this.inNegative;}
	def boolean hasFunctionCallAncestor() {return this.inFunctionCall;}
	def boolean hasTupleAncestor() {return this.inTuple;}
	
	def boolean hasLogicalBeforeComparison() {return this.logicalBeforeComparison;}
	def boolean hasTupleBeforeFunctionCall() {return this.tupleBeforeFunctionCall;}
	
	def void setProofAncestor(boolean inProof) {this.inProof = inProof;}
	def void setFunctionDefinitionAncestor(boolean inFunctionDefinition) {this.inFunctionDefinition = inFunctionDefinition;}
	def void setDisjunctionAncestor(boolean inDisjunction) {this.inDisjunction = inDisjunction;}
	def void setConjunctionAncestor(boolean inConjunction) {this.inConjunction = inConjunction;}
	def void setComparisonAncestor(boolean inComparison) {this.inComparison = inComparison;}
	def void setSumAncestor(boolean inSum) {this.inSum = inSum;}
	def void setProductAncestor(boolean inProduct) {this.inProduct = inProduct;}
	def void setPowerAncestor(boolean inPower) {this.inPower = inPower;}
	def void setNegativeAncestor(boolean inNegative) {this.inNegative = inNegative;}
	def void setFunctionCallAncestor(boolean inFunctionCall) {this.inFunctionCall = inFunctionCall;}
	def void setTupleAncestor(boolean inTuple) {this.inTuple = inTuple;}
	
	def void setLogicalBeforeComparison(boolean logicalBeforeComparison) {this.logicalBeforeComparison = logicalBeforeComparison}
	def void setTupleBeforeFunctionCall(boolean tupleBeforeFunctionCall) {this.tupleBeforeFunctionCall = tupleBeforeFunctionCall;}
	
	def void updateGeneral(EObject node) {
		depth++;
		parent = node;
	}
	
	def dispatch void updateState(Model model) {
		updateGeneral(model);
	}
	
	def dispatch void updateState(FunctionDefinition function) {
		updateGeneral(function);
		setFunctionDefinitionAncestor(true);
	}
	
	def dispatch void updateState(ParameterList parameterList) {
		updateGeneral(parameterList);
	}
	
	def dispatch void updateState(Parameter parameter) {
		updateGeneral(parameter);
	}
	
	def dispatch void updateState(PublicParameterList publicParameterList) {
		updateGeneral(publicParameterList);
	}
	
	def dispatch void updateState(PublicParameter publicParameter) {
		updateGeneral(publicParameter);
	}
	
	def dispatch void updateState(ConstantList constantList) {
		updateGeneral(constantList);
	}
	
	def dispatch void updateState(Constant constant) {
		updateGeneral(constant);
	}
	
	def dispatch void updateState(WitnessList witnessList) {
		updateGeneral(witnessList);
	}
	
	def dispatch void updateState(Witness witness) {
		updateGeneral(witness);
	}
	
	def dispatch void updateState(Disjunction disjunction) {
		updateGeneral(disjunction);
		setDisjunctionAncestor(true);
		setLogicalBeforeComparison(true);
	}

	def dispatch void updateState(Conjunction conjunction) {
		updateGeneral(conjunction);
		setConjunctionAncestor(true);
		setLogicalBeforeComparison(true);
		
	}
	
	def dispatch void updateState(Comparison comparison) {
		updateGeneral(comparison);
		setComparisonAncestor(true);
		setLogicalBeforeComparison(false);
	}
	
	def dispatch void updateState(Sum sum) {
		updateGeneral(sum);
		setSumAncestor(true);
	}
	
	def dispatch void updateState(Product product) {
		updateGeneral(product);
		setProductAncestor(true);
	}
	
	def dispatch void updateState(Power power) {
		updateGeneral(power);
		setPowerAncestor(true);
 	}
	
	def dispatch void updateState(StringLiteral stringLiteral) {
		updateGeneral(stringLiteral);
	}
	
	def dispatch void updateState(Tuple tuple) {
		updateGeneral(tuple);
		setTupleAncestor(true);
		setTupleBeforeFunctionCall(true);
	}
	
	def dispatch void updateState(Negative negative) {
		updateGeneral(negative);
		setNegativeAncestor(true);
	}
	
	def dispatch void updateState(FunctionCall call) {
		updateGeneral(call);
		setFunctionCallAncestor(true);
		setTupleBeforeFunctionCall(false);
	}
	
	def dispatch void updateState(Argument argument) {
		updateGeneral(argument);
	}
	
	def dispatch void updateState(Variable variable) {
		updateGeneral(variable);
	}
	
	def dispatch void updateState(NumberLiteral number) {
		updateGeneral(number);
	}
	
	def dispatch void updateState(Brackets brackets) {
		updateGeneral(brackets);
	}
	
	def dispatch void updateState(EObject node) {
		System.out.println("Error: unhandled node type in update branch state");
	}
	
	def String getPackageLiteral() {
		return this.packageLiteral;
	}
	
}
