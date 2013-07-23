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

public abstract class AbstractResourceList<IN, OUT> {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OUT> asJSON() {
		List<OUT> results = getQuery().getResultList();
		return results;
	}

	protected abstract TypedQuery<OUT> getQuery();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public OUT newProject(IN message) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		OUT jpaEntity = message2jpa(message);
		transaction.begin();
		entityManager.persist(jpaEntity);
		transaction.commit();
		return jpaEntity;
	}

	protected abstract OUT message2jpa(IN message);
}
