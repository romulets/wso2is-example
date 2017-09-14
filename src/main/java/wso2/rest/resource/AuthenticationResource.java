package wso2.rest.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;

import wso2.rest.util.AuthProperties;


@Path("auth")
public class AuthenticationResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@QueryParam("consumerKey") String consumerKey) {
		
		AuthProperties props = AuthProperties.inst();
		
		try {
			
			OAuthClientRequest request = OAuthClientRequest
			        .authorizationLocation(props.getAuthzEndpoint())
			        .setClientId(consumerKey)
			        .setRedirectURI(props.getCallBackUrl())
			        .setResponseType(props.getAuthzGrantType())
			        .setScope(props.getScope())
			        .buildQueryMessage();
			
			String locationURI = request.getLocationUri();
			
			return Response.status(200).entity("response " + locationURI).build();
			
		} catch (OAuthSystemException e) {
			return Response.status(500).entity(e).build();
		}
	}
	
}
