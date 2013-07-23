package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Project;

@Path("/projects/{projectId}")
public class ProjectResource extends AbstractSingleResource<Project, Project> {

	public ProjectResource() {
		super(Project.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Project asJSON(@PathParam("projectId") int id) {
		return super.asJSON(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Project put(@PathParam("projectId") int id, Project modifications) {
		return super.put(id, modifications);
	}

	@Override
	protected Project mergeModifications(EntityManager entityManager, int id,
			Project modifications) {
		modifications.setId(id);
		return modifications;
	}
}
