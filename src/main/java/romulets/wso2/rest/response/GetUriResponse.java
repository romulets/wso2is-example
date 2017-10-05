package romulets.wso2.rest.response;

import java.io.Serializable;

public class GetUriResponse implements Serializable {

    private static final long serialVersionUID = 5803921329177338689L;

    private String uri;

    public GetUriResponse() {
        this("");
    }

    public GetUriResponse(String uri) {
        setUri(uri);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
