package org.cryptimeleon.subzero.java;

import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
import org.cryptimeleon.math.serialization.ObjectRepresentation;
import org.cryptimeleon.math.serialization.Representation;
import org.cryptimeleon.math.serialization.StandaloneRepresentable;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.cartesian.GroupElementVector;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.subzero.builder.ClassBuilder;
import org.cryptimeleon.subzero.builder.ConstructorBuilder;
import org.cryptimeleon.subzero.builder.FieldBuilder;
import org.cryptimeleon.subzero.builder.ImportBuilder;
import org.cryptimeleon.subzero.builder.MethodBuilder;
import org.cryptimeleon.subzero.builder.SourceBuilder;
import org.cryptimeleon.subzero.generator.ClassGenerator;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.Type;

import java.util.Map;
import java.util.Map.Entry;

import static org.cryptimeleon.subzero.builder.Modifier.FINAL;
import static org.cryptimeleon.subzero.builder.Modifier.PUBLIC;
import static org.cryptimeleon.subzero.builder.Modifier.STATIC;

/**
 * Generates the public parameters class
 */
class PublicParametersClassGenerator implements ClassGenerator {
	
	AugmentedModel augmentedModel;
	boolean hasRangeProof;
	boolean hasPairing;
	boolean hasOrDescendantOfAnd;
	
	// Declared as an extension variable to allow classObject.use() to be
	// written instead of importBuilder.use(classObject);
	extension ImportBuilder importBuilder;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		hasRangeProof = augmentedModel.hasRangeProof();
		hasPairing = augmentedModel.hasPairing();
		hasOrDescendantOfAnd = augmentedModel.hasOrDescendantOfAnd();
		importBuilder = new ImportBuilder();
	}
	
	override SourceBuilder generate() {
		val String packageName = augmentedModel.getPackageName();
		val ClassBuilder publicParametersClass = buildClass();
		val SourceBuilder publicParametersSource = new SourceBuilder(packageName, publicParametersClass, importBuilder);
	
		return publicParametersSource;
	}

	def private buildClass() {
		val String protocolName = augmentedModel.getProtocolName();
		val String className = GenerationUtils.createPublicParametersClassName(protocolName);
		val ClassBuilder publicParametersClass = new ClassBuilder(PUBLIC, className).implement(StandaloneRepresentable.use());
		
		var Class<?> groupClass;
		var String groupName;
		var String groupUsed;
		if (hasRangeProof || hasPairing) {
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
			val FieldBuilder ppField = new FieldBuilder(PUBLIC, FINAL, SetMembershipPublicParameters.use(), "rangeProofpp");
			publicParametersClass.addField(ppField);
		}
		
		if (hasOrDescendantOfAnd) {
			val FieldBuilder commitmentField = new FieldBuilder(PUBLIC, FINAL, GroupElementVector.use(), "crossOrCommitmentBases");
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
	
	
	def private ConstructorBuilder buildConstructor(Class<?> groupClass, String groupName, String groupUsed) {
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(Representation.use(), "repr");
		
		val String body = '''
			«groupName» = («groupClass.use()») repr.obj().get("«groupName»").repr().recreateRepresentable();
			«IF hasRangeProof»
			rangeProofpp = new «SetMembershipPublicParameters.use()»(«groupName», repr.obj().get("setMembershipPp"));
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			crossOrCommitmentBases = «groupUsed».restoreVector(repr.obj().get("commitmentBases"));
			«ENDIF»
		''';
		constructor.addBody(body);
		
		return constructor;
	}
	
	def private MethodBuilder buildGenerateNewParametersMethod(String className, Class<?> groupClass, String groupName, String groupUsed) {
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		val MethodBuilder method = new MethodBuilder(PUBLIC, STATIC, className, "generateNewParameters");
		method.addParameter(groupClass.use(), groupName);
		
		var int numberOfZnWitnesses = 0;
		for (Entry<String, Type> entry : witnessTypes.entrySet()) {
			if (entry.getValue() === Type.EXPONENT) numberOfZnWitnesses++;
		}
		
		val String body = '''
			«IF hasRangeProof»
			«SetMembershipPublicParameters.use()» rangeProof1pp = «TwoSidedRangeProof.use()».generatePublicParameters(«groupName», 100);
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			int numberOfZnWitnesses = «numberOfZnWitnesses»;
			«GroupElementVector.use()» crossOrCommitmentBases = «groupUsed».getUniformlyRandomNonNeutrals(numberOfZnWitnesses + 1);
			«ENDIF»
			return new «className»(«groupName»«IF hasRangeProof», rangeProof1pp«ENDIF»«IF hasOrDescendantOfAnd», crossOrCommitmentBases«ENDIF»);
		''';
		method.addBody(body);
		
		return method;
	}
	
	def private MethodBuilder buildGetRepresentationMethod(Class<?> groupClass, String groupName) {
		val MethodBuilder method = new MethodBuilder(PUBLIC, Representation.use(), "getRepresentation");
		method.setOverride();
		
		val String body = '''
			«ObjectRepresentation.use()» repr = new ObjectRepresentation();
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
	
}