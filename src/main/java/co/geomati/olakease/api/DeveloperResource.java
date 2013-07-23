package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Developer;

@Path("/developers/{developerId}")
public class DeveloperResource extends
		AbstractSingleResource<Developer, Developer> {

	public DeveloperResource() {
		super(Developer.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Developer asJSON(@PathParam("developerId") int id) {
		return super.asJSON(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Developer put(@PathParam("developerId") int id,
			Developer modifications) {
		return super.put(id, modifications);
	}

	@Override
	protected Developer mergeModifications(EntityManager entityManager, int id,
			Developer modifications) {
		modifications.setId(id);
		return modifications;
	}
}
