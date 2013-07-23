package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import co.geomati.olakease.api.messages.AssignmentMessage;
import co.geomati.olakease.persistence.Assignment;
import co.geomati.olakease.persistence.Developer;
import co.geomati.olakease.persistence.Project;

@Path("/assignments")
public class AssignmentsResource extends
		AbstractResourceList<AssignmentMessage, Assignment> {

	private static TypedQuery<Assignment> query;

	@Override
	protected TypedQuery<Assignment> getQuery() {
		if (query == null) {
			query = ApplicationListener.getEntityManager().createQuery(
					"Select d from Assignment d", Assignment.class);
		}

		return query;
	}

	@Override
	protected Assignment message2jpa(AssignmentMessage message) {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		Project project = entityManager.getReference(Project.class,
				message.getProjectId());
		if (project != null) {
			Developer developer = entityManager.getReference(Developer.class,
					message.getDeveloperId());
			if (developer != null) {
				Assignment ret = message;
				ret.setProject(project);
				ret.setDeveloper(developer);
				return ret;
			}
		}

		throw new RuntimeException("An analysis of the test coverage will "
				+ "show how crappy is this code");
	}
}