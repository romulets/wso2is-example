package romulets.wso2.rest.resource;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.wso2.carbon.identity.oauth2.stub.OAuth2TokenValidationServiceStub;
import org.wso2.carbon.identity.oauth2.stub.dto.OAuth2TokenValidationRequestDTO;
import org.wso2.carbon.identity.oauth2.stub.dto.OAuth2TokenValidationRequestDTO_OAuth2AccessToken;
import org.wso2.carbon.identity.oauth2.stub.dto.OAuth2TokenValidationResponseDTO;
import org.wso2.carbon.utils.CarbonUtils;

import romulets.wso2.rest.response.TokenValidationResponse;
import romulets.wso2.rest.util.AuthProperties;

@Path("validate-token")
public class TokenValidationResource {

    private static final int TIMEOUT_IN_MILLIS = 15 * 1000;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorizate(@QueryParam("accessToken") String accessToken) {
        try {

            boolean isTokenValid = isTokenValid(accessToken);
            return Response.status(200).entity(new TokenValidationResponse(isTokenValid)).build();

        } catch (RemoteException e) {
            return Response.status(500).entity(e).build();
        }
    }

    /**
     * 
     * @param accessToken
     * @return token is valid
     * @throws RemoteException
     */
    private boolean isTokenValid(String accessToken) throws RemoteException {
        OAuth2TokenValidationRequestDTO oauthReq = new OAuth2TokenValidationRequestDTO();
        oauthReq.setAccessToken(getOAuthToken(accessToken));

        OAuth2TokenValidationResponseDTO resp = getValidationService().validate(oauthReq);
        return resp.getValid();
    }

    /**
     * 
     * @param accessToken
     * @return Token object provided by carbon API
     */
    private OAuth2TokenValidationRequestDTO_OAuth2AccessToken getOAuthToken(String accessToken) {
        OAuth2TokenValidationRequestDTO_OAuth2AccessToken oauthToken;
        oauthToken = new OAuth2TokenValidationRequestDTO_OAuth2AccessToken();
        oauthToken.setIdentifier(accessToken);
        oauthToken.setTokenType("bearer");
        return oauthToken;
    }

    /**
     * 
     * @return validation service ready for use
     * @throws AxisFault
     */
    private OAuth2TokenValidationServiceStub getValidationService() throws AxisFault {
        String serviceURL = AuthProperties.inst().getTokenValidationEndpoint();
        OAuth2TokenValidationServiceStub stub = new OAuth2TokenValidationServiceStub(null, serviceURL);
        return setupValidationService(stub);
    }

    /**
     * 
     * @param stub
     * @return receives a non prepared stub and set up it
     */
    private OAuth2TokenValidationServiceStub setupValidationService(OAuth2TokenValidationServiceStub stub) {
        AuthProperties props = AuthProperties.inst();

        ServiceClient client = stub._getServiceClient();
        Options options = client.getOptions();

        CarbonUtils.setBasicAccessSecurityHeaders(props.getWso2User(), props.getWso2Password(), true, client);

        options.setTimeOutInMilliSeconds(TIMEOUT_IN_MILLIS);
        options.setProperty(HTTPConstants.SO_TIMEOUT, TIMEOUT_IN_MILLIS);
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, TIMEOUT_IN_MILLIS);
        options.setCallTransportCleanup(true);
        options.setManageSession(true);

        return stub;
    }

}
