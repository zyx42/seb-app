package se.seb.backend.security.payload;

import se.seb.backend.domain.User;

import java.util.EnumSet;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private EnumSet<User.Role> roles;

    public JwtResponse(String accessToken,
                       String username,
                       EnumSet<User.Role> roles) {

        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EnumSet<User.Role> getRoles() {
        return roles;
    }

    public void setRoles(EnumSet<User.Role> roles) {
        this.roles = roles;
    }
}
