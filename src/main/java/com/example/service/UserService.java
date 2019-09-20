package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.getOne(id));
    }

    public User registerUser(UserDTO userDTO) {
        if (emailExists(userDTO.getEmail())) {
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }
}
