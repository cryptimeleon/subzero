package org.cryptimeleon.zeroknowledge.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a folder in the generated project
 * 
 */
public class ProjectFolder {
	
	private String name;
	private List<ProjectFolder> subFolders;
	private List<ProjectFile> files;
	
	
	public ProjectFolder(String name) {
		this.name = name;
		files = new ArrayList<ProjectFile>();
		subFolders = new ArrayList<ProjectFolder>();
	}
	
	public void addFolder(ProjectFolder folder) {
		subFolders.add(folder);
	}
	
	public void addFile(ProjectFile file) {
		files.add(file);
	}

	public String getName() {
		return name;
	}

	public List<ProjectFolder> getFolders() {
		return subFolders;
	}

	public List<ProjectFile> getFiles() {
		return files;
	}
}

