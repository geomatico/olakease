package co.geomati.api;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.sql.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import co.geomati.olakease.api.messages.AssignmentMessage;
import co.geomati.olakease.persistence.Assignment;
import co.geomati.olakease.persistence.Developer;
import co.geomati.olakease.persistence.Project;

public class AssignmentsTest extends
		AbstractResourceManagementTest<AssignmentMessage, Assignment> {

	private static final int POST_WORKING_HOURS = 142;
	private static final Date POST_START_DATE = new Date(
			System.currentTimeMillis());
	private static final int POST_HOURS_DAY_DEDICATION = 4;
	private int POST_DEVELOPER_ID;
	private int POST_PROJECT_ID;

	private static final int PUT_WORKING_HOURS = 100;
	private static final Date PUT_START_DATE = new Date(0);
	private static final int PUT_HOURS_DAY_DEDICATION = 2;
	private int PUT_DEVELOPER_ID;
	private int PUT_PROJECT_ID;

	@Override
	protected String getPath() {
		return "assignments";
	}

	@Override
	protected Class<Assignment> getOUTResourceClass() {
		return Assignment.class;
	}

	@Override
	protected AssignmentMessage createResourceToPost()
			throws JsonGenerationException, JsonMappingException, IOException {
		Project project = addProject();
		Developer developer = addDeveloper();
		AssignmentMessage ret = new AssignmentMessage();
		ret.setDeveloperId(POST_DEVELOPER_ID = developer.getId());
		ret.setHoursDayDedication(POST_HOURS_DAY_DEDICATION);
		ret.setProjectId(POST_PROJECT_ID = project.getId());
		ret.setStart(POST_START_DATE);
		ret.setWorkingHours(POST_WORKING_HOURS);

		return ret;
	}

	private Developer addDeveloper() throws IOException,
			JsonGenerationException, JsonMappingException {
		Developer developer = new Developer();
		developer.setId(1);
		developer.setName("fergonco");
		developer = postResource("developers", developer, Developer.class);
		return developer;
	}

	private Project addProject() throws IOException, JsonGenerationException,
			JsonMappingException {
		Project project = new Project();
		project.setId(1);
		project.setName("olakease");
		project.setDescription("olakease description");
		project = postResource("projects", project, Project.class);
		return project;
	}

	@Override
	protected void checkResourceIsEqualPosted(Assignment assignment) {
		assertEquals(POST_HOURS_DAY_DEDICATION,
				assignment.getHoursDayDedication());
		assertEquals(POST_START_DATE, assignment.getStart());
		assertEquals(POST_WORKING_HOURS, assignment.getWorkingHours());
		assertEquals(POST_DEVELOPER_ID, assignment.getDeveloper().getId());
		assertEquals(POST_PROJECT_ID, assignment.getProject().getId());
	}

	@Override
	protected void modifyResource(AssignmentMessage resource)
			throws JsonGenerationException, JsonMappingException, IOException {
		Project project = addProject();
		Developer developer = addDeveloper();
		resource.setDeveloperId(PUT_DEVELOPER_ID = developer.getId());
		resource.setHoursDayDedication(PUT_HOURS_DAY_DEDICATION);
		resource.setProjectId(PUT_PROJECT_ID = project.getId());
		resource.setStart(PUT_START_DATE);
		resource.setWorkingHours(PUT_WORKING_HOURS);
	}

	@Override
	protected void checkResourceIsModified(Assignment updatedAssignment) {
		assertEquals(PUT_HOURS_DAY_DEDICATION,
				updatedAssignment.getHoursDayDedication());
		assertEquals(PUT_START_DATE, updatedAssignment.getStart());
		assertEquals(PUT_WORKING_HOURS, updatedAssignment.getWorkingHours());
		assertEquals(PUT_DEVELOPER_ID, updatedAssignment.getDeveloper().getId());
		assertEquals(PUT_PROJECT_ID, updatedAssignment.getProject().getId());
	}
}
