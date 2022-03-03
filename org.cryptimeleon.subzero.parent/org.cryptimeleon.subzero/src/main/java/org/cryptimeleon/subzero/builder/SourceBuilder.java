package org.cryptimeleon.subzero.builder;

/**
 * Represents a single source file in generated code (consisting of a class, its package
 * declaration, and its required imports)
 */
public class SourceBuilder {
	
	private final String packageName;
	private final ClassBuilder classBuilder;
	private final ImportBuilder importBuilder;
	
	public SourceBuilder(String packageName, ClassBuilder classBuilder) {
		this(packageName, classBuilder, new ImportBuilder());
	}
	
	public SourceBuilder(String packageName, ClassBuilder classBuilder, ImportBuilder importBuilder) {
		this.packageName = packageName;
		this.classBuilder = classBuilder;
		this.importBuilder = importBuilder;
	}
	
	@Override
	public String toString() {
		CodeBuilder builder = new CodeBuilder();
		
		builder.append("package ");
		builder.append(packageName);
		builder.append(";");
		builder.newLine();
		builder.newLine();
		
		builder.append(importBuilder);
		builder.newLine();
		
		builder.append(classBuilder);

		return builder.toString();
	}
}
