package org.cryptimeleon.zeroknowledge.model

import org.eclipse.emf.ecore.EObject;

import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model;
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable;
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral;
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.type.TypeInference

/**
 * A debugging class to print out a formatted view of the model to the console
 */
class ModelPrinter {
		
	def static void print(Model model) {
		val TypeInference typeInference = new TypeInference(model);
		
		ModelMap.preorderWithState(model, new BranchState(), [EObject node, BranchState state |
			for (var int i = 0; i < state.getDepth(); i++) {
				System.out.print("---|");
			}
			
			var String className = node.getClass().toString();
			val int periodIndex = className.lastIndexOf('.');
			if (periodIndex > 0) {
				className = className.substring(periodIndex + 1);
			}
			
			System.out.print(className.substring(0, className.length() - 4));
			
			switch node {
				Witness: System.out.print(" - " + node.getName().toString())
				FunctionCall: System.out.print(" - " + node.getName().toString())
				FunctionDefinition: System.out.print(" - " + node.getName().toString())
				Parameter: System.out.print(" - " + node.getName().toString())
				Variable: System.out.print(" - " + node.getName().toString())
				NumberLiteral: System.out.print(" - " + node.getValue().toString())
				default: System.out.print("")
			}
			
			if (typeInference.getTypes() !== null && typeInference.getTypes().containsKey(node)) {
				System.out.print(" - " + typeInference.getTypes().get(node).toString());
			} else {
				System.out.print("");
			}
			
			if (typeInference.getSizes() !== null && typeInference.getSizes().containsKey(node)) {
				System.out.print(" (" + typeInference.getSizes().get(node).toString() + ")");
			} else {
				System.out.print("");
			}
			
			System.out.println("");
		]);
	}
}