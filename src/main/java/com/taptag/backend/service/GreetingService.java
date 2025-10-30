package com.taptag.backend.service;

import com.taptag.backend.model.GreetingEntity;
import com.taptag.backend.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GreetingService {

    // Repositorio de Greeting
    private final GreetingRepository greetingRepository;
    private final Random random = new Random();

    // Contructor e Inyección de Dependencias
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Función para crear nuevo saludo
    public GreetingEntity createNewGreeting(GreetingEntity greeting) {
        return greetingRepository.save(greeting);
    }

    // Función para obtener todos los saludos de la base de datos
    public List<GreetingEntity> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Función que retorna un saludo random de la base de datos
    public GreetingEntity getRandomGreeting() {
        long count = greetingRepository.count();

        if (count == 0) {
            return new GreetingEntity("Saludo default, hola!");
        }

        List<GreetingEntity> allGreetings = greetingRepository.findAll();
        int randomIndex = random.nextInt(allGreetings.size());
        return allGreetings.get(randomIndex);

    }
}
