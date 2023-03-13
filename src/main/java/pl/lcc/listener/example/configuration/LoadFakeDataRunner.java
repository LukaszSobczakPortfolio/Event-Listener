/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.configuration;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.security.Authority;
import pl.lcc.listener.example.security.SecuredUser;
import pl.lcc.listener.example.security.UserManegementService;
import pl.lcc.listener.example.service.MessageService;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.example.user.MsgStatus;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 * Loads some data, for manual app testing
 *
 * @author Nauczyciel
 */
@Slf4j
@Profile("!test")
@Component
public class LoadFakeDataRunner implements CommandLineRunner {

    private final MessageService messageService;
    private final VerificationService modService;
    private final UserManegementService uService;
    private final DispatcherInterface dispatcher;

    public LoadFakeDataRunner(MessageService userService, VerificationService modService, UserManegementService uService, DispatcherInterface dispatcher) {
        this.messageService = userService;
        this.modService = modService;
        this.uService = uService;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Fake Data Runner Supplied database");

        List<GrantedAuthority> userAuthority = List.of(Authority.USER);
        List<GrantedAuthority> modAuthority = List.of(Authority.USER, Authority.MOD);

        uService.createUser(new SecuredUser("test", "pass").setAuthorities(userAuthority));
        uService.createUser(new SecuredUser("enthalpy", "entropy").setAuthorities(userAuthority));
        uService.createUser(new SecuredUser("bomber-man", "bomb").setAuthorities(userAuthority));
        uService.createUser(new SecuredUser("admin", "admin").setAuthorities(modAuthority));

        messageService
                .addMessage(new Message(LocalDateTime.MIN, "Minimum minimorum", "test"))
                .addMessage(new Message(LocalDateTime.now(), "By Tester created", "test"))
                .addMessage(new Message(LocalDateTime.now(), "This is calorimetric bomb!", "enthalpy"))
                .addMessage(new Message(LocalDateTime.now(), "I made a Bomb!", "bomber-man"))
                .addMessage(new Message(LocalDateTime.now(), "Public One", "test").setPublic())
                .addMessage(new Message(LocalDateTime.now(), "Another public one ", "test").setPublic())
                .addMessage(new Message(LocalDateTime.now(), "Science is fun, bomb calorimeter is essentioal", "enthalpy").setPublic());

        dispatcher.dispatch(new BanEvent("bomber-man"));
    }

}
