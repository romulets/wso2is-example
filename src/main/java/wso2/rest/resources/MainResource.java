package wso2.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class MainResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		return "test";
	}
	
}
