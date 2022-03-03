package org.cryptimeleon.subzero.java;

import org.cryptimeleon.subzero.builder.SourceBuilder;
import org.cryptimeleon.subzero.generator.CodeGenerator;
import org.cryptimeleon.subzero.generator.GenerationUtils;
import org.cryptimeleon.subzero.latex.LatexGenerator;
import org.cryptimeleon.subzero.model.AugmentedModel;
import org.cryptimeleon.subzero.project.Project;
import org.cryptimeleon.subzero.project.ProjectDirectory;
import org.cryptimeleon.subzero.project.ProjectFile;

/**
 * Generates the Java code for a protocol
 */
public class JavaGenerator implements CodeGenerator {
    private final AugmentedModel augmentedModel;

	public JavaGenerator(AugmentedModel augmentedModel) {
        this.augmentedModel = augmentedModel;
    }

    @Override
    public String generate() {
        try {
            Project project = buildJavaProject();
            return project.toString();
        } catch (Throwable e) {
            StackTrace error = new StackTrace(e);
            return error.toString();
        }
    }

    private Project buildJavaProject() {
        // For debugging purposes
        System.out.println(augmentedModel);

        String protocolName = augmentedModel.getProtocolName();
        String packageName = augmentedModel.getPackageName();
        String publicParametersClassName = GenerationUtils.createPublicParametersClassName(protocolName);

        // Create protocol class
        SourceBuilder protocolSource = new ProtocolClassGenerator(augmentedModel).generate();
        String protocolCode = protocolSource.toString();

        // Create library test class
        SourceBuilder testSource = new TestClassGenerator(augmentedModel).generate();
        String testCode = testSource.toString();

        // Create public parameters class, if needed
        String publicParametersCode = null;
        if (augmentedModel.requiresPublicParametersClass()) {
            SourceBuilder publicParametersSource = new PublicParametersClassGenerator(augmentedModel).generate();
            publicParametersCode = publicParametersSource.toString();
        }

        // Fetch the raw DSL code
        String rawCode = augmentedModel.getCode();

        // Fetch the LaTeX text
        String latexText = new LatexGenerator(augmentedModel).generate();

        ProjectDirectory root = new ProjectDirectory(packageName);

        ProjectFile code = new ProjectFile(protocolName + ".sub0", rawCode);
        ProjectFile latex = new ProjectFile(protocolName + ".tex", latexText);
        ProjectFile gradleBuilder = new ProjectFile("build.gradle", "project", false);
        ProjectFile gradleSettings = new ProjectFile("settings.gradle", "project", false);
        ProjectFile gradleBat = new ProjectFile("gradlew.bat", "project", false);
        ProjectFile gradlew = new ProjectFile("gradlew", "project", false);
        ProjectFile dotProject = new ProjectFile(".project", "project", false);
        ProjectFile dotClasspath = new ProjectFile(".classpath", "project", false);

        root.addFile(code);
        root.addFile(latex);
        root.addFile(gradleBuilder);
        root.addFile(gradleSettings);
        root.addFile(gradleBat);
        root.addFile(gradlew);
        root.addFile(dotProject);
        root.addFile(dotClasspath);

        ProjectDirectory gradle = new ProjectDirectory("gradle");
        ProjectDirectory wrapper = new ProjectDirectory("wrapper");
        ProjectFile gradleWrapperJar = new ProjectFile("gradle-wrapper.jar", "project", true);
        ProjectFile gradleWrapperSettings = new ProjectFile("gradle-wrapper.properties", "project", false);

        wrapper.addFile(gradleWrapperJar);
        wrapper.addFile(gradleWrapperSettings);

        gradle.addDirectory(wrapper);
        root.addDirectory(gradle);

        ProjectDirectory src = new ProjectDirectory("src");

        ProjectDirectory main = new ProjectDirectory("main");
        ProjectDirectory mainJava = new ProjectDirectory("java");
        ProjectDirectory mainPrototype = new ProjectDirectory("prototype");

        ProjectFile mainProtocol = new ProjectFile(protocolName + ".java", protocolCode);

        mainPrototype.addFile(mainProtocol);

        if (publicParametersCode != null) {
            ProjectFile publicParameters = new ProjectFile(publicParametersClassName + ".java", publicParametersCode);
            mainPrototype.addFile(publicParameters);
        }

        mainJava.addDirectory(mainPrototype);
        main.addDirectory(mainJava);

        ProjectDirectory test = new ProjectDirectory("test");
        ProjectDirectory testJava = new ProjectDirectory("java");
        ProjectDirectory testPrototype = new ProjectDirectory("prototype");

        ProjectFile testLibrary = new ProjectFile("LibraryTest.java", testCode);
        testPrototype.addFile(testLibrary);

        testJava.addDirectory(testPrototype);
        test.addDirectory(testJava);

        src.addDirectory(main);
        src.addDirectory(test);

        root.addDirectory(src);

        return new Project(root);
    }
}