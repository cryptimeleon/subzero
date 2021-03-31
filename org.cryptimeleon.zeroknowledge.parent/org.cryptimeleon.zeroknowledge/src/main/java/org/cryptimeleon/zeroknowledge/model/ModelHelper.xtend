package org.cryptimeleon.zeroknowledge.model

import java.util.HashMap
import java.util.Iterator
import java.util.Map
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Expression
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil

/*
 * A helper class providing functions for working with a model
 */
class ModelHelper {

	// Helper function to get the root Model object
	def static Model getRoot(EObject node) {
		return EcoreUtil.getRootContainer(node) as Model;
	}

	// Sets a child node's parent's reference to the child node to reference a new child node
	// Precondition: node cannot be the root Model node
	def static void replaceParentReferenceToSelf(EObject child_node, EObject new_child_node) {
		val EObject parent = child_node.eContainer();
		val EStructuralFeature feature = child_node.eContainingFeature();
		if (parent.eGet(feature) instanceof EList) {
			val EList<EObject> list = parent.eGet(feature) as EList<EObject>;
			list.set(list.indexOf(child_node), new_child_node);
		} else {
			parent.eSet(feature, new_child_node);
		}
	}


	def package static dispatch void replaceFunctionCallWithDefinition(EObject node, Map<String, FunctionDefinition> functions) {
		return;
	}
	def package static dispatch void replaceFunctionCallWithDefinition(FunctionCall call, Map<String, FunctionDefinition> functions) {
		// Precondition: validation ensures that function call is valid
		val FunctionDefinition definition = EcoreUtil.copy(functions.get(call.getName()));
		val Map<String, Expression> mapping = new HashMap<String, Expression>();
		val Iterator<Expression> argumentIterator = call.getArguments().iterator();
		val Iterator<Parameter> parameterIterator = functions.get(call.getName()).getParameterList().getParameters().
			iterator();

		while (argumentIterator.hasNext() && parameterIterator.hasNext()) {
			val Argument argument = argumentIterator.next() as Argument;
			val String parameter = parameterIterator.next().getName();
			mapping.put(parameter, argument.getExpression());
		}

		ModelMap.preorder(definition.getBody(), [ EObject bodyNode |
			if (bodyNode instanceof Variable) {
				val Expression expression = EcoreUtil.copy(mapping.get((bodyNode as Variable).getName()));
				if (expression !== null) {
					replaceParentReferenceToSelf(bodyNode, expression);
				}
			}
		]);

		replaceParentReferenceToSelf(call, definition.getBody());
	}

	
	// For an argument in a function call, get the function name
	def static String getArgumentFunction(Argument argument) {
		return (argument.eContainer() as FunctionCall).getName();
	}
	
	// For an argument in a function call, get the position (0-based) of the argument in the call
	def static int getArgumentIndex(Argument argument) {
		return (argument.eContainer() as FunctionCall).getArguments().indexOf(argument);
	}

	def static boolean containsWitnessVariable(EObject node) {
		return ModelMap.postorderAny(node, [EObject currentNode |
            currentNode instanceof WitnessVariable;
        ]);
	}
	
	def static boolean isEqualityComparison(EObject node) {
		if (node instanceof Comparison) {
			val String operator = (node as Comparison).getOperation();
			return operator == "=" || operator == "!=";
		}
		
		return false;
	}

	def static boolean isInequalityComparison(EObject node) {
		if (node instanceof Comparison) {
			val String operator = (node as Comparison).getOperation();
			return operator == "<" || operator == "<=" || operator == ">" || operator == ">=";
		}	
	}

}
