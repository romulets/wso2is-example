package romulets.wso2.rest.util;

public class NameValuePair implements org.apache.http.NameValuePair {

    private String name;
    private String value;
    
    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

}
