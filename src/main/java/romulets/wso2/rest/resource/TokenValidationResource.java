package romulets.wso2.rest.resource;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import romulets.wso2.rest.response.TokenValidationResponse;
import romulets.wso2.rest.service.TokenValidationService;

@Path("validate-token")
public class TokenValidationResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorizate(@QueryParam("accessToken") String accessToken) {
        TokenValidationService service = new TokenValidationService();
        
        try {

            boolean isTokenValid = service.isTokenValid(accessToken);
            return Response.ok(new TokenValidationResponse(isTokenValid)).build();

        } catch (RemoteException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e).build();
        }
    }
}
