package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("olakease");
		EntityManager entityManager = emf.createEntityManager();
		try {
			long longId;
			try {
				longId = Long.parseLong(id);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Project ids are numbers");
			}
			Project project = entityManager.find(Project.class, longId);
			return project;
		} finally {
			entityManager.close();
			emf.close();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void newProject(@PathParam("projectId") String id,
			@FormParam("name") String name,
			@FormParam("description") String description) {
		throw new UnsupportedOperationException(
				"http://www.vogella.com/articles/REST/#first_class");
	}
}
