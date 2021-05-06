package org.cryptimeleon.zeroknowledge.generator

import java.util.Map
import java.util.Map.Entry
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters
import org.cryptimeleon.math.serialization.Representation
import org.cryptimeleon.math.serialization.StandaloneRepresentable
import org.cryptimeleon.math.structures.groups.Group
import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup
import org.cryptimeleon.zeroknowledge.builder.ClassBuilder
import org.cryptimeleon.zeroknowledge.builder.ConstructorBuilder
import org.cryptimeleon.zeroknowledge.builder.FieldBuilder
import org.cryptimeleon.zeroknowledge.builder.MethodBuilder
import org.cryptimeleon.zeroknowledge.builder.SourceBuilder
import org.cryptimeleon.zeroknowledge.model.AugmentedModel
import org.cryptimeleon.zeroknowledge.model.Type

import static org.cryptimeleon.zeroknowledge.builder.Modifier.*

/**
 * Generates the public parameters class
 */
class PublicParametersClassGenerator {
	
	AugmentedModel augmentedModel;
	String protocolName;
	boolean hasRangeProof;
	boolean hasPairing;
	boolean hasOrDescendantOfAnd;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		this.protocolName = augmentedModel.getProtocolName();
		this.hasRangeProof = augmentedModel.hasRangeProof();
		this.hasPairing = augmentedModel.hasPairing();
		this.hasOrDescendantOfAnd = augmentedModel.hasOrDescendantOfAnd();
	}
	
	def SourceBuilder generate() {
		val String packageName = augmentedModel.getPackageName();
		val ClassBuilder publicParametersClass = buildClass();
		val SourceBuilder publicParametersSource = new SourceBuilder(packageName, publicParametersClass);
		publicParametersSource.setImports(buildImports());
	
		return publicParametersSource;
	}

	def buildClass() {
		val String className = GenerationHelper.createPublicParametersClassName(protocolName);
		val ClassBuilder publicParametersClass = new ClassBuilder(PUBLIC, className, StandaloneRepresentable);
		
		var Class<?> groupClass;
		var String groupName;
		var String groupUsed;
		if (hasRangeProof) {
			groupClass = BilinearGroup;
			groupName = "bilinearGroup";
			groupUsed = "bilinearGroup.getG1()"
		} else {
			groupClass = Group;
			groupName = "group";
			groupUsed = "group";
		}
		
		val FieldBuilder groupField = new FieldBuilder(PUBLIC, FINAL, groupClass, groupName);
		publicParametersClass.addField(groupField);
		
		if (hasRangeProof) {
			val FieldBuilder ppField = new FieldBuilder(PUBLIC, FINAL, SetMembershipPublicParameters, "rangeProofpp");
			publicParametersClass.addField(ppField);
		}
		
		if (hasOrDescendantOfAnd) {
			val FieldBuilder commitmentField = new FieldBuilder(PUBLIC, FINAL, GroupElementVector, "crossOrCommitmentBases");
			publicParametersClass.addField(commitmentField);
		}
		
		val ConstructorBuilder publicParametersConstructor = buildConstructor(groupClass, groupName, groupUsed);
		val MethodBuilder generateNewParametersMethod = buildGenerateNewParametersMethod(className, groupClass, groupName, groupUsed);
		val MethodBuilder getRepresentationMethod = buildGetRepresentationMethod(groupClass, groupName);
		
		publicParametersClass.addBasicConstructor();
		publicParametersClass.addConstructor(publicParametersConstructor);
		publicParametersClass.addMethod(generateNewParametersMethod);
		publicParametersClass.addMethod(getRepresentationMethod);
		
		return publicParametersClass;
	}
	
	
	def ConstructorBuilder buildConstructor(Class<?> groupClass, String groupName, String groupUsed) {
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(Representation, "repr");
		
		val String body = '''
			«groupName» = («groupClass.getSimpleName()») repr.obj().get("«groupName»").repr().recreateRepresentable();
			«IF hasRangeProof»
			rangeProofpp = new SetMembershipPublicParameters(«groupName», repr.obj().get("setMembershipPp"));
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			crossOrCommitmentBases = «groupUsed».restoreVector(repr.obj().get("commitmentBases"));
			«ENDIF»
		''';
		constructor.addBody(body);
		
		return constructor;
	}
	
	def MethodBuilder buildGenerateNewParametersMethod(String className, Class<?> groupClass, String groupName, String groupUsed) {
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		val MethodBuilder method = new MethodBuilder(PUBLIC, STATIC, className, "generateNewParameters");
		method.addParameter(groupClass, groupName);
		
		var int numberOfZnWitnesses = 0;
		for (Entry<String, Type> entry : witnessTypes.entrySet()) {
			if (entry.getValue() === Type.EXPONENT) numberOfZnWitnesses++;
		}
		
		val String body = '''
			«IF hasRangeProof»
			SetMembershipPublicParameters rangeProof1pp = TwoSidedRangeProof.generatePublicParameters(«groupName», 100);
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			int numberOfZnWitnesses = «numberOfZnWitnesses»;
			GroupElementVector crossOrCommitmentBases = «groupUsed».getUniformlyRandomNonNeutrals(numberOfZnWitnesses + 1);
			«ENDIF»
			return new «className»(«groupName»«IF hasRangeProof», rangeProof1pp«ENDIF»«IF hasOrDescendantOfAnd», crossOrCommitmentBases«ENDIF»);
		''';
		method.addBody(body);
		
		return method;
	}
	
	def MethodBuilder buildGetRepresentationMethod(Class<?> groupClass, String groupName) {
		val MethodBuilder method = new MethodBuilder(PUBLIC, Representation, "getRepresentation");
		method.setOverride();
		
		val String body = '''
			ObjectRepresentation repr = new ObjectRepresentation();
			repr.put("«groupName»", «groupName».getRepresentation());
			«IF hasRangeProof»
			repr.put("rangeProofpp", rangeProofpp.getRepresentation());
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			repr.put("commitmentBases", crossOrCommitmentBases.getRepresentation());
			«ENDIF»
			return repr;
		''';
		method.addBody(body);
		
		return method 
	}
	
	def String buildImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
			import org.cryptimeleon.math.serialization.ObjectRepresentation;
			import org.cryptimeleon.math.serialization.Representation;
			import org.cryptimeleon.math.serialization.StandaloneRepresentable;
			«IF hasRangeProof»
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
			«ENDIF»
			«IF hasRangeProof || hasPairing»
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector;
			«ENDIF»
		''';
		
		return GenerationHelper.organizeImports(imports);
	}
}