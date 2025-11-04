package com.taptag.backend.service;

import com.taptag.backend.model.User;
import com.taptag.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    //Dependencias
    private final UserRepository userRepository;

    // Constructor e Inyección de Dependencias
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
