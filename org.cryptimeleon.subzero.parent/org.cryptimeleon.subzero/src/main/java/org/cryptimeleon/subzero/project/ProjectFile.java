package org.cryptimeleon.subzero.project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a file in the generated project.
 * The file contents can be provided directly (in text), or 
 * the file can be loaded (from text or binary)
 */
public class ProjectFile {

	private static final Map<String, String> fileCache = new HashMap<>();

	private final String name;
	private String contents;
	
	public ProjectFile(String name, String contents) {
		this.name = name;
		this.contents = contents;
	}

	// A ProjectFile should not be constructed for a file whose contents can change,
	// as the contents of the file are only read once and then cached
	public ProjectFile(String name, String partialPath, boolean isBinary) {
		this.name = name;
		String path = "/" + partialPath + '/' + name;

		contents = fileCache.get(path);
		if (contents != null) {
			return;
		}
		
		try {
			byte[] fileContent = ProjectFile.class.getResourceAsStream(path).readAllBytes();

			if (isBinary) {		
				contents = Base64.getEncoder().encodeToString(fileContent);
			} else {
				contents = new String(fileContent, StandardCharsets.UTF_8);
			}

			fileCache.put(path, contents);

		} catch (NullPointerException | IOException e) {
			System.err.println("Cannot read project file: " + path);
		}
	}

	public String getName() {
		return name;
	}

	public String getContents() {
		return contents;
	}
	
	
}
