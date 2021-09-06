package rltw.todo.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Introspected
public class SignupPayload {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3,max=50,message = "Username must be between 3-50 characters long")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 5,max=50,message = "Password must be between 5-50 characters long")
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
