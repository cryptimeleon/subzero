package org.cryptimeleon.subzero.java;

import org.cryptimeleon.craco.protocols.CommonInput;
import org.cryptimeleon.craco.protocols.SecretInput;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
import org.cryptimeleon.math.structures.groups.Group;
import org.cryptimeleon.math.structures.groups.GroupElement;
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
import org.cryptimeleon.math.structures.rings.zn.Zp;
import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;
import org.cryptimeleon.subzero.builder.ClassBuilder;
import org.cryptimeleon.subzero.builder.ImportBuilder;
import org.cryptimeleon.subzero.builder.MethodBuilder;
import org.cryptimeleon.subzero.builder.SourceBuilder;
import org.cryptimeleon.subzero.generator.ClassGenerator;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.model.GroupType;
import org.cryptimeleon.subzero.model.Type;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.cryptimeleon.subzero.builder.Modifier.PUBLIC;

/**
 * Generates the LibraryTest class that will run the protocol
 */
class TestClassGenerator implements ClassGenerator {
	
	AugmentedModel augmentedModel;
	boolean hasRangeProof;
	boolean hasPairing;
	boolean hasOrDescendantOfAnd;
	Class<?> groupClass;
	
	// Declared as an extension variable to allow classObject.use() to be
	// written instead of importBuilder.use(classObject);
	extension ImportBuilder importBuilder;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		hasRangeProof = augmentedModel.hasRangeProof();
		hasPairing = augmentedModel.hasPairing();
		hasOrDescendantOfAnd = augmentedModel.hasOrDescendantOfAnd();
		groupClass = augmentedModel.getGroupClass();
		importBuilder = new ImportBuilder();
	}
	
	override SourceBuilder generate() {
		val String packageName = augmentedModel.getPackageName();
		val ClassBuilder testClass = buildClass();
		
		// These classes cannot be imported in this class, so add them as a manually (by string) instead
		"org.junit.Test".use();
		"org.junit.Assert.assertTrue".useStatic();
		
		val SourceBuilder testSource = new SourceBuilder(packageName, testClass, importBuilder);

		return testSource;
	}
	
	def private ClassBuilder buildClass() {
		// Fetch all model info needed for generation
		val String protocolClassName = augmentedModel.getProtocolName();
		val String commonInputClassName = GenerationUtils.createCommonInputClassName(protocolClassName);
		val String secretInputClassName = GenerationUtils.createSecretInputClassName(protocolClassName);
		val String publicParametersClassName = GenerationUtils.createPublicParametersClassName(protocolClassName);
		
		val Map<String, GroupType> groups = augmentedModel.getGroups();

		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		val Set<String> constrainedWitnessNames = augmentedModel.getConstrainedWitnessNames();
		
		val List<String> publicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		
		val List<String> constantNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> constantTypes = augmentedModel.getConstantVariableTypes();
		
		// Code generation
		val ClassBuilder testClass = new ClassBuilder(PUBLIC, "LibraryTest");

		val MethodBuilder testMethod = new MethodBuilder(PUBLIC, void, "protocolTest");		
		testMethod.setTest();
		
		val groupVariableName = GenerationUtils.convertClassToVariableName(groupClass);
		var String groupInstance;
		var String defaultGroup;
		
		if (groupClass == BilinearGroup) {
			groupInstance = '''new «BarretoNaehrigBilinearGroup.use()»(80)''';
			defaultGroup = "groupG1";
		} else {
			groupInstance = '''new «Secp256k1.use()»()''';
			defaultGroup = groupVariableName;
		}
		
		val StringBuilder witnessesBuilder = new StringBuilder();
		val StringBuilder publicParametersBuilder = new StringBuilder();
		val StringBuilder constantsBuilder = new StringBuilder();
		
		// Build initialization statements for all witnesses
		for (String witnessName : witnessNames) {
			val String javaWitnessName = GenerationUtils.createWitnessName(witnessName);
			if (witnessTypes.get(witnessName) == Type.EXPONENT) {
				if (constrainedWitnessNames.contains(witnessName)) {
					witnessesBuilder.append('''«ZpElement.use()» «javaWitnessName» = zp.valueOf(0); // Change this value so that it satisfies all constraints on the witness''')
				} else {
					witnessesBuilder.append('''«ZpElement.use()» «javaWitnessName» = zp.getUniformlyRandomElement();''');
				}
			} else {
				witnessesBuilder.append('''«GroupElement.use()» «javaWitnessName» = «defaultGroup».getUniformlyRandomElement();''');
			}
			witnessesBuilder.append('\n');
		}
		
		// Build initialization statements for all public parameters
		for (String publicParameterName : publicParameterNames) {
			createVariableInitialization(publicParameterName, publicParameterTypes, groups, defaultGroup, publicParametersBuilder);
		}
		
		// Build initialization statements for all constants
		for (String constantName : constantNames) {
			createVariableInitialization(constantName, constantTypes, groups, defaultGroup, constantsBuilder);
		}
		
		val String witnesses = witnessesBuilder.toString();
		val String publicParameters = publicParametersBuilder.toString();
		val String constants = constantsBuilder.toString();
		
		// Create a comma-delimited list of all public parameter variables
		var String publicParameterArguments = "";
		if (!publicParameterNames.isEmpty()) {
			publicParameterArguments = ", " + GenerationUtils.createCommaList(
				publicParameterNames.stream()
				.map(name | GenerationUtils.convertToJavaName(name))
				.collect(Collectors.toList())
			);
		}
		
		// Create a comma-delimited list of all common input variables
		val String commonInputArguments = GenerationUtils.createCommaList(
			constantNames.stream()
			.map(name | GenerationUtils.convertToJavaName(name))
			.collect(Collectors.toList())
		);
		// Create a comma-delimited list of all witnesses
		val String secretInputArguments = GenerationUtils.createCommaList(
			witnessNames.stream()
			.map(name | GenerationUtils.convertToJavaName(name))
			.collect(Collectors.toList())
		);
		
		val String methodBody = '''
			«groupClass.use()» «groupVariableName» = «groupInstance»;
			«IF hasRangeProof || hasOrDescendantOfAnd»
			«publicParametersClassName» pp = «publicParametersClassName».generateNewParameters(«groupVariableName»);
			«ENDIF»
			«IF hasRangeProof || hasPairing»
			«Group.use()» groupG1 = bilinearGroup.getG1();
			«ENDIF»
			«IF hasPairing»
			«Group.use()» groupG2 = bilinearGroup.getG2();
			«Group.use()» groupGT = bilinearGroup.getGT();
			«ENDIF»
			«Zp.use()» zp = (Zp) «defaultGroup».getZn();
			
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
			
			«CommonInput.use()» commonInput = new «protocolClassName».«commonInputClassName»(«commonInputArguments»);
			«SecretInput.use()» secretInput = new «protocolClassName».«secretInputClassName»(«secretInputArguments»);
			
			«SigmaProtocolProverInstance.use()» prover = protocol.getProverInstance(commonInput, secretInput);
			«SigmaProtocolVerifierInstance.use()» verifier = protocol.getVerifierInstance(commonInput);
			
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
	def private void createVariableInitialization(String variableName, Map<String, Type> variableTypes, Map<String, GroupType> variableGroups, String defaultGroup, StringBuilder builder) {
		val String javaVariableName = GenerationUtils.convertToJavaName(variableName);
		val Type variableType = variableTypes.get(variableName);
		val Class<?> variableTypeClass = variableType.getTypeClass();
		
		if (variableType === Type.EXPONENT) {
			// Exponent variable
			builder.append('''«variableTypeClass.use()» «javaVariableName» = zp.getZeroElement();''');
		} else {
			// Group element variable
			var String variableGroup;
			if (hasPairing && variableGroups.containsKey(variableName)) {
				variableGroup = "group" + variableGroups.get(variableName).toString();
			} else {
				variableGroup = defaultGroup;
			}
			
			builder.append('''«variableTypeClass.use()» «javaVariableName» = «variableGroup».getNeutralElement();''');
		}
		builder.append('\n');
	}
	
}