package org.cryptimeleon.zeroknowledge.console;

/**
 * A class for sending messages to the compiler website's console box.
 * Currently unused and not working.
 */
public class Console {

	public static void warning(String message) {
		System.out.println("Warning: " + message);
	}
	
	
	public static void error(String message) {
		System.out.println("Error: " + message);
	}
	
	public static void info(String message) {
		System.out.println("Info: " + message);
	}
}
