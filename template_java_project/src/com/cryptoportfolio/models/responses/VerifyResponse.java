package com.cryptoportfolio.models.responses;

import java.util.Objects;

public class VerifyResponse {

    private String username;
    private String token;
    private boolean verified;
    private String message;



    public VerifyResponse(String username, String token, boolean verified, String message) {
        this.username = username;
        this.token = token;
        this.verified = verified;
        this.message = message;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyResponse that = (VerifyResponse) o;
        return isVerified() == that.isVerified() && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getToken(), that.getToken()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getToken(), isVerified(), getMessage());
    }

    @Override
    public String toString() {
        return "VerifyResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", verified=" + verified +
                ", message='" + message + '\'' +
                '}';
    }
}
