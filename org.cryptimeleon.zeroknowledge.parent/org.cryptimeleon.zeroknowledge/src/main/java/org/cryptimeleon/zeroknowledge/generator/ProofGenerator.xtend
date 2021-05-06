package org.cryptimeleon.zeroknowledge.generator

import java.util.Map
import java.util.Set
import org.cryptimeleon.zeroknowledge.model.AugmentedModel
import org.cryptimeleon.zeroknowledge.model.BranchState
import org.cryptimeleon.zeroknowledge.model.GroupType
import org.cryptimeleon.zeroknowledge.model.ModelHelper
import org.cryptimeleon.zeroknowledge.model.Type
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Brackets
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Conjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Disjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Expression
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.LocalVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Power
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Product
import org.cryptimeleon.zeroknowledge.zeroKnowledge.StringLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import org.eclipse.emf.ecore.EObject

/**
 * Generates proof expressions
 */
class ProofGenerator {
	String OPERATOR_ADDITION = "+";
	String OPERATOR_SUBTRACTION = "-";
	String OPERATOR_MULTIPLICATION = "*";
	String OPERATOR_DIVISION = "/";
	String OPERATOR_EQUAL = "=";
	String OPERATOR_INEQUAL = "!=";
	String OPERATOR_LESS = "<";
	String OPERATOR_GREATER = ">";
	String OPERATOR_LESSEQUAL = "<=";
	String OPERATOR_GREATEREQUAL = ">=";
	
	AugmentedModel augmentedModel;
	Map<EObject, Type> types;
	Map<EObject, Integer> sizes;
	Map<EObject, GroupType> groups;
	Set<String> publicParameterNames;
	
	int subprotocolCount;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		subprotocolCount = 0;
		types = augmentedModel.getTypes();
		sizes = augmentedModel.getSizes();
		groups = augmentedModel.getGroups();
		publicParameterNames = augmentedModel.getPublicParameterNames();
	}
	
	def String generate() {
		return generateCode(augmentedModel.getModel());
	}
	
	def String generate(EObject node) {
		return generateCode(node);
	}
	
	// Generates the Java code for the main expression
	def dispatch String generateCode(Model model) {
		return generateCode(model.getProof());
	}
	
	def dispatch String generateCode(Conjunction conjunction) {
		val String left = generateCode(conjunction.getLeft());
		val String right = generateCode(conjunction.getRight());
		
		return left + right;
	}
	
	def dispatch String generateCode(Disjunction disjunction) {
		throw new UnsupportedOperationException("Disjunctions should not be generated in the proof expression");
	}
	
	def dispatch String generateCode(Comparison comparison) {
		var EObject leftNode = comparison.getLeft();
		var EObject rightNode = comparison.getRight();
			
		var String operator = comparison.getOperation();
		
		subprotocolCount++;
		var String subprotocolName = comparison.getSubprotocolName();
		
		if (subprotocolName === null) {
			subprotocolName = "statement" + subprotocolCount;
		} else {
			subprotocolName = subprotocolName.substring(1, subprotocolName.length()-1);
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
				subprotocolSpecBuilder.addSubprotocol("«subprotocolName»",
					new «fragmentClass»(«left».«method»(«right»)«extraParameter»)
				);
			''';
		}
		
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
				
				lowerBound = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
				(lowerBound as NumberLiteral).setValue(0);
				member = leftNode;
				upperBound = rightNode;
				operator2 = operator;
				operator = OPERATOR_LESSEQUAL;
				
			} else {
				// Right side contains a witness variable
				
				lowerBound = leftNode;
				member = rightNode;
				upperBound = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
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
				val Sum sum = ZeroKnowledgeFactory.eINSTANCE.createSum();
				val NumberLiteral literal = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
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
				val Sum sum = ZeroKnowledgeFactory.eINSTANCE.createSum();
				val NumberLiteral literal = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
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
			subprotocolSpecBuilder.addSubprotocol("«subprotocolName»",
				new TwoSidedRangeProof(«memberCode», «lowerBoundCode», «upperBoundCode», pp.rangeProofpp)
			);
		''';
	}
	
	def dispatch String generateCode(Sum sum) {
		val String left = generateCode(sum.getLeft());
		val String right = generateCode(sum.getRight());
		
		if (sum.getOperation() == OPERATOR_ADDITION) {
			return '''«left».add(«right»)''';
		} else {
			return '''«left».sub(«right»)''';
		}
	}
	
	def dispatch String generateCode(Product product) {
		val String left = generateCode(product.getLeft());
		val String right = generateCode(product.getRight());
		val String inverse = (product.getOperation == OPERATOR_MULTIPLICATION) ? "" : ".inv()";
		
		if (types.get(product) === Type.EXPONENT) {
			return '''«left».mul(«right»«inverse»)''';
		} else {
			return '''«left».op(«right»«inverse»)''';
		}
	}
	
	def dispatch String generateCode(Power power) {
		val String left = generateCode(power.getLeft());
		val String right = generateCode(power.getRight());
		
		return '''«left».pow(«right»)''';		
	}
	
	def dispatch String generateCode(StringLiteral string) {
		val String value = string.getValue(); // Value includes double quotes
		return value;
	}
	
	def dispatch String generateCode(Tuple node) {
		return '''«FOR element : node.getElements() SEPARATOR ', '»«generateCode(element)»«ENDFOR»'''
	}

	def dispatch String generateCode(Negative node) {
		val String term = generateCode(node.getTerm());
		return '''«term».neg()''';		
	}
	
	def dispatch String generateCode(FunctionCall call) {
		var String name = GenerationHelper.convertToJavaName(call.getName());
		
		if (name == "e") {
			name = "this.e.applyExpr";
		}
		
		return '''«name»(«FOR argument : call.getArguments() SEPARATOR ', '»«generateCode(argument)»«ENDFOR»)'''
	}
	
	def dispatch String generateCode(Argument argument) {
		generateCode(argument.getExpression());
	}
	
	def dispatch String generateCode(Variable variable) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		
		if (publicParameterNames.contains(variable.getName())) {
			return name;
		} else {
			return GenerationHelper.INPUT_VARIABLE + '.' + name;
		}
	}
	
	def dispatch String generateCode(LocalVariable variable) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return name;
	}
	
	def dispatch String generateCode(WitnessVariable witness) {
		val String name = GenerationHelper.convertToJavaName(witness.getName());
		return name + GenerationHelper.WITNESS_SUFFIX;
	}
	
	def dispatch String generateCode(NumberLiteral number) {
		return '''zp.valueOf(«number.getValue()»)''';
	}
	
	def dispatch String generateCode(Brackets brackets) {
		return generateCode(brackets.getContent());
	}
	
}