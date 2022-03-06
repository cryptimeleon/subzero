package org.cryptimeleon.subzero.validation;

import org.cryptimeleon.subzero.environment.EnvironmentGenerator;
import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.BranchState;
import org.cryptimeleon.subzero.model.TreeTraversals;
import org.cryptimeleon.subzero.subzero.Argument;
import org.cryptimeleon.subzero.subzero.Brackets;
import org.cryptimeleon.subzero.subzero.Comparison;
import org.cryptimeleon.subzero.subzero.Conjunction;
import org.cryptimeleon.subzero.subzero.Constant;
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
import org.cryptimeleon.subzero.subzero.StringLiteral;
import org.cryptimeleon.subzero.subzero.Sum;
import org.cryptimeleon.subzero.subzero.Tuple;
import org.cryptimeleon.subzero.subzero.Variable;
import org.cryptimeleon.subzero.subzero.Witness;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;

/**
 * This class contains custom validation rules for validating the syntax tree
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 * 
 * The language grammar specification is designed to be less strict, with stricter rules
 * instead applied during validation. This allows for more descriptive errors/warnings in
 * the editor, as opposed to generating less descriptive syntax errors
 */
class SubzeroValidator extends AbstractSubzeroValidator {

	var FormatValidation validateFormat;
	var FunctionCallValidation validateCall;
	var FunctionDefinitionValidation validateFunction;
	var GrammarValidation validateGrammar;
	var GroupTypeValidation validateGroup;
	var SizeValidation validateSize;
	var TypeValidation validateType;
	var UniquenessValidation validateUnique;
	var VariableValidation validateVar;

	/*
	 * Validation proceeds in a topdown, preorder traversal of the syntax tree,
	 * starting at the root Model node.
	 * 
	 * checkModel is the only function with the @Check annotation (it will be called by the EValidator)
	 * checkNode is a dispatch function to call the corresponding validation functions for each
	 * different type of syntax tree nodes
	 * Any other function prefixed with 'check' can create validation errors or warnings
	 * All other functions are helper functions
	 */
	@Check
	def void checkModel(Model model) {
		// Do not perform validation if there are still syntax errors
		if (!model.eResource.errors.isEmpty()) {
			return;
		}

		val AugmentedModel augmentedModel = new AugmentedModel(model);
        val ValidationLogger logger = new ValidationLogger(
			[e1, e2, e3 | error(e1, e2, e3)],
			[e1, e2, e3 | warning(e1, e2, e3)],
			[e1, e2, e3 | info(e1, e2, e3)]
		);

        validateFormat = new FormatValidation(augmentedModel, logger);
        validateCall = new FunctionCallValidation(augmentedModel, logger);
        validateFunction = new FunctionDefinitionValidation(augmentedModel, logger);
        validateGrammar = new GrammarValidation(augmentedModel, logger);
        validateGroup = new GroupTypeValidation(augmentedModel, logger);
		validateSize = new SizeValidation(augmentedModel, logger);
        validateType = new TypeValidation(augmentedModel, logger);
        validateUnique = new UniquenessValidation(augmentedModel, logger);
        validateVar = new VariableValidation(augmentedModel, logger);

		System.out.println("Validating the model");

		val EnvironmentGenerator envGenerator = new EnvironmentGenerator(augmentedModel);
		info(envGenerator.generate(), null, null);

		// Iterate over the tree in a preorder traversal and perform validation on each node
		TreeTraversals.preorderTraversalWithState(model, [EObject node, BranchState state |
			checkNode(node, state);
		]);
	}

	def private dispatch void checkNode(Model model, BranchState state) {
		validateGrammar.checkOrWithAndAncestorGroupElementWitnesses(model);
		validateGrammar.checkHasProof(model);

		validateUnique.checkSubprotocolNamesAreUnique(model);
		validateUnique.checkFunctionNamesAreUnique(model.getFunctions());
		validateUnique.checkWitnessNamesAreUnique(model.getWitnesses());
		validateUnique.checkPublicParameterNamesAreUnique(model.getPublicParameters());
		validateUnique.checkConstantNamesAreUnique(model.getConstants());

		validateVar.checkModelHasWitness(model);
	}

	def private dispatch void checkNode(FunctionDefinition function, BranchState state) {
		validateFormat.checkFunctionNameFormat(function);
		
		validateFunction.checkFunctionIsCalled(function);
		validateFunction.checkFunctionParametersAreUsed(function);
		validateFunction.checkFunctionHasNoDisjunction(function);
		
		validateUnique.checkFunctionNameIsNotPredefined(function);
		validateUnique.checkFunctionParameterNamesAreUnique(function.getParameters());
	}

	def private dispatch void checkNode(Parameter parameter, BranchState state) {
		validateFormat.checkParameterNameFormat(parameter);
	}

	def private dispatch void checkNode(PublicParameter publicParameter, BranchState state) {
		validateFormat.checkPublicParameterNameFormat(publicParameter);
		
		validateVar.checkPublicParameterIsUsed(publicParameter);
	}

	def private dispatch void checkNode(Witness witness, BranchState state) {
		validateFormat.checkWitnessNameFormat(witness);
		
		validateVar.checkWitnessIsUsed(witness);
	}

	def private dispatch void checkNode(Constant constant, BranchState state) {
		validateFormat.checkConstantNameFormat(constant);
		
		validateVar.checkConstantIsUsed(constant);
	}

	def private dispatch void checkNode(Disjunction disjunction, BranchState state) {
		validateGrammar.checkDisjunctionPositionIsValid(disjunction, state);
		
		validateSize.checkIsScalar(disjunction);

		validateType.checkIsBoolean(disjunction);
		validateType.checkDisjunctionOperandTypes(disjunction);
	}

	def private dispatch void checkNode(Conjunction conjunction, BranchState state) {
		validateGrammar.checkConjunctionPositionIsValid(conjunction, state);
		
		validateSize.checkIsScalar(conjunction);

		validateType.checkIsBoolean(conjunction);
		validateType.checkConjunctionOperandTypes(conjunction);
	}

	def private dispatch void checkNode(Comparison comparison, BranchState state) {
		validateGrammar.checkComparisonOperations(comparison);
		validateGrammar.checkComparisonOperandWitnesses(comparison);
		validateGrammar.checkValidComparisonPosition(comparison, state);
		
		validateSize.checkIsScalar(comparison);
		validateSize.checkComparisonOperandSizes(comparison);

		validateType.checkIsBoolean(comparison);
		validateType.checkComparisonOperandTypes(comparison);
	}

	def private dispatch void checkNode(Sum sum, BranchState state) {
		validateGrammar.checkProofAlgebraicPosition(sum, state);
		validateGrammar.checkValidAlgebraicPosition(sum, state);

		validateSize.checkSumOperandSizes(sum);
		
		validateType.checkIsExponent(sum);
		validateType.checkSumOperandTypes(sum);
	}

	def private dispatch void checkNode(Product product, BranchState state) {
		validateGrammar.checkProofAlgebraicPosition(product, state);
		validateGrammar.checkValidAlgebraicPosition(product, state);

		validateSize.checkProductOperandSizes(product);
		
		validateType.checkProductOperandTypes(product);
	}

	def private dispatch void checkNode(Power power, BranchState state) {
		validateGrammar.checkWitnessIsNotInExponentOfWitness(power);
		validateGrammar.checkProofAlgebraicPosition(power, state);
		validateGrammar.checkValidAlgebraicPosition(power, state);

		validateSize.checkPowerOperandSizes(power);
		
		validateType.checkIsExponent(power.getRight());
		validateType.checkPowerOperandTypes(power);
	}

	def private dispatch void checkNode(StringLiteral stringLiteral, BranchState state) {
		validateGrammar.checkStringLiteralPositionIsValid(stringLiteral, state);
		
		validateSize.checkIsScalar(stringLiteral);

		validateType.checkIsString(stringLiteral);
	}

	def private dispatch void checkNode(Tuple tuple, BranchState state) {
		validateGrammar.checkProofAlgebraicPosition(tuple, state);
		validateGrammar.checkTuplePositionIsValid(tuple, state);
		
		validateSize.checkTupleElementsAreSameType(tuple);
		validateSize.checkTupleSize(tuple);
		validateSize.checkIsTuple(tuple);
	}

	def private dispatch void checkNode(Negative negative, BranchState state) {
		validateGrammar.checkProofAlgebraicPosition(negative, state);
		
		validateType.checkIsExponent(negative);
	}

	def private dispatch void checkNode(FunctionCall call, BranchState state) {
		validateCall.checkFunctionCallIsValid(call);

		validateFunction.checkFunctionHasNoFunctionCalls(call, state);

		validateGrammar.checkFunctionCallPositionIsValid(call, state);

		validateType.checkPredefinedFunctionCallType(call);
	}

	def private dispatch void checkNode(Argument argument, BranchState state) {
		// Intentionally blank
	}

	def private dispatch void checkNode(Variable variable, BranchState state) {
		validateFormat.checkVariableNameFormat(variable);
		
		validateGrammar.checkProofAlgebraicPosition(variable, state);
		
		validateGroup.checkGroupType(variable);
		
		validateVar.checkVariableIsDeclared(variable);
	}

	def private dispatch void checkNode(NumberLiteral numberLiteral, BranchState state) {
		validateGrammar.checkProofAlgebraicPosition(numberLiteral, state);
		
		validateSize.checkIsScalar(numberLiteral);

		validateType.checkIsExponent(numberLiteral);
	}

	def private dispatch void checkNode(Brackets brackets, BranchState state) {
		// TODO
		// if (types.get(brackets) === Type.BOOLEAN) {
		// 	if (!isValidBooleanPosition(brackets, state)) {
		// 		error("Boolean expressions cannot be nested within algebraic expressions, comparison expressions, or function calls", brackets, getDefaultFeature(brackets));
		// 	}
		// } else {
		// 	validateGrammar.checkProofAlgebraicPosition(brackets, state);
		// }
	}

	def private dispatch void checkNode(EObject node, BranchState state) {
		System.err.println("Error: unhandled node type in validator");
	}


}
