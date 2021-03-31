package org.cryptimeleon.zeroknowledge.generator

import java.math.BigInteger
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.cryptimeleon.craco.protocols.CommonInput
import org.cryptimeleon.craco.protocols.SecretInput
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters
import org.cryptimeleon.math.serialization.Representation
import org.cryptimeleon.math.serialization.StandaloneRepresentable
import org.cryptimeleon.math.structures.groups.Group
import org.cryptimeleon.math.structures.groups.GroupElement
import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup
import org.cryptimeleon.math.structures.rings.zn.Zp
import org.cryptimeleon.zeroknowledge.model.AugmentedModel
import org.cryptimeleon.zeroknowledge.model.BranchState
import org.cryptimeleon.zeroknowledge.model.ModelHelper
import org.cryptimeleon.zeroknowledge.model.Type
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Brackets
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Conjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Disjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.LocalVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Power
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Product
import org.cryptimeleon.zeroknowledge.zeroKnowledge.StringLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Sum
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Tuple
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Variable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Witness
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessList
import org.cryptimeleon.zeroknowledge.zeroKnowledge.WitnessVariable
import org.eclipse.emf.ecore.EObject

import static org.cryptimeleon.zeroknowledge.generator.Modifier.*
import java.util.Map.Entry
import java.util.Comparator
import org.cryptimeleon.zeroknowledge.zeroKnowledge.ZeroKnowledgeFactory
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Expression

class CodeGenerator {
	
	var String generatedCode;
	
	String protocolName;
	int subprotocolCount;
	
	boolean hasRangeProof;
	boolean hasPairing;
	Class<?> groupClass;
	String groupName;
	String groupClassName;
	
	Map<EObject, Type> types;
	Map<EObject, Integer> sizes;
	
	String OPERATOR_ADDITION = "+";
	String OPERATOR_SUBTRACTION = "-";
	String OPERATOR_MULTIPLICATION = "*";
	String OPERATOR_DIVISION = "/";
	String OPERATOR_EQUAL = "=";
	String OPERATOR_INEQUAL = "!=";
	String OPERATOR_LESS = "<";
	String OPERATOR_GREATER = ">";
	String OPERATOR_LESSEQUAL = "<=";
	String OPERATOR_GREATEREQUAL = ">=";	
	
	String INPUT_VARIABLE = "input";
	String WITNESS_SUFFIX = "Var";
	
	new(Model model) {
		performGeneration(model, false);
	}
	
	new(Model model, boolean inlineFunctions) {
		performGeneration(model, inlineFunctions);
	}
	
	def String getCode() {
		return generatedCode;
	}
	
	def void performGeneration(Model model, boolean inlineFunctions) {
		val AugmentedModel augmentedModel = new AugmentedModel(model);
		
		System.out.println(augmentedModel);
		
		// If option is set, inline all functions
		if (inlineFunctions) augmentedModel.inlineFunctions();
		
		// Perform type resolution on the model
		types = augmentedModel.getTypes();
		sizes = augmentedModel.getSizes();
		
		// Replace Variables with LocalVariable and WitnessVariable nodes, where applicable
		augmentedModel.identifySpecialVariables();

		hasRangeProof = augmentedModel.hasRangeProof();
		hasPairing = augmentedModel.hasPairing();
		
		if (hasRangeProof || hasPairing) {
			groupClass = BilinearGroup;
			groupName = "bilinearGroup";			
		} else {
			groupClass = Group;
			groupName = "group";
		}
		groupClassName = groupClass.getSimpleName();


		val String proof = generateCode(model, new BranchState());
		System.out.println(proof);
		
		val List<String> witnessNames = getWitnessNames(model.getWitnessList());
		Collections.sort(witnessNames);
		
		val List<Pair<String, Type>> variableTypes = new ArrayList<Pair<String, Type>>();
		for (Entry<String, List<Variable>> entry : augmentedModel.getAllVariables().entrySet()) {
			val String variableName = entry.getKey();
			val Variable variable = entry.getValue().get(0);
			if (!(variable instanceof WitnessVariable)) {
				variableTypes.add(new Pair(variableName, types.get(variable)));
			}
		}
		
		Collections.sort(variableTypes, new Comparator<Pair<String, Type>>() {
			override compare(Pair<String, Type> arg0, Pair<String, Type> arg1) {
				if (arg0.getValue() == arg1.getValue()) {
					return arg0.getKey().compareTo(arg1.getKey());
				}
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});
		
		val Set<String> constrainedWitnessNames = augmentedModel.getConstrainedWitnessNames();
		
		protocolName = "MySigmaProtocol";
		val String packageName = "prototype";
		val String commonInputClassName = protocolName + CommonInput.getSimpleName();
		val String secretInputClassName = protocolName + SecretInput.getSimpleName();
		val String publicParametersClassName = protocolName + "PublicParameters";
		
		subprotocolCount = 0;
		
		
		val ClassBuilder protocolClass = buildProtocolClass(
			protocolName, commonInputClassName, secretInputClassName, publicParametersClassName, witnessNames, variableTypes, proof
		);
		
		val SourceBuilder protocolSource = new SourceBuilder(packageName, protocolClass);
		protocolSource.setImports(buildProtocolImports());
		val String protocolCode = protocolSource.toString();
		
		val ClassBuilder testClass = buildTestClass(
			protocolName, commonInputClassName, secretInputClassName, publicParametersClassName, witnessNames, variableTypes, constrainedWitnessNames
		);
		val SourceBuilder testSource = new SourceBuilder(packageName, testClass);
		testSource.setImports(buildTestImports());
		val String testCode = testSource.toString();
		
		var String publicParametersCode = null;
		if (hasRangeProof) {
			val ClassBuilder publicParametersClass = buildPublicParametersClass(publicParametersClassName);
			val SourceBuilder publicParametersSource = new SourceBuilder(packageName, publicParametersClass);
			publicParametersSource.setImports(buildPublicParametersImports());
			publicParametersCode = publicParametersSource.toString();
		}
		
		val String project = buildProject(protocolName, packageName, protocolCode, testCode, publicParametersClassName, publicParametersCode).getProject();
		generatedCode = project;
	}
	
	/*
	 * 
	 * 
	 * PROTOCOL CLASS GENERATION
	 * 
	 * 
	 */
	def ClassBuilder buildProtocolClass(
		String protocolName,
		String commonInputClassName,
		String secretInputClassName,
		String publicParametersClassName,
		List<String> witnessNames,
		List<Pair<String, Type>> variableTypes,
		String proof
	) {
		val ClassBuilder protocolClass = new ClassBuilder(PUBLIC, protocolName, DelegateProtocol);
		
		// Build fields
		val FieldBuilder ppField = new FieldBuilder(PROTECTED, publicParametersClassName, "pp");
		val FieldBuilder groupField = new FieldBuilder(PROTECTED, groupClass, groupName);
		val FieldBuilder zpField = new FieldBuilder(PROTECTED, Zp, "zp");
		
		// Build constructor
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(groupClass, groupName);
		if (hasRangeProof) constructor.addParameter(publicParametersClassName, "pp");
		
		val String constructorBody = '''
			«IF hasRangeProof»
			this.pp = pp;
			«ENDIF»
			this.«groupName» = «groupName»;
			this.zp = (Zp) this.«groupName».getZn();
		''';
		constructor.addBody(constructorBody);
		
		// Build subprotocol spec method
		val MethodBuilder provideSubprotocolSpecMethod = buildProvideSubprotocolSpecMethod(commonInputClassName, witnessNames, proof);

		// Build prover spec method
		val MethodBuilder provideProverSpecWithNoSendFirstMethod = buildProvideProverSpecWithNoSendFirstMethod(secretInputClassName, witnessNames);
		
		// Build challenge space method
		val MethodBuilder getChallengeSpaceSizeMethod = buildGetChallengeSpaceSizeMethod();
		
		// Build common input class
		val ClassBuilder commonInputClass = buildCommonInputClass(commonInputClassName, variableTypes);
		
		// Build secret input class
		val ClassBuilder secretInputClass = buildSecretInputClass(secretInputClassName, witnessNames);
		
		// Add all fields, methods and inner classes to the protocol class
		if (hasRangeProof) protocolClass.addField(ppField);
		protocolClass.addField(groupField);
		protocolClass.addField(zpField);
		protocolClass.addConstructor(constructor);
		
		protocolClass.addMethod(provideSubprotocolSpecMethod);
		protocolClass.addMethod(provideProverSpecWithNoSendFirstMethod);
		protocolClass.addMethod(getChallengeSpaceSizeMethod);

		protocolClass.addInnerClass(commonInputClass);
		protocolClass.addInnerClass(secretInputClass);

		return protocolClass;
	}
	
	def MethodBuilder buildProvideSubprotocolSpecMethod(String commonInputClassName, List<String> witnessNames, String proof) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, SubprotocolSpec, "provideSubprotocolSpec");
		method.setOverride();
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SubprotocolSpecBuilder, "subprotocolSpecBuilder");
		
		val String methodBody = '''
			«commonInputClassName» «INPUT_VARIABLE» = («commonInputClassName») commonInput;
			
			//Add variables (witnesses)
			«FOR String witnessName : witnessNames»
			SchnorrZnVariable «witnessName + WITNESS_SUFFIX» = subprotocolSpecBuilder.addZnVariable("«witnessName»", zp);
			«ENDFOR»
			
			//Add statements
			«proof»
			
			return subprotocolSpecBuilder.build();
		''';
		method.addBody(methodBody);
	
		return method;
	}
	
	def MethodBuilder buildProvideProverSpecWithNoSendFirstMethod(String secretInputClassName, List<String> witnessNames) {
		val MethodBuilder method = new MethodBuilder(PROTECTED, ProverSpec, "provideProverSpecWithNoSendFirst");
		method.setOverride();
		method.addParameter(CommonInput, "commonInput");
		method.addParameter(SecretInput, "secretInput");
		method.addParameter(ProverSpecBuilder, "proverSpecBuilder");
		
		val String methodBody = '''
			«secretInputClassName» witness = («secretInputClassName») secretInput;

			«FOR String witnessName : witnessNames»
			proverSpecBuilder.putWitnessValue("«witnessName»", witness.«witnessName»);
	        «ENDFOR»
			
			return proverSpecBuilder.build();
		''';
		method.addBody(methodBody);

		return method;
	}
	
	def MethodBuilder buildGetChallengeSpaceSizeMethod() {
		val MethodBuilder method = new MethodBuilder(PUBLIC, BigInteger, "getChallengeSpaceSize");
		method.setOverride();
		
		method.addStatement("return zp.size();");
		
		return method;
	}
	
	def ClassBuilder buildCommonInputClass(String commonInputClassName, List<Pair<String, Type>> variableTypes) {
		val ClassBuilder commonInputClass = new ClassBuilder(PUBLIC, STATIC, commonInputClassName, CommonInput);
		commonInputClass.addBasicConstructor(PUBLIC);
				
		for (Pair<String, Type> entry : variableTypes) {
			val FieldBuilder variableField = new FieldBuilder(PUBLIC, FINAL, entry.getValue().getTypeClass(), entry.getKey());	
			commonInputClass.addField(variableField);
		}

		return commonInputClass;
	}
	
	def ClassBuilder buildSecretInputClass(String secretInputClassName, List<String> witnessNames) {
		val ClassBuilder secretInputClass = new ClassBuilder(PUBLIC, STATIC, secretInputClassName, SecretInput);
		
		for (String witnessName : witnessNames) {
			val FieldBuilder field = new FieldBuilder(PUBLIC, FINAL, Zp.ZpElement, witnessName);
			secretInputClass.addField(field);
		}
		
		secretInputClass.addBasicConstructor(PUBLIC);
		
		return secretInputClass;
	}
	
	def String buildProtocolImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearExponentStatementFragment;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;
			import java.math.BigInteger;
		''';
		
		return imports; 
	}
	
	/*
	 * 
	 * 
	 * LIBRARY TEST CLASS GENERATION
	 * 
	 * 
	 */
	def ClassBuilder buildTestClass(
		String protocolClassName,
		String commonInputClassName,
		String secretInputClassName,
		String publicParametersClassName,
		List<String> witnessNames,
		List<Pair<String, Type>> variableTypes,
		Set<String> constrainedWitnessNames
	) {
		val ClassBuilder testClass = new ClassBuilder(PUBLIC, "LibraryTest");

		val MethodBuilder testMethod = new MethodBuilder(PUBLIC, void, "protocolTest");		
		testMethod.setTest();
		
		val List<String> variableNames = new ArrayList<String>();
		for (Pair<String, Type> entry : variableTypes) {
			variableNames.add(entry.getKey());
		}

		val String commonInputParameters = GenerationHelper.createCommaList(variableNames);
		val String secretInputParameters = GenerationHelper.createCommaList(witnessNames);
		
		var String groupInstance;
		var String defaultGroup;
		if (hasRangeProof || hasPairing) {
			groupInstance = "new BarretoNaehrigBilinearGroup(80)";
			defaultGroup = "groupG1";
		} else {
			groupInstance = "new LazyGroup(new Secp256k1())";
			defaultGroup = groupName;
		}
		
		val String body = '''
			«groupClassName» «groupName» = «groupInstance»;
			«IF hasRangeProof || hasPairing»
			«publicParametersClassName» pp = «publicParametersClassName».generateNewParameters(bilinearGroup);
			Group groupG1 = bilinearGroup.getG1();
			«ENDIF»
			Zp zp = (Zp) «defaultGroup».getZn();
			
			//Set witness
			«FOR String witnessName : witnessNames»
			«IF constrainedWitnessNames.contains(witnessName)»
			Zp.ZpElement «witnessName» = zp.valueOf(0); // Change this value so that it satisfies all constraints on the witness
			«ELSE»
			Zp.ZpElement «witnessName» = zp.getUniformlyRandomElement();
			«ENDIF»
			«ENDFOR»
			
			//Set constants
			«FOR Pair<String, Type> entry : variableTypes»
			«entry.getValue().getTypeClass().getSimpleName()» «entry.getKey()» = «defaultGroup».getNeutralElement();
			«ENDFOR»
			
			//Instantiate protocol and input
			«protocolClassName» protocol = new «protocolClassName»(«groupName»«IF hasRangeProof», pp«ENDIF»);
			
			CommonInput commonInput = new «protocolClassName».«commonInputClassName»(«commonInputParameters»);
			SecretInput secretInput = new «protocolClassName».«secretInputClassName»(«secretInputParameters»);
			
			SigmaProtocolProverInstance prover = protocol.getProverInstance(commonInput, secretInput);
			SigmaProtocolVerifierInstance verifier = protocol.getVerifierInstance(commonInput);
			
			protocol.runProtocolLocally(prover, verifier);
			assertTrue(verifier.hasTerminated());
			assertTrue(verifier.isAccepting());
			if (verifier.isAccepting())
			    System.out.println("Yay, the protocol worked!");
		''';
		
		testMethod.addBody(body);
		
		testClass.addMethod(testMethod);
		
		return testClass;
	}
	
	def String buildTestImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
			import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
			import org.cryptimeleon.math.structures.groups.lazy.LazyGroup;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import org.junit.Test;
			import static org.junit.Assert.assertTrue;
		''';
		
		return imports;
	}
	
	/*
	 * 
	 * 
	 * PUBLIC PARAMETERS CLASS GENERATION
	 * 
	 * 
	 * 
	 */
	def buildPublicParametersClass(String className) {
		val ClassBuilder publicParametersClass = new ClassBuilder(PUBLIC, protocolName + "PublicParameters", StandaloneRepresentable);
		
		val FieldBuilder groupField = new FieldBuilder(PUBLIC, FINAL, BilinearGroup, "bilinearGroup");
		val FieldBuilder ppField = new FieldBuilder(PUBLIC, FINAL, SetMembershipPublicParameters, "rangeProofpp");
		
		val ConstructorBuilder publicParametersConstructor = buildPublicParametersConstructor();
		val MethodBuilder generateNewParametersMethod = buildGenerateNewParametersMethod(className);
		val MethodBuilder getRepresentationMethod = buildGetRepresentationMethod();
		
		publicParametersClass.addField(groupField);
		publicParametersClass.addField(ppField);
		
		publicParametersClass.addBasicConstructor();
		publicParametersClass.addConstructor(publicParametersConstructor);
		publicParametersClass.addMethod(generateNewParametersMethod);
		publicParametersClass.addMethod(getRepresentationMethod);
		
		return publicParametersClass;
	}
	
	
	def ConstructorBuilder buildPublicParametersConstructor() {
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(Representation, "repr");
		
		val String body = '''
			bilinearGroup = (BilinearGroup) repr.obj().get("bilinearGroup").repr().recreateRepresentable();
			rangeProofpp = new SetMembershipPublicParameters(bilinearGroup, repr.obj().get("setMembershipPp"));
		''';
		constructor.addBody(body);
		
		return constructor;
	}
	
	def MethodBuilder buildGenerateNewParametersMethod(String className) {
		val MethodBuilder method = new MethodBuilder(PUBLIC, STATIC, className, "generateNewParameters");
		method.addParameter(BilinearGroup, "bilinearGroup");
		
		val String body = '''
			SetMembershipPublicParameters rangeProof1pp = TwoSidedRangeProof.generatePublicParameters(bilinearGroup, 100);
			return new «className»(bilinearGroup, rangeProof1pp);
		''';
		method.addBody(body);
		
		return method;
	}
	
	def MethodBuilder buildGetRepresentationMethod() {
		val MethodBuilder method = new MethodBuilder(PUBLIC, Representation, "getRepresentation");
		method.setOverride();
		
		val String body = '''
			ObjectRepresentation repr = new ObjectRepresentation();
			repr.put("bilinearGroup", bilinearGroup.getRepresentation());
			repr.put("rangeProofpp", rangeProofpp.getRepresentation());
			return repr;
		''';
		method.addBody(body);
		
		return method 
	}
	
	
	
	def String buildPublicParametersImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.SetMembershipPublicParameters;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.setmembership.TwoSidedRangeProof;
			import org.cryptimeleon.math.serialization.ObjectRepresentation;
			import org.cryptimeleon.math.serialization.Representation;
			import org.cryptimeleon.math.serialization.StandaloneRepresentable;
			import org.cryptimeleon.math.structures.groups.elliptic.BilinearGroup;
			import org.cryptimeleon.math.structures.groups.elliptic.type3.bn.BarretoNaehrigBilinearGroup;
		''';
		
		return imports;
	}
	
	
	/*
	 * 
	 * 
	 * PROJECT GENERATION
	 * 
	 * 
	 */
	def ProjectBuilder buildProject(
		String protocolName,
		String packageName,
		String protocolClassCode,
		String testClassCode,
		String publicParametersClassName,
		String publicParametersClassCode
	) {
		val ProjectFolder root = new ProjectFolder(packageName);
		
		val ProjectFile gradleBuilder = new ProjectFile("build.gradle", "project", false);
		val ProjectFile gradleSettings = new ProjectFile("settings.gradle", "project", false);
		val ProjectFile gradleBat = new ProjectFile("gradlew.bat", "project", false);
		val ProjectFile gradlew = new ProjectFile("gradlew", "project", false);
		val ProjectFile dotProject = new ProjectFile(".project", "project", false);
		val ProjectFile dotClasspath = new ProjectFile(".classpath", "project", false);
		
		root.addFile(gradleBuilder);
		root.addFile(gradleSettings);
		root.addFile(gradleBat);
		root.addFile(gradlew);
		root.addFile(dotProject);
		root.addFile(dotClasspath);
		
		val ProjectFolder gradle = new ProjectFolder("gradle");
		val ProjectFolder wrapper = new ProjectFolder("wrapper");
		val ProjectFile gradleWrapperJar = new ProjectFile("gradle-wrapper.jar", "project", true);
		val ProjectFile gradleWrapperSettings = new ProjectFile("gradle-wrapper.properties", "project", false);
		
		wrapper.addFile(gradleWrapperJar);
		wrapper.addFile(gradleWrapperSettings);
		
		gradle.addFolder(wrapper);
		root.addFolder(gradle);
		
		val ProjectFolder src = new ProjectFolder("src");
		
		val ProjectFolder main = new ProjectFolder("main");
		val ProjectFolder mainJava = new ProjectFolder("java");
		val ProjectFolder mainPrototype = new ProjectFolder("prototype");
		
		val ProjectFile mainProtocol = new ProjectFile(protocolName + '.java', protocolClassCode);
		
		mainPrototype.addFile(mainProtocol);
		
		if (publicParametersClassCode !== null) {
			val ProjectFile publicParameters = new ProjectFile(publicParametersClassName + '.java', publicParametersClassCode);
			mainPrototype.addFile(publicParameters);
		}
		
		mainJava.addFolder(mainPrototype);
		main.addFolder(mainJava);
		
		val ProjectFolder test = new ProjectFolder("test");
		val ProjectFolder testJava = new ProjectFolder("java");
		val ProjectFolder testPrototype = new ProjectFolder("prototype");
		
		val ProjectFile testLibrary = new ProjectFile("LibraryTest.java", testClassCode);
		testPrototype.addFile(testLibrary);

		testJava.addFolder(testPrototype);
		test.addFolder(testJava);
		
		src.addFolder(main);
		src.addFolder(test);
		
		root.addFolder(src);
		
		return new ProjectBuilder(root);
	}

	
	def List<String> getWitnessNames(WitnessList witnessList) {
		val List<String> witnessNames = new ArrayList<String>();
		
		for (Witness witness : witnessList.getWitnesses()) {
			val String name = witness.getName();
				witnessNames.add(name);				
		}
		
		return witnessNames;
	}
	
	
	
	
	
	// Generates the Java equivalent of all user defined functions
//	def void generateFunctions(Model model, BranchState state) {
//		for (FunctionDefinition function : model.getFunctions()) {
//			
//			if (types.containsKey(function)) {
//				
//				// TODO: if the function type is EXPONENT, and the function size is greater than 1,
//				// then the returnType should instead be ExponentExprTuple
//				val String returnType = Type.toString(types.get(function));
//				
//				// TODO: for each parameter, if the function type is EXPONENT, and the parameter size is greater than 1,
//				// then the type should instead be ExponentExprTuple
//				functionBuilder.append(
//					'''
//					private static «returnType» «function.getName()»(«FOR Parameter parameter : function.getParameterList().getParameters() SEPARATOR ', '»«Type.toString(types.get(parameter))» «parameter.getName()»«ENDFOR») {
//					  return «generateCode(function.getBody(), state)»;
//					}
//					'''
//				);
//			}
//			// Maybe throw a console warning above if the type cannot be determined for the variable
//			// Or possibly move this warning to validation
//		}
//	}
	
	/*
	 * 
	 * 
	 * PROOF STATEMENT GENERATION
	 * 
	 * 
	 */
	
	// Generates the Java code for the main expression
	def dispatch String generateCode(Model model, BranchState state) {
		return generateCode(model.getProof(), state);
	}
	
	def dispatch String generateCode(Conjunction conjunction, BranchState state) {
		val String left = generateCode(conjunction.getLeft(), state);
		val String right = generateCode(conjunction.getRight(), state);
		
		return left + right;
	}
	
	def dispatch String generateCode(Disjunction disjunction, BranchState state) {
		val String left = generateCode(disjunction.getLeft(), state);
		val String right = generateCode(disjunction.getRight(), state);
		
		return '''«left».or(«right»)''';
	}
	
	def private String shiftUp(String expression) {
		return expression + ".add(zp.valueOf(1))";
	}
	
	def private String shiftDown(String expression) {
		return expression + ".sub(zp.valueOf(1))";
	}
	
	def private boolean isStrictComparison(String operator) {
		return operator == OPERATOR_LESS || operator == OPERATOR_GREATER;
	}
	
	def private boolean isLessComparison(String operator) {
		return operator == OPERATOR_LESS || operator == OPERATOR_LESSEQUAL;
	}
	
	def private String swapComparisonDirection(String operator) {
		switch operator {
			case OPERATOR_LESS: return OPERATOR_GREATER
			case OPERATOR_LESSEQUAL: return OPERATOR_GREATEREQUAL
			case OPERATOR_GREATER: return OPERATOR_LESS
			case OPERATOR_GREATEREQUAL: return OPERATOR_LESSEQUAL
		}
	}
	
	def dispatch String generateCode(Comparison comparison, BranchState state) {
		var EObject leftNode = comparison.getLeft();
		var EObject rightNode = comparison.getRight();
		var EObject extraNode = comparison.getExtra();
			
		var String operator = comparison.getOperation();
		
		subprotocolCount++;
		var String protocolName = comparison.getProtocolName();
		
		if (protocolName === null) {
			protocolName = "statement" + subprotocolCount;
		} else {
			protocolName = protocolName.substring(1, protocolName.length()-1);
		}
		
		// Handle = and != cases
		if (operator == OPERATOR_EQUAL || operator == OPERATOR_INEQUAL) {
			var String left = generateCode(leftNode, state);
			var String right = generateCode(rightNode, state);	
			val String negated = (operator == OPERATOR_EQUAL) ? "" : "!";
			var String fragmentClass;
			
			var String extraParameter;
			if (types.get(leftNode) === Type.EXPONENT) {
				fragmentClass = "LinearExponentStatementFragment";
				extraParameter = ", zp";
			} else {
				fragmentClass = "LinearStatementFragment";
				extraParameter = "";
			}
			
			return '''
				subprotocolSpecBuilder.addSubprotocol("«protocolName»",
					new «fragmentClass»(«negated»«left».isEqualTo(«right»)«extraParameter»)
				);
			''';
		}
		
		var EObject lowerBound;
		var EObject upperBound;
		var EObject member;
		
		var String operator2 = comparison.getOperation2();
		
		if (operator2 === null) {
			// Single comparison
			
			var boolean leftHasWitness = ModelHelper.containsWitnessVariable(leftNode);
			
			// Normalize the direction of the inequality
			if (!isLessComparison(operator)) {
				var EObject tempNode = leftNode;
				leftNode = rightNode;
				rightNode = tempNode;
				operator = swapComparisonDirection(operator);
				leftHasWitness = !leftHasWitness;
			}
			
			// Normalize a single comparison into a double comparison
			if (leftHasWitness) {
				// Left side contains a witness variable
				
				lowerBound = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
				(lowerBound as NumberLiteral).setValue(0);
				member = leftNode;
				upperBound = rightNode;
				operator2 = operator;
				operator = OPERATOR_LESSEQUAL;
				
			} else {
				// Right side contains a witness variable
				
				lowerBound = leftNode;
				member = rightNode;
				upperBound = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
				(upperBound as NumberLiteral).setValue(-1);
				operator2 = OPERATOR_LESSEQUAL;
			}
			
		} else {
			// Double comparison
			
			// Normalize the direction of the inequality
			if (!isLessComparison(operator)) {
				var EObject tempNode = leftNode;
				leftNode = extraNode;
				extraNode = tempNode;
				
				val String temp = operator;
				operator = swapComparisonDirection(operator2);
				operator2 = swapComparisonDirection(temp);
			}
			
			lowerBound = leftNode;
			member = rightNode;
			upperBound = extraNode;
		}
		
		
		// Shift the lower bound up by 1 if the first inequality is strict
		if (isStrictComparison(operator)) {
			if (lowerBound instanceof NumberLiteral) {
				val NumberLiteral literal = lowerBound;
				literal.setValue(literal.getValue() + 1);
			} else {
				val Sum sum = ZeroKnowledgeFactory.eINSTANCE.createSum();
				val NumberLiteral literal = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
				literal.setValue(1);
				sum.setLeft(lowerBound as Expression);
				sum.setRight(literal as Expression);
				sum.setOperation(OPERATOR_ADDITION);
				lowerBound = sum;
			}
		}
		
		// Shift the upper bound down by 1 if the second inequality is strict
		if (isStrictComparison(operator2)) {
			if (upperBound instanceof NumberLiteral) {
				val NumberLiteral literal = upperBound;
				literal.setValue(literal.getValue() - 1);
			} else {
				val Sum sum = ZeroKnowledgeFactory.eINSTANCE.createSum();
				val NumberLiteral literal = ZeroKnowledgeFactory.eINSTANCE.createNumberLiteral();
				literal.setValue(1);
				sum.setLeft(upperBound as Expression);
				sum.setRight(literal as Expression);
				sum.setOperation(OPERATOR_SUBTRACTION);
				upperBound = sum;
			}
		}
		
		val String lowerBoundCode = generateCode(lowerBound, state);
		val String memberCode = generateCode(member, state);
		val String upperBoundCode = generateCode(upperBound, state);
		
		return '''
			subprotocolSpecBuilder.addSubprotocol("«protocolName»",
				new TwoSidedRangeProof(«memberCode», «lowerBoundCode», «upperBoundCode», pp.rangeProofpp)
			);
		''';
	}
	
	def dispatch String generateCode(Sum sum, BranchState state) {
		val String left = generateCode(sum.getLeft(), state);
		val String right = generateCode(sum.getRight(), state);
		
		if (sum.getOperation() == OPERATOR_ADDITION) {
			return '''«left».add(«right»)''';
		} else {
			return '''«left».sub(«right»)''';
		}
	}
	
	def dispatch String generateCode(Product product, BranchState state) {

		val String left = generateCode(product.getLeft(), state);
		val String right = generateCode(product.getRight(), state);
		
		if (product.getOperation == OPERATOR_MULTIPLICATION) {
			if (types.get(product) === Type.EXPONENT) {
				return '''«left».mul(«right»)''';
			} else {
				return '''«left».op(«right»)''';
			}
		} else {
			if (types.get(product) === Type.EXPONENT) {
				return '''«left».mul(«right».inv())''';
			} else {
				return '''«left».op(«right».inv())''';
			}
		}
		
		

	}
	
	def dispatch String generateCode(Power power, BranchState state) {
		
		val String left = generateCode(power.getLeft(), state);
		val String right = generateCode(power.getRight(), state);
		
		return '''«left».pow(«right»)''';		
	}
	
	def dispatch String generateCode(StringLiteral string, BranchState state) {
		val String value = string.getValue(); // Value includes double quotes
		return value;
	}
	
	def dispatch String generateCode(Tuple node, BranchState state) {
		return '''«FOR element : node.getElements() SEPARATOR ', '»«generateCode(element, state)»«ENDFOR»'''
	}

	def dispatch String generateCode(Negative node, BranchState state) {
		
		val String term = generateCode(node.getTerm(), state);
		
		return '''«term».neg()''';		
	}
	
	def dispatch String generateCode(FunctionCall call, BranchState state) {
		val String name = GenerationHelper.convertToJavaName(call.getName());
		
		return '''«name»(«FOR argument : call.getArguments() SEPARATOR ','»«generateCode(argument, state)»«ENDFOR»)'''
	}
	
	def dispatch String generateCode(Argument argument, BranchState state) {
		generateCode(argument.getExpression(), state);
	}
	
	def dispatch String generateCode(Variable variable, BranchState state) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return INPUT_VARIABLE + '.' + name;
	}
	
	def dispatch String generateCode(LocalVariable variable, BranchState state) {
		val String name = GenerationHelper.convertToJavaName(variable.getName());
		return name;
	}
	
	def dispatch String generateCode(WitnessVariable witness, BranchState state) {
		val String name = GenerationHelper.convertToJavaName(witness.getName());
		return name + WITNESS_SUFFIX;
	}
	
	def dispatch String generateCode(NumberLiteral number, BranchState state) {
		return '''zp.valueOf(«number.getValue()»)''';
	}
	
	def dispatch String generateCode(Brackets brackets, BranchState state) {
		return generateCode(brackets.getContent(), state);
	}
	
}