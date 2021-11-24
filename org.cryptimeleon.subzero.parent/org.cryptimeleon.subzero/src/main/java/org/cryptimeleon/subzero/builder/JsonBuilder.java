package org.cryptimeleon.subzero.builder;

import java.util.List;

/**
 * Represents a serialized JSON object
 */
public class JsonBuilder {

	private StringBuilder builder;
	private boolean commaNeeded;
	private boolean isArray;
	private boolean inArrayValue;
	
	public JsonBuilder() {
		this(false);
	}
	
	public JsonBuilder(boolean isArray) {
		this.isArray = isArray;
		builder = new StringBuilder();
		
		if (isArray) {
			builder.append('[');
		} else {
			builder.append('{');
		}
		
		commaNeeded = false;
		inArrayValue = isArray;
	}
	
	private void addQuote() {
		builder.append("\"");
	}
	
	private void addColon() {
		builder.append(":");
	}
	
	private void addComma() {
		if (commaNeeded) builder.append(",");
		commaNeeded = true;
	}
	
	private void checkNotInArray() {
		if (inArrayValue) {
			throw new IllegalStateException("Cannot add object property when in an array");
		}
	}
	
	private void checkInArray() {
		if (!inArrayValue) {
			throw new IllegalStateException("Cannot add value when not in an array");
		}
	}
	
	private String escapeString(String value) {
		return value
			.replace("\\", "\\\\")
			.replace("\t", "\\t")
			.replace("\n", "\\n")
			.replace("\r", "\\r")
			.replace("\"", "\\\"");
	}
	
	private void addPropertyName(String propertyName) {
		checkNotInArray();
		addQuote();
		builder.append(propertyName);
		addQuote();
	}
	
	public void addObjectProperty(String propertyName, JsonBuilder jsonBuilder) {
		addComma();
		addPropertyName(propertyName);
		addColon();
		builder.append(jsonBuilder.toString());
	}
	
	public void addBooleanProperty(String propertyName, boolean value) {
		checkNotInArray();
		addComma();
		addPropertyName(propertyName);
		builder.append(value);
		addQuote();
	}
	
	public void addNumberProperty(String propertyName, int value) {
		addComma();
		addPropertyName(propertyName);
		builder.append(value);
	}
	
	public void addNumberProperty(String propertyName, double value) {
		checkNotInArray();
		addComma();
		addPropertyName(propertyName);
		builder.append(value);
	}
	
	public void addNumberProperty(String propertyName, float value) {
		checkNotInArray();
		addComma();
		addPropertyName(propertyName);
		builder.append(value);
	}

	public void addStringProperty(String propertyName, String value) {
		checkNotInArray();
		addComma();
		addPropertyName(propertyName);
		addColon();
		addQuote();
		builder.append(escapeString(value));
		addQuote();
	}
	
	public void addArrayProperty(String propertyName, List<String> values) {
		checkNotInArray();
		addComma();
		addPropertyName(propertyName);
		addColon();
		builder.append('[');
		
		inArrayValue = true;
		commaNeeded = false;
		for (String value : values ) {
			addStringValue(value);
		}
		
		builder.append(']');
		inArrayValue = false;
	}
	
	public void startArrayProperty(String propertyName) {
		inArrayValue = true;
		addComma();
		addPropertyName(propertyName);
		addColon();
		builder.append('[');
	}
	
	public void endArrayProperty() {
		builder.append(']');
		inArrayValue = false;
	}
	
	public void addBooleanValue(boolean value) {
		checkInArray();
		addComma();
		builder.append(value);
	}
	
	public void addNumberValue(int value) {
		checkInArray();
		addComma();
		builder.append(value);
	}
	
	public void addNumberValue(double value) {
		checkInArray();
		addComma();
		builder.append(value);
	}
	
	public void addNumberValue(float value) {
		checkInArray();
		addComma();
		builder.append(value);
	}
	
	public void addStringValue(String value) {
		checkInArray();
		addComma();
		addQuote();
		builder.append(escapeString(value));
		addQuote();
	}
	
	public void addObjectValue(JsonBuilder jsonBuilder) {
		addComma();
		builder.append(jsonBuilder.toString());
	}
	
	@Override
	public String toString() {
		char terminator = isArray ? ']' : '}';
		return builder.toString() + terminator;
	}
	
}
