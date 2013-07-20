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
public class DeveloperResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Developer asJSON(@PathParam("developerId") long id) {
		Developer developer = ApplicationListener.getEntityManager().find(
				Developer.class, id);
		return developer;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Developer put(@PathParam("developerId") long id,
			Developer modifications) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		Developer developer = entityManager.find(Developer.class, id);
		if (developer != null) {
			modifications.setId(id);
			entityManager.merge(modifications);
		}

		return modifications;
	}
}
