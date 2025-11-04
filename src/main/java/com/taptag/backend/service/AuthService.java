package com.taptag.backend.service;

import com.taptag.backend.model.User;
import com.taptag.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    // Dependencias
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor e Inyección de Dependencias
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Función para registrar al usuario
    public User registerUser(String name, String email, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con este email");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(rawPassword));

        return userRepository.save(newUser);
    }

    // Función para hacer login
    public User loginUser(String email, String rawPassword) {
        // TODO: Mejorar mensajes de error para el usuario, actualmente solo recibe 403
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        User userEntity = optionalUser.get();

        if (!passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return userEntity;
    }

}
