package de.upb.crypto.zeroknowledge.model;

import org.eclipse.emf.ecore.EObject;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.ParameterList
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument

// Contains information about the current branch of the abstract syntax tree
// and all ancestor nodes of the node which receives the BranchState object
class BranchState {
	
	int depth;
	EObject parent;
	
	boolean inProof;
	boolean inFunctionDefinition;
	boolean inConjunction;
	boolean inDisjunction;
	boolean inComparison;
	boolean inSum;
	boolean inProduct;
	boolean inPower;
	boolean inPowerRightBranch;
	boolean inNegative;
	boolean inFunctionCall;
	boolean inTuple;
	
	boolean propositionalBeforeComparison;
	boolean tupleBeforeFunctionCall;
	
	new() {
		this.depth = 0;
		this.parent = null;
		
		this.inProof = false;
		this.inFunctionDefinition = false;
		this.inConjunction = false;
		this.inDisjunction = false;
		this.inComparison = false;
		this.inSum = false;
		this.inProduct = false;
		this.inPower = false;
		this.inPowerRightBranch = false;
		this.inNegative = false;
		this.inFunctionCall = false;
		this.inTuple = false;
		
		this.propositionalBeforeComparison = false;
		this.tupleBeforeFunctionCall = false;
	}

	
	new(BranchState state) {
		this.depth = state.depth;
		this.parent = state.parent;
		
		this.inProof = state.inProof
		this.inFunctionDefinition = state.inFunctionDefinition;
		this.inConjunction = state.inConjunction;
		this.inDisjunction = state.inDisjunction;
		this.inComparison = state.inComparison;
		this.inSum = state.inSum;
		this.inProduct = state.inProduct;
		this.inPower = state.inPower;
		this.inPowerRightBranch = state.inPowerRightBranch;
		this.inNegative = state.inNegative;
		this.inFunctionCall = state.inFunctionCall;
		this.inTuple = state.inTuple;
		
		this.propositionalBeforeComparison = state.propositionalBeforeComparison;
		this.tupleBeforeFunctionCall = state.tupleBeforeFunctionCall;
	}
	
	def int getDepth() {return depth;}
	def void setDepth(int depth) {this.depth = depth;}
	
	def EObject getParent() {return parent;}
	def void setParent(EObject parent) {this.parent = parent;}
	
	def boolean hasProofAncestor() {return this.inProof;}
	def boolean hasFunctionDefinitionAncestor() {return this.inFunctionDefinition;}
	def boolean hasConjunctionAncestor() {return this.inConjunction;}
	def boolean hasDisjunctionAncestor() {return this.inDisjunction;}
	def boolean hasComparisonAncestor() {return this.inComparison;}
	def boolean hasSumAncestor() {return this.inSum;}
	def boolean hasProductAncestor() {return this.inProduct;}
	def boolean hasPowerAncestor() {return this.inPower;}
	def boolean isInPowerRightBranch() {return this.inPowerRightBranch;}
	def boolean hasNegativeAncestor() {return this.inNegative;}
	def boolean hasFunctionCallAncestor() {return this.inFunctionCall;}
	def boolean hasTupleAncestor() {return this.inTuple;}
	
	def boolean hasPropositionalBeforeComparison() {return this.propositionalBeforeComparison;}
	def boolean hasTupleBeforeFunctionCall() {return this.tupleBeforeFunctionCall;}
	
	def void setProofAncestor(boolean inProof) {this.inProof = inProof;}
	def void setFunctionDefinitionAncestor(boolean inFunctionDefinition) {this.inFunctionDefinition = inFunctionDefinition;}
	def void setConjunctionAncestor(boolean inConjunction) {this.inConjunction = inConjunction;}
	def void setDisjunctionAncestor(boolean inDisjunction) {this.inDisjunction = inDisjunction;}
	def void setComparisonAncestor(boolean inComparison) {this.inComparison = inComparison;}
	def void setSumAncestor(boolean inSum) {this.inSum = inSum;}
	def void setProductAncestor(boolean inProduct) {this.inProduct = inProduct;}
	def void setPowerAncestor(boolean inPower) {this.inPower = inPower;}
	def void setInPowerRightBranch(boolean inPowerRightBranch) {this.inPowerRightBranch = inPowerRightBranch;}
	def void setNegativeAncestor(boolean inNegative) {this.inNegative = inNegative;}
	def void setFunctionCallAncestor(boolean inFunctionCall) {this.inFunctionCall = inFunctionCall;}
	def void setTupleAncestor(boolean inTuple) {this.inTuple = inTuple;}
	
	def void setPropositionalBeforeComparison(boolean propositionalBeforeComparison) {this.propositionalBeforeComparison = propositionalBeforeComparison}
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
	
	def dispatch void updateState(WitnessList witnessList) {
		updateGeneral(witnessList);
	}
	
	def dispatch void updateState(Witness witness) {
		updateGeneral(witness);
	}
	
	def dispatch void updateState(Conjunction conjunction) {
		updateGeneral(conjunction);
		setConjunctionAncestor(true);
		setPropositionalBeforeComparison(true);
		
	}
	
	def dispatch void updateState(Disjunction disjunction) {
		updateGeneral(disjunction);
		setDisjunctionAncestor(true);
		setPropositionalBeforeComparison(true);
	}
	
	def dispatch void updateState(Comparison comparison) {
		updateGeneral(comparison);
		setComparisonAncestor(true);
		setPropositionalBeforeComparison(false);
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
	
	def String getPackageLiteral() {
		return this.packageLiteral;
	}
	
}