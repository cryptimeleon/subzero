package org.cryptimeleon.subzero.builder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Represents a file in the generated project.
 * The file contents can be provided directly (in text), or 
 * the file can be loaded (from text or binary)
 */
public class ProjectFile {
	private String name;
	private String contents;
	
	public ProjectFile(String name, String contents) {
		this.name = name;
		this.contents = contents;
	}
	
	public ProjectFile(String name, String partialPath, boolean isBinary) {
		this.name = name;
		String path = partialPath + '/' + name;
		
		try {
			if (isBinary) {
				byte[] fileContent = Files.readAllBytes(Paths.get(path));
				contents = Base64.getEncoder().encodeToString(fileContent);
			} else {
				contents = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			System.out.println("Cannot read project file: " + path);
		}
	}

	public String getName() {
		return name;
	}

	public String getContents() {
		return contents;
	}
	
	
}
