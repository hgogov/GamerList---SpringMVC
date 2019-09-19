package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    @NotBlank
    @Size(min = 1, max = 55)
    private String username;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    @Column(unique = true)
    @NotBlank
    @Email(message = "{errors.invalid_email}")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
