package org.cryptimeleon.subzero.builder;

/**
 * A helper class to build strings of generated code.
 * A wrapper of the StringBuilder class that provides additional convenient methods, and handles indentations
 *
 */
public class CodeBuilder {

	public static final String INDENT_CHAR = "\t";
	public static final char INDENT_SIZE = 1;

	private final StringBuilder builder;
	private final String indent;

	private int indentLevel;

	public CodeBuilder() {
		this(0);
	}
	
	public CodeBuilder(int indentLevel) {
		this.builder = new StringBuilder();
		this.indentLevel = indentLevel;
		indent = INDENT_CHAR.repeat(INDENT_SIZE);
	}

	public static CodeBuilder createWithSameIndent(CodeBuilder codeBuilder) {
		return new CodeBuilder(codeBuilder.indentLevel);
	}
	
	private void handleIndent() {
		int length = builder.length();
		boolean isStartOfLine = length == 0 || builder.charAt(length-1) == '\n';
		
		if (isStartOfLine) {
			builder.append(indent.repeat(indentLevel));
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
		handleIndent();
		builder.append(ch);
	}
	
	public void append(String str) {
		handleIndent();
		builder.append(str);
	}
	
	
	// Appended code builder must handle correct indentation
	public void append(CodeBuilder codeBuilder) {
		builder.append(codeBuilder.builder);
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
	
	public void append(Object obj) {
		handleIndent();
		builder.append(obj);
	}
	
	public int getIndent() {
		return indentLevel;
	}
	
	public void space() {
		append(" ");
	}
	
	public void semicolon() {
		append(";");
	}
	
	public void newLine() {
		append('\n');
	}
	
	public void openParen() {
		append('(');
	}
	
	public void closeParen() {
		append(')');
	}
	
	public void openBrace() {
		append('{');
	}
	
	public void closeBrace() {
		append('}');
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}
}
