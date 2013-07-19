package co.geomati.api;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.geomati.olakease.api.ApplicationListener;
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
		new ApplicationListener().contextInitialized(null);
	}

	@AfterClass
	public static void stopDBMS() throws Exception {
		server.stop();
		new ApplicationListener().contextDestroyed(null);
	}

	@Before
	public void clearDB() {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("DELETE FROM Project").executeUpdate();
		transaction.commit();
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
		Response response = postProject();
		assertEquals(200, response.getStatus());
	}

	private Response postProject() throws IOException, JsonGenerationException,
			JsonMappingException {
		Project project = new Project();
		project.setId(9);
		project.setName("olakease");
		project.setDescription("Olakease resource management");
		Response response = target("projects").request().post(
				Entity.json(new ObjectMapper().writeValueAsString(project)));
		return response;
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
		Response postResponse = postProject();
		assertEquals(200, postResponse.getStatus());
		Project postedProject = new ObjectMapper().readValue(
				postResponse.readEntity(String.class), Project.class);

		String getResponse = target("projects/" + postedProject.getId())
				.request().get(String.class);
		Project project = new ObjectMapper().readValue(getResponse,
				Project.class);
		assertEquals(postedProject.getId(), project.getId());
		assertEquals("olakease", project.getName());
	}

}
