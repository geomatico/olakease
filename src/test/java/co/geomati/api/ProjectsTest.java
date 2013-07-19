package co.geomati.api;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.h2.tools.Server;
import org.junit.AfterClass;
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
		String response = target("projects").request().get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = mapper.getJsonFactory();
		JsonParser jp = factory.createJsonParser(response);
		JsonNode actualObj = mapper.readTree(jp);
		assertEquals(1, actualObj.size());
	}

}
