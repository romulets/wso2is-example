package wso2.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("wso2")
public class ServicesConfig extends ResourceConfig {

	public ServicesConfig() {		
		packages("com.fasterxml.jackson.jarxrs.json");
		packages("wso2");
	}
	
}
