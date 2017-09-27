package romulets.wso2.rest.response;

import java.io.Serializable;

public class TokenValidationResponse implements Serializable {

    private static final long serialVersionUID = 4308448426312365524L;

    private boolean isTokenValid;

    public TokenValidationResponse() {
        setTokenValid(false);
    }

    public TokenValidationResponse(boolean isTokenValid) {
        setTokenValid(isTokenValid);
    }

    public boolean isTokenValid() {
        return isTokenValid;
    }

    public void setTokenValid(boolean isTokenValid) {
        this.isTokenValid = isTokenValid;
    }
}
