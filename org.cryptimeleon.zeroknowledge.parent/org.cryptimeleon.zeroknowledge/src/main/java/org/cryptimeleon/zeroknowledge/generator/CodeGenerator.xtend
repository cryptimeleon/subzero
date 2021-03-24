package org.cryptimeleon.zeroknowledge.generator

import java.math.BigInteger
import java.nio.file.Path
import java.nio.file.Paths
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import org.cryptimeleon.craco.protocols.CommonInput
import org.cryptimeleon.craco.protocols.SecretInput
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder
import org.cryptimeleon.math.structures.groups.Group
import org.cryptimeleon.math.structures.groups.GroupElement
import org.cryptimeleon.math.structures.rings.zn.Zp
import org.cryptimeleon.zeroknowledge.model.BranchState
import org.cryptimeleon.zeroknowledge.model.ModelHelper
import org.cryptimeleon.zeroknowledge.model.ModelPrinter
import org.cryptimeleon.zeroknowledge.type.Type
import org.cryptimeleon.zeroknowledge.type.TypeInference
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Argument
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Brackets
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Comparison
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Conjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Disjunction
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionCall
import org.cryptimeleon.zeroknowledge.zeroKnowledge.FunctionDefinition
import org.cryptimeleon.zeroknowledge.zeroKnowledge.LocalVariable
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Negative
import org.cryptimeleon.zeroknowledge.zeroKnowledge.NumberLiteral
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Parameter
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

class CodeGenerator {
	
	var String generatedCode;
	
	String protocolName;
	int subprotocolCount;
	
	HashMap<EObject, Type> types;
	HashMap<EObject, Integer> sizes;

	HashSet<String> variables; // Contains the set of names of variables
	HashSet<String> witnesses; // Contains the set of names of witnesses
	
	String OPERATOR_MULTIPLICATION = "*";
	String OPERATOR_DIVISION = "/";
	String OPERATOR_EQUAL = "=";
	String OPERATOR_INEQUAL = "!=";
	String OPERATOR_LESS = "<";
	String OPERATOR_GREATER = ">";
	String OPERATOR_LESSEQUAL = "<=";
	String OPERATOR_GREATEREQUAL = ">=";
	String NEWLINE = "\n";
	String INDENT = "  ";
	
	
	String INPUT_VARIABLE = "input";
	String WITNESS_SUFFIX = "Var";
	
	new(Model model) {
		performGeneration(model, false);
	}
	
	new(Model model, boolean inlineFunctions) {
		performGeneration(model, inlineFunctions);
	}
	
	def void performGeneration(Model model, boolean inlineFunctions) {
		variables = new HashSet<String>;
		witnesses = new HashSet<String>;
		
		// Perform model transformations
		ModelPrinter.print(model);
		
		// If option is set, inline all functions
		if (inlineFunctions) ModelHelper.inlineFunctions(model);
		
		// Replace all subtraction operations with sum operations of negative nodes
		ModelHelper.normalizeNegatives(model);

		// Perform type resolution on the model
		val TypeInference typeInference = new TypeInference(model);	
		types = typeInference.getTypes();
		sizes = typeInference.getSizes();
		
		// Replace Variables with LocalVariable and WitnessVariable nodes, where applicable
		ModelHelper.identifySpecialVariables(model);

//		generateImports();
//		generateFunctions(model, new BranchState());

		val String proof = generateCode(model, new BranchState());
		
		val List<String> witnessNames = getWitnessNames(model.getWitnessList());
		Collections.sort(witnessNames);
		val List<String> variableNames = new ArrayList<String>(variables);
		Collections.sort(variableNames);
		
		protocolName = "MySigmaProtocol";
		val String packageName = "prototype";
		val String commonInputClassName = protocolName + CommonInput.getSimpleName();
		val String secretInputClassName = protocolName + SecretInput.getSimpleName();
		subprotocolCount = 0;
		
		val ClassBuilder protocolClass = buildProtocolClass(protocolName, commonInputClassName, secretInputClassName, witnessNames, variableNames, proof);
		val ClassBuilder testClass = buildTestClass(protocolName, commonInputClassName, secretInputClassName, witnessNames, variableNames);
		
		val SourceBuilder protocolSource = new SourceBuilder(packageName, protocolClass);
		protocolSource.setImports(generateProtocolImports());
		
		val SourceBuilder testSource = new SourceBuilder(packageName, testClass);
		testSource.setImports(generateTestImports());
		
		val String protocolCode = protocolSource.toString();
		val String testCode = testSource.toString();
		
		val String project = buildProject(protocolName, packageName, protocolCode, testCode).getProject();
		generatedCode = project;
	}
	
	def ClassBuilder buildProtocolClass(
		String protocolName,
		String commonInputClassName,
		String secretInputClassName,
		List<String> witnessNames,
		List<String> variableNames,
		String proof
	) {
		val ClassBuilder protocolClass = new ClassBuilder(PUBLIC, protocolName, DelegateProtocol);
		
		// Build fields
		val FieldBuilder groupField = new FieldBuilder(PROTECTED, Group, "group");
		val FieldBuilder zpField = new FieldBuilder(PROTECTED, Zp, "zp");
		
		// Build constructor
		val ConstructorBuilder constructor = new ConstructorBuilder(PUBLIC);
		constructor.addParameter(Group, "group");
		
		val String constructorBody = '''
			this.group = group;
			this.zp = (Zp) this.group.getZn();
		''';
		constructor.addBody(constructorBody);
		
		// Build subprotocol spec method
		val MethodBuilder provideSubprotocolSpecMethod = buildProvideSubprotocolSpecMethod(commonInputClassName, witnessNames, proof);

		// Build prover spec method
		val MethodBuilder provideProverSpecWithNoSendFirstMethod = buildProvideProverSpecWithNoSendFirstMethod(secretInputClassName, witnessNames);
		
		// Build challenge space method
		val MethodBuilder getChallengeSpaceSizeMethod = new MethodBuilder(PUBLIC, BigInteger, "getChallengeSpaceSize");
		getChallengeSpaceSizeMethod.setOverride();
		getChallengeSpaceSizeMethod.addStatement("return zp.size();");
		
		// Build common input class
		val ClassBuilder commonInputClass = buildCommonInputClass(commonInputClassName, variableNames);
		
		// Build secret input class
		val ClassBuilder secretInputClass = buildSecretInputClass(secretInputClassName, witnessNames);
		
		// Add all fields, methods and inner classes to the protocol class
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
	
	def ClassBuilder buildCommonInputClass(String commonInputClassName, List<String> variableNames) {
		val ClassBuilder commonInputClass = new ClassBuilder(PUBLIC, STATIC, commonInputClassName, CommonInput);
		commonInputClass.addBasicConstructor(PUBLIC);
				
		for (String variableName : variableNames) {
			val FieldBuilder variableField = new FieldBuilder(PUBLIC, FINAL, GroupElement, variableName);	
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
	
	def ClassBuilder buildTestClass(
		String protocolClassName,
		String commonInputClassName,
		String secretInputClassName,
		List<String> witnessNames,
		List<String> variableNames
	) {
		val ClassBuilder testClass = new ClassBuilder(PUBLIC, "LibraryTest");

		val MethodBuilder testMethod = new MethodBuilder(PUBLIC, void, "protocolTest");		
		testMethod.setTest();

		val String commonInputParameters = GenerationHelper.createCommaList(variableNames);
		val String secretInputParameters = GenerationHelper.createCommaList(witnessNames);
		
		val String body = '''
			Group group = new LazyGroup(new Secp256k1());
			Zp zp = (Zp) group.getZn();
			
			//Set witness
			«FOR String witnessName : witnessNames»
			Zp.ZpElement «witnessName» = zp.getUniformlyRandomElement();
			«ENDFOR»
			
			//Set constants
			«FOR String variableName : variableNames»
			GroupElement «variableName» = group.getNeutralElement();
			«ENDFOR»
			
			//Instantiate protocol and input
			«protocolClassName» protocol = new «protocolClassName»(group);
			
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
	
	def ProjectBuilder buildProject(String protocolName, String packageName, String protocolClassCode, String testClassCode) {
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
		
		val ProjectFile mainPublicParameters = new ProjectFile("MyProtocolPublicParameters.java", "project", false);
		val ProjectFile mainProtocol = new ProjectFile(protocolName + '.java', protocolClassCode);
		
		mainPrototype.addFile(mainPublicParameters);
		mainPrototype.addFile(mainProtocol);
		
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
	
	
	def String getCode() {
		return generatedCode;
	}
	
	def List<String> getWitnessNames(WitnessList witnessList) {
		val List<String> witnessNames = new ArrayList<String>();
		
		for (Witness witness : witnessList.getWitnesses()) {
			val String name = witness.getName();
				witnessNames.add(name);				
		}
		
		return witnessNames;
	}
	
	// Generates all required import statements
	def String generateProtocolImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.DelegateProtocol;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.LinearStatementFragment;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.ProverSpecBuilder;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpec;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SendThenDelegateFragment.SubprotocolSpecBuilder;
			import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.variables.SchnorrZnVariable;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.rings.zn.Zp.ZpElement;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import java.math.BigInteger;
		''';
		
		return imports; 
	}
	
	def String generateTestImports() {
		val String imports = '''
			import org.cryptimeleon.craco.protocols.CommonInput;
			import org.cryptimeleon.craco.protocols.SecretInput;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolProverInstance;
			import org.cryptimeleon.craco.protocols.arguments.sigma.instance.SigmaProtocolVerifierInstance;
			import org.cryptimeleon.math.structures.groups.Group;
			import org.cryptimeleon.math.structures.groups.GroupElement;
			import org.cryptimeleon.math.structures.groups.elliptic.nopairing.Secp256k1;
			import org.cryptimeleon.math.structures.groups.lazy.LazyGroup;
			import org.cryptimeleon.math.structures.rings.zn.Zp;
			import org.junit.Test;
			import static org.junit.Assert.assertTrue;
		''';
		
		return imports;
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
	
	// Generates the Java code for the main expression
	def dispatch String generateCode(Model model, BranchState state) {
		return generateCode(model.getProof(), state);
	}
	
	def generateConjunctionHelper(String contents) {
		subprotocolCount++;
		return '''
			subprotocolSpecBuilder.addSubprotocol("statement«subprotocolCount»",
				new LinearStatementFragment(«contents»)
			);
		''';
	}
	
	def dispatch String generateCode(Conjunction conjunction, BranchState state) {
		val String left = generateCode(conjunction.getLeft(), state);
		val String right = generateCode(conjunction.getRight(), state);
		
		if (conjunction.getLeft() instanceof Conjunction) {
			return generateConjunctionHelper(left) + right;
		} else {
			return generateConjunctionHelper(left) + "\n" + generateConjunctionHelper(right);
		}
	}
	
	def dispatch String generateCode(Disjunction disjunction, BranchState state) {
		val String left = generateCode(disjunction.getLeft(), state);
		val String right = generateCode(disjunction.getRight(), state);
		
		return '''«left».or(«right»)''';
	}
	
	def dispatch String generateCode(Comparison comparison, BranchState state) {
		val String left = generateCode(comparison.getLeft(), state);
		val String right = generateCode(comparison.getRight(), state);		
		var String operator;
		
		switch comparison.getOperation() {
			case OPERATOR_EQUAL: operator = "isEqualTo"
			case OPERATOR_INEQUAL: operator = "?"
			case OPERATOR_LESS: operator = "?"
			case OPERATOR_GREATER: operator = "?"
			case OPERATOR_LESSEQUAL: operator = "?"
			case OPERATOR_GREATEREQUAL: operator = "?"
		}
		
		return '''«left».«operator»(«right»)''';
	}
	
	def dispatch String generateCode(Sum sum, BranchState state) {
		
		val newState = new BranchState(state);
//		newState.setExponentContext();

		val String left = generateCode(sum.getLeft(), newState);
		val String right = generateCode(sum.getRight(), newState);
		
		return '''«left».add(«right»)''';
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
		val String name = ModelHelper.convertToJavaName(call.getName());
		
		return '''«name»(«FOR argument : call.getArguments() SEPARATOR ','»«generateCode(argument, state)»«ENDFOR»)'''
	}
	
	def dispatch String generateCode(Argument argument, BranchState state) {
		generateCode(argument.getExpression(), state);
	}
	
	def dispatch String generateCode(Variable variable, BranchState state) {
		val String name = ModelHelper.convertToJavaName(variable.getName());
		
		if (!variables.contains(name)) {
			variables.add(name);
		}
		
		return INPUT_VARIABLE + '.' + name;
	}
	
	def dispatch String generateCode(LocalVariable variable, BranchState state) {
		val String name = ModelHelper.convertToJavaName(variable.getName());
		return name;
	}
	
	def dispatch String generateCode(WitnessVariable witness, BranchState state) {
		val String name = ModelHelper.convertToJavaName(witness.getName());
		return name + WITNESS_SUFFIX;
	}
	
	def dispatch String generateCode(NumberLiteral number, BranchState state) {
		return number.getValue().toString();
	}
	
	def dispatch String generateCode(Brackets brackets, BranchState state) {
		return generateCode(brackets.getContent(), state);
	}
	
}