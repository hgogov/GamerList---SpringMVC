package com.example.service.dto;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.helpers.PasswordMatches;
import com.example.helpers.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@PasswordMatches
public class UserDTO {
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 128, message = "The password should be between 6 and 128 characters long")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "The email is not valid")
    @ValidEmail(message = "The email is not valid")
    private String email;

    @NotNull(message = "Role is required")
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
