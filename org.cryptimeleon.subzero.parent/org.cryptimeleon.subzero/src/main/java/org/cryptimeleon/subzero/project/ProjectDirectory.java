package org.cryptimeleon.subzero.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the generated project, which can contain files and subdirectories
 */
public class ProjectDirectory {
	
	private final String name;
	private final List<ProjectDirectory> subDirectories;
	private final List<ProjectFile> files;
	
	public ProjectDirectory(String name) {
		this.name = name;
		files = new ArrayList<>();
		subDirectories = new ArrayList<>();
	}
	
	public void addDirectory(ProjectDirectory directory) {
		subDirectories.add(directory);
	}
	
	public void addFile(ProjectFile file) {
		files.add(file);
	}

	public String getName() {
		return name;
	}

	public List<ProjectDirectory> getDirectories() {
		return subDirectories;
	}

	public List<ProjectFile> getFiles() {
		return files;
	}
}

