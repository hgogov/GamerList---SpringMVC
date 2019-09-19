package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Developer extends BaseEntity {
    @Column(unique = true)
    @Size(min = 2, max = 128)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "developer")
    private Set<Game> games = new HashSet<>();

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
