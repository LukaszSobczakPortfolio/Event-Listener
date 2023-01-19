/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.user.Mod;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class ModController {

    @Autowired
    private Mod mod;
    
    VerificationService service;

    public ModController(VerificationService service) {
        this.service = service;
    }
    
    
    
    @GetMapping("/mod")
    public String index(Model model) {
        mod.setMessageForVerification(service.getMessageForModeration());
        model.addAttribute("name", mod.getName());
        model.addAttribute("list", mod.getMessageForVerification());
        return "Mod";
    }

}
