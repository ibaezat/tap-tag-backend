package com.taptag.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = {
        "https://tap-tag-one.vercel.app",
        "http://localhost:5173"
})

@RestController
@RequestMapping("/api/status")
public class StatusController {

    // Dependencias
    private final Random random;
    private final List<String> GREETINGS = List.of(
            "¡Hola!",
            "¡Qué bueno verte por aquí!",
            "¡Bienvenido a TapTag! 🚀",
            "¡Saludos desde el backend!",
            "Hello!",
            "Good Morning!",
            ":)",
            "Halo",
            "Oi!",
            "Ola",
            "Hoooola!"
    );

    // Constructor e Inyección de Dependencias
    public StatusController() {
        this.random = new Random();
    }

    @GetMapping
    public Map<String, String> status(){
        return Map.of("status", "OK");
    }

    // Retorna un saludo random
    @GetMapping("/greeting")
    public Map<String, String> getTestRandomGreeting() {
        String greeting = GREETINGS.get(random.nextInt(GREETINGS.size()));
        return Map.of("message", greeting);
    }

}
