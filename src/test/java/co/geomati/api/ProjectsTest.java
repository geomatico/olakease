package co.geomati.api;

import static junit.framework.Assert.assertEquals;
import co.geomati.olakease.persistence.Project;

public class ProjectsTest extends AbstractResourceManagementTest<Project> {

	private static String PUT_DESCRIPTION = "new description";
	private static String PUT_NAME = "new name";
	private static String POST_DESCRIPTION = "Olakease resource management";
	private static String POST_NAME = "olakease";

	@Override
	protected String getCleanSQL() {
		return "DELETE FROM Project";
	}

	@Override
	protected String getPath() {
		return "projects";
	}

	@Override
	protected Class<Project> getResourceClass() {
		return Project.class;
	}

	@Override
	protected Project createResourceToPost() {
		Project project = new Project();
		project.setId(9);
		project.setName(POST_NAME);
		project.setDescription(POST_DESCRIPTION);
		return project;
	}

	@Override
	protected void checkResourceIsEqualPosted(Project project) {
		assertEquals(POST_NAME, project.getName());
		assertEquals(POST_DESCRIPTION, project.getDescription());
	}

	@Override
	protected void modifyResource(Project resource) {
		resource.setDescription(PUT_DESCRIPTION);
		resource.setName(PUT_NAME);
	}

	@Override
	protected void checkResourceIsModified(Project updatedProject) {
		assertEquals(PUT_NAME, updatedProject.getName());
		assertEquals(PUT_DESCRIPTION, updatedProject.getDescription());
	}
}
