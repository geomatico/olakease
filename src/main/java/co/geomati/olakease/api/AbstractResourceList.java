package co.geomati.olakease.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class AbstractResourceList<T> {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> asJSON() {
		List<T> results = getQuery().getResultList();
		return results;
	}

	protected abstract TypedQuery<T> getQuery();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public T newProject(T jpaEntity) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(jpaEntity);
		transaction.commit();
		return jpaEntity;
	}
}
