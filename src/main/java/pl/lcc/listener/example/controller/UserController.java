/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import pl.lcc.listener.example.service.MessageService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.example.user.User;

/**
 * Login and user (not mod) interactions
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private User user;

    private final MessageService mService;

    public UserController(MessageService mService) {
        log.info("WebController Constructor");
        this.mService = mService;
    }

    
    
    //display user panel
    @GetMapping("/addMessage")
    public String showUserPanel(@ModelAttribute Message msg, Model model) {
        log.info("addMessage/get on thread: " + Thread.currentThread().getName());
        prepareModerForNewMessage(model);
        return "UserPanel";
    }

    //new messagre created
    @PostMapping("/addMessage")
    public String addMessage(@ModelAttribute Message msg, Model model) {
        log.info("addMessage/post on thread: " + Thread.currentThread().getName());
        log.info("addMessage: " + msg.toString());
        mService.addMessage(new Message(LocalDateTime.now(), msg.getMessageBody(), user.getName()));
        prepareModerForNewMessage(model);
        return "UserPanel";
    }

    private void prepareModerForNewMessage(Model model) {
        log.info("preparing model for user: " + user.getName());
        model
                .addAttribute("name", user.getName())
                .addAttribute("messages", mService.getMessages(user.getName()))
                .addAttribute("banned", user.isFlagged())
                .addAttribute("admin", user.isAdmin())
                .addAttribute("newMessage", new Message(null, null, user.getName()));
    }
}

