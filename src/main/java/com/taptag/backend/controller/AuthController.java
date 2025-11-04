package com.taptag.backend.controller;

import com.taptag.backend.dto.UserDTO;
import com.taptag.backend.model.User;
import com.taptag.backend.security.JWTUtil;
import com.taptag.backend.service.AuthService;
import com.taptag.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = {
        "https://tap-tag-one.vercel.app",
        "http://localhost:5173"
})

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Dependencias
    private final AuthService authService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    // Constructor e Inyección de Dependencias
    public AuthController(AuthService authService, JWTUtil jwtUtil, UserService userService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");

        return authService.registerUser(name, email, password);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = authService.loginUser(email, password);
        String token = jwtUtil.generateToken(email);
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName());
        return Map.of(
                "token", token,
                "user", userDTO
        );
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(java.security.Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("No autorizado");
        }

        String email = principal.getName();

        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        User user = userOptional.get();

        return ResponseEntity.ok(new UserDTO(user.getId(), user.getEmail(), user.getName()));
    }
}
