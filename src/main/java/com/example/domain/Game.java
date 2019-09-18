package com.example.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Game extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "developer_id")
    @NotNull
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @NotNull
    private Genre genre;

    @Column(unique = true)
    @NotNull
    private String title;

    @Length(max = 2048)
    private String description;


    public Game() {
    }

    public Game(@NotNull Developer developer, @NotNull Genre genre, @NotNull String title, @Length(max = 2048) String description) {
        this.developer = developer;
        this.genre = genre;
        this.title = title;
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

    @Override
    public String toString() {
        return this.getTitle();
    }
}
