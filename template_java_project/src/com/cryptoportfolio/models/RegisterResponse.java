package com.cryptoportfolio.models;

public class RegisterResponse {

    private String username;

    public RegisterResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
