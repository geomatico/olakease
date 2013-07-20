package co.geomati.api;

import static junit.framework.Assert.assertEquals;
import co.geomati.olakease.persistence.Developer;

public class DevelopersTest extends AbstractResourceManagementTest<Developer> {

	private static String PUT_NAME = "new name";
	private static String POST_NAME = "olakease";

	@Override
	protected String getCleanSQL() {
		return "DELETE FROM Developer";
	}

	@Override
	protected String getPath() {
		return "developers";
	}

	@Override
	protected Class<Developer> getResourceClass() {
		return Developer.class;
	}

	@Override
	protected Developer createResourceToPost() {
		Developer developer = new Developer();
		developer.setId(9);
		developer.setName(POST_NAME);
		return developer;
	}

	@Override
	protected void checkResourceIsEqualPosted(Developer resource) {
		assertEquals(POST_NAME, resource.getName());
	}

	@Override
	protected void modifyResource(Developer resource) {
		resource.setName(PUT_NAME);
	}

	@Override
	protected void checkResourceIsModified(Developer updatedResource) {
		assertEquals(PUT_NAME, updatedResource.getName());
	}
}
