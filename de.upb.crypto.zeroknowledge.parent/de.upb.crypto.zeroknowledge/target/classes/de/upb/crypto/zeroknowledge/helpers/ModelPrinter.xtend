package de.upb.crypto.zeroknowledge.helpers


import org.eclipse.emf.ecore.EObject;

import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter

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
				FunctionDefinition: System.out.println(" - " + node.getName().toString())
				Parameter: System.out.println(" - " + node.getName().toString())
				Variable: System.out.println(" - " + node.getName().toString())
				NumberLiteral: System.out.println(" - " + node.getValue().toString())
				default: System.out.println("")
			}
		])
	}
}