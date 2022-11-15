package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyUserService implements UserService{

    @Autowired
    private final GreetingService greetingService;

    public DummyUserService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void greetAll() {
        List<String> list= List.of("Elodie","Charles");
        for (String prenom : list) {
            greetingService.greet(prenom) ;
        }
    }
}
