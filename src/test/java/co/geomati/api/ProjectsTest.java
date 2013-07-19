package co.geomati.api;

import static junit.framework.Assert.fail;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.geomati.olakease.api.ProjectsResource;

public class ProjectsTest extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig resourceConfig = new ResourceConfig(
				ProjectsResource.class);
		resourceConfig
				.packages("co.geomati.olakease.api;org.codehaus.jackson.jaxrs");
		return resourceConfig;
	}

	@Test
	public void testGetProjects() {
		String hello = target("projects").request().get(String.class);
		System.out.println(hello);
		fail();
	}

}
