package responses;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 5803921329177338689L;

	private String authPage;
	
	public AuthenticationResponse() {
		
	}
	
	public AuthenticationResponse(String authPage) {
		setAuthPage(authPage);
	}
	

	public String getAuthPage() {
		return authPage;
	}

	public void setAuthPage(String authPage) {
		this.authPage = authPage;
	}

}
