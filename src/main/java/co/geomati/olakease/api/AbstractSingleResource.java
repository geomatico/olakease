package co.geomati.olakease.api;

import javax.persistence.EntityManager;

import co.geomati.olakease.persistence.AbstractIdentifiableEntity;

public class AbstractSingleResource<T extends AbstractIdentifiableEntity> {

	private Class<T> resourceClass;

	public AbstractSingleResource(Class<T> resourceClass) {
		this.resourceClass = resourceClass;
	}

	public T asJSON(long id) {
		T jpaEntity = ApplicationListener.getEntityManager().find(
				resourceClass, id);
		return jpaEntity;
	}

	public T put(long id, T modifications) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		T original = entityManager.find(resourceClass, id);
		if (original != null) {
			modifications.setId(id);
			entityManager.merge(modifications);
		}

		return modifications;
	}

}
