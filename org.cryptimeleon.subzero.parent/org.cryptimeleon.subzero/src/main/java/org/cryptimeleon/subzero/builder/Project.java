package org.cryptimeleon.subzero.builder;

/**
 * Represents a generated project, which is a serialized JSON object representing
 * the directory structure of the generated project and containing each generated
 * file's contents
 */
public class Project {
	
	private ProjectDirectory root;

	public Project(ProjectDirectory root) {
		this.root = root;
	}
	
	@Override
	public String toString() {
		JsonBuilder projectObject = new JsonBuilder();
		projectObject.addObjectProperty(root.getName(), buildProject(root));
		return projectObject.toString();
	}
	
	private JsonBuilder buildProject(ProjectDirectory currentDirectory) {
		JsonBuilder builder = new JsonBuilder();
		
		for (ProjectFile file : currentDirectory.getFiles()) {
			builder.addStringProperty(file.getName(), file.getContents());
		}
		
		for (ProjectDirectory subDirectory : currentDirectory.getDirectories()) {
			builder.addObjectProperty(subDirectory.getName(), buildProject(subDirectory));
		}
		
		return builder;
	}
	
}
