package com.cryptoportfolio.models.responses;

import java.util.Objects;

public class LoginResponse {

    private String username;
    private String token;

    public LoginResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponse that = (LoginResponse) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getToken());
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
