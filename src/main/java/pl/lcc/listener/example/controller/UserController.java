/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.example.user.User;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class UserController {

    private final ApplicationContext ctx;

    @Autowired
    private User user;
    
    private final MessageService service;

    public UserController(ApplicationContext ctx, MessageService service) {
        log.info("WebController Constructor");
        this.ctx = ctx;
        this.service = service;
        this.service.addMessage( new Message(LocalDateTime.MIN, "Minimum minimorum", "test"))
                .addMessage( new Message(LocalDateTime.now(), "Tester created", "test"));
    }

    @GetMapping("/login")
    public String index(Model model) {
         log.info("login/get: " + Thread.currentThread().getName());
        user.put();
        log.info(user.get().stream().map(d -> d.toString()).collect(Collectors.joining(" : ")));
        model.addAttribute("user", user);
        log.info("First User" + user.toString());
        return "Login";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        log.info("login/post: " + Thread.currentThread().getName());
        model.addAttribute("name", user.getName());
        this.user.setName(user.getName());
        makeUserPanelModel(model);
        log.info("second User: " + this.user.toString());
        return "UserPanel";
    }
    
     @PostMapping("/addMessage")
     public String addMessage(@ModelAttribute Message msg, Model model){
         log.info("addMessage/post: " + Thread.currentThread().getName());
         service.addMessage(new Message(LocalDateTime.now(), msg.getMessage(),user.getName()));
         makeUserPanelModel(model);
         return "UserPanel";
     }       
    
    void makeUserPanelModel(Model model){
        model.addAttribute("name", user.getName());
        model.addAttribute("messages", service.getMessages(user.getName()));
        model.addAttribute("flagged", user.isFlagged());
        model.addAttribute("newMessage", new Message(null, null,user.getName()));
    }
}
