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
public class ProjectResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Project asJSON(@PathParam("projectId") long id) {
		Project project = ApplicationListener.getEntityManager().find(
				Project.class, id);
		return project;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Project put(@PathParam("projectId") long id, Project modifications) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		Project project = entityManager.find(Project.class, id);
		if (project != null) {
			modifications.setId(id);
			entityManager.merge(modifications);
		}

		return modifications;
	}
}
