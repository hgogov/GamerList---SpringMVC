package com.example.service;

import com.example.repository.RoleRepository;
import com.example.service.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDTO::new)
                .collect(Collectors.toList());
    }

    public RoleDTO findById(Long id) {
        return new RoleDTO(roleRepository.getOne(id));
    }
}
