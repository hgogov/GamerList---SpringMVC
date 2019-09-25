package com.example;

import com.example.domain.Game;
import com.example.repository.GameRepository;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

public class GameRepoTest {
    private final GameRepository gameRepository = mock(GameRepository.class);

    @Test
    void injectedGameRepositoryIsNotNull() {
        assertThat(gameRepository).isNotNull();
    }

    @Test
    public void findGameByTitle() {
        gameRepository.save(new Game());
    }
}
