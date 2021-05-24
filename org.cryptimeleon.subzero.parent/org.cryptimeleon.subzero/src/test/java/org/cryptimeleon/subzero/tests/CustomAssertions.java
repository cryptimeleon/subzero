package org.cryptimeleon.subzero.tests;

import java.util.Map;

import org.cryptimeleon.subzero.model.GroupType;
import org.cryptimeleon.subzero.model.Type;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;

public class CustomAssertions {

	public void assertIsBoolean(EObject node, Map<EObject, Type> types) {
		assertNodeType(node, Type.BOOLEAN, types);
	}
	
	public void assertIsExponent(EObject node, Map<EObject, Type> types) {
		assertNodeType(node, Type.EXPONENT, types);
	}
	
	public void assertIsGroupElement(EObject node, Map<EObject, Type> types) {
		assertNodeType(node, Type.GROUP_ELEMENT, types);
	}
	
	public void assertIsString(EObject node, Map<EObject, Type> types) {
		assertNodeType(node, Type.STRING, types);
	}
	
	public void assertNodeType(EObject node, Type type, Map<EObject, Type> types) {
		Assertions.assertTrue(types.get(node) == type);
	}
	
	public void assertIsG1GroupElement(EObject node, Map<EObject, GroupType> groupTypes) {
		assertNodeGroupType(node, GroupType.G1, groupTypes);
	}
	
	public void assertIsG2GroupElement(EObject node, Map<EObject, GroupType> groupTypes) {
		assertNodeGroupType(node, GroupType.G2, groupTypes);
	}
	
	public void assertIsGTGroupElement(EObject node, Map<EObject, GroupType> groupTypes) {
		assertNodeGroupType(node, GroupType.GT, groupTypes);
	}
	
	public void assertNodeGroupType(EObject node, GroupType groupType, Map<EObject, GroupType> groupTypes) {
		GroupType trueGroupType = groupTypes.get(node);
		Assertions.assertTrue(
			trueGroupType == groupType,
			"Expected node type is " + groupType + ", actual group type is " + trueGroupType
		);
	}
	
	
	
}
