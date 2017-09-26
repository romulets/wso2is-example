package romulets.wso2.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import romulets.wso2.rest.response.TokenValidationResponse;

@Path("validate-token")
public class TokenValidationResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorizate(@QueryParam("token") String token) {
		return Response.status(200).entity(new TokenValidationResponse(false)).build();
	}
	
}
