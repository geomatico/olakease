package co.geomati.olakease.api;

import javax.persistence.EntityManager;

public abstract class AbstractSingleResource<IN, OUT> {

	private Class<OUT> resourceClass;

	public AbstractSingleResource(Class<OUT> outClass) {
		this.resourceClass = outClass;
	}

	public OUT asJSON(int id) {
		OUT jpaEntity = ApplicationListener.getEntityManager().find(
				resourceClass, id);
		return jpaEntity;
	}

	public OUT put(int id, IN modifications) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		OUT original = mergeModifications(entityManager, id, modifications);
		entityManager.merge(original);

		return original;
	}

	protected abstract OUT mergeModifications(EntityManager entityManager,
			int id, IN modifications);

}
