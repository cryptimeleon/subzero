package org.cryptimeleon.subzero.generator

import java.math.BigInteger
import java.util.ArrayList
import java.util.Deque
import java.util.LinkedList
import java.util.List
import java.util.Map
import java.util.Set
import java.util.stream.Collectors
import org.cryptimeleon.craco.protocols.CommonInput
import org.cryptimeleon.craco.protocols.SecretInput
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendFirstValue
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder
import org.cryptimeleon.math.expressions.bool.BooleanExpression
import org.cryptimeleon.math.serialization.Representation
import org.cryptimeleon.math.structures.groups.GroupElement
import org.cryptimeleon.math.structures.groups.elliptic.BilinearMap
import org.cryptimeleon.math.structures.rings.zn.Zn.ZnElement
import org.cryptimeleon.math.structures.rings.zn.Zp
import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement
import org.cryptimeleon.subzero.builder.ClassBuilder
import org.cryptimeleon.subzero.builder.CodeBuilder
import org.cryptimeleon.subzero.builder.ConstructorBuilder
import org.cryptimeleon.subzero.builder.FieldBuilder
import org.cryptimeleon.subzero.builder.MethodBuilder
import org.cryptimeleon.subzero.builder.SourceBuilder
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.model.ModelMap
import org.cryptimeleon.subzero.model.Type
import org.cryptimeleon.subzero.subzero.Comparison
import org.cryptimeleon.subzero.subzero.Conjunction
import org.cryptimeleon.subzero.subzero.Disjunction
import org.eclipse.emf.ecore.EObject

import static org.cryptimeleon.subzero.builder.Modifier.*
import org.cryptimeleon.math.structures.groups.Group
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrGroupElemVariable
import org.cryptimeleon.subzero.subzero.FunctionDefinition
import org.cryptimeleon.subzero.subzero.Parameter
import org.cryptimeleon.subzero.subzero.Variable
import org.cryptimeleon.subzero.subzero.WitnessVariable
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SchnorrFragment
import org.cryptimeleon.subzero.subzero.FunctionCall
import org.cryptimeleon.subzero.subzero.Brackets

/**
 * Generates the protocol class that specifies the protocol
 */
class ProtocolClassGenerator extends ClassGenerator {
	
	AugmentedModel augmentedModel;
	boolean hasRangeProof;
	boolean hasPairing;
	boolean hasOrProof;
	boolean hasOrDescendantOfAnd;
	
	// TODO: remove these temp classes
	private static class ZnChallengeSpace {}
	private static class ChallengeSpace {}
	private static class ProofOfPartialKnowledge {}
	private static class ProtocolTree {}
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
		this.hasRangeProof = augmentedModel.hasRangeProof();
		this.hasPairing = augmentedModel.hasPairing();
		this.hasOrProof = augmentedModel.hasOrProof();
		this.hasOrDescendantOfAnd = augmentedModel.hasOrDescendantOfAnd();
	}
	
	override SourceBuilder generate() {
		val ClassBuilder protocolClass = buildClass();
		val String packageName = augmentedModel.getPackageName();
		val SourceBuilder protocolSource = new SourceBuilder(packageName, protocolClass);
		protocolSource.setImports(buildProtocolImports());
		return protocolSource;
	}
	
	def ClassBuilder buildClass() {
		val String protocolClassName = augmentedModel.getProtocolName();
		val String commonInputClassName = GenerationHelper.createCommonInputClassName(protocolClassName);
		val String secretInputClassName = GenerationHelper.createSecretInputClassName(protocolClassName);
		val String publicParametersClassName = GenerationHelper.createPublicParametersClassName(protocolClassName);
		
		val EObject rootNode = augmentedModel.getModel().getProof();
		val boolean requiresPublicParameterClass = augmentedModel.requiresPublicParametersClass()
		
		val Map<EObject, Type> nodeTypes = augmentedModel.getTypes();
		
		val List<String> witnessNames = augmentedModel.getSortedWitnessNames();
		val Map<String, Type> witnessTypes = augmentedModel.getWitnessTypes();
		
		val List<String> variableNames = augmentedModel.getSortedConstantVariableNames();
		val Map<String, Type> variableTypes = augmentedModel.getConstantVariableTypes();
		
		val List<String> sortedPublicParameterNames = augmentedModel.getSortedPublicParameterNames();
		val Set<String> publicParameterNames = augmentedModel.getPublicParameterNames();
		val Map<String, Type> publicParameterTypes = augmentedModel.getPublicParameterTypes();
		
		val Map<String, List<FunctionCall>> userFunctionCalls = augmentedModel.getUserFunctionCallNodes();
		
		val Class<?> groupClass = augmentedModel.getGroupClass();
		val groupName = GenerationHelper.convertClassToVariableName(groupClass);
		
		val ProofGenerator proofGenerator = new ProofGenerator(augmentedModel);
		
		var ClassBuilder protocolClass;
		if (hasOrProof) {
			protocolClass = new ClassBuilder(PUBLIC, protocolClassName, ProofOfPartialKnowledge);
		} else {
			protocolClass = buildSubprotocolClass(
				protocolClassName, commonInputClassName, secretInputClassName, groupClass, witnessNames, witnessTypes, proofGenerator, rootNode
			);
		}
		
		// Build fields
		if (requiresPublicParameterClass) {
			val FieldBuilder ppClassField = new FieldBuilder(PROTECTED, publicParametersClassName, "pp");
			protocolClass.addField(ppClassField);
		}
		
		val FieldBuilder groupField = new FieldBuilder(PROTECTED, groupClass, groupName);
		protocolClass.addField(groupField);
		
		val FieldBuilder zpField = new FieldBuilder(PROTECTED, Zp, "zp");
		protocolClass.addField(zpField);
		
		if (hasPairing) {
			val FieldBuilder eField = new FieldBuilder(PROTECTED, BilinearMap, "e");
			protocolClass.addField(eField);
		}
		
		for (String publicParameterName : sortedPublicParameterNames) {
			val FieldBuilder ppField = new FieldBuilder(
				PROTECTED, FINAL, publicParameterTypes.get(publicParameterName).getTypeClass(),
				GenerationHelper.convertToJavaName(publicParameterName)
			);
			protocolClass.addField(ppField);
		}
		
		// Build constructor
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(groupClass, groupName);
		if (requiresPublicParameterClass) constructor.addParameter(publicParametersClassName, "pp");
		for (String publicParameterName : sortedPublicParameterNames) {
			val String javaPublicParameterName = GenerationHelper.convertToJavaName(publicParameterName);
			constructor.addParameter(publicParameterTypes.get(publicParameterName).getTypeClass(), javaPublicParameterName);
		}
		
		val String constructorBody = '''
			«IF requiresPublicParameterClass»
			this.pp = pp;
			«ENDIF»
			this.«groupName» = «groupName»;
			this.zp = (Zp) this.«groupName».getZn();
			«IF hasPairing»
			this.e = «groupName».getBilinearMap();
			«ENDIF»
			«FOR String publicParameterName : sortedPublicParameterNames»
			this.«GenerationHelper.convertToJavaName(publicParameterName)» = «GenerationHelper.convertToJavaName(publicParameterName)»;
			«ENDFOR»
		''';
		constructor.addBody(constructorBody);
		protocolClass.addConstructor(constructor);
		
		// Build user defined functions
		val List<MethodBuilder> userFunctions = buildUserFunctions(nodeTypes, witnessTypes, userFunctionCalls, commonInputClassName, proofGenerator);
		for (MethodBuilder method : userFunctions) {
			protocolClass.addMethod(method);
		}
		
		// Build common input class
		val ClassBuilder commonInputClass = buildCommonInputClass(commonInputClassName, publicParameterNames, variableNames, variableTypes);
		protocolClass.addInnerClass(commonInputClass);
		
		// Build secret input class
		val ClassBuilder secretInputClass = buildSecretInputClass(secretInputClassName, witnessNames, witnessTypes);
		protocolClass.addInnerClass(secretInputClass);
		
		
		if (hasOrProof) {
			val CodeBuilder protocolTreeBuilder = new CodeBuilder();
			val List<EObject> subtreeRootNodes = new ArrayList<EObject>();
			val List<String> subprotocolNames = new ArrayList<String>();
			createProtocolTree(rootNode, protocolTreeBuilder, subtreeRootNodes, subprotocolNames);
			
			val MethodBuilder provideProtocolTreeMethod = buildProvideProtocolTreeMethod(commonInputClassName, protocolTreeBuilder.toString());
			val MethodBuilder restoreSendFirstValueMethod = buildRestoreSendFirstValueMethod(groupClass);
			val MethodBuilder simulateSendFirstValueMethod = buildSimulateSendFirstValueMethod(groupClass);
			val MethodBuilder provideAdditionalCheckMethod = buildProvideAdditionalCheckMethod();
			val MethodBuilder getChallengeSpaceMethod = buildGetChallengeSpaceMethod(false);
			
			val Deque<ClassBuilder> subprotocolClasses = new LinkedList<ClassBuilder>();
			for (var int i = 0; i < subtreeRootNodes.size(); i++) {
				var String subprotocolName = subprotocolNames.get(i);
				var EObject subtreeRootNode = subtreeRootNodes.get(i);
				
				val ClassBuilder subprotocolClass = buildSubprotocolClass(
					subprotocolName, commonInputClassName, secretInputClassName, groupClass, witnessNames, witnessTypes, proofGenerator, subtreeRootNode
				);
				
				subprotocolClasses.addFirst(subprotocolClass);
			}
			
			val MethodBuilder provideProverSpecMethod = buildProvideProverSpecMethod(secretInputClassName, subprotocolNames, witnessNames, witnessTypes);
			
			protocolClass.addMethod(provideProtocolTreeMethod);
			protocolClass.addMethod(provideProverSpecMethod);
			protocolClass.addMethod(restoreSendFirstValueMethod);
			protocolClass.addMethod(simulateSendFirstValueMethod);
			protocolClass.addMethod(provideAdditionalCheckMethod);
			protocolClass.addMethod(getChallengeSpaceMethod);
			
			for (ClassBuilder subprotocolClass : subprotocolClasses) {
				protocolClass.prependInnerClass(subprotocolClass);
			}
		}
		
		if (hasOrDescendantOfAnd) {
			val ClassBuilder subprotocolCommonInputClass = buildSubprotocolCommonInputClass(commonInputClassName);
			val ClassBuilder subprotocolSecretInputClass = buildSubprotocolSecretInputClass(secretInputClassName);
			
			protocolClass.addInnerClass(subprotocolCommonInputClass);
			protocolClass.addInnerClass(subprotocolSecretInputClass);
		}

		return protocolClass;
	}
	
	def ClassBuilder buildSubprotocolClass(
		String subprotocolClassName,
		String commonInputClassName,
		String secretInputClassName,
		Class<?> groupClass,
		List<String> witnessNames,
		Map<String, Type> witnessTypes,
		ProofGenerator proofGenerator,
		EObject protocolRoot
	) {
		val ClassBuilder subprotocolClass = new ClassBuilder(PUBLIC, subprotocolClassName, DelegateProtocol);
		
		// Build proof expression		
		val String proof = proofGenerator.generate(protocolRoot);
		
		// Build subprotocol spec method
		val MethodBuilder provideSubprotocolSpecMethod = buildProvideSubprotocolSpecMethod(commonInputClassName, groupClass, witnessNames, witnessTypes, proof);

		// Build prover spec method
		val MethodBuilder provideProverSpecWithNoSendFirstMethod = buildProvideProverSpecWithNoSendFirstMethod(secretInputClassName, witnessNames);
		
		// Build challenge space method
		val MethodBuilder getChallengeSpaceMethod = buildGetChallengeSpaceMethod(true);
		
		subprotocolClass.addMethod(provideSubprotocolSpecMethod);
		subprotocolClass.addMethod(provideProverSpecWithNoSendFirstMethod);
		subprotocolClass.addMethod(getChallengeSpaceMethod);
		
		return subprotocolClass;
	}
	
	def MethodBuilder buildProvideProverSpecMethod(String secretInputClassName, List<String> subprotocolNames, List<String> witnessNames, Map<String, Type> witnessTypes) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, ProverSpec, "provideProverSpec");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SecretInput, "secretInput");
		method.addParameter(ProverSpecBuilder, "builder");
		
		val List<String> exponentWitnessNames = witnessNames.stream()
			.filter([witnessName |  witnessTypes.get(witnessName) == Type.EXPONENT])
			.map([witnessName | "overallSecretInput." + GenerationHelper.convertToJavaName(witnessName)])
			.collect(Collectors.toList());
			
		var String exponentWitnessArguments = "";
		if (!exponentWitnessNames.isEmpty()) {
			exponentWitnessArguments = GenerationHelper.createCommaList(exponentWitnessNames) + ", ";
		}
			
		val String secretInput = hasOrDescendantOfAnd ? "subprotocolSecret" : "secretInput"
		
		val String methodBody = '''
			«secretInputClassName» overallSecretInput = («secretInputClassName») secretInput;
			
			«IF hasOrDescendantOfAnd»
			// Commit to all Zn variables
			ZnElement crossOrCommitmentRandomness = zp.getUniformlyRandomElement();
			GroupElement crossOrCommitment = pp.crossOrCommitmentBases.innerProduct(RingElementVector.of(«exponentWitnessArguments»crossOrCommitmentRandomness));
			
			// Send this commitment
			builder.setSendFirstValue(new SendFirstValue.AlgebraicSendFirstValue(crossOrCommitment));
			
			// Set up witnesses for subprotocols (which is the secret input for the whole protocol plus the commitment randomness)
			SecretInput subprotocolSecret = new SubprotocolSecretInput(overallSecretInput, crossOrCommitmentRandomness);
			«ELSE»
			// Send this commitment
			builder.setSendFirstValue(SendFirstValue.EMPTY);
			
			// Set up witnesses for subprotocols
			«ENDIF»
			
			«FOR String subprotocolName : subprotocolNames»
			builder.putSecretInput("«subprotocolName»", «secretInput»);
			«ENDFOR»

			return builder.build();
		''';
		method.addBody(methodBody);
		
		return method;
	}
	
	def MethodBuilder buildProvideSubprotocolSpecMethod(String commonInputClassName, Class<?> groupClass, List<String> witnessNames, Map<String, Type> witnessTypes, String proof) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, SubprotocolSpec, "provideSubprotocolSpec");
		method.setOverride();
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SubprotocolSpecBuilder, "subprotocolSpecBuilder");

		val String groupUsed = (groupClass == Group) ? "group" : "bilinearGroup.getG1()";
		
		val List<String> exponentWitnessNames = witnessNames.stream()
			.filter([witnessName |  witnessTypes.get(witnessName) == Type.EXPONENT])
			.map([witnessName | GenerationHelper.createWitnessName(witnessName)])
			.collect(Collectors.toList());
		
		var String exponentWitnessArguments = "";
		if (!exponentWitnessNames.isEmpty()) {
			exponentWitnessArguments = GenerationHelper.createCommaList(exponentWitnessNames) + ", ";
		}
		
		val String methodBody = '''
			«IF hasOrDescendantOfAnd»
			«commonInputClassName» «GenerationHelper.INPUT_VARIABLE» = ((SubprotocolCommonInput) commonInput).commonInput;
			«ELSE»
			«commonInputClassName» «GenerationHelper.INPUT_VARIABLE» = («commonInputClassName») commonInput;
			«ENDIF»
			
			//Add variables (witnesses)
			«FOR String witnessName : witnessNames»
			«IF witnessTypes.get(witnessName) == Type.EXPONENT»
			SchnorrZnVariable «GenerationHelper.createWitnessName(witnessName)» = subprotocolSpecBuilder.addZnVariable("«witnessName»", zp);
			«ELSE»
			SchnorrGroupElemVariable «GenerationHelper.createWitnessName(witnessName)» = subprotocolSpecBuilder.addGroupElemVariable("«witnessName»", «groupUsed»);
			«ENDIF»
			«ENDFOR»
			
			//Add statements
			«proof»
			
			«IF hasOrDescendantOfAnd»
			// Add proof that the same values are used in this subprotocol as in the others
			SchnorrZnVariable crossOrCommitmentRandomness = subprotocolSpecBuilder.addZnVariable("crossOrCommitmentRandomness", zp);
			subprotocolSpecBuilder.addSubprotocol("orProofConsistency",
				new LinearStatementFragment(
					pp.crossOrCommitmentBases.expr().innerProduct(ExponentExpressionVector.of(«exponentWitnessArguments»crossOrCommitmentRandomness))
						.isEqualTo(((SubprotocolCommonInput) commonInput).crossOrCommitment)
				)
			);
			«ENDIF»
			
			return subprotocolSpecBuilder.build();
		''';
		method.addBody(methodBody);
	
		return method;
	}
	
	def MethodBuilder buildProvideProverSpecWithNoSendFirstMethod(String secretInputClassName, List<String> witnessNames) {
		var MethodBuilder method;
		if (hasOrProof) {
			method = new MethodBuilder(PROTECTED, "SendThenDelegateFragment.ProverSpec", "provideProverSpecWithNoSendFirst");
		} else {
			method = new MethodBuilder(PROTECTED, ProverSpec, "provideProverSpecWithNoSendFirst");
		}
		
		method.setOverride();
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SecretInput, "secretInput");
		if (hasOrProof) {
			method.addParameter("SendThenDelegateFragment.ProverSpecBuilder", "proverSpecBuilder");
		} else {
			method.addParameter(ProverSpecBuilder, "proverSpecBuilder");
		}
		
		
		val String methodBody = '''
			«IF hasOrDescendantOfAnd»
			«secretInputClassName» witness = ((SubprotocolSecretInput) secretInput).secretInput;
			«ELSE»
			«secretInputClassName» witness = («secretInputClassName») secretInput;
			«ENDIF»

			«FOR String witnessName : witnessNames»
			proverSpecBuilder.putWitnessValue("«witnessName»", witness.«GenerationHelper.convertToJavaName(witnessName)»);
	        «ENDFOR»
			
			«IF hasOrDescendantOfAnd»
			proverSpecBuilder.putWitnessValue("crossOrCommitmentRandomness", ((SubprotocolSecretInput) secretInput).crossOrCommitmentRandomness);
			«ENDIF»
			
			return proverSpecBuilder.build();
		''';
		method.addBody(methodBody);

		return method;
	}
	
	// TODO: not needed
	def MethodBuilder buildGetChallengeSpaceSizeMethod() {
		val MethodBuilder method = new MethodBuilder(PUBLIC, BigInteger, "getChallengeSpaceSize");
		method.setOverride();
		
		method.addStatement("return zp.size();");
		
		return method;
	}
	
	def MethodBuilder buildGetChallengeSpaceMethod(boolean znChallengeSpace) {
		val Class<?> returnType = znChallengeSpace ? ZnChallengeSpace : ChallengeSpace;
		val MethodBuilder method = new MethodBuilder(PUBLIC, returnType, "getChallengeSpace");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		
		method.addStatement("return new ZnChallengeSpace(zp);");
		
		return method;
	}
	
	def ClassBuilder buildCommonInputClass(String commonInputClassName, Set<String> publicParameterNames, List<String> variableNames, Map<String, Type> variableTypes) {
		val ClassBuilder commonInputClass = new ClassBuilder(PUBLIC, STATIC, commonInputClassName, CommonInput);
		commonInputClass.addBasicConstructor(PUBLIC);
		
		for (String variableName : variableNames) {
			val String javaVariableName = GenerationHelper.convertToJavaName(variableName);
			val FieldBuilder variableField = new FieldBuilder(PUBLIC, FINAL, variableTypes.get(variableName).getTypeClass(), javaVariableName);
			commonInputClass.addField(variableField);
		}

		return commonInputClass;
	}
	
	def ClassBuilder buildSecretInputClass(String secretInputClassName, List<String> witnessNames, Map<String, Type> witnessTypes) {
		val ClassBuilder secretInputClass = new ClassBuilder(PUBLIC, STATIC, secretInputClassName, SecretInput);
		
		for (String witnessName : witnessNames) {
			val String javaWitnessName = GenerationHelper.convertToJavaName(witnessName);
			val FieldBuilder field = new FieldBuilder(PUBLIC, FINAL, witnessTypes.get(witnessName).getTypeClass(), javaWitnessName);
			secretInputClass.addField(field);
		}
		
		secretInputClass.addBasicConstructor(PUBLIC);
		
		return secretInputClass;
	}
	
	def ClassBuilder buildSubprotocolCommonInputClass(String commonInputClassName) {
		val ClassBuilder subprotocolCommonInputClass = new ClassBuilder(PRIVATE, STATIC, "SubprotocolCommonInput", CommonInput);
		
		val FieldBuilder commonInputField = new FieldBuilder(PUBLIC, FINAL, commonInputClassName, "commonInput");
		val FieldBuilder crossOrCommitmentField = new FieldBuilder(PUBLIC, FINAL, GroupElement, "crossOrCommitment");
		
		subprotocolCommonInputClass.addField(commonInputField);
		subprotocolCommonInputClass.addField(crossOrCommitmentField);
		
		subprotocolCommonInputClass.addBasicConstructor(PUBLIC);
		
		return subprotocolCommonInputClass;
	}
	
	def ClassBuilder buildSubprotocolSecretInputClass(String secretInputClassName) {
		val ClassBuilder subprotocolSecretInputClass = new ClassBuilder(PRIVATE, STATIC, "SubprotocolSecretInput", SecretInput);
		
		val FieldBuilder secretInputField = new FieldBuilder(PUBLIC, FINAL, secretInputClassName, "secretInput");
		val FieldBuilder randomnessField = new FieldBuilder(PUBLIC, FINAL, ZnElement, "crossOrCommitmentRandomness");
		
		subprotocolSecretInputClass.addField(secretInputField);
		subprotocolSecretInputClass.addField(randomnessField);
		
		subprotocolSecretInputClass.addBasicConstructor(PUBLIC);
		
		return subprotocolSecretInputClass;
	}
	
	def String buildProtocolImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.ZnChallengeSpace;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearExponentStatementFragment;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrGroupElemVariable;
			import org.cryptimeleon.math.serialization.Representation;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.rings.zn.Zn.ZnElement;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SchnorrFragment;
			«IF hasRangeProof»
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
			«ENDIF»
			«IF hasPairing»
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearMap;
			«ENDIF»
			«IF hasRangeProof || hasPairing»
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			«ENDIF»
			«IF hasOrProof»
			import org.cryptimeleon.craco.protocols.arguments.sigma.ChallengeSpace;
			import org.cryptimeleon.craco.protocols.arguments.sigma.partial.ProofOfPartialKnowledge;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendFirstValue;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment;
			import org.cryptimeleon.math.expressions.bool.BooleanExpression;
			«ELSE»
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
			«ENDIF»
			«IF hasOrDescendantOfAnd»
			import org.cryptimeleon.math.structures.cartesian.ExponentExpressionVector;
			import org.cryptimeleon.math.structures.rings.cartesian.RingElementVector;
			«ENDIF»
			«IF !augmentedModel.getModel().getFunctions().isEmpty()»
			import org.cryptimeleon.math.expressions.exponent.ExponentExpr;
			import org.cryptimeleon.math.expressions.group.GroupElementExpression;
			«ENDIF»
		''';
		
		return GenerationHelper.organizeImports(imports); 
	}
	
	def MethodBuilder buildRestoreSendFirstValueMethod(Class<?> groupClass) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, SendFirstValue, "restoreSendFirstValue");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(Representation, "repr");
		
		val String groupUsed = (groupClass == Group) ? "group" : "bilinearGroup.getG1()";
		
		if (hasOrDescendantOfAnd) {
			method.addStatement('''return new SendFirstValue.AlgebraicSendFirstValue(repr, «groupUsed»);''');
		} else {
			method.addStatement("return SendFirstValue.EMPTY;");
		}

		return method; 
	}
	
	def MethodBuilder buildSimulateSendFirstValueMethod(Class<?> groupClass) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, SendFirstValue, "simulateSendFirstValue");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		
		val String groupUsed = (groupClass == Group) ? "group" : "bilinearGroup.getG1()";
		
		if (hasOrDescendantOfAnd) {
			method.addStatement('''return new SendFirstValue.AlgebraicSendFirstValue(«groupUsed».getUniformlyRandomElement());''');
		} else {
			method.addStatement("return SendFirstValue.EMPTY;");
		}
		
		
		return method;
	}
	
	def MethodBuilder buildProvideAdditionalCheckMethod() {
		val MethodBuilder method = new MethodBuilder(PROTECTED, BooleanExpression, "provideAdditionalCheck");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SendFirstValue, "sendFirstValue");
		
		method.addStatement("return BooleanExpression.TRUE;");
		
		return method;
	}
	
	def MethodBuilder buildProvideProtocolTreeMethod(String commonInputClassName, String protocolTree) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, ProtocolTree, "provideProtocolTree");
		method.setOverride();
		
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SendFirstValue, "sendFirstValue");
		
		if (hasOrDescendantOfAnd) {
			method.addStatement('''SubprotocolCommonInput subprotocolCommonInput = new SubprotocolCommonInput((«commonInputClassName») commonInput, ((SendFirstValue.AlgebraicSendFirstValue) sendFirstValue).getGroupElement(0));''');
		}
		
		method.addBody('''return «protocolTree»;''');
		return method;
	}
	
	def void createProtocolTree(EObject root, CodeBuilder builder, List<EObject> subtreeRootNodes, List<String> subprotocolNames) {
		createProtocolTreeHelper(root, builder, subtreeRootNodes, subprotocolNames);
	}
	
	// Returns true if the current subtree contains a disjunction
	def void createProtocolTreeHelper(EObject node, CodeBuilder builder, List<EObject> subtreeRootNodes, List<String> subprotocolNames) {
	
		if (node instanceof Disjunction) {
			builder.append("or");
			builder.openParen();
			builder.newLine();
			builder.indent();
			createProtocolTreeHelper(node.getLeft(), builder, subtreeRootNodes, subprotocolNames);
			builder.append(",");
			builder.newLine();
			createProtocolTreeHelper(node.getRight(), builder, subtreeRootNodes, subprotocolNames);
			builder.outdent();
			builder.newLine();
			builder.closeParen();
			
		} else if (node instanceof Conjunction) {
			val EObject leftNode = node.getLeft();
			val EObject rightNode = node.getRight();
			
			val boolean leftContainsOr = ModelMap.preorderAny(leftNode, [EObject child |
				return child instanceof Disjunction;
			]);

			val boolean rightContainsOr = ModelMap.preorderAny(rightNode, [EObject child |
				return child instanceof Disjunction;
			]);


			if (leftContainsOr || rightContainsOr) {
				val leftBuilder = new CodeBuilder(builder);
				val rightBuilder = new CodeBuilder(builder);
				leftBuilder.indent();
				rightBuilder.indent();

				createProtocolTreeHelper(leftNode, leftBuilder, subtreeRootNodes, subprotocolNames);
				createProtocolTreeHelper(rightNode, rightBuilder, subtreeRootNodes, subprotocolNames);

				builder.append("and");
				builder.openParen();
				builder.newLine();
				builder.indent();
				builder.append(leftBuilder);
				builder.append(",");
				builder.newLine();
				builder.append(rightBuilder);
				builder.newLine();
				builder.outdent();
				builder.closeParen();
			} else {
				val String subprotocolName = createProtocolLeaf(node, builder, subtreeRootNodes.size());
				subtreeRootNodes.add(node);
				subprotocolNames.add(subprotocolName);
			}
			
		} else if (node instanceof Brackets) {
			createProtocolTreeHelper(node.getContent(), builder, subtreeRootNodes, subprotocolNames);
		} else {
			val String subprotocolName = createProtocolLeaf(node, builder, subtreeRootNodes.size());
			subtreeRootNodes.add(node);
			subprotocolNames.add(subprotocolName);
		}
	}
	
	def String createProtocolLeaf(EObject node, CodeBuilder builder, int subprotocolCount) {
		var String subprotocolName = null;
		
		if (node instanceof Comparison) {
			subprotocolName = node.getSubprotocolName();
		}
		
		if (subprotocolName === null) {
			subprotocolName = "Subprotocol" + (subprotocolCount+1);
		} else {
			subprotocolName = subprotocolName.substring(1, subprotocolName.length()-1);
		}
		
		val String subprotocolClassName = GenerationHelper.convertToClassName(subprotocolName);
		val String commonInput = hasOrDescendantOfAnd ? "subprotocolCommonInput" : "commonInput";
		builder.append('''leaf("«subprotocolClassName»", new «subprotocolClassName»(), «commonInput»)''');
		
		return subprotocolClassName;
	}
	
	// Generates the Java equivalent of all user defined functions
	def List<MethodBuilder> buildUserFunctions(
		Map<EObject, Type> nodeTypes,
		Map<String, Type> witnessTypes,
		Map<String, List<FunctionCall>> userFunctionCalls,
		String commonInputClassName,
		ProofGenerator proofGenerator
	) {
		val List<MethodBuilder> methods = new ArrayList<MethodBuilder>();
		
		for (FunctionDefinition function : augmentedModel.getModel().getFunctions()) {
			val String name = function.getName();
			
			if (userFunctionCalls.containsKey(name) && !function.isInline()) {
				val EObject body = function.getBody();
				val Type returnType = nodeTypes.get(function);
				val Class<?> returnTypeClass = returnType.getTypeExprClass();
				
				var MethodBuilder method;
				if (returnType == Type.GROUP_ELEMENT || returnType == Type.EXPONENT) {
					method = new MethodBuilder(PRIVATE, returnTypeClass, name);
	
					var String statement = proofGenerator.generate(body);
					
					if (body instanceof Variable && !(body instanceof WitnessVariable)) {
						if (returnType == Type.GROUP_ELEMENT) {
							// GroupElement does not implement GroupElementExpression, so .expr() is necessary
							// SchnorrGroupElemVariable does implement GroupElementExpression, so .expr() is not needed
							statement += ".expr()";
						} else if (returnType == Type.EXPONENT) {
							// ZpElement does not implement ExponentExpr, so .asExponentExpression() is necessary
							// SchnorrZnVariable does implement ExponentExpr, so .asExponentExpression() is not needed
							statement += ".asExponentExpression()";
						}
					}
					
					method.addBody('''return «statement»;''');
				} else if (returnType == Type.BOOLEAN) {
					var String statement = proofGenerator.generate(function);
					
					if (body instanceof Conjunction) {
						method = new MethodBuilder(PRIVATE, void, name);
						method.addParameter(SubprotocolSpecBuilder, "subprotocolSpecBuilder");
						method.addParameter(String, GenerationHelper.SUBPROTOCOL_VARIABLE);
						
						method.addBody(statement);
					} else if (body instanceof Comparison) {
						method = new MethodBuilder(PRIVATE, SchnorrFragment, name);
						statement = statement.substring(statement.indexOf("new"), statement.indexOf("\n);"));
						method.addBody('''return «statement»;''');
					}
				}
				
				// If the function has a constant variable anywhere, add the CommonInput variable as a parameter
				if (augmentedModel.userFunctionHasConstant(name)) {
					method.addParameter(commonInputClassName, GenerationHelper.INPUT_VARIABLE);
				}
				
				// If the function has witness variables anywhere, add each witness variable as a parameter
				val Set<String> functionWitnesses = augmentedModel.getUserFunctionWitnessNames(name);
				if (functionWitnesses !== null) {
					for (String witnessName : functionWitnesses) {
						val Class<?> parameterTypeClass = witnessTypes.get(witnessName).getWitnessTypeClass();
						method.addParameter(parameterTypeClass, GenerationHelper.createWitnessName(witnessName));
					}
				}
				
				// Add all user defined parameters for the function
				for (Parameter parameter : function.getParameterList().getParameters()) {
					val Class<?> parameterTypeClass = nodeTypes.get(parameter).getTypeExprClass();
					method.addParameter(parameterTypeClass, parameter.getName());
				}
				
				methods.add(method);
			}
		}
		
		return methods;
	}

}