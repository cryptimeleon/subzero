package org.cryptimeleon.subzero.builder;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Represents the serialized JSON object of a Java error stack trace thrown during code generation
 */
public class StackTrace {
	private String trace;
	
	public StackTrace(Throwable e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		trace = "{\"error\": \"" + stringWriter.toString()
				.replace("\\", "\\\\")
				.replace("\t", "\\t")
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\"", "\\\"") + "\"}";
	}
	
	@Override
	public String toString() {
		return trace;
	}
}
