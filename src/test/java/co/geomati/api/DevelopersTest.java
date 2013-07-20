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
import co.geomati.olakease.api.DevelopersResource;
import co.geomati.olakease.persistence.Developer;

public class DevelopersTest extends JerseyTest {

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
		entityManager.createQuery("DELETE FROM Developer").executeUpdate();
		transaction.commit();
	}

	@Override
	protected Application configure() {
		ResourceConfig resourceConfig = new ResourceConfig(
				DevelopersResource.class);
		resourceConfig.packages("co.geomati.olakease.api;"
				+ "org.codehaus.jackson.jaxrs");
		return resourceConfig;
	}

	@Test
	public void testPostDeveloper() throws Exception {
		Response response = postDeveloper();
		assertEquals(200, response.getStatus());
	}

	private Response postDeveloper() throws IOException,
			JsonGenerationException, JsonMappingException {
		Developer developer = new Developer();
		developer.setId(9);
		developer.setName("fergonco");
		return postDeveloper(developer);
	}

	private Response postDeveloper(Developer project) throws IOException,
			JsonGenerationException, JsonMappingException {
		Response response = target("developers").request().post(
				Entity.json(new ObjectMapper().writeValueAsString(project)));
		return response;
	}

	@Test
	public void testPostDeveloperWrongParameterTypes() throws Exception {
		Response response = target("developers").request().post(
				Entity.json("{\"id\":\"a\", \"name\":\"fergonco\"}"));
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testGetDevelopers() throws Exception {
		testPostDeveloper();

		String response = target("developers").request().get(String.class);
		@SuppressWarnings("unchecked")
		List<Developer> developers = new ObjectMapper().readValue(response,
				List.class);
		assertEquals(1, developers.size());
	}

	@Test
	public void testGetDeveloper() throws Exception {
		Response postResponse = postDeveloper();
		assertEquals(200, postResponse.getStatus());
		Developer postedDeveloper = new ObjectMapper().readValue(
				postResponse.readEntity(String.class), Developer.class);
		long projectId = postedDeveloper.getId();

		String getResponse = target("developers/" + projectId).request().get(
				String.class);
		Developer project = new ObjectMapper().readValue(getResponse,
				Developer.class);
		assertEquals(projectId, project.getId());
		assertEquals("fergonco", project.getName());
	}

	@Test
	public void testPutDeveloper() throws Exception {
		Developer project = new Developer();
		project.setId(9);
		project.setName("fergonco");
		Response postResponse = postDeveloper(project);
		assertEquals(200, postResponse.getStatus());
		Developer postedDeveloper = new ObjectMapper().readValue(
				postResponse.readEntity(String.class), Developer.class);
		long projectId = postedDeveloper.getId();

		String editedName = "Fernando";
		project.setName(editedName);
		Response putResponse = target("developers/" + projectId).request().put(
				Entity.json(new ObjectMapper().writeValueAsString(project)));
		assertEquals(200, putResponse.getStatus());

		String getResponse = target("developers/" + projectId).request().get(
				String.class);
		Developer updatedDeveloper = new ObjectMapper().readValue(getResponse,
				Developer.class);
		assertEquals(projectId, updatedDeveloper.getId());
		assertEquals(editedName, updatedDeveloper.getName());
	}
}
