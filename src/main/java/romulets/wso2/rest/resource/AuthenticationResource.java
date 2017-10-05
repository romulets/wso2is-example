package romulets.wso2.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.amber.oauth2.common.exception.OAuthSystemException;

import romulets.wso2.rest.response.GetUriResponse;
import romulets.wso2.rest.service.AuthenticationService;

@Path("authentication")
public class AuthenticationResource {

    @GET
    @Path("uri")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthUrl(@QueryParam("callbackUri") String callbackUri) {
        AuthenticationService service = new AuthenticationService();
        
        try {
            
            String authUri = service.buildAuthUri(callbackUri);
            return Response.ok(new GetUriResponse(authUri)).build();
            
        } catch (OAuthSystemException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
    @GET
    @Path("logout-uri")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogoutUrl(@QueryParam("callbackUri") String callbackUri) {
        AuthenticationService service = new AuthenticationService();
        String logoutUri = service.buildLogoutUri(callbackUri);
        return Response.ok(new GetUriResponse(logoutUri)).build();
    }

}
