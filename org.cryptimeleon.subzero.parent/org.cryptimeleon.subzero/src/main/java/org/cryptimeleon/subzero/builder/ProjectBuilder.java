package org.cryptimeleon.subzero.builder;

import java.util.List;

/**
 * Represents a generated project.
 * Generates a serialized JSON object representing the directory structure of the generated project
 * and containing each generated file's contents
 */
public class ProjectBuilder {
	
	private ProjectDirectory root;

	public ProjectBuilder(ProjectDirectory root) {
		this.root = root;
		
	}
	
	public String getProject() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("{");
		generateProjectHelper(root, builder);
		builder.append("}");
		
		return builder.toString();
	}
	
	private void generateProjectHelper(ProjectDirectory currentDirectory, StringBuilder builder) {
		builder.append("\"");
		builder.append(currentDirectory.getName());
		builder.append("\":{");
		
		List<ProjectFile> files = currentDirectory.getFiles();
		int numFiles = files.size();
		
		List<ProjectDirectory> subDirectories = currentDirectory.getDirectories();
		int numSubDirectories = subDirectories.size();
		
		for (int i = 0; i < numFiles; i++) {
			ProjectFile file = files.get(i);
			builder.append("\"");
			builder.append(file.getName());
			builder.append("\":\"");
			String contents = file.getContents()
					.replace("\\", "\\\\")
					.replace("\t", "\\t")
					.replace("\n", "\\n")
					.replace("\r", "\\r")
					.replace("\"", "\\\"");
			builder.append(contents);
			builder.append("\"");
			if (i != numFiles-1 || numSubDirectories > 0) builder.append(",");
		}
		
		for (int i = 0; i < numSubDirectories; i++) {
			ProjectDirectory subDirectory = subDirectories.get(i);
			generateProjectHelper(subDirectory, builder);
			if (i != numSubDirectories-1) builder.append(",");
		}
		
		builder.append("}");
	}
	
}
