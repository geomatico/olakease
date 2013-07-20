package co.geomati.olakease.api;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Project;

@Path("/projects/{projectId}")
public class ProjectResource extends AbstractSingleResource<Project> {

	public ProjectResource() {
		super(Project.class);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Project put(@PathParam("projectId") long id, Project modifications) {
		return super.put(id, modifications);
	}
}
