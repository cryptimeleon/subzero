package de.upb.crypto.zeroknowledge.model


import org.eclipse.emf.ecore.EObject;

import de.upb.crypto.zeroknowledge.type.TypeResolution;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness

class ModelPrinter {
		
	def static void print(Model model) {
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
			
			if (TypeResolution.getTypes() !== null && TypeResolution.getTypes().containsKey(node)) {
				System.out.print(" - " + TypeResolution.getTypes().get(node).toString());
			} else {
				System.out.print("");
			}
			
			if (TypeResolution.getSizes() !== null && TypeResolution.getSizes().containsKey(node)) {
				System.out.print(" (" + TypeResolution.getSizes().get(node).toString() + ")");
			} else {
				System.out.print("");
			}
			
			System.out.println("");
		]);
	}
}