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
import pl.lcc.listener.example.user.Mod;
import pl.lcc.listener.example.user.User;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class ModController {

    @Autowired
    private Mod mod;
    
    @GetMapping("/mod")
    public String index(Model model) {
        model.addAttribute("mod", mod);
        return "Mod";
    }

}
