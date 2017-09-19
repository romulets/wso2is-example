package wso2.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class ServiceConfig extends ResourceConfig {

	public ServiceConfig() {	
		packages("com.fasterxml.jackson.jarxrs.json");
		packages("wso2");
	}
	
}
