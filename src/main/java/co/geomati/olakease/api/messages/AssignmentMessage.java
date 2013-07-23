package co.geomati.olakease.api.messages;

import co.geomati.olakease.persistence.Assignment;
import co.geomati.olakease.persistence.Developer;
import co.geomati.olakease.persistence.Project;

public class AssignmentMessage extends Assignment {

	private int projectId;

	private int developerId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	@Override
	public void setProject(Project project) {
		throw new UnsupportedOperationException(
				"For messages use only setProjectId");
	}

	@Override
	public void setDeveloper(Developer developer) {
		throw new UnsupportedOperationException(
				"For messages use only setDeveloperId");
	}
}
