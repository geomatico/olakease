package co.geomati.olakease.api;

import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import co.geomati.olakease.persistence.Project;

@Path("/projects")
public class ProjectsResource extends AbstractResourceList<Project, Project> {

	private static TypedQuery<Project> query;

	@Override
	protected TypedQuery<Project> getQuery() {
		if (query == null) {
			query = ApplicationListener.getEntityManager().createQuery(
					"Select d from Project d", Project.class);
		}

		return query;
	}

	@Override
	protected Project message2jpa(Project message) {
		return message;
	}

}