package co.geomati.api;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import co.geomati.olakease.persistence.AbstractIdentifiableEntity;

public abstract class AbstractResourceManagementTest<IN, OUT extends AbstractIdentifiableEntity>
		extends AbstractAPITest {

	@Test
	public void testPostResource() throws Exception {
		postResource();
	}

	private OUT postResource() throws IOException, JsonGenerationException,
			JsonMappingException {
		IN resource = createResourceToPost();
		return postResource(getPath(), resource, getOUTResourceClass());
	}

	protected abstract IN createResourceToPost() throws IOException;

	protected <LOCAL_IN, LOCAL_OUT> LOCAL_OUT postResource(String path,
			LOCAL_IN resource, Class<LOCAL_OUT> returnClass)
			throws IOException, JsonGenerationException, JsonMappingException {
		Response response = target(path).request().post(
				Entity.json(new ObjectMapper().writeValueAsString(resource)));
		assertEquals(200, response.getStatus());
		LOCAL_OUT postedResource = new ObjectMapper().readValue(
				response.readEntity(String.class), returnClass);
		return postedResource;
	}

	protected abstract String getPath();

	@Test
	public void testPostResourceWrongParameterTypes() throws Exception {
		Response response = target(getPath()).request().post(
				Entity.json("{\"id\":\"a\"}"));
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testGetResources() throws Exception {
		testPostResource();

		String response = target(getPath()).request().get(String.class);
		@SuppressWarnings("unchecked")
		List<OUT> resources = new ObjectMapper()
				.readValue(response, List.class);
		assertEquals(1, resources.size());
	}

	@Test
	public void testGetResource() throws Exception {
		OUT postedResource = postResource();
		int resourceId = postedResource.getId();

		String getResponse = target(getPath() + "/" + resourceId).request()
				.get(String.class);
		OUT resource = new ObjectMapper().readValue(getResponse,
				getOUTResourceClass());
		assertEquals(resourceId, resource.getId());
		checkResourceIsEqualPosted(resource);
	}

	protected abstract void checkResourceIsEqualPosted(OUT resource);

	@Test
	public void testPutResource() throws Exception {
		IN message = createResourceToPost();
		OUT postedResource = postResource(getPath(), message,
				getOUTResourceClass());
		int resourceId = postedResource.getId();

		modifyResource(message);
		Response putResponse = target(getPath() + "/" + resourceId)
				.request()
				.put(Entity.json(new ObjectMapper().writeValueAsString(message)));
		assertEquals(200, putResponse.getStatus());

		String getResponse = target(getPath() + "/" + resourceId).request()
				.get(String.class);
		OUT updatedResource = new ObjectMapper().readValue(getResponse,
				getOUTResourceClass());
		assertEquals(resourceId, updatedResource.getId());
		checkResourceIsModified(updatedResource);
	}

	protected abstract void checkResourceIsModified(OUT updatedResource);

	protected abstract void modifyResource(IN resource) throws IOException;

	protected abstract Class<OUT> getOUTResourceClass();

}
