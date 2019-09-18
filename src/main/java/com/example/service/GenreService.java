package com.example.service;

import com.example.domain.Genre;
import com.example.repository.GenreRepository;
import com.example.service.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }

    public GenreDTO findById(Long id) {
        return new GenreDTO(genreRepository.getOne(id));
    }

    public void add(Genre genre) {
        genreRepository.save(genre);
        genreRepository.flush();
    }
}
