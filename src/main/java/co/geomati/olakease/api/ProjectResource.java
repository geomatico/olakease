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
	public Project asJSON(@PathParam("projectId") String id) {
		long longId;
		try {
			longId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Project ids are numbers");
		}
		Project project = ApplicationListener.getEntityManager().find(
				Project.class, longId);
		return project;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Project put(@PathParam("projectId") String id, Project modifications) {
		long longId;
		try {
			longId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Project ids are numbers");
		}
		EntityManager entityManager = ApplicationListener.getEntityManager();
		Project project = entityManager.find(Project.class, longId);
		if (project != null) {
			modifications.setId(longId);
			entityManager.merge(modifications);
		}

		return modifications;
	}
}
