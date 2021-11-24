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
		
		JsonBuilder builder = new JsonBuilder();
		builder.addStringProperty("error", stringWriter.toString());
		
		trace = builder.toString();
	}
	
	@Override
	public String toString() {
		return trace;
	}
}
