package com.taptag.backend.controller;

import com.taptag.backend.model.GreetingEntity;
import com.taptag.backend.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = {
        "https://tap-tag-one.vercel.app",
        "http://localhost:5173"
})
@RestController
@RequestMapping("/api/greetings")

public class GreetingController {

    private final GreetingService greetingService;
    private final Random random = new Random();
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
            "Hoooola!"
    );

    // Constructor e Inyección de Dependencias
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Métodos

    // Retorna un saludo random
    @GetMapping("/test-random")
    public Map<String, String> getTestRandomGreeting() {
        String greeting = GREETINGS.get(random.nextInt(GREETINGS.size()));
        return Map.of("message", greeting);
    }

    // Guarda un nuevo saludo en la base de datos
    @PostMapping
    public GreetingEntity createGreeting(@RequestBody GreetingEntity request) {
        return greetingService.createNewGreeting(request);
    }

    // Obtiene todos los saludos de la base de datos
    @GetMapping
    public List<GreetingEntity> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // Obtiene un saludo random de la base de datos
    @GetMapping("/random")
    public GreetingEntity getRandomGreeting(){
        return greetingService.getRandomGreeting();
    }
}
