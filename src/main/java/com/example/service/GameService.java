package com.example.service;

import com.example.domain.Game;
import com.example.repository.GameRepository;
import com.example.service.dto.GameDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> findAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(GameDTO::new)
                .collect(Collectors.toList());
    }

    public GameDTO findById(Long id) {
        // Game game = gameRepository.getOne(id);
        return new GameDTO(gameRepository.getOne(id));
    }

    @Transactional
    public Game add(Game game) {
        return gameRepository.save(game);
    }

    public Game update(Long id, Game game) {
        Game gameToUpdate = gameRepository.getOne(id);
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setDescription(game.getDescription());
        return gameRepository.save(gameToUpdate);
    }

    public void delete(Long id) {
        Game game = gameRepository.getOne(id);
        gameRepository.delete(game);
    }
}
