package org.cryptimeleon.zeroknowledge.latex

import org.cryptimeleon.zeroknowledge.model.AugmentedModel
import org.cryptimeleon.zeroknowledge.model.ModelHelper
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Brackets
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Conjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Disjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ParameterList
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Power
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Product
import org.cryptimeleon.zeroknowledge.zeroKnowledge.StringLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * Converts a syntax tree to valid LaTeX output
 * 
 * Precondition: the Model object used to create the LatexPreview object
 * must be free of validation errors
 */
class LatexPreview {

	// Contains the final generated LaTeX output
	var String latexCode;
	
	var StringBuilder builder;

	// Token constants
	static val String NEWLINE = "\\\\";
	static val String SPACE = " ";
	static val String COMMA = ",";
	static val String COLON = ":";
	static val String SEMICOLON = ";";
	static val String QUOTE = "'";
	static val String LEFTPAREN = "(";
	static val String RIGHTPAREN = ")";
	static val String LEFTBRACE = "{";
	static val String RIGHTBRACE = "}";
	static val String DELIMITER = "$$";
	
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

	// Counts the number of open curly braces, used for formatting exponents
	var int openBraces = 0;
	
	// If true, all function calls will be inlined (replaced with the full
	// function definition) before being converted to LaTeX
	var boolean inlineFunctions;

	new(AugmentedModel augmentedModel) {
		builder = new StringBuilder();
		builder.append(DELIMITER);
		generateLatex(augmentedModel.getModel());
		builder.append(DELIMITER);
		
		latexCode = builder.toString();
	}
	
	def String getRawLatex() {
		return latexCode;
	}

	// Formats underscores in identifiers as subscript in Latex
	def private String formatIdentifier(String name) {
		if (name.indexOf('_') > 0) {
			if (name.charAt(name.length() - 1) == '\'') {
				return name.replace("_", "_{").replaceFirst("'", "}'");
			} else {
				return name.replace("_", "_{") + "}";
			}
		}
		return name;
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
		builder.append(LEFTPAREN);

		var boolean isFirstItem = true;
		for (EObject item : items) {
			if (isFirstItem) {
				isFirstItem = false;
			} else {
				builder.append(COMMA);
			}
			generateLatex(item);
		}

		builder.append(RIGHTPAREN);
	}

	// This function should never be called
	def dispatch private void generateLatex(EObject node) {
		System.err.println("Unhandled EObject type in Latex generation function");
	}

	def dispatch private void generateLatex(Model model) {
		if (!inlineFunctions) {
			for (FunctionDefinition function : model.getFunctions()) {
				generateLatex(function);
				builder.append(NEWLINE);
				builder.append(NEWLINE);
			}
		}

		generateLatex(model.getWitnessList());
		builder.append(SEMICOLON);
		builder.append(NEWLINE);
		generateLatex(model.getProof());
	}

	def dispatch private void generateLatex(FunctionDefinition function) {
		builder.append(function.getName());		
		generateLatex(function.getParameterList());
		builder.append(COLON);
		builder.append(NEWLINE);
		builder.append(NEWLINE);
		generateLatex(function.getBody());
	}
	
	def dispatch private void generateLatex(ParameterList parameterList) {
		generateList(parameterList.getParameters());
	}

	def dispatch private void generateLatex(Parameter parameter) {
		builder.append(parameter.getName());
	}
	
	def dispatch private void generateLatex(WitnessList witnessList) {
		generateList(witnessList.getWitnesses());
	}

	def dispatch private void generateLatex(Witness witness) {
		builder.append(formatIdentifier(witness.getName()));
	}

	def dispatch private void generateLatex(Conjunction conjunction) {
		generateLatex(conjunction.getLeft());
		generateOperator(CONJUNCTION);
		generateLatex(conjunction.getRight());
	}

	def dispatch private void generateLatex(Disjunction disjunction) {
		generateLatex(disjunction.getLeft());
		generateOperator(DISJUNCTION);
		generateLatex(disjunction.getRight());
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
		generateLatex(comparison.getRight());
		
		// Handle double sided inequalities
		val String operator2 = comparison.getOperation2();
		if (operator2 !== null) {
			generateComparisonOperator.apply(operator2);
			generateLatex(comparison.getExtra());
		}
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
		// Uncomment if getValue() is changed
		// Change quote to use some Latex notation
		// builder.append(QUOTE);
		builder.append(literal.getValue());
		// builder.append(QUOTE);
	}

	def dispatch private void generateLatex(Tuple tuple) {
		generateList(tuple.getElements());		
	}

	def dispatch private void generateLatex(Negative negative) {
		builder.append(SPACE);
		builder.append(NEGATION);
		generateBraces(negative.getTerm());
	}

	def dispatch private void generateLatex(FunctionCall call) {
		builder.append(call.getName());
		generateList(call.getArguments());
	}
	
	def dispatch private void generateLatex(Argument argument) {
		generateLatex(argument.getExpression());
	}

	def dispatch private void generateLatex(Variable variable) {
		builder.append(formatIdentifier(variable.getName()));
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
