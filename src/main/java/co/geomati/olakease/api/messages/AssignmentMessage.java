package co.geomati.olakease.api.messages;

import co.geomati.olakease.persistence.Assignment;

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

}
