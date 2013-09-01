package co.geomati.olakease.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class AbstractResourceList<TYPE> {

	private Class<TYPE> typeClass;

	public AbstractResourceList(Class<TYPE> typeClass) {
		this.typeClass = typeClass;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TYPE create(TYPE resource) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(resource);
		transaction.commit();

		return resource;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public TYPE read(@PathParam("id") int id) {
		return ApplicationListener.getEntityManager().find(typeClass, id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TYPE update(TYPE resource) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(resource);
		transaction.commit();

		return resource;
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		TYPE resource = read(id);
		if (null != resource) {
			EntityManager entityManager = ApplicationListener
					.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			ApplicationListener.getEntityManager().remove(resource);
			transaction.commit();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TYPE> asJSON() {
		List<TYPE> results = getQuery().getResultList();
		return results;
	}

	protected abstract TypedQuery<TYPE> getQuery();
}
