package org.cryptimeleon.subzero.builder;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorBuilder {
	private String stackTrace;
	
	public ErrorBuilder(Throwable e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		stackTrace = "{\"error\": \"" + stringWriter.toString()
				.replace("\\", "\\\\")
				.replace("\t", "\\t")
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\"", "\\\"") + "\"}";
	}
	
	@Override
	public String toString() {
		return stackTrace;
	}
}
