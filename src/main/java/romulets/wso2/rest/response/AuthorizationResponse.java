package romulets.wso2.rest.response;

import java.io.Serializable;

public class AuthorizationResponse implements Serializable {

    private static final long serialVersionUID = 6204577444828537572L;

    private String accessToken;

    public AuthorizationResponse(String accessToken) {
        setAccessToken(accessToken);
    }

    public AuthorizationResponse() {
        this("");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
