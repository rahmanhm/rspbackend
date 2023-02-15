package com.example.rspbackend.model;

public class AuthToken {
    private String authToken;

    public AuthToken() {
        authToken="a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"; 
    }

    public AuthToken(String authToken) {
    this.authToken = authToken;
    }

    public String getAuthToken() {
    return authToken;
    }

    public void setAuthToken(String authToken) {
    this.authToken = authToken;
    }

}
