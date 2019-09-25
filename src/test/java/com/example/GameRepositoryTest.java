package com.example;

import com.example.domain.Developer;
import com.example.domain.Game;
import com.example.domain.Genre;
import com.example.repository.GameRepository;
import com.example.service.GameService;
import com.example.service.dto.GameDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class GameRepositoryTest {
    private GameRepository gameRepository = Mockito.mock(GameRepository.class);
    private GameService gameService;
    private final static String SEARCH_TERM = "The Witcher";

//    @BeforeEach
//    public void setUp() {
//        gameService = new GameService(gameRepository, m);
//    }

    @Test
    public void findGameByTitle() {
        Mockito.when(gameRepository.findByTitle("The Witcher")).thenReturn(new Game(new Developer(), new Genre(), "The Witcher", ""));
//        Game foundGame = gameService.findByTitle(SEARCH_TERM);
//        assertEquals(SEARCH_TERM, foundGame.getTitle());
    }

    @Test
    public void findGameById() {
        Long id = 1L;


        Mockito.when(gameService.findById(id)).thenReturn(gameDTO());
        assertEquals(id, gameService.findById(id).getId());
    }

    private GameDTO gameDTO() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(1L);
        gameDTO.setTitle("Ratchet and Clank");
        gameDTO.setDescription("Ratchet and Clank");
        Developer developer = new Developer();
        developer.setName("Activision");
        gameDTO.setDeveloper(developer);
        Genre genre = new Genre();
        genre.setName("Action");
        gameDTO.setGenre(genre);
        return gameDTO;
    }
}
