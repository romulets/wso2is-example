package romulets.wso2.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class ServiceConfig extends ResourceConfig {

    public ServiceConfig() {
        packages("com.fasterxml.jackson.jarxrs.json");
        packages("romulets.wso2.rest");
    }

}
