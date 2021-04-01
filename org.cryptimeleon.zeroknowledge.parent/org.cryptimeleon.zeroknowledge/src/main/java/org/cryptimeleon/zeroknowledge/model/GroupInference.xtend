package org.cryptimeleon.zeroknowledge.model

import java.util.HashMap
import java.util.Map
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.eclipse.emf.ecore.EObject

/*
 * Determines what group each group element is from
 * Necessary when there is a pairing in the protocol
 */
class GroupInference {
	
	val Map<EObject, Type> types;
	val Map<EObject, GroupType> groups;
	
	new(AugmentedModel augmentedModel) {
		types = augmentedModel.getTypes();
		groups = new HashMap<EObject, GroupType>();
		inferGroups(augmentedModel);
	}

	def Map<EObject, GroupType> getGroups() {
		return groups;
	}
	
	def GroupType getNodeGroup(EObject node) {
		return groups.get(node);
	}


	// Label all group elements within the second argument of an e call as G2 elements
	def private void fillG2(EObject node) {
		ModelMap.postorder(node, [EObject childNode |
			if (childNode instanceof Variable && types.get(childNode) === Type.GROUP_ELEMENT) {
				if (groups.get(childNode) === GroupType.GT) {
					groups.put(childNode, GroupType.UNKNOWN);
				} else {
					groups.put(childNode, GroupType.G2);
				}
			}
		]);
	}
	
	
	// Label all group elements within an equality that contains an e call as GT elements
	def private void fillGT(EObject node) {
		ModelMap.preorderWithControl(node, [EObject childNode, ModelMap.Controller controller |
			if (childNode instanceof FunctionCall) {
				controller.continueTraversal();
			} else if (childNode instanceof Variable && types.get(childNode) === Type.GROUP_ELEMENT) {
				if (groups.get(childNode) === GroupType.G2) {
					groups.put(childNode, GroupType.UNKNOWN)
				} else {
					groups.put(childNode, GroupType.GT);
				}
			}
		]);
	}


	// Label all remaining unlabeled group elements as G1 elements
	def private void fillG1(Model model) {
		ModelMap.postorder(model, [EObject node |
			if (node instanceof Variable && types.get(node) === Type.GROUP_ELEMENT && !groups.containsKey(node)) {
				groups.put(node, GroupType.G1);
			}
		]);
	}
	
	
	def private void inferGroups(AugmentedModel augmentedModel) {
		val Model model = augmentedModel.getModel();
		
		ModelMap.preorderWithControl(model, [EObject node, ModelMap.Controller controller |
			
			if (node instanceof Comparison) {
				var boolean containsECall = false;
				val String operation = node.getOperation();
				if (operation == "=" || operation == "!=") {
					
					containsECall = ModelMap.preorderWithControl(node, [EObject newNode, ModelMap.Controller newController |
						if (newNode instanceof FunctionCall) {
							
							if (newNode.getName() == "e") {
								newController.returnTrue();
								fillG2(newNode.getArguments().get(1));
							}
							
							newController.continueTraversal();
						}
					]);
					
					if (containsECall) {
						fillGT(node);
					}
				}
				
				controller.continueTraversal();
			}
		]);
		
		fillG1(model);
	}
	
}