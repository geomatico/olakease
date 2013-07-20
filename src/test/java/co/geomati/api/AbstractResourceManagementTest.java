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

public abstract class AbstractResourceManagementTest<T extends AbstractIdentifiableEntity>
		extends AbstractAPITest {

	@Test
	public void testPostResource() throws Exception {
		Response response = postResource();
		assertEquals(200, response.getStatus());
	}

	private Response postResource() throws IOException,
			JsonGenerationException, JsonMappingException {
		T resource = createResourceToPost();
		return postResource(resource);
	}

	protected abstract T createResourceToPost();

	private Response postResource(T resource) throws IOException,
			JsonGenerationException, JsonMappingException {
		Response response = target(getPath()).request().post(
				Entity.json(new ObjectMapper().writeValueAsString(resource)));
		return response;
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
		List<T> resources = new ObjectMapper().readValue(response, List.class);
		assertEquals(1, resources.size());
	}

	@Test
	public void testGetResource() throws Exception {
		Response postResponse = postResource();
		assertEquals(200, postResponse.getStatus());
		T postedResource = new ObjectMapper().readValue(
				postResponse.readEntity(String.class), getResourceClass());
		long resourceId = postedResource.getId();

		String getResponse = target(getPath() + "/" + resourceId).request()
				.get(String.class);
		T resource = new ObjectMapper().readValue(getResponse,
				getResourceClass());
		assertEquals(resourceId, resource.getId());
		checkResourceIsEqualPosted(resource);
	}

	protected abstract void checkResourceIsEqualPosted(T resource);

	@Test
	public void testPutResource() throws Exception {
		T resource = createResourceToPost();
		Response postResponse = postResource(resource);
		assertEquals(200, postResponse.getStatus());
		T postedResource = new ObjectMapper().readValue(
				postResponse.readEntity(String.class), getResourceClass());
		long resourceId = postedResource.getId();

		modifyResource(resource);
		Response putResponse = target(getPath() + "/" + resourceId).request()
				.put(Entity.json(new ObjectMapper()
						.writeValueAsString(resource)));
		assertEquals(200, putResponse.getStatus());

		String getResponse = target(getPath() + "/" + resourceId).request()
				.get(String.class);
		T updatedResource = new ObjectMapper().readValue(getResponse,
				getResourceClass());
		assertEquals(resourceId, updatedResource.getId());
		checkResourceIsModified(updatedResource);
	}

	protected abstract void checkResourceIsModified(T updatedResource);

	protected abstract void modifyResource(T resource);

	protected abstract Class<T> getResourceClass();

}
