package org.cryptimeleon.subzero.generator

import java.util.Map
import java.util.Set
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.model.BranchState
import org.cryptimeleon.subzero.model.GroupType
import org.cryptimeleon.subzero.model.ModelHelper
import org.cryptimeleon.subzero.model.Type
import org.cryptimeleon.subzero.subzero.Argument
import org.cryptimeleon.subzero.subzero.Brackets
import org.cryptimeleon.subzero.subzero.Comparison
import org.cryptimeleon.subzero.subzero.Conjunction
import org.cryptimeleon.subzero.subzero.Disjunction
import org.cryptimeleon.subzero.subzero.Expression
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.LocalVariable
import org.cryptimeleon.subzero.subzero.Model
import org.cryptimeleon.subzero.subzero.Negative
import org.cryptimeleon.subzero.subzero.NumberLiteral
import org.cryptimeleon.subzero.subzero.Power
import org.cryptimeleon.subzero.subzero.Product
import org.cryptimeleon.subzero.subzero.StringLiteral
import org.cryptimeleon.subzero.subzero.Sum
import org.cryptimeleon.subzero.subzero.Tuple
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.WitnessVariable
import org.cryptimeleon.subzero.subzero.SubzeroFactory
import org.eclipse.emf.ecore.EObject
import java.util.List
import java.util.ArrayList
import org.cryptimeleon.subzero.subzero.FunctionDefinition
import java.util.HashMap
import org.cryptimeleon.subzero.subzero.ConstantVariable
import org.cryptimeleon.subzero.subzero.PPVariable
import org.cryptimeleon.subzero.subzero.Parameter
import org.eclipse.emf.common.util.EList
import org.cryptimeleon.subzero.predefined.PredefinedFunctionsHelper
import java.util.HashSet

/**
 * Generates proof expressions
 */
class ProofGenerator {
	static String OPERATOR_ADDITION = "+";
	static String OPERATOR_SUBTRACTION = "-";
	static String OPERATOR_MULTIPLICATION = "*";
	static String OPERATOR_DIVISION = "/";
	static String OPERATOR_EQUAL = "=";
	static String OPERATOR_INEQUAL = "!=";
	static String OPERATOR_LESS = "<";
	static String OPERATOR_GREATER = ">";
	static String OPERATOR_LESSEQUAL = "<=";
	static String OPERATOR_GREATEREQUAL = ">=";
	
	static String STATEMENT = "statement";
	
	AugmentedModel augmentedModel;
	
	Map<EObject, Type> types;
	Map<String, FunctionDefinition> functions;
	
	// Used to create unique subprotocol names
	int subprotocolCount;
	int functionSubprotocolCount;

	// If a function body is currently being generated
	boolean inFunctionBody;
	
	 // If a call to an inline function is currently being generated
	boolean inInlineFunction;
	
	// Contains the expressions for all inline functions that are called at least once
	// The expression has placeholders for the local variables, which are then replaced
	// with the passed arguments when generating a function call
	Map<String, String> inlineFunctionsCode; 
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		subprotocolCount = 0;
		functionSubprotocolCount = 0;
		types = augmentedModel.getTypes();
		functions = augmentedModel.getUserFunctionNodes();
		inFunctionBody = false;
		inlineFunctionsCode = new HashMap<String, String>();
	}
	
	// If no node is provided, generate from the root
	def String generate() {
		return generateCode(augmentedModel.getModel());
	}
	
	// Generates the body expression of a user defined function
	def dispatch String generate(FunctionDefinition function) {
		functionSubprotocolCount = 0;
		inFunctionBody = true;
		val String generatedCode = generateCode(function);
		inFunctionBody = false;
		return generatedCode;
	}
	
	def dispatch String generate(EObject node) {
		return generateCode(node);
	}
	
	// Generates the Java code for the main expression
	def private dispatch String generateCode(Model model) {
		return generateCode(model.getProof());
	}
	
	// Generates the Java code for a user defined function body expression
	def private dispatch String generateCode(FunctionDefinition function) {
		return generateCode(function.getBody());
	}
	
	def private dispatch String generateCode(Conjunction conjunction) {
		val String left = generateCode(conjunction.getLeft());
		val String right = generateCode(conjunction.getRight());
		
		return left + right;
	}
	
	def private dispatch String generateCode(Disjunction disjunction) {
		throw new UnsupportedOperationException("Disjunctions should not be generated in the proof expression");
	}
	
	def private dispatch String generateCode(Comparison comparison) {
		var EObject leftNode = comparison.getLeft();
		var EObject rightNode = comparison.getRight();
			
		var String operator = comparison.getOperation();
		
		// Create the unique subprotocol name that will be passed to .addSubprotocol
		var String subprotocolParameter;
		if (inFunctionBody) {
			functionSubprotocolCount++;
			subprotocolParameter = '''«GenerationHelper.SUBPROTOCOL_VARIABLE» + "_«functionSubprotocolCount»"''';
		} else {
			subprotocolCount++;
			var String subprotocolName = comparison.getSubprotocolName();
			
			if (subprotocolName === null) {
				subprotocolName = STATEMENT + subprotocolCount;
			} else {
				subprotocolName = subprotocolName.substring(1, subprotocolName.length()-1);
			}
			
			subprotocolParameter = '''"«subprotocolName»"''';
		}
		
		
		
		// Handle = and != cases
		if (operator == OPERATOR_EQUAL || operator == OPERATOR_INEQUAL) {
			var String left = generateCode(leftNode);
			var String right = generateCode(rightNode);	
			val String method = (operator == OPERATOR_EQUAL) ? "isEqualTo" : "isNotEqualTo"
			var String fragmentClass;
			
			var String extraParameter;
			if (types.get(leftNode) === Type.EXPONENT) {
				fragmentClass = "LinearExponentStatementFragment";
				extraParameter = ", zp";
			} else {
				fragmentClass = "LinearStatementFragment";
				extraParameter = "";
			}
			
			return '''
				subprotocolSpecBuilder.addSubprotocol(«subprotocolParameter»,
					new «fragmentClass»(«left».«method»(«right»)«extraParameter»)
				);
			''';
		}
		
		// Handle single and double inequalities
		
		var EObject lowerBound;
		var EObject upperBound;
		var EObject member;
		
		var EObject centerNode = comparison.getCenter();
		var String operator2 = comparison.getOperation2();
		
		if (operator2 === null) {
			// Single comparison
			
			var boolean leftHasWitness = ModelHelper.containsWitnessVariable(leftNode);
			
			// Normalize the direction of the inequality
			if (!ModelHelper.isLessComparison(operator)) {
				var EObject tempNode = leftNode;
				leftNode = rightNode;
				rightNode = tempNode;
				operator = ModelHelper.swapComparisonDirection(operator);
				leftHasWitness = !leftHasWitness;
			}
			
			// Normalize a single comparison into a double comparison
			if (leftHasWitness) {
				// Left side contains a witness variable
				
				lowerBound = SubzeroFactory.eINSTANCE.createNumberLiteral();
				(lowerBound as NumberLiteral).setValue(0);
				member = leftNode;
				upperBound = rightNode;
				operator2 = operator;
				operator = OPERATOR_LESSEQUAL;
				
			} else {
				// Right side contains a witness variable
				
				lowerBound = leftNode;
				member = rightNode;
				upperBound = SubzeroFactory.eINSTANCE.createNumberLiteral();
				(upperBound as NumberLiteral).setValue(-1);
				operator2 = OPERATOR_LESSEQUAL;
			}
			
		} else {
			// Double comparison
			
			// Normalize the direction of the inequality
			if (!ModelHelper.isLessComparison(operator)) {
				var EObject tempNode = leftNode;
				leftNode = rightNode;
				rightNode = tempNode;
				
				val String temp = operator;
				operator = ModelHelper.swapComparisonDirection(operator2);
				operator2 = ModelHelper.swapComparisonDirection(temp);
			}
			
			lowerBound = leftNode;
			member = centerNode;
			upperBound = rightNode;
		}
		
		
		// Shift the lower bound up by 1 if the first inequality is strict
		if (ModelHelper.isStrictComparison(operator)) {
			if (lowerBound instanceof NumberLiteral) {
				val NumberLiteral literal = lowerBound;
				literal.setValue(literal.getValue() + 1);
			} else {
				val Sum sum = SubzeroFactory.eINSTANCE.createSum();
				val NumberLiteral literal = SubzeroFactory.eINSTANCE.createNumberLiteral();
				literal.setValue(1);
				sum.setLeft(lowerBound as Expression);
				sum.setRight(literal as Expression);
				sum.setOperation(OPERATOR_ADDITION);
				lowerBound = sum;
			}
		}
		
		// Shift the upper bound down by 1 if the second inequality is strict
		if (ModelHelper.isStrictComparison(operator2)) {
			if (upperBound instanceof NumberLiteral) {
				val NumberLiteral literal = upperBound;
				literal.setValue(literal.getValue() - 1);
			} else {
				val Sum sum = SubzeroFactory.eINSTANCE.createSum();
				val NumberLiteral literal = SubzeroFactory.eINSTANCE.createNumberLiteral();
				literal.setValue(1);
				sum.setLeft(upperBound as Expression);
				sum.setRight(literal as Expression);
				sum.setOperation(OPERATOR_SUBTRACTION);
				upperBound = sum;
			}
		}
		
		val String lowerBoundCode = generateCode(lowerBound);
		val String memberCode = generateCode(member);
		val String upperBoundCode = generateCode(upperBound);
		
		return '''
			subprotocolSpecBuilder.addSubprotocol(«subprotocolParameter»,
				new TwoSidedRangeProof(«memberCode», «lowerBoundCode», «upperBoundCode», pp.rangeProofpp)
			);
		''';
	}
	
	def private dispatch String generateCode(Sum sum) {
		val String left = generateCode(sum.getLeft());
		val String right = generateCode(sum.getRight());
		val String operation = sum.getOperation();
		
		if (operation == OPERATOR_ADDITION) {
			return '''«left».add(«right»)''';
		} else if (operation == OPERATOR_SUBTRACTION) {
			return '''«left».sub(«right»)''';
		} else {
			throw new UnsupportedOperationException("Invalid sum operation");
		}
	}
	
	def private dispatch String generateCode(Product product) {
		val String left = generateCode(product.getLeft());
		val String right = generateCode(product.getRight());
		val String operation = product.getOperation();
		
		var String inverse;
		if (operation == OPERATOR_MULTIPLICATION) {
			inverse = "";
		} else if (operation == OPERATOR_DIVISION) {
			inverse = ".inv()";
		} else {
			throw new UnsupportedOperationException("Invalid product operation");
		}
		
		if (types.get(product) === Type.EXPONENT) {
			return '''«left».mul(«right»«inverse»)''';
		} else {
			return '''«left».op(«right»«inverse»)''';
		}
	}
	
	def private dispatch String generateCode(Power power) {
		val String left = generateCode(power.getLeft());
		val String right = generateCode(power.getRight());
		
		return '''«left».pow(«right»)''';		
	}
	
	def private dispatch String generateCode(StringLiteral string) {
		val String value = string.getValue(); // Value includes double quotes
		return value;
	}
	
	def private dispatch String generateCode(Tuple node) {
		return '''«FOR element : node.getElements() SEPARATOR ', '»«generateCode(element)»«ENDFOR»'''
	}

	def private dispatch String generateCode(Negative node) {
		val String term = generateCode(node.getTerm());
		return '''«term».neg()''';		
	}
	
	def private dispatch String generateCode(FunctionCall call) {
		var String name = call.getName();
		
		val boolean isInlineFunction = augmentedModel.isInlineFunctionName(name);
		
		if (name == "e") {
			// Handle the special pairing function case
			
			val List<String> arguments = new ArrayList<String>();
			
			for (argument : call.getArguments()) {
				var String argumentCode = generateCode(argument);
				arguments.add(argumentCode);
			}
			
			return '''e.applyExpr(«GenerationHelper.createCommaList(arguments)»)''';
						
		} else if (isInlineFunction) {
			// Handle calls to inline user defined functions
			
			var String functionCode = inlineFunctionsCode.get(name);
			val FunctionDefinition function = functions.get(name);
			
			if (functionCode === null) {
				inInlineFunction = true;
				functionCode = generateCode(function.getBody());
				inInlineFunction = false;
				inlineFunctionsCode.put(name, functionCode);
			}
			
			val List<Parameter> parameters = function.getParameterList().getParameters();
			val EList<Expression> arguments = call.getArguments();
			var inlineCode = functionCode;
			
			for (var int i = 0; i < parameters.size(); i++) {
				val String parameterName = parameters.get(i).getName();
				val String argumentCode = generateCode(arguments.get(i));
				inlineCode = inlineCode.replace(GenerationHelper.createLocalName(parameterName), argumentCode);
			}
			
			return inlineCode;
		
		} else {
			// Handle calls to regular user defined functions
			
			val Type functionType = types.get(call);

			// Default when functionType == Type.GROUP_ELEMENT || functionType == Type.EXPONENT
			var callStatement = [String functionName, List<String> args |
				return '''«functionName»(«GenerationHelper.createCommaList(args)»)''';
			];
			
			val List<String> arguments = new ArrayList<String>();
			
			if (functionType == Type.BOOLEAN) {
				val EObject body = functions.get(call.getName()).getBody();
				
				if (body instanceof Conjunction) {
					arguments.add("subprotocolSpecBuilder");
					subprotocolCount++;
					var String subprotocolName = STATEMENT + subprotocolCount;
					arguments.add('''"«subprotocolName»"''');
					callStatement = [String functionName, List<String> args |
						return '''«functionName»(«GenerationHelper.createCommaList(args)»);''' + '\n';
					];
				} else if (body instanceof Comparison) {
					subprotocolCount++;
					val String subprotocolName = STATEMENT + subprotocolCount;
					callStatement = [String functionName, List<String> args |
						return '''subprotocolSpecBuilder.addSubprotocol("«subprotocolName»", «functionName»(«GenerationHelper.createCommaList(args)»));''' + '\n';
					];
				}
			}
			
			// If the function has a constant variable anywhere, add the CommonInput variable as an argument
			if (augmentedModel.userFunctionHasConstant(name)) {
				arguments.add(GenerationHelper.INPUT_VARIABLE);
			}
			
			// If the function has witness variables anywhere, add each witness variable as an argument
			val Set<String> functionWitnesses = augmentedModel.getUserFunctionWitnessNames(name);
			if (functionWitnesses !== null) {
				for (String witnessName : functionWitnesses) {
					arguments.add(witnessName);
				}
			}
			
			// Add all user provided arguments to the function call
			for (argument : call.getArguments()) {
				var String argumentCode = generateCode(argument);
				var EObject argumentExpr = (argument as Argument).getExpression();
				
				if (argumentExpr instanceof Variable && !(argumentExpr instanceof WitnessVariable)) {
					var Type argumentType = types.get(argumentExpr);
					if (argumentType == Type.GROUP_ELEMENT) {
						// GroupElement does not implement GroupElementExpression, so .expr() is necessary
						// SchnorrGroupElemVariable does implement GroupElementExpression, so .expr() is not needed
						argumentCode += ".expr()";
					} else if (argumentType == Type.EXPONENT) {
						// ZpElement does not implement ExponentExpr, so .asExponentExpression() is necessary
						// SchnorrZnVariable does implement ExponentExpr, so .asExponentExpression() is not needed
						argumentCode += ".asExponentExpression()";
					}
				}

				arguments.add(argumentCode);
			}
			
			// Generate and return the function call statement
			return callStatement.apply(name, arguments);
		}
	}
	
	def private dispatch String generateCode(Argument argument) {
		generateCode(argument.getExpression());
	}
	
	def private dispatch String generateCode(PPVariable variable) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return GenerationHelper.createPPName(name);
	}
	
	def private dispatch String generateCode(ConstantVariable variable) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return GenerationHelper.createConstantName(name);
	}
	
	def private dispatch String generateCode(LocalVariable variable) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return inInlineFunction ? GenerationHelper.createLocalName(name) : name;
	}
	
	def private dispatch String generateCode(WitnessVariable witness) {
		val String name = GenerationHelper.convertToJavaName(witness.getName());
		return GenerationHelper.createWitnessName(name);
	}
	
	def private dispatch String generateCode(NumberLiteral number) {
		return '''zp.valueOf(«number.getValue()»)''';
	}
	
	def private dispatch String generateCode(Brackets brackets) {
		return generateCode(brackets.getContent());
	}
	
}