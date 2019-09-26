package com.example;

import com.example.domain.Developer;
import com.example.domain.Game;
import com.example.domain.GameBuilder;
import com.example.domain.Genre;
import com.example.exception.GameNotFoundException;
import com.example.repository.GameRepository;
import com.example.service.GameService;
import com.example.service.dto.GameDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class GameServiceTest {

    private GameRepository gameRepository = mock(GameRepository.class);

    private GameService gameService;
    private String message = "Game not found";

    @BeforeEach
    void setUp() {
        gameService = new GameService(gameRepository, message);
    }

    @Test
    void shouldFindGameById() {
        Long id = 1L;
        Game returnedGame = new Game();
        returnedGame.setId(id);

        when(gameRepository.getOne(id)).thenReturn(returnedGame);
        GameDTO foundGame = gameService.findById(1L);
        verify(gameRepository).getOne(1L);
//        assertEquals(id, foundGame.getId());
        assertNotNull(foundGame);
    }

    @Test
    void whenGameIdDoesNotExistShould() {
        Long id = 213L;
        when(gameRepository.getOne(id)).thenReturn(null);
        try {
            gameService.findById(id);
        } catch (GameNotFoundException e) {
            assertEquals(e.getMessage(), message);
        }

//        Assertions.assertThrows(GameNotFoundException.class, () -> gameService.findById(id), "Game with id " + id + "was not founds");
    }

    @Test
    void shouldCreateGame() {
        Developer developer = new Developer();
        developer.setId(1L);
        Genre genre = new Genre();
        genre.setId(1L);
        Game game = new Game(developer, genre, "The First Templar", "The First Templar");

        when(gameRepository.save(game)).thenReturn(game);
        Game createdGame = gameService.add(game);
        assertEquals(game, createdGame);
    }

    @Test
    void shouldUpdateGame() {
        Long id = 1L;
//        Genre genre = new Genre();
//        genre.setId(1L);
//        genre.setName("Action");
//        Developer developer = new Developer();
//        developer.setId(1L);
//        developer.setName("Activision");
//        Game gameToUpdate = new Game(developer, genre, "Assassins' Creed", "");
//        gameToUpdate.setId(id);


        Game gameToUpdate = new GameBuilder()
                .newGame(id, "Assassins Creed")
                .withDeveloper(1L, "Ubisoft")
                .withGenre(1L, "Action")
                .build();

        GameDTO updatedGameInformation = new GameDTO(gameToUpdate);
        updatedGameInformation.setId(gameToUpdate.getId());
        updatedGameInformation.setTitle("Assassin's Creed II");

        when(gameRepository.getOne(id)).thenReturn(gameToUpdate);
        when(gameRepository.save(gameToUpdate)).thenReturn(gameToUpdate);
        Game updatedGame = gameService.update(id, updatedGameInformation);
        assertEquals(gameToUpdate, updatedGame);
    }
}
