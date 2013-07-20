package co.geomati.olakease.api;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Developer;

@Path("/developers/{developerId}")
public class DeveloperResource extends AbstractSingleResource<Developer> {

	public DeveloperResource() {
		super(Developer.class);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Developer put(@PathParam("developerId") long id,
			Developer modifications) {
		return super.put(id, modifications);
	}
}
