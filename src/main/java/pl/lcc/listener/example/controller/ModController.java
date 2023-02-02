/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.user.Mod;

/**
 * Controller for a Mod panel
 * @author Nauczyciel
 */
@Slf4j
@Controller
public class ModController {

    @Autowired
    private Mod mod;

    VerificationService service;

    public ModController(VerificationService service) {
        log.info("Mod Controller");
        this.service = service;
    }

    @GetMapping("/mod")
    public String index(Model model) {
        prepareModel(model);
        return "Mod";
    }

    @PostMapping("/verified")
    public String verified(@RequestParam("id") String messageTextAsId, @RequestParam("ban") Optional<Boolean> banned, Model model) {

        log.info("message " + messageTextAsId + "moderated. Effect: " + banned);

        if (banned.isEmpty() || banned.get() == false) {
            mod.okMessage(messageTextAsId);
        } else {
            mod.itIsBomb(messageTextAsId);
        }

        prepareModel(model);
        return "redirect:/mod";
    }

    private void prepareModel(Model model) {
        mod.setMessageForVerification(service.getMessageForModeration());
        model.addAttribute("name", mod.getName());
        model.addAttribute("list", mod.getMessageForVerification());
    }
}
