package org.cryptimeleon.subzero.tests;

import java.util.Map;

import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.GroupType;
import org.cryptimeleon.subzero.model.Type;
import org.cryptimeleon.subzero.model.ModelUtils;
import org.cryptimeleon.subzero.subzero.Model;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;

public class ModelAssertions {
	
	private AugmentedModel augmentedModel;
	private Map<EObject, Type> types;
	private Map<String, GroupType> groups;
	
	ModelAssertions(Model model) {
		augmentedModel = new AugmentedModel(model);
		types = augmentedModel.getTypes();
		groups = augmentedModel.getGroups();
	}

	public void assertIsBoolean(EObject node) {
		assertNodeType(node, Type.BOOLEAN);
	}
	
	public void assertIsExponent(EObject node) {
		assertNodeType(node, Type.EXPONENT);
	}
	
	public void assertIsGroupElement(EObject node) {
		assertNodeType(node, Type.GROUP_ELEMENT);
	}
	
	public void assertIsString(EObject node) {
		assertNodeType(node, Type.STRING);
	}
	
	public void assertNodeType(EObject node, Type type) {
		Assertions.assertTrue(types.get(node) == type);
	}
	
	public void assertIsG1GroupElement(EObject node) {
		assertNodeGroupType(node, GroupType.G1);
	}
	
	public void assertIsG2GroupElement(EObject node) {
		assertNodeGroupType(node, GroupType.G2);
	}
	
	public void assertIsGTGroupElement(EObject node) {
		assertNodeGroupType(node, GroupType.GT);
	}
	
	public void assertNodeGroupType(EObject node, GroupType groupType) {
		GroupType trueGroupType = groups.get(ModelUtils.getNodeName(node));
		Assertions.assertTrue(
			trueGroupType == groupType,
			"Expected node type is " + groupType + ", actual group type is " + trueGroupType
		);
	}
	
	
	
}
