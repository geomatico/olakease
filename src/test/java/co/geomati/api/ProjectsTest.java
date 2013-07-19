package co.geomati.api;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.geomati.olakease.api.ProjectsResource;
import co.geomati.olakease.persistence.Project;

public class ProjectsTest extends JerseyTest {

	private static Server server;

	@BeforeClass
	public static void startDBMS() throws Exception {
		server = Server.createTcpServer();
		if (server.start() == null) {
			throw new Exception("Cannot start server");
		}
	}

	@AfterClass
	public static void stopDBMS() throws Exception {
		server.stop();
	}

	@After
	@Before
	public void clearDB() {
		File db = new File("tmp/test.h2.db");
		assertTrue(!db.exists() || db.delete());
	}

	@Override
	protected Application configure() {
		ResourceConfig resourceConfig = new ResourceConfig(
				ProjectsResource.class);
		resourceConfig.packages("co.geomati.olakease.api;"
				+ "org.codehaus.jackson.jaxrs");
		return resourceConfig;
	}

	@Test
	public void testPostProject() throws Exception {
		Project project = new Project();
		project.setId(9);
		project.setName("olakease");
		project.setDescription("Olakease resource management");
		Response response = target("projects").request().post(
				Entity.json(new ObjectMapper().writeValueAsString(project)));
		assertEquals(200, response.getStatus());
	}

	@Test
	public void testPostProjectWrongParameterTypes() throws Exception {
		Response response = target("projects").request().post(
				Entity.json("{\"id\":\"a\", \"name\":\"olakease\", "
						+ "\"description\":\"olakease\"}"));
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testGetProjects() throws Exception {
		testPostProject();

		String response = target("projects").request().get(String.class);
		@SuppressWarnings("unchecked")
		List<Project> projects = new ObjectMapper().readValue(response,
				List.class);
		assertEquals(1, projects.size());
	}

	@Test
	public void testGetProject() throws Exception {
		testPostProject();

		String response = target("projects/1").request().get(String.class);
		Project project = new ObjectMapper().readValue(response, Project.class);
		assertEquals(1, project.getId());
		assertEquals("olakease", project.getName());
	}

}
