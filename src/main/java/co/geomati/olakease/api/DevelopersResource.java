package co.geomati.olakease.api;

import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import co.geomati.olakease.persistence.Developer;

@Path("/developers")
public class DevelopersResource extends
		AbstractResourceList<Developer, Developer> {

	private static TypedQuery<Developer> query;

	@Override
	protected TypedQuery<Developer> getQuery() {
		if (query == null) {
			query = ApplicationListener.getEntityManager().createQuery(
					"Select d from Developer d", Developer.class);
		}

		return query;
	}

	@Override
	protected Developer message2jpa(Developer message) {
		return message;
	}

}
