package com.example.ycseneapplication;

public class Photo {
    private String resourceUrl;

    public Photo(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceId(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
