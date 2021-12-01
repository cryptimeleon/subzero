package org.cryptimeleon.subzero.java

import org.cryptimeleon.subzero.project.ProjectFile
import org.cryptimeleon.subzero.builder.SourceBuilder
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.project.ProjectDirectory
import org.cryptimeleon.subzero.latex.LatexGenerator
import org.cryptimeleon.subzero.project.Project
import org.cryptimeleon.subzero.generator.CodeGenerator
import org.cryptimeleon.subzero.generator.GenerationHelper

/**
 * Generates the Java code for a protocol
 */
class JavaGenerator implements CodeGenerator {
	AugmentedModel augmentedModel;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
	}

	override String generate() {
		try {
			val Project project = buildJavaProject();
			return project.toString();
		} catch (Throwable e) {
			val StackTrace error = new StackTrace(e);
			return error.toString();
		}
	}
	
	def private Project buildJavaProject() {
		// For debugging purposes
		System.out.println(augmentedModel);

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
		
		// Fetch the raw DSL code
		val String rawCode = augmentedModel.getCode();
		
		// Fetch the LaTeX text
		val String latexText = new LatexGenerator(augmentedModel).generate();
		
		val ProjectDirectory root = new ProjectDirectory(packageName);
		
		val ProjectFile code = new ProjectFile(protocolName + '.sub0', rawCode);
		val ProjectFile latex = new ProjectFile(protocolName + '.tex', latexText);
		val ProjectFile gradleBuilder = new ProjectFile("build.gradle", "project", false);
		val ProjectFile gradleSettings = new ProjectFile("settings.gradle", "project", false);
		val ProjectFile gradleBat = new ProjectFile("gradlew.bat", "project", false);
		val ProjectFile gradlew = new ProjectFile("gradlew", "project", false);
		val ProjectFile dotProject = new ProjectFile(".project", "project", false);
		val ProjectFile dotClasspath = new ProjectFile(".classpath", "project", false);
		
		root.addFile(code);
		root.addFile(latex);
		root.addFile(gradleBuilder);
		root.addFile(gradleSettings);
		root.addFile(gradleBat);
		root.addFile(gradlew);
		root.addFile(dotProject);
		root.addFile(dotClasspath);
		
		val ProjectDirectory gradle = new ProjectDirectory("gradle");
		val ProjectDirectory wrapper = new ProjectDirectory("wrapper");
		val ProjectFile gradleWrapperJar = new ProjectFile("gradle-wrapper.jar", "project", true);
		val ProjectFile gradleWrapperSettings = new ProjectFile("gradle-wrapper.properties", "project", false);
		
		wrapper.addFile(gradleWrapperJar);
		wrapper.addFile(gradleWrapperSettings);
		
		gradle.addDirectory(wrapper);
		root.addDirectory(gradle);
		
		val ProjectDirectory src = new ProjectDirectory("src");
		
		val ProjectDirectory main = new ProjectDirectory("main");
		val ProjectDirectory mainJava = new ProjectDirectory("java");
		val ProjectDirectory mainPrototype = new ProjectDirectory("prototype");
		
		val ProjectFile mainProtocol = new ProjectFile(protocolName + '.java', protocolCode);
		
		mainPrototype.addFile(mainProtocol);
		
		if (publicParametersCode !== null) {
			val ProjectFile publicParameters = new ProjectFile(publicParametersClassName + '.java', publicParametersCode);
			mainPrototype.addFile(publicParameters);
		}
		
		mainJava.addDirectory(mainPrototype);
		main.addDirectory(mainJava);
		
		val ProjectDirectory test = new ProjectDirectory("test");
		val ProjectDirectory testJava = new ProjectDirectory("java");
		val ProjectDirectory testPrototype = new ProjectDirectory("prototype");
		
		val ProjectFile testLibrary = new ProjectFile("LibraryTest.java", testCode);
		testPrototype.addFile(testLibrary);

		testJava.addDirectory(testPrototype);
		test.addDirectory(testJava);
		
		src.addDirectory(main);
		src.addDirectory(test);
		
		root.addDirectory(src);
		
		return new Project(root);
	}
}