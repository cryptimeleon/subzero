package org.cryptimeleon.subzero.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the generated project, which can contain files and subdirectories
 */
public class ProjectDirectory {
	
	private String name;
	private List<ProjectDirectory> subDirectories;
	private List<ProjectFile> files;
	
	public ProjectDirectory(String name) {
		this.name = name;
		files = new ArrayList<ProjectFile>();
		subDirectories = new ArrayList<ProjectDirectory>();
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

