package com.taptag.backend.service;

import com.taptag.backend.model.Greeting;
import com.taptag.backend.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GreetingService {

    // Repositorio de Greeting
    private final GreetingRepository greetingRepository;
    private final Random random;

    // Contructor e Inyección de Dependencias
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
        this.random = new Random();
    }

    // Función para crear nuevo saludo
    public Greeting createNewGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    // Función para obtener todos los saludos de la base de datos
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Función que retorna un saludo random de la base de datos
    public Greeting getRandomGreeting() {
        long count = greetingRepository.count();

        if (count == 0) {
            return new Greeting("Saludo default, hola!");
        }

        List<Greeting> allGreetings = greetingRepository.findAll();
        int randomIndex = random.nextInt(allGreetings.size());
        return allGreetings.get(randomIndex);

    }
}
