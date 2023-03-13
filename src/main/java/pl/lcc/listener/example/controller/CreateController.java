/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lcc.listener.example.security.Authority;
import pl.lcc.listener.example.security.SecuredUser;

/**
 *
 * @author Nauczyciel
 */
@Controller
@Slf4j
public class CreateController {

    private final UserDetailsManager userManager;

    public CreateController(UserDetailsManager userManager) {
        this.userManager = userManager;
    }

    //Display Login Screen
    @GetMapping("/login")
    public String index(@ModelAttribute("udto") UDTO udto, Model model) {
        log.info("login/get on thread: " + Thread.currentThread().getName());
        return "Login";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("udto") UDTO udto, Model model) {
        try {
        log.info("create/post on thread: " + Thread.currentThread().getName());
        var user = createSecuredUser(udto);
        userManager.createUser(user);
        log.info("Post Create user - udto: " + udto);
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "Create";
        }
        return "redirect:/login";
    }

    private UserDetails createSecuredUser(UDTO udto) {
        var user = new SecuredUser(udto.getUsername(), udto.getPassword());
        if (udto.isAdmin()) {
            user.setAuthorities(List.of(Authority.USER, Authority.MOD));
        } else {
            user.setAuthorities(List.of(Authority.USER));
        }
        return user;
    }

    @GetMapping("/create")
    public String createUserPanel(@ModelAttribute("udto") UDTO udto, Model model) {
        log.info("create/get on thread: " + Thread.currentThread().getName());
        log.info("Get Create user - udto: " + udto);
        return "Create";
    }

}
