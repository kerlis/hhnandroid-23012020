package me.doapps.appdhn.models;

/**
 * Created by Leandro on 10/18/17.
 */

public class FileDate {

    private String name;
    private String baseUrl;

    public FileDate(String name, String baseUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
