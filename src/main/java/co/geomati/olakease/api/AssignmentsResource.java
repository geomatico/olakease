package co.geomati.olakease.api;

import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import co.geomati.olakease.persistence.Assignment;

@Path("/assignments")
public class AssignmentsResource extends AbstractResourceList<Assignment> {

	private static TypedQuery<Assignment> query;

	public AssignmentsResource() {
		super(Assignment.class);
	}

	@Override
	protected TypedQuery<Assignment> getQuery() {
		if (query == null) {
			query = ApplicationListener.getEntityManager().createQuery(
					"Select d from Assignment d", Assignment.class);
		}

		return query;
	}

}