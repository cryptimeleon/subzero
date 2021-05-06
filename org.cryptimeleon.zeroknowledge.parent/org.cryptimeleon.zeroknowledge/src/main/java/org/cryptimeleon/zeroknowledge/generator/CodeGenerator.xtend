package org.cryptimeleon.zeroknowledge.generator

import org.cryptimeleon.zeroknowledge.builder.ProjectBuilder
import org.cryptimeleon.zeroknowledge.builder.ProjectFile
import org.cryptimeleon.zeroknowledge.builder.ProjectFolder
import org.cryptimeleon.zeroknowledge.builder.SourceBuilder
import org.cryptimeleon.zeroknowledge.model.AugmentedModel
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model

/**
 * Generates the Java code for a protocol
 */
class CodeGenerator {
	
	var String generatedCode;
	
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
		
		// Replace Variables with LocalVariable and WitnessVariable nodes, where applicable
		augmentedModel.identifySpecialVariables();

		val String protocolName = augmentedModel.getProtocolName();
		val String packageName = augmentedModel.getPackageName();
		val String publicParametersClassName = GenerationHelper.createPublicParametersClassName(protocolName);
		
		// Create protocol class
		val SourceBuilder protocolSource = new ProtocolClassGenerator(augmentedModel).generate();
		val String protocolCode = protocolSource.toString();
		
		// Create library test class
		val SourceBuilder testSource = new TestClassGenerator(augmentedModel).generate();
		val String testCode = testSource.toString();
		
		// Create public parameters class, if needed
		var String publicParametersCode = null;
		if (augmentedModel.requiresPublicParametersClass()) {
			val publicParametersSource = new PublicParametersClassGenerator(augmentedModel).generate();
			publicParametersCode = publicParametersSource.toString();
		}
		
		val String project = buildProject(protocolName, packageName, protocolCode, testCode, publicParametersClassName, publicParametersCode).getProject();
		generatedCode = project;
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
}