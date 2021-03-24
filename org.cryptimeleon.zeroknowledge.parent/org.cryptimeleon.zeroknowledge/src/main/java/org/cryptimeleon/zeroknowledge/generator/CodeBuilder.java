package org.cryptimeleon.zeroknowledge.generator;


/**
 * A helper class to build strings of generated code.
 * A wrapper of the StringBuilder class that provides additional convenient methods, and handles indentations
 *
 */
public class CodeBuilder {

	public static final char INDENT_CHAR = '\t';
	public static final char INDENT_SIZE = 1;
	
	private int indentLevel;
	private String indent;
	private StringBuilder builder;
	private boolean isStartOfLine;
	
	public CodeBuilder() {
		this(0);
	}
	
	public CodeBuilder(int indentLevel) {
		this.builder = new StringBuilder();
		this.isStartOfLine = true;
		this.indentLevel = indentLevel;
		indent = "";
		for (int i = 0; i < INDENT_SIZE; i++) {
			indent += INDENT_CHAR;
		}
	}
	
	private void addIndent() {
		for (int i = 0; i < indentLevel; i++) {
			builder.append(indent);
		}
	}
	
	public void indent() {
		indentLevel++;
	}
	
	public void indent(int levels) {
		indentLevel += levels;
	}
	
	public void outdent() {
		if (indentLevel == 0) return;
		indentLevel--;
	}
	
	public void outdent(int levels) {
		if (indentLevel == 0) return;
		indentLevel-= levels;
	}
	
	public void append(char ch) {
		if (isStartOfLine) {
			addIndent();
			isStartOfLine = false;
		}
		builder.append(ch);
	}
	
	public void append(String str) {
		if (isStartOfLine) {
			addIndent();
			isStartOfLine = false;
		}
		builder.append(str);
	}
	
	public void append(FieldBuilder fieldBuilder) {
		builder.append(fieldBuilder.toString(indentLevel));
	}
	
	public void append(MethodBuilder methodBuilder) {
		builder.append(methodBuilder.toString(indentLevel));
	}
	
	public void append(ConstructorBuilder constructorBuilder) {
		builder.append(constructorBuilder.toString(indentLevel));
	}
	
	public void append(ClassBuilder classBuilder) {
		builder.append(classBuilder.toString(indentLevel));
	}
	
	public int getIndent() {
		return indentLevel;
	}
	
	public void space() {
		builder.append(" ");
	}
	
	public void newLine() {
		builder.append('\n');
		isStartOfLine = true;
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}

	
}
