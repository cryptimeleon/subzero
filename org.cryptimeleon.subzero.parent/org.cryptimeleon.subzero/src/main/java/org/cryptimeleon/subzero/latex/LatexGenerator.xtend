package org.cryptimeleon.subzero.latex

import org.cryptimeleon.subzero.model.AugmentedModel
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
import org.cryptimeleon.subzero.subzero.Power
import org.cryptimeleon.subzero.subzero.Product
import org.cryptimeleon.subzero.subzero.PublicParameter
import org.cryptimeleon.subzero.subzero.StringLiteral
import org.cryptimeleon.subzero.subzero.Sum
import org.cryptimeleon.subzero.subzero.Tuple
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.Witness
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * Converts a syntax tree to valid LaTeX output
 * 
 * Precondition: the Model object used to create the LatexPreview object
 * must be free of validation errors
 */
class LatexGenerator {

	// Token constants
	static val String NEWLINE = "\\\\";
	static val String SPACE = " ";
	static val String COMMA = ",";
	static val String COLON = ":";
	static val String SEMICOLON = ";";
	static val String LEFTPAREN = "(";
	static val String RIGHTPAREN = ")";
	static val String LEFTBRACE = "{";
	static val String RIGHTBRACE = "}";
	static val String AND = "&";
	static val String START = "\\begin{align*}";
	static val String END = "\\end{align*}";
	
	static val String CONJUNCTION = "\\land";
	static val String DISJUNCTION = "\\lor";
	static val String EQUAL = "=";
	static val String INEQUAL = "\\neq";
	static val String LESS = "<";
	static val String GREATER = ">";
	static val String LESSEQUAL = "\\leq";
	static val String GREATEREQUAL = "\\geq";
	static val String ADDITION = "+";
	static val String SUBTRACTION = "-";
	static val String MULTIPLICATION = "\\cdot";
	static val String DIVISION = "\\frac";
	static val String EXPONENTIATION = "^";
	static val String NEGATION = "-";
	
	static val String OPERATOR_ADDITION = "+";
	static val String OPERATOR_SUBTRACTION = "-";
	static val String OPERATOR_MULTIPLICATION = "*";
	static val String OPERATOR_DIVISION = "/";
	static val String OPERATOR_EXPONENTIATION = "^";
	static val String OPERATOR_EQUAL = "=";
	static val String OPERATOR_INEQUAL = "!=";
	static val String OPERATOR_LESS = "<";
	static val String OPERATOR_GREATER = ">";
	static val String OPERATOR_LESSEQUAL = "<=";
	static val String OPERATOR_GREATEREQUAL = ">=";
	
	// Contains the final generated LaTeX output
	var String latexText;
	
	var StringBuilder builder;

	// Counts the number of open curly braces, used for formatting exponents
	var int openBraces;

	new(AugmentedModel augmentedModel) {
		if (augmentedModel.hasErrors()) {
			throw new IllegalArgumentException("Cannot generate LaTeX preview for model with validation errors");
		}
		
		openBraces = 0;
		builder = new StringBuilder();
		builder.append(START);
		generateLatex(augmentedModel.getModel());
		builder.append(END);
		
		latexText = builder.toString();
	}
	
	def String generate() {
		return latexText;
	}

	def private void generateBraces(EObject node) {
		builder.append(LEFTBRACE);
		generateLatex(node);
		builder.append(RIGHTBRACE);
	}

	def private void generateOperator(String operator) {
		builder.append(SPACE);
		builder.append(operator);
		builder.append(SPACE);
	}

	def private void generateList(EList<? extends EObject> items) {
		var boolean isFirstItem = true;
		for (EObject item : items) {
			if (isFirstItem) {
				isFirstItem = false;
			} else {
				builder.append(COMMA);
			}
			generateLatex(item);
		}
	}

	// This function should never be called
	def dispatch private void generateLatex(EObject node) {
		System.err.println("Unhandled EObject type in Latex generation function");
	}

	def dispatch private void generateLatex(Model model) {
		for (FunctionDefinition function : model.getFunctions()) {
			generateLatex(function);
			builder.append(NEWLINE);
			builder.append(NEWLINE);
		}

		val EList<PublicParameter> publicParameterList = model.getPublicParameters();
		if (publicParameterList.size() > 0) {
			builder.append("pp & = ");
			builder.append(LEFTPAREN);
			generateList(publicParameterList);
			builder.append(RIGHTPAREN);
			builder.append(SEMICOLON);
			builder.append(NEWLINE);
			builder.append(NEWLINE);
		}
		
		builder.append("\\mathrm{ZK} & \\{");
		builder.append(LEFTPAREN);
		generateList(model.getWitnesses());
		builder.append(RIGHTPAREN);
		builder.append(COLON);
		builder.append(SPACE);
		builder.append(NEWLINE);
		
		builder.append(AND);
		builder.append(SPACE);
		generateLatex(model.getProof());
		builder.append(SPACE);
		builder.append(NEWLINE);
		
		builder.append(AND);
		builder.append(SPACE);
		builder.append("\\}");
	}

	def dispatch private void generateLatex(FunctionDefinition function) {
		builder.append(function.getName());
		builder.append(AND);
		builder.append(SPACE);
		builder.append(LEFTPAREN);
		generateList(function.getParameters());
		builder.append(RIGHTPAREN);
		builder.append(COLON);
		builder.append(NEWLINE);
		builder.append(AND);
		builder.append(SPACE);
		generateLatex(function.getBody());
	}

	def dispatch private void generateLatex(Parameter parameter) {
		builder.append(new LatexIdentifier(parameter.getName()));
	}
	
	def dispatch private void generateLatex(PublicParameter publicParameter) {
		builder.append(new LatexIdentifier(publicParameter.getName()));
	}

	def dispatch private void generateLatex(Witness witness) {
		builder.append(new LatexIdentifier(witness.getName()));
	}

	def dispatch private void generateLatex(Disjunction disjunction) {
		generateLatex(disjunction.getLeft());
		generateOperator(DISJUNCTION);
		generateLatex(disjunction.getRight());
	}

	def dispatch private void generateLatex(Conjunction conjunction) {
		generateLatex(conjunction.getLeft());
		generateOperator(CONJUNCTION);
		generateLatex(conjunction.getRight());
	}

	def dispatch private void generateLatex(Comparison comparison) {
		val generateComparisonOperator = [String operation |
			switch operation {
				case OPERATOR_EQUAL: generateOperator(EQUAL)
				case OPERATOR_INEQUAL: generateOperator(INEQUAL)
				case OPERATOR_LESS: generateOperator(LESS)
				case OPERATOR_GREATER: generateOperator(GREATER)
				case OPERATOR_LESSEQUAL: generateOperator(LESSEQUAL)
				case OPERATOR_GREATEREQUAL: generateOperator(GREATEREQUAL)
			}
		];
		
		generateLatex(comparison.getLeft());
		generateComparisonOperator.apply(comparison.getOperation());
		
		// Handle double sided inequalities
		val String operator2 = comparison.getOperation2();
		if (operator2 !== null) {
			generateLatex(comparison.getCenter());
			generateComparisonOperator.apply(operator2);
		}
		
		generateLatex(comparison.getRight());
	}

	def dispatch private void generateLatex(Sum sum) {
		generateLatex(sum.getLeft());
		if (sum.getOperation() == OPERATOR_ADDITION) {
			generateOperator(ADDITION);
		} else {
			generateOperator(SUBTRACTION);
		}
		generateLatex(sum.getRight());
	}

	def dispatch private void generateLatex(Product product) {
		if (product.getOperation() == OPERATOR_MULTIPLICATION) {
			generateLatex(product.getLeft());
			generateOperator(MULTIPLICATION);
			generateLatex(product.getRight());
		} else {
			builder.append(SPACE);
			builder.append(DIVISION);
			generateBraces(product.getLeft());
			generateBraces(product.getRight());
		}
	}

	def dispatch private void generateLatex(Power power) {
		generateLatex(power.getLeft());
		generateOperator(EXPONENTIATION);
		openBraces++;
		builder.append(LEFTBRACE);
		generateLatex(power.getRight());
		
		// If the power object is not contained within another power object,
		// then close all braces
		if (!(power.eContainer() instanceof Power)) {
			while (openBraces > 0) {
				builder.append(RIGHTBRACE);
				openBraces--;
			}
		}
	}

	def dispatch private void generateLatex(StringLiteral literal) {
		builder.append(literal.getValue());
	}

	def dispatch private void generateLatex(Tuple tuple) {
		builder.append(LEFTPAREN);
		generateList(tuple.getElements());
		builder.append(RIGHTPAREN);		
	}

	def dispatch private void generateLatex(Negative negative) {
		builder.append(SPACE);
		builder.append(NEGATION);
		generateBraces(negative.getTerm());
	}

	def dispatch private void generateLatex(FunctionCall call) {
		builder.append(call.getName());
		builder.append(LEFTPAREN);
		generateList(call.getArguments());
		builder.append(RIGHTPAREN);
	}
	
	def dispatch private void generateLatex(Argument argument) {
		generateLatex(argument.getExpression());
	}

	def dispatch private void generateLatex(Variable variable) {
		builder.append(new LatexIdentifier(variable.getName()));
	}

	def dispatch private void generateLatex(NumberLiteral literal) {
		builder.append(literal.getValue());
	}

	def dispatch private void generateLatex(Brackets brackets) {
		builder.append(LEFTPAREN);
		generateLatex(brackets.getContent());
		builder.append(RIGHTPAREN);
	}

}