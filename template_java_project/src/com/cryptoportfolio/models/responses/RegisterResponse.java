package com.cryptoportfolio.models.responses;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterResponse that = (RegisterResponse) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "username='" + username + '\'' +
                '}';
    }
}
