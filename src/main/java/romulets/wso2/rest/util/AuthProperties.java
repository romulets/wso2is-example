package romulets.wso2.rest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AuthProperties {

	private static String PROPS_FILE = "/.wso2Example/conf.prp";
	
	private static AuthProperties inst;
	
	private String authzEndpoint;
	private String tokenEndpoint;
	private String scope;
	
	private AuthProperties() {
		initProperties();
	}
	
	public static AuthProperties inst() {
		if (inst == null)
			inst = new AuthProperties();
		
		return inst;
	}
	
	private void initProperties() {		
		Properties props = new Properties();
		
		String homeDir = System.getenv("HOME");
		PROPS_FILE = homeDir + PROPS_FILE;
		
		try {
			props.load(new FileInputStream(PROPS_FILE));
			
			authzEndpoint = props.getProperty("authzEndpoint");
			tokenEndpoint = props.getProperty("tokenEndpoint");
			scope = props.getProperty("scope");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAuthzEndpoint() {
		return authzEndpoint;
	}
	
	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public String getScope() {
		return scope;
	}
	
}
