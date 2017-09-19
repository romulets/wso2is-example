package romulets.wso2.rest.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;

import romulets.wso2.rest.responses.AuthenticationResponse;
import romulets.wso2.rest.util.AuthProperties;

@Path("authenticate")
public class AuthenticationResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@QueryParam("consumerKey") String consumerKey,
									@QueryParam("callbackUri") String callbackUri) {

		try {
			String authUri = buildAuthUri(consumerKey, callbackUri);
			return Response.status(200).entity(new AuthenticationResponse(authUri)).build();
		} catch (OAuthSystemException e) {
			return Response.status(500).entity(e).build();
		}
	}

	private String buildAuthUri(String consumerKey, String callbackUri) throws OAuthSystemException {
		AuthProperties props = AuthProperties.inst();

		OAuthClientRequest request = OAuthClientRequest.authorizationLocation(props.getAuthzEndpoint())
				.setClientId(consumerKey).setRedirectURI(callbackUri)
				.setResponseType(props.getAuthzGrantType()).setScope(props.getScope()).buildQueryMessage();

		String locationURI = request.getLocationUri();

		return locationURI;
	}

}
