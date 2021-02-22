package de.upb.crypto.zeroknowledge.generator

import java.util.HashMap
import java.util.HashSet

import org.eclipse.emf.ecore.EObject

import de.upb.crypto.math.expressions.*;

import de.upb.crypto.zeroknowledge.model.ModelPrinter
import de.upb.crypto.zeroknowledge.model.BranchState
import de.upb.crypto.zeroknowledge.model.ModelHelper

import de.upb.crypto.zeroknowledge.type.Type

import de.upb.crypto.zeroknowledge.predefined.PredefinedFunctionsHelper

import de.upb.crypto.zeroknowledge.zeroKnowledge.Model
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
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument
import de.upb.crypto.zeroknowledge.type.TypeInference
import org.eclipse.xtext.generator.IFileSystemAccess

class CodeGeneration {
	
	var String generatedCode;
	
	HashMap<EObject, Type> types;
	HashMap<EObject, Integer> sizes;

	HashSet<String> variables; // Contains the set of names of variables
	HashSet<String> numberLiterals; // Contains the set of names of number literals
	HashSet<String> stringLiterals; // Contains the set of values of string literals
	
	StringBuilder codeBuilder; // Builds the entire generated Java code
	StringBuilder importBuilder; // Builds the required import statements
	StringBuilder functionBuilder; // Builds the function definitions for user functions
	StringBuilder exponentVariableBuilder; // Builds the required exponent variable initializations
	StringBuilder groupVariableBuilder; // Builds the required group variable initializations
	StringBuilder numberLiteralBuilder; // Builds the required number literal initializations
	StringBuilder stringLiteralBuilder; // Builds the required string literal initializations
	
	int stringLiteralCount; // Counter used to name new string literals
	
	String OPERATOR_MULTIPLICATION = "*";
	String OPERATOR_DIVISION = "/";
	String OPERATOR_EQUAL = "=";
	String OPERATOR_INEQUAL = "!=";
	String OPERATOR_LESS = "<";
	String OPERATOR_GREATER = ">";
	String OPERATOR_LESSEQUAL = "<=";
	String OPERATOR_GREATEREQUAL = ">=";
	String NEWLINE = "\n";
	String INDENT = "  ";
	
	new(Model model) {
		performGeneration(model, false);
	}
	
	new(Model model, boolean inline) {
		performGeneration(model, inline);
	}
	
	def void performGeneration(Model model, boolean inlineFunctions) {
		variables = new HashSet<String>;
		numberLiterals = new HashSet<String>;
		stringLiterals = new HashSet<String>;
	
		codeBuilder = new StringBuilder();
		importBuilder = new StringBuilder();
		functionBuilder = new StringBuilder();
		exponentVariableBuilder = new StringBuilder();
		groupVariableBuilder = new StringBuilder();
		numberLiteralBuilder = new StringBuilder();
		stringLiteralBuilder = new StringBuilder();
	
		// Perform model transformations
		
		
		ModelPrinter.print(model);
		
		// If option is set, inline all functions
		if (inlineFunctions) ModelHelper.inlineFunctions(model);
		
		// Replace all subtraction operations with sum operations of negative nodes
		ModelHelper.normalizeNegatives(model);

		// Perform type resolution on the model
		TypeInference.inferTypes(model);	
		types = TypeInference.getTypes();
		sizes = TypeInference.getSizes();

		generateImports();
		generateFunctions(model, new BranchState());
		val String code = generateCode(model, new BranchState());
	
		// Combine all sections of generated code
		codeBuilder.append(importBuilder);
		if (importBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(functionBuilder);
		if (functionBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(numberLiteralBuilder);
		if (numberLiteralBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(stringLiteralBuilder);
		if (stringLiteralBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(exponentVariableBuilder);
		if (exponentVariableBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(groupVariableBuilder);
		if (groupVariableBuilder.length() !== 0) codeBuilder.append(NEWLINE);
		codeBuilder.append(code);
		
		
		generatedCode = codeBuilder.toString();
		System.out.println(generatedCode);
	}
	
	def String getCode() {
		return generatedCode;
	}
	
	// Generates all required import statements
	def void generateImports() {
		importBuilder.append(
			'''
			import de.upb.crypto.math.expressions.*;
			import static de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;
			'''
		);
	}
	
	// Generates the Java equivalent of all user defined functions
	def void generateFunctions(Model model, BranchState state) {
		for (FunctionDefinition function : model.getFunctions()) {
			
			if (types.containsKey(function)) {
				
				// TODO: if the function type is EXPONENT, and the function size is greater than 1,
				// then the returnType should instead be ExponentExprTuple
				val String returnType = Type.toString(types.get(function));
				
				// TODO: for each parameter, if the function type is EXPONENT, and the parameter size is greater than 1,
				// then the type should instead be ExponentExprTuple
				functionBuilder.append(
					'''
					private static «returnType» «function.getName()»(«FOR Parameter parameter : function.getParameterList().getParameters() SEPARATOR ', '»«Type.toString(types.get(parameter))» «parameter.getName()»«ENDFOR») {
					  return «generateCode(function.getBody(), state)»;
					}
					'''
				);
			}
			// Maybe throw a console warning above if the type cannot be determined for the variable
			// Or possibly move this warning to validation
		}
	}
	
	// Generates the Java code for the main expression
	def dispatch String generateCode(Model model, BranchState state) {
		return 
		'''
		private static boolean proof() {
			return «generateCode(model.getProof(), state)»;
		}
		''';
	}
	
	def dispatch String generateCode(Conjunction conjunction, BranchState state) {
		val String left = generateCode(conjunction.getLeft(), state);
		val String right = generateCode(conjunction.getRight(), state);
		
		return 
		'''
		«left»
		.and(«right»)''';
	}
	
	def dispatch String generateCode(Disjunction disjunction, BranchState state) {
		val String left = generateCode(disjunction.getLeft(), state);
		val String right = generateCode(disjunction.getRight(), state);
		
		return '''«left».or(«right»)''';
		}
	
	def dispatch String generateCode(Comparison comparison, BranchState state) {
		val String left = generateCode(comparison.getLeft(), state);
		val String right = generateCode(comparison.getRight(), state);		
		var String operator;
		
		switch comparison.getOperation() {
			case OPERATOR_EQUAL: operator = "equals"
			case OPERATOR_INEQUAL: operator = "notequals"
			case OPERATOR_LESS: operator = "lessthan"
			case OPERATOR_GREATER: operator = "greaterthan"
			case OPERATOR_LESSEQUAL: operator = "lessthanequal"
			case OPERATOR_GREATEREQUAL: operator = "greaterthanequal"
		}
		
		return '''«left».«operator»(«right»)''';
	}
	
	def dispatch String generateCode(Sum sum, BranchState state) {
		
		val newState = new BranchState(state);
//		newState.setExponentContext();

		val String left = generateCode(sum.getLeft(), newState);
		val String right = generateCode(sum.getRight(), newState);
		
		return '''«left».add(«right»)''';
	}
	
	def dispatch String generateCode(Product product, BranchState state) {

		val String left = generateCode(product.getLeft(), state);
		val String right = generateCode(product.getRight(), state);
		
		if (product.getOperation === OPERATOR_MULTIPLICATION) {
			if (types.get(product) === Type.EXPONENT) {
				return '''«left».mul(«right»)''';
			} else {
				return '''«left».op(«right»)''';
			}
		} else {
			if (types.get(product) === Type.EXPONENT) {
				return '''«left».mul(«right».inv())''';
			} else {
				return '''«left».op(«right».inv())''';
			}
		}
		
		

	}
	
	def dispatch String generateCode(Power power, BranchState state) {
		
		val String left = generateCode(power.getLeft(), state);
		val String right = generateCode(power.getRight(), state);
		
		return '''«left».pow(«right»)''';		
	}
	
	def dispatch String generateCode(StringLiteral string, BranchState state) {
		
		val String name = "stringLiteral" + (stringLiteralCount++)
		val String value = string.getValue(); // Value includes double quotes

		if (!stringLiterals.contains(value)) {
			stringLiterals.add(value);
			stringLiteralBuilder.append('''
				String «name» = «value»;
			''');
		}
		
		return '''value''';
	}
	
	def dispatch String generateCode(Tuple node, BranchState state) {
		return '''«FOR element : node.getElements() SEPARATOR ', '»«generateCode(element, state)»«ENDFOR»'''
	}

	def dispatch String generateCode(Negative node, BranchState state) {
		
		val String term = generateCode(node.getTerm(), state);
		
		return '''«term».neg()''';		
	}
	
	def dispatch String generateCode(FunctionCall call, BranchState state) {
		val String name = ModelHelper.convertToJavaName(call.getName());
		
		return '''«name»(«FOR argument : call.getArguments() SEPARATOR ','»«generateCode(argument, state)»«ENDFOR»)'''
	}
	
	def dispatch String generateCode(Argument argument, BranchState state) {
		generateCode(argument.getExpression(), state);
	}
	
	def dispatch String generateCode(Variable variable, BranchState state) {
		val String name = ModelHelper.convertToJavaName(variable.getName());
		
		if (!variables.contains(name)) {
			variables.add(name);
			
			if (types.get(variable) === Type.EXPONENT) {
				// TODO: if the variable type is EXPONENT, and the variable size is greater than 1,
				// then this should instead be ExponentExprTuple
				exponentVariableBuilder.append(
					'''
					ExponentVariableExpr «name» = new ExponentVariableExpr("«name»");
					'''
				);
			} else {
				groupVariableBuilder.append(
					'''
					GroupVariableExpr «name» = new GroupVariableExpr("«name»");
					'''
				);
			}
		}
		
		return name;
	}
	
	def dispatch String generateCode(LocalVariable variable, BranchState state) {
		val String name = ModelHelper.convertToJavaName(variable.getName());
		return name;
	}
	
	def dispatch String generateCode(NumberLiteral number, BranchState state) {
		
		val String name = "val_" + number.getValue();
		
		if (!numberLiterals.contains(name)) {
			numberLiterals.add(name);
			numberLiteralBuilder.append('''
				ExponentLiteralExpr «name» = new ExponentLiteralExpr(«number.getValue()»);
			''');
		}

		return name;
	}
	
	def dispatch String generateCode(Brackets brackets, BranchState state) {
		return generateCode(brackets.getContent(), state);
	}
	
}