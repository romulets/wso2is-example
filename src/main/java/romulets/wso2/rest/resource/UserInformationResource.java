package romulets.wso2.rest.resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import romulets.wso2.rest.service.UserInformationService;

@Path("user-information")
public class UserInformationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInformation(@QueryParam("accessToken") String accessToken) {
        UserInformationService service = new UserInformationService();
        
        try {
            String response = service.getUserInformation(accessToken);
            return Response.accepted(response).build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
}
