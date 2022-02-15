package com.cryptoportfolio.models.responses;

import com.cryptoportfolio.models.PortfolioModel;

import java.util.Objects;

public class VerifyResponse {

    private String username;
    private String token;

    public VerifyResponse(Builder builder) {
        this.username = builder.username;
        this.token = builder.token;
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

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        public String username;
        private String token;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public VerifyResponse build() {return new VerifyResponse(this);}
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyResponse that = (VerifyResponse) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getToken());
    }

    @Override
    public String toString() {
        return "VerifyResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
