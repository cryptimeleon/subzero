package org.cryptimeleon.zeroknowledge.generator;

public class SourceBuilder {
	
	private String packageName;
	private ClassBuilder classBuilder;
	private String imports;
	
	public SourceBuilder(String packageName, ClassBuilder classBuilder) {
		this.packageName = packageName;
		this.classBuilder = classBuilder;
		this.imports = "";
	}
	
	// TODO: better way of handling imports
	public void setImports(String imports) {
		this.imports = imports;
	}
	
	@Override
	public String toString() {
		CodeBuilder builder = new CodeBuilder();
		
		builder.append("package ");
		builder.append(packageName);
		builder.append(";");
		builder.newLine();
		builder.newLine();
		
		builder.append(imports);
		builder.newLine();
		
		builder.append(classBuilder);
		
		return builder.toString();
	}
	
}
