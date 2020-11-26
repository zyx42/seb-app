package se.seb.backend.security.payload;

import se.seb.backend.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.EnumSet;

public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
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
