package com.example.service;

import com.example.domain.Developer;
import com.example.repository.DeveloperRepository;
import com.example.service.dto.DeveloperDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    private DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<DeveloperDTO> findAll() {
        return developerRepository.findAll()
                .stream()
                .map(DeveloperDTO::new)
                .collect(Collectors.toList());
    }

    public DeveloperDTO findById(Long id) {
        return new DeveloperDTO(developerRepository.getOne(id));
    }

    public void add(Developer developer) {
        developerRepository.save(developer);
        developerRepository.flush();
    }
}
