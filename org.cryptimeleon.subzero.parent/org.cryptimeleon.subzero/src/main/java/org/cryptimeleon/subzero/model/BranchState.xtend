package org.cryptimeleon.subzero.model;

import org.cryptimeleon.subzero.subzero.Argument;
import org.cryptimeleon.subzero.subzero.Brackets;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Constant;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Disjunction;
import org.cryptimeleon.subzero.subzero.FunctionCall;
import org.cryptimeleon.subzero.subzero.FunctionDefinition;
import org.cryptimeleon.subzero.subzero.Model;
import org.cryptimeleon.subzero.subzero.Negative;
import org.cryptimeleon.subzero.subzero.NumberLiteral;
import org.cryptimeleon.subzero.subzero.Parameter;
import org.cryptimeleon.subzero.subzero.Power;
import org.cryptimeleon.subzero.subzero.Product;
import org.cryptimeleon.subzero.subzero.PublicParameter;
import org.cryptimeleon.subzero.subzero.Sum;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.eclipse.emf.ecore.EObject;

/**
 * Contains information about the current branch of the abstract syntax tree
 * and all ancestor nodes of the node which receives the BranchState object
 */
public class BranchState {

	private int depth = 0;
	private EObject parent = null;

	private boolean inProof = false;
	private boolean inFunctionDefinition = false;
	private boolean inDisjunction = false;
	private boolean inConjunction = false;
	private boolean inComparison = false;
	private boolean inSum = false;
	private boolean inProduct = false;
	private boolean inPower = false;
	private boolean inNegative = false;
	private boolean inFunctionCall = false;
	private boolean inTuple = false;

	private boolean logicalBeforeComparison = false;
	private boolean tupleBeforeFunctionCall = false;

	public new() {
		// Intentionally blank
	}

	public new(BranchState state) {
		depth = state.depth;
		parent = state.parent;

		inProof = state.inProof;
		inFunctionDefinition = state.inFunctionDefinition;
		inDisjunction = state.inDisjunction;
		inConjunction = state.inConjunction;
		inComparison = state.inComparison;
		inSum = state.inSum;
		inProduct = state.inProduct;
		inPower = state.inPower;
		inNegative = state.inNegative;
		inFunctionCall = state.inFunctionCall;
		inTuple = state.inTuple;

		logicalBeforeComparison = state.logicalBeforeComparison;
		tupleBeforeFunctionCall = state.tupleBeforeFunctionCall;
	}

    public new(BranchState parentState, EObject childNode) {
		this(parentState);
		updateState(childNode);
    }

	def public int getDepth() {
		return depth;
	}

	def public EObject getParent() {
		return parent;
	}

	def public boolean hasProofAncestor() {
		return inProof;
	}

	def public boolean hasFunctionDefinitionAncestor() {
		return inFunctionDefinition;
	}

	def public boolean hasDisjunctionAncestor() {
		return inDisjunction;
	}

	def public boolean hasConjunctionAncestor() {
		return inConjunction;
	}

	def public boolean hasComparisonAncestor() {
		return inComparison;
	}

	def public boolean hasSumAncestor() {
		return inSum;
	}

	def public boolean hasProductAncestor() {
		return inProduct;
	}

	def public boolean hasPowerAncestor() {
		return inPower;
	}

	def public boolean hasNegativeAncestor() {
		return inNegative;
	}

	def public boolean hasFunctionCallAncestor() {
		return inFunctionCall;
	}

	def public boolean hasTupleAncestor() {
		return inTuple;
	}

	def public boolean hasLogicalBeforeComparison() {
		return logicalBeforeComparison;
	}

	def public boolean hasTupleBeforeFunctionCall() {
		return tupleBeforeFunctionCall;
	}

	def private void updateGeneral(EObject node) {
		depth++;
		parent = node;
	}

	def private dispatch void updateState(Model model) {
		updateGeneral(model);
	}

	def private dispatch void updateState(FunctionDefinition function) {
		updateGeneral(function);
		inFunctionDefinition = true;
	}

	def private dispatch void updateState(Parameter parameter) {
		updateGeneral(parameter);
	}

	def private dispatch void updateState(PublicParameter publicParameter) {
		updateGeneral(publicParameter);
	}

	def private dispatch void updateState(Constant constant) {
		updateGeneral(constant);
	}

	def private dispatch void updateState(Witness witness) {
		updateGeneral(witness);
	}

	def private dispatch void updateState(Disjunction disjunction) {
		updateGeneral(disjunction);
		inDisjunction = true;
		logicalBeforeComparison = true;
	}

	def private dispatch void updateState(Conjunction conjunction) {
		updateGeneral(conjunction);
		inConjunction = true;
		logicalBeforeComparison = true;
	}

	def private dispatch void updateState(Comparison comparison) {
		updateGeneral(comparison);
		inComparison = true;
		logicalBeforeComparison = false;
	}

	def private dispatch void updateState(Sum sum) {
		updateGeneral(sum);
		inSum = true;
	}

	def private dispatch void updateState(Product product) {
		updateGeneral(product);
		inProduct = true;
	}

	def private dispatch void updateState(Power power) {
		updateGeneral(power);
		inPower = true;
 	}

	def private dispatch void updateState(Tuple tuple) {
		updateGeneral(tuple);
		inTuple = true;
		tupleBeforeFunctionCall = true;
	}

	def private dispatch void updateState(Negative negative) {
		updateGeneral(negative);
		inNegative = true;
	}

	def private dispatch void updateState(FunctionCall call) {
		updateGeneral(call);
		inFunctionCall = true;
		tupleBeforeFunctionCall = false;
	}

	def private dispatch void updateState(Argument argument) {
		updateGeneral(argument);
	}

	def private dispatch void updateState(Variable variable) {
		updateGeneral(variable);
	}

	def private dispatch void updateState(NumberLiteral number) {
		updateGeneral(number);
	}

	def private dispatch void updateState(Brackets brackets) {
		updateGeneral(brackets);
	}

	def private dispatch void updateState(EObject node) {
		System.out.println("Error: unhandled node type in update branch state");
	}

}
