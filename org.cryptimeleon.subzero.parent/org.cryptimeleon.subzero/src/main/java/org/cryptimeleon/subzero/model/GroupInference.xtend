package org.cryptimeleon.subzero.model

import java.util.HashMap
import java.util.Map
import java.util.Map.Entry
import org.cryptimeleon.subzero.subzero.Comparison
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.Model
import org.cryptimeleon.subzero.subzero.Variable
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList
import org.cryptimeleon.subzero.subzero.Argument
import org.cryptimeleon.subzero.subzero.Expression
import org.cryptimeleon.subzero.subzero.FunctionDefinition
import org.cryptimeleon.subzero.subzero.LocalVariable
import org.cryptimeleon.subzero.subzero.Witness
import org.cryptimeleon.subzero.subzero.PublicParameter
import org.cryptimeleon.subzero.subzero.WitnessVariable
import org.cryptimeleon.subzero.subzero.PPVariable

/*
 * Determines what group each group element is from
 * Necessary when there is a pairing in the protocol
 */
class GroupInference {
	
	val Map<EObject, Type> types;
	val Map<EObject, GroupType> groups;
	val Map<String, GroupType> groupsByName;
	val Map<String, FunctionDefinition> userFunctions;
	val Map<String, Witness> witnessNodes;
	val Map<String, PublicParameter> publicParameterNodes;
	
	new(AugmentedModel augmentedModel) {
		groups = new HashMap<EObject, GroupType>();
		groupsByName = new HashMap<String, GroupType>();
		
		types = augmentedModel.getTypes();
		userFunctions = augmentedModel.getUserFunctionNodes();
		
		witnessNodes = augmentedModel.getWitnessNodes();
		publicParameterNodes = augmentedModel.getPublicParameterNodes();
		
		inferGroups(augmentedModel);
	}

	def Map<EObject, GroupType> getGroups() {
		return groups;
	}
	
	def GroupType getNodeGroup(EObject node) {
		return groups.get(node);
	}
	
	def Map<String, GroupType> getGroupsByName() {
		return groupsByName;
	}

	def private dispatch void setGroup(WitnessVariable witnessVariable, GroupType type) {
		groups.put(witnessVariable, type);
		groups.put(witnessNodes.get(witnessVariable.getName()), type);
	}
	
	def private dispatch void setGroup(PPVariable ppVariable, GroupType type) {
		groups.put(ppVariable, type);
		groups.put(publicParameterNodes.get(ppVariable.getName()), type);
	}
	
	def private dispatch void setGroup(EObject node, GroupType type) {
		groups.put(node, type);
	}

	// Label all group elements within the second argument of an e call as G2 elements
	def private void fillG2(EObject node) {
		ModelMap.postorder(node, [EObject childNode |
			if (childNode instanceof Variable && !(childNode instanceof LocalVariable) && types.get(childNode) === Type.GROUP_ELEMENT) {
				if (groups.get(childNode) === GroupType.GT) {
					setGroup(childNode, GroupType.UNKNOWN);
				} else {
					setGroup(childNode, GroupType.G2);
				}
			} else if (childNode instanceof FunctionCall) {
				val FunctionDefinition function = userFunctions.get(childNode.getName());
				if (function !== null) {
					fillG2(function.getBody());
				}
			}
		]);
	}
	
	
	// Label all group elements within an equality that contains an e call as GT elements
	def private void fillGT(EObject node) {
		ModelMap.preorderWithControl(node, [EObject childNode, ModelMap.Controller controller |
			if (childNode instanceof Variable && !(childNode instanceof LocalVariable) && types.get(childNode) === Type.GROUP_ELEMENT) {
				if (groups.get(childNode) === GroupType.G2) {
					setGroup(childNode, GroupType.UNKNOWN)
				} else {
					setGroup(childNode, GroupType.GT);
				}
			} else if (childNode instanceof FunctionCall) {
				val String functionName = childNode.getName();
				if (childNode.getName() == "e") {
					controller.continueTraversal();
				} else if (userFunctions.containsKey(functionName)) {
					fillGT(userFunctions.get(functionName).getBody());
					// Model map will also label all function arguments as GT
				}
				
			}
		]);
	}


	// Label all remaining unlabeled group elements as G1 elements
	def private void fillG1(Model model) {
		ModelMap.postorder(model, [EObject node |
			if (node instanceof Variable && !(node instanceof LocalVariable)) {
				setG1(node);
			}
		]);
		
		for (Witness witness : model.getWitnesses()) {
			setG1(witness);
		}
		
		for (PublicParameter publicParameter : model.getPublicParameters()) {
			setG1(publicParameter);
		}
	}
	
	def private void setG1(EObject node) {
		if (types.get(node) === Type.GROUP_ELEMENT && !groups.containsKey(node)) {
			setGroup(node, GroupType.G1);
		}
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
							val EList<Expression> arguments = newNode.getArguments();
							if (newNode.getName() == "e" && arguments.size() == 2) {
								newController.returnTrue();
								fillG2(arguments.get(1));
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
		
		for (Entry<EObject, GroupType> entry : groups.entrySet()) {
			val EObject node = entry.getKey();
			val GroupType groupType = entry.getValue();
			var String variableName = '';
			
			if (node instanceof Variable) {
				variableName = (node as Variable).getName();	
			} else if (node instanceof PublicParameter) {
				variableName = (node as PublicParameter).getName();
			} else if (node instanceof Witness) {
				variableName = (node as Witness).getName();
			}
			
			if (groupsByName.containsKey(variableName) && groupsByName.get(variableName) !== groupType) {
				groupsByName.put(variableName, GroupType.UNKNOWN);
			} else {
				groupsByName.put(variableName, groupType);
			}
		}
	}
}