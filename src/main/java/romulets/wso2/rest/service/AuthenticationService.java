package romulets.wso2.rest.service;


import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;

import romulets.wso2.rest.util.AuthProperties;

public class AuthenticationService {
    
    private static final String AUTHZ_GRANT_TYPE = "code";
    
    /**
     * 
     * @param consumerKey
     * @param callbackUri
     * @return the authentication URL built by amber API
     * @throws OAuthSystemException
     */
    public String buildAuthUri(String callbackUri) throws OAuthSystemException {        
        OAuthClientRequest request = getRequest(callbackUri);
        String locationURI = request.getLocationUri();
        return locationURI;
    }

    /**
     * 
     * @param consumerKey
     * @param callbackUri
     * @return configured request
     * @throws OAuthSystemException
     */
    private OAuthClientRequest getRequest(String callbackUri) throws OAuthSystemException {
        AuthProperties props = AuthProperties.inst();
        
        return OAuthClientRequest.authorizationLocation(props.getAuthzEndpoint())
        .setClientId(props.getConsumerKey())
        .setRedirectURI(callbackUri)
        .setResponseType(AUTHZ_GRANT_TYPE)
        .setScope(props.getScope())
        .buildQueryMessage();
    }
    
}
