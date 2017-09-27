package romulets.wso2.rest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AuthProperties {

    private static String PROPS_FILE = "/.wso2Example/conf.prp";

    private static AuthProperties inst;

    private String scope;
    private String wso2User;
    private String wso2Password;
    private String authzEndpoint;
    private String tokenEndpoint;
    private String tokenValidationEndpoint;

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

            scope = props.getProperty("scope");
            wso2User = props.getProperty("wso2User");
            wso2Password = props.getProperty("wso2Password");
            authzEndpoint = props.getProperty("authzEndpoint");
            tokenEndpoint = props.getProperty("tokenEndpoint");
            tokenValidationEndpoint = props.getProperty("tokenValidationEndpoint");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getScope() {
        return scope;
    }

    public String getAuthzEndpoint() {
        return authzEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public String getTokenValidationEndpoint() {
        return tokenValidationEndpoint;
    }

    public String getWso2User() {
        return wso2User;
    }

    public String getWso2Password() {
        return wso2Password;
    }

}
