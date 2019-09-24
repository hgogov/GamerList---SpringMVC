package com.example;

import com.example.domain.Developer;
import com.example.domain.Game;
import com.example.domain.Genre;
import com.example.repository.GameRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class GameRepositoryTest {
    private GameRepository gameRepository = Mockito.mock(GameRepository.class);
    private final static String SEARCH_TERM = "The Witcher";

    @Test
    public void findGameByTitle() {
        Mockito.when(gameRepository.findByTitle("The Witcher")).thenReturn(new Game(new Developer(), new Genre(), "The Witcher", ""));
        Game foundGame = gameRepository.findByTitle(SEARCH_TERM);
        assertEquals(SEARCH_TERM, foundGame.getTitle());
    }
}
