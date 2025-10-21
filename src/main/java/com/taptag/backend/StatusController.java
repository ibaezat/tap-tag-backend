package com.taptag.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "https://tap-tag-one.vercel.app")
@RestController
@RequestMapping("/api")
public class StatusController {

    private final List<String> greetings = List.of(
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

    private final Random random = new Random();

    @GetMapping("/status")
    public Map<String, String> status(){
        return Map.of("status", "OK");
    }

    @GetMapping("/saludo")
    public Map<String, String> getGreeting() {
        String greeting = greetings.get(random.nextInt(greetings.size()));
        return Map.of("message", greeting);
    }

}
