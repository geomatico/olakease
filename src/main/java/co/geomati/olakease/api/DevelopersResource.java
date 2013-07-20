package co.geomati.olakease.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Developer;
import co.geomati.olakease.persistence.Project;

@Path("/developers")
public class DevelopersResource {

	private static TypedQuery<Project> query;

	static {
		query = ApplicationListener.getEntityManager().createQuery(
				"Select d from Developer d", Project.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> asJSON() {
		List<Project> results = query.getResultList();
		return results;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Developer newProject(Developer developer) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(developer);
		transaction.commit();
		return developer;
	}
}
