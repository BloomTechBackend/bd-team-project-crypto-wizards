package com.cryptoportfolio.models.requests;

import java.util.Objects;

public class VerifyRequest {

    private String username;
    private String authToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return authToken;
    }

    public void setToken(String token) {
        this.authToken = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyRequest that = (VerifyRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getToken());
    }

    @Override
    public String toString() {
        return "VerifyRequest{" +
                "username='" + username + '\'' +
                ", token='" + authToken + '\'' +
                '}';
    }
}
