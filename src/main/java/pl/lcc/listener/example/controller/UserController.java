/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import pl.lcc.listener.example.service.MessageService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lcc.listener.example.service.UserService;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.example.user.User;
import pl.lcc.listener.example.user.UserCore;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private User user;

    private final MessageService mService;

    private final UserService uService;

    public UserController(MessageService mService, UserService uService) {
        log.info("WebController Constructor");
        this.mService = mService;
        this.uService = uService;
    }

    @GetMapping("/login")
    public String index(@ModelAttribute("udto") UDTO udto, Model model) {
        log.info("login/get: " + Thread.currentThread().getName());
        return "Login";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute("udto") UDTO udto, Model model) {
        log.info("login/post: " + Thread.currentThread().getName());
        log.info(udto.toString());
        Optional<UserCore> core;
        if (udto.isCreate() || udto.isAdmin()) {
            System.out.println("cr " + udto.create + " ");
            core = uService.tryCreateUser(udto.name, udto.password, udto.isAdmin());
        } else {
            core = uService.tryGetUserCore(udto.name, udto.password);
        }
        
        if (core.isEmpty()) {
            return repeatLogin(udto, model);
        } else {
            user.setCore(core.get());
            prepareModerForNewMessage(model);
            return "UserPanel";
        }

    }

    private String repeatLogin(UDTO udto, Model model) {
        model.addAttribute("udto", new UDTO().setName(udto.getName()));
        return "Login";
    }

    @PostMapping("/addMessage")
    public String addMessage(@ModelAttribute Message msg, Model model) {
        log.info("addMessage/post: " + Thread.currentThread().getName());
        mService.addMessage(new Message(LocalDateTime.now(), msg.getMessage(), user.getName()));
        prepareModerForNewMessage(model);
        return "UserPanel";
    }

    private void prepareModerForNewMessage(Model model) {
        model
                .addAttribute("name", user.getName())
                .addAttribute("messages", mService.getMessages(user.getName()))
                .addAttribute("banned", user.isFlagged())
                .addAttribute("admin", user.isAdmin())
                .addAttribute("newMessage", new Message(null, null, user.getName()));
        System.out.println("admin: " + user.isAdmin() + ", banned" + user.isFlagged());
        System.out.println(user);
    }
}

class UDTO {

    String name;
    String password;
    boolean admin = false;
    boolean create = false;

    public String getName() {
        return name;
    }

    public UDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public UDTO setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public boolean isCreate() {
        return create;
    }

    public UDTO setCreate(boolean create) {
        this.create = create;
        return this;
    }

    @Override
    public String toString() {
        return "UDTO{" + "name=" + name + ", password=" + password + ", admin=" + admin + ", create=" + create + '}';
    }

}
