package com.example.service.dto;

import com.example.domain.Role;

import javax.validation.constraints.NotBlank;

public class RoleDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
