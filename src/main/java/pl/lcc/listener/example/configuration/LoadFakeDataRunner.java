/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.configuration;

import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.service.MessageService;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.user.Message;

/**
 *
 * @author Nauczyciel
 */
@Profile("!test")
@Component
public class LoadFakeDataRunner implements CommandLineRunner {

    private final MessageService userService;
    private final VerificationService modService;

    public LoadFakeDataRunner(MessageService userService, VerificationService modService) {
        this.userService = userService;
        this.modService = modService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService
                .addMessage(new Message(LocalDateTime.MIN, "Minimum minimorum", "test"))
                .addMessage(new Message(LocalDateTime.now(), "Tester created", "test"))
                .addMessage(new Message(LocalDateTime.now(), "This is calorimetric bomb!", "thermochemistry-enthusiast"))
                .addMessage(new Message(LocalDateTime.now(), "I made a Bomb!", "bomber-man"));

    }

}
