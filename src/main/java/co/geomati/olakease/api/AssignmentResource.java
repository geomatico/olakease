package co.geomati.olakease.api;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.geomati.olakease.persistence.Assignment;

@Path("/assignments/{assignmentId}")
public class AssignmentResource extends AbstractSingleResource<Assignment> {

	public AssignmentResource() {
		super(Assignment.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Assignment asJSON(@PathParam("assignmentId") long id) {
		return super.asJSON(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Assignment put(@PathParam("assignmentId") long id,
			Assignment modifications) {
		return super.put(id, modifications);
	}
}
