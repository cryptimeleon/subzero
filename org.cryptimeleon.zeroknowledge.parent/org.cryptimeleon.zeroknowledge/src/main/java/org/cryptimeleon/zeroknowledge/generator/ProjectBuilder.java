package org.cryptimeleon.zeroknowledge.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProjectBuilder {
	
	private String generatedProject;

	public ProjectBuilder(ProjectFolder root) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		generateProjectHelper(root, builder);
		builder.append("}");
		generatedProject = builder.toString();
	}
	
	public String getProject() {
		return generatedProject;
	}
	
	private void generateProjectHelper(ProjectFolder currentFolder, StringBuilder builder) {
		builder.append("\"");
		builder.append(currentFolder.getName());
		builder.append("\":{");
		
		List<ProjectFile> files = currentFolder.getFiles();
		int numFiles = files.size();
		
		List<ProjectFolder> subFolders = currentFolder.getFolders();
		int numSubFolders = subFolders.size();
		
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
			if (i != numFiles-1 || numSubFolders > 0) builder.append(",");
		}
		
		for (int i = 0; i < numSubFolders; i++) {
			ProjectFolder subFolder = subFolders.get(i);
			generateProjectHelper(subFolder, builder);
			if (i != numSubFolders-1) builder.append(",");
		}
		
		builder.append("}");
	}
	
}
