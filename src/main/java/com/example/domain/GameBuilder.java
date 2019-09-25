package com.example.domain;

public class GameBuilder {

    private Game game;
    private Developer developer;
    private Genre genre;

    public GameBuilder newGame(Long id, String name) {
        Game game = new Game();
        game.setId(id);
        game.setTitle(name);
        this.game = game;
        return this;
    }

    public GameBuilder withDeveloper(Long id, String name) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setName(name);
        this.developer = developer;
        return this;
    }

    public GameBuilder withGenre(Long id, String name) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        this.genre = genre;
        return this;
    }

    public Game build(){
        this.game.setDeveloper(developer);
        this.game.setGenre(genre);
        return this.game;
    }
}
