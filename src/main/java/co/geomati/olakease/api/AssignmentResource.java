package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.api.messages.AssignmentMessage;
import co.geomati.olakease.persistence.Assignment;

@Path("/assignments/{assignmentId}")
public class AssignmentResource extends
		AbstractSingleResource<AssignmentMessage, Assignment> {

	public AssignmentResource() {
		super(Assignment.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Assignment asJSON(@PathParam("assignmentId") int id) {
		return super.asJSON(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Assignment put(@PathParam("assignmentId") int id,
			AssignmentMessage modifications) {
		return super.put(id, modifications);
	}

	@Override
	protected Assignment mergeModifications(EntityManager entityManager,
			int id, AssignmentMessage modifications) {
		modifications.setId(id);
		return modifications;
	}
}