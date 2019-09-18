package com.example.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.domain.Developer;
import com.example.domain.Game;
import com.example.domain.Genre;

public class GameDTO {
    private Long id;

    @NotBlank(message = "The title field should not be empty")
    @Size(min = 1, max = 255, message = "The title should be between 1 and 255 characters long")
    private String title;

    @NotBlank(message = "The description field should not be empty")
    @Size(min = 1, max = 2048, message = "The description should be between 1 and 2048 characters long")
    private String description;

    @NotNull(message = "Developer is required")
    private Developer developer;

    @NotNull(message = "Genre is required")
    private Genre genre;

    public GameDTO() {
    }

    public GameDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.description = game.getDescription();
        this.developer = game.getDeveloper();
        this.genre = game.getGenre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
