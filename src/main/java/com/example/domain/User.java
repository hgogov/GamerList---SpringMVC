package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinColumn()
    @NotNull
    private Set<Role> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(@NotBlank @Size(min = 1, max = 55) String username, @NotBlank @Size(min = 6, max = 128) String password, @NotBlank @Email(message = "{errors.invalid_email}") String email, @NotNull Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
