package com.taptag.backend.controller;

import com.taptag.backend.model.Greeting;
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

    // Dependencias
    private final GreetingService greetingService;

    // Constructor e Inyección de Dependencias
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Métodos

    // Guarda un nuevo saludo en la base de datos
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting request) {
        return greetingService.createNewGreeting(request);
    }

    // Obtiene todos los saludos de la base de datos
    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // Obtiene un saludo random de la base de datos
    @GetMapping("/random")
    public Greeting getRandomGreeting(){
        return greetingService.getRandomGreeting();
    }
}
