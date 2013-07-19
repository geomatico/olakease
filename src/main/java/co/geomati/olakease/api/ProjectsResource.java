package co.geomati.olakease.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Project;

@Path("/projects")
public class ProjectsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> asJSON(@PathParam("id") String id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("olakease");
		EntityManager entityManager = emf.createEntityManager();
		try {
			TypedQuery<Project> query = entityManager.createQuery(
					"Select p from Project p", Project.class);
			List<Project> results = query.getResultList();
			System.out.println(results.size());
			return results;
		} finally {
			entityManager.close();
			emf.close();
		}
	}

}
