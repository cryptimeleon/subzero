package org.cryptimeleon.subzero.generator

import java.util.List
import java.util.Map
import java.util.Set
import java.util.stream.Collectors
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup
import org.cryptimeleon.subzero.builder.ClassBuilder
import org.cryptimeleon.subzero.builder.MethodBuilder
import org.cryptimeleon.subzero.builder.SourceBuilder
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.model.GroupType
import org.cryptimeleon.subzero.model.Type

import static org.cryptimeleon.subzero.builder.Modifier.*
import org.cryptimeleon.math.structures.groups.Group

/**
 * Generates the LibraryTest class that will run the protocol
 */
class TestClassGenerator extends ClassGenerator {
	
	AugmentedModel augmentedModel;
	boolean hasRangeProof;
	boolean hasPairing;
	boolean hasOrDescendantOfAnd;
	Class<?> groupClass;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		this.hasRangeProof = augmentedModel.hasRangeProof();
		this.hasPairing = augmentedModel.hasPairing();
		this.hasOrDescendantOfAnd = augmentedModel.hasOrDescendantOfAnd();
		this.groupClass = augmentedModel.getGroupClass();
	}
	
	override SourceBuilder generate() {
		val String packageName = augmentedModel.getPackageName();
		
		val ClassBuilder testClass = buildClass();
		val SourceBuilder testSource = new SourceBuilder(packageName, testClass);
		testSource.setImports(buildImports());

		return testSource;
	}
	
	private def ClassBuilder buildClass() {
		// Fetch all model info needed for generation
		val String protocolClassName = augmentedModel.getProtocolName();
		val String commonInputClassName = GenerationHelper.createCommonInputClassName(protocolClassName);
		val String secretInputClassName = GenerationHelper.createSecretInputClassName(protocolClassName);
		val String publicParametersClassName = GenerationHelper.createPublicParametersClassName(protocolClassName);
		
		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		val Set<String> constrainedWitnessNames = augmentedModel.getConstrainedWitnessNames();
		
		val List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		val Map<String, GroupType> publicParameterGroups = augmentedModel.getPublicParameterGroups();
		
		val List<String> constantNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> constantTypes = augmentedModel.getConstantVariableTypes();
		val Map<String, GroupType> constantGroups = augmentedModel.getConstantVariableGroups();
		
		// Code generation
		val ClassBuilder testClass = new ClassBuilder(PUBLIC, "LibraryTest");

		val MethodBuilder testMethod = new MethodBuilder(PUBLIC, void, "protocolTest");		
		testMethod.setTest();
		
		val groupClassName = groupClass.getSimpleName();
		val groupVariableName = GenerationHelper.convertClassToVariableName(groupClass);
		var String groupInstance;
		var String defaultGroup;
		
		if (groupClass == BilinearGroup) {
			groupInstance = "new BarretoNaehrigBilinearGroup(80)";
			defaultGroup = "groupG1";
		} else {
			groupInstance = "new LazyGroup(new Secp256k1())";
			defaultGroup = groupVariableName;
		}
		
		val StringBuilder witnessesBuilder = new StringBuilder();
		val StringBuilder publicParametersBuilder = new StringBuilder();
		val StringBuilder constantsBuilder = new StringBuilder();
		
		// Build initialization statements for all witnesses
		for (String witnessName : witnessNames) {
			val String javaWitnessName = GenerationHelper.createWitnessName(witnessName);
			if (witnessTypes.get(witnessName) == Type.EXPONENT) {
				if (constrainedWitnessNames.contains(witnessName)) {
					witnessesBuilder.append('''ZpElement «javaWitnessName» = zp.valueOf(0); // Change this value so that it satisfies all constraints on the witness''')
				} else {
					witnessesBuilder.append('''ZpElement «javaWitnessName» = zp.getUniformlyRandomElement();''');
				}
			} else {
				witnessesBuilder.append('''GroupElement «javaWitnessName» = «defaultGroup».getUniformlyRandomElement();''');
			}
			witnessesBuilder.append('\n');
		}
		
		// Build initialization statements for all public parameters
		for (String publicParameterName : publicParameterNames) {
			createVariableInitialization(publicParameterName, publicParameterTypes, publicParameterGroups, defaultGroup, publicParametersBuilder);
		}
		
		// Build initialization statements for all constants
		for (String constantName : constantNames) {
			createVariableInitialization(constantName, constantTypes, constantGroups, defaultGroup, constantsBuilder);
		}
		
		val String witnesses = witnessesBuilder.toString();
		val String publicParameters = publicParametersBuilder.toString();
		val String constants = constantsBuilder.toString();
		
		// Create a comma-delimited list of all public parameter variables
		var String publicParameterArguments = "";
		if (!publicParameterNames.isEmpty()) {
			publicParameterArguments = ", " + GenerationHelper.createCommaList(
				publicParameterNames.stream()
				.map(name | GenerationHelper.convertToJavaName(name))
				.collect(Collectors.toList())
			);
		}
		
		// Create a comma-delimited list of all common input variables
		val String commonInputArguments = GenerationHelper.createCommaList(
			constantNames.stream()
			.map(name | GenerationHelper.convertToJavaName(name))
			.collect(Collectors.toList())
		);
		// Create a comma-delimited list of all witnesses
		val String secretInputArguments = GenerationHelper.createCommaList(
			witnessNames.stream()
			.map(name | GenerationHelper.convertToJavaName(name))
			.collect(Collectors.toList())
		);
		
		val String methodBody = '''
			«groupClassName» «groupVariableName» = «groupInstance»;
			«IF hasRangeProof || hasOrDescendantOfAnd»
			«publicParametersClassName» pp = «publicParametersClassName».generateNewParameters(«groupVariableName»);
			«ENDIF»
			«IF hasRangeProof || hasPairing»
			Group groupG1 = bilinearGroup.getG1();
			«ENDIF»
			«IF hasPairing»
			Group groupG2 = bilinearGroup.getG2();
			Group groupGT = bilinearGroup.getGT();
			«ENDIF»
			Zp zp = (Zp) «defaultGroup».getZn();
			
			«IF !publicParameters.isEmpty()»
			// Set public parameters
			«publicParameters»
			
			«ENDIF»
			// Set witnesses
			«witnesses»
			
			«IF !constants.isEmpty()»
			// Set constants
			«constants»
			
			«ENDIF»
			// Instantiate protocol and input
			«protocolClassName» protocol = new «protocolClassName»(«groupVariableName»«IF hasRangeProof || hasOrDescendantOfAnd», pp«ENDIF»«publicParameterArguments»);
			
			CommonInput commonInput = new «protocolClassName».«commonInputClassName»(«commonInputArguments»);
			SecretInput secretInput = new «protocolClassName».«secretInputClassName»(«secretInputArguments»);
			
			SigmaProtocolProverInstance prover = protocol.getProverInstance(commonInput, secretInput);
			SigmaProtocolVerifierInstance verifier = protocol.getVerifierInstance(commonInput);
			
			protocol.runProtocolLocally(prover, verifier);
			assertTrue(verifier.hasTerminated());
			assertTrue(verifier.isAccepting());
			if (verifier.isAccepting())
			    System.out.println("Yay, the protocol worked!");
		''';
		
		testMethod.addBody(methodBody);
		testClass.addMethod(testMethod);
		return testClass;
	}
	
	// Helper class for building initialization statements for variables
	private def void createVariableInitialization(String variableName, Map<String, Type> variableTypes, Map<String, GroupType> variableGroups, String defaultGroup, StringBuilder builder) {
		val String javaVariableName = GenerationHelper.convertToJavaName(variableName);
		val Type variableType = variableTypes.get(variableName);
		val String variableTypeClassName = variableType.getTypeClass().getSimpleName();
		
		if (variableType === Type.EXPONENT) {
			// Exponent variable
			builder.append('''«variableTypeClassName» «javaVariableName» = zp.getZeroElement();''');
		} else {
			// Group element variable
			var String variableGroup;
			if (hasPairing && variableGroups.containsKey(variableName)) {
				variableGroup = "group" + variableGroups.get(variableName).toString();
			} else {
				variableGroup = defaultGroup;
			}
			
			builder.append('''«variableTypeClassName» «javaVariableName» = «variableGroup».getNeutralElement();''');
		}
		builder.append('\n');
	}
	
	private def String buildImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;
			import org.junit.Test;
			import static org.junit.Assert.assertTrue;
			«IF groupClass == BilinearGroup»
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
			«ELSE»
			import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
			import org.cryptimeleon.math.structures.groups.lazy.LazyGroup;
			«ENDIF»
		''';
		
		return GenerationHelper.organizeImports(imports);
	}
	
}