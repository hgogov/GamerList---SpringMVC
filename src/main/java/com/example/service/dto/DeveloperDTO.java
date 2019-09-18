package com.example.service.dto;

import com.example.domain.Developer;
import com.example.domain.Game;

import java.util.HashSet;
import java.util.Set;

public class DeveloperDTO {
    private Long id;

    private String name;

    private Set<Game> games = new HashSet<>();

    public DeveloperDTO() {
    }

    public DeveloperDTO(Developer developer) {
        this.id = developer.getId();
        this.name = developer.getName();
        this.games = developer.getGames();
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
