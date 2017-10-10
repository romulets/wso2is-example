package romulets.wso2.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import romulets.wso2.rest.util.AuthProperties;

public class UserInformationService {

    /**
     * 
     * @param accessToken
     * @return JSON with user info
     * @throws IOException
     */
    public String getUserInformation(String accessToken) throws IOException {
        String endpoint = AuthProperties.inst().getUserInformationEndpoint();
        
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        String authorizationHeader = String.format("Bearer %s", accessToken);
        con.addRequestProperty("Authorization", authorizationHeader);
        
        con.setRequestMethod("GET");
        con.connect();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();
        
        return response.toString();
    }
    
}
