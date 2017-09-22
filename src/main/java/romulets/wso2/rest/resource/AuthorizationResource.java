package romulets.wso2.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.client.response.OAuthClientResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.GrantType;

import romulets.wso2.rest.util.AuthProperties;

@Path("authorizate")
public class AuthorizationResource {

	public static final String ACCESS_TOKEN_PARAM = "access_token";
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorizate(@QueryParam("authorizationToken") String authToken,
			@QueryParam("consumerKey") String consumerKey) {
		
		try {
			String accessToken = requestAccessToken(consumerKey, authToken);
			return Response.status(200).entity(accessToken).build();
		} catch (OAuthSystemException | OAuthProblemException e) {
			return Response.status(500).entity(e).build();
		}
	}
	
	private String requestAccessToken(String consumerKey, String authToken) throws OAuthSystemException, OAuthProblemException {
		AuthProperties props = AuthProperties.inst();

		OAuthClientRequest accessRequest = OAuthClientRequest.tokenLocation(props.getTokenEndpoint())
				.setGrantType(GrantType.AUTHORIZATION_CODE)
				.setClientId(consumerKey)
				.setClientSecret("")
				.setRedirectURI(props.getAuthzEndpoint())
				.setCode(authToken)
				.buildBodyMessage();

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

		OAuthClientResponse oAuthResponse = oAuthClient.accessToken(accessRequest);
		String accessToken = oAuthResponse.getParam(ACCESS_TOKEN_PARAM);
		
		return accessToken;
	}

}
