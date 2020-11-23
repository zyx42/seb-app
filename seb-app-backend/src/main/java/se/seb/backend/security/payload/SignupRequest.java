package se.seb.backend.security.payload;

import se.seb.backend.domain.User;

import java.util.EnumSet;

public class SignupRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
