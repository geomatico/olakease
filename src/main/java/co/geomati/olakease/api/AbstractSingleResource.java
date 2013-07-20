package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.AbstractIdentifiableEntity;
import co.geomati.olakease.persistence.Developer;

public class AbstractSingleResource<T extends AbstractIdentifiableEntity> {

	private Class<T> resourceClass;

	public AbstractSingleResource(Class<T> resourceClass) {
		this.resourceClass = resourceClass;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public T asJSON(@PathParam("developerId") long id) {
		T developer = ApplicationListener.getEntityManager().find(
				resourceClass, id);
		return developer;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public T put(@PathParam("developerId") long id, T modifications) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		Developer developer = entityManager.find(Developer.class, id);
		if (developer != null) {
			modifications.setId(id);
			entityManager.merge(modifications);
		}

		return modifications;
	}

}
