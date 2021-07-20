package org.cryptimeleon.subzero.generator

import org.cryptimeleon.subzero.builder.ProjectBuilder
import org.cryptimeleon.subzero.builder.ProjectFile
import org.cryptimeleon.subzero.builder.ProjectFolder
import org.cryptimeleon.subzero.builder.SourceBuilder
import org.cryptimeleon.subzero.model.AugmentedModel
import org.cryptimeleon.subzero.subzero.Model
import org.cryptimeleon.subzero.latex.LatexPreview

/**
 * Generates the Java code for a protocol
 */
class CodeGenerator {
	AugmentedModel augmentedModel;
	
	new(AugmentedModel augmentedModel) {
		this.augmentedModel = augmentedModel;
	}

	def ProjectBuilder generate() {
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
		val String latexText = new LatexPreview(augmentedModel).getRawLatex();
		
		val ProjectFolder root = new ProjectFolder(packageName);
		
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
		
		val ProjectFile mainProtocol = new ProjectFile(protocolName + '.java', protocolCode);
		
		mainPrototype.addFile(mainProtocol);
		
		if (publicParametersCode !== null) {
			val ProjectFile publicParameters = new ProjectFile(publicParametersClassName + '.java', publicParametersCode);
			mainPrototype.addFile(publicParameters);
		}
		
		mainJava.addFolder(mainPrototype);
		main.addFolder(mainJava);
		
		val ProjectFolder test = new ProjectFolder("test");
		val ProjectFolder testJava = new ProjectFolder("java");
		val ProjectFolder testPrototype = new ProjectFolder("prototype");
		
		val ProjectFile testLibrary = new ProjectFile("LibraryTest.java", testCode);
		testPrototype.addFile(testLibrary);

		testJava.addFolder(testPrototype);
		test.addFolder(testJava);
		
		src.addFolder(main);
		src.addFolder(test);
		
		root.addFolder(src);
		
		return new ProjectBuilder(root);
	}
}