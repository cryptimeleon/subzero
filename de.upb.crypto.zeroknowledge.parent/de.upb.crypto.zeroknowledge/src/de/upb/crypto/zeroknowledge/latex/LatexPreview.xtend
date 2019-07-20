package de.upb.crypto.zeroknowledge.latex

import java.lang.StringBuilder;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.EList;

import de.upb.crypto.zeroknowledge.helpers.ModelHelper;

import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product;
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness;
import de.upb.crypto.zeroknowledge.zeroKnowledge.ParameterList
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList

// Converts a syntax tree to valid Latex output
class LatexPreview {

	var String latexCode;
	val StringBuilder builder = new StringBuilder();

	// Token constants
	val String CONJUNCTION = "\\land";
	val String DISJUNCTION = "\\lor";
	val String EQUAL = "=";
	val String INEQUAL = "\\neq";
	val String LESS = "<";
	val String GREATER = ">";
	val String LESSEQUAL = "\\leq";
	val String GREATEREQUAL = "\\geq";
	val String ADDITION = "+";
	val String SUBTRACTION = "-";
	val String MULTIPLICATION = "\\cdot";
	val String DIVISION = "\\frac";
	val String EXPONENTIATION = "^";
	val String NEGATION = "-";
	val String NEWLINE = "\\\\";
	val String SPACE = " ";
	val String COMMA = ",";
	val String COLON = ":";
	val String SEMICOLON = ";";
	val String QUOTE = "'";
	val String LEFTPAREN = "(";
	val String RIGHTPAREN = ")";
	val String LEFTBRACE = "{";
	val String RIGHTBRACE = "}";
	val String DELIMITER = "$$";
	val String OPERATOR_ADDITION = "+";
	val String OPERATOR_SUBTRACTION = "-";
	val String OPERATOR_MULTIPLICATION = "*";
	val String OPERATOR_DIVISION = "/";
	val String OPERATOR_EXPONENTIATION = "^";
	val String OPERATOR_EQUAL = "=";
	val String OPERATOR_INEQUAL = "!=";
	val String OPERATOR_LESS = "<";
	val String OPERATOR_GREATER = ">";
	val String OPERATOR_LESSEQUAL = "<=";
	val String OPERATOR_GREATEREQUAL = ">=";

	// Counts the number of open curly braces, used for formatting exponents
	var int openBraces = 0;
	
	// If true, all function calls will be inlined before converted to Latex
	var boolean inline;

	new(Model model) {
		constructLatexPreview(model, false);
	}

	new(Model model, boolean inline) {
		constructLatexPreview(model, inline);
	}

	def private void constructLatexPreview(Model model, boolean inline) {
		this.inline = inline;
		if (inline) {
			ModelHelper.inlineFunctions(model);
		}
		builder.append(DELIMITER);
		generateLatex(model);
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

		var boolean firstItem = true;
		for (EObject item : items) {
			if (firstItem) {
				firstItem = false;
			} else {
				builder.append(COMMA);
			}
			generateLatex(item);
		}

		builder.append(RIGHTPAREN);
	}

	// Function catches any node that currently has type EObject
	// and casts it to its actual type
	def dispatch private void generateLatex(EObject node) {
		System.err.println("Unhandled EObject type in Latex generation function");
	}

	def dispatch private void generateLatex(Model model) {
		if (!inline) {
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
		generateLatex(comparison.getLeft());
		
		switch comparison.getOperation() {
			case OPERATOR_EQUAL: generateOperator(EQUAL)
			case OPERATOR_INEQUAL: generateOperator(INEQUAL)
			case OPERATOR_LESS: generateOperator(LESS)
			case OPERATOR_GREATER: generateOperator(GREATER)
			case OPERATOR_LESSEQUAL: generateOperator(LESSEQUAL)
			case OPERATOR_GREATEREQUAL: generateOperator(GREATEREQUAL)
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
//		builder.append(QUOTE);
		builder.append(literal.getValue());
//		builder.append(QUOTE);
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
