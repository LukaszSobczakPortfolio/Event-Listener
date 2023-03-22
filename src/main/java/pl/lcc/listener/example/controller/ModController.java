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
import pl.lcc.listener.example.service.AdService;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.service.SystemService;
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

    private final VerificationService service;
    private final SystemService sysService;
    private final AdService adService;

    public ModController(VerificationService service, AdService adService, SystemService sysService ) {
        log.info("Mod Controller");
        this.service = service;
        this.sysService = sysService;
        this.adService = adService;        
    }

    @GetMapping("/mod/mod")
    public String index(Model model) {
        prepareModel(model);
        return "Mod";
    }

    @PostMapping("/mod/shut")
    public String shutDown(){
        sysService.shutdown();
        return "redirect:/mod/mod";
    }
    
    @PostMapping("/mod/verified")
    public String verified(@RequestParam("id") String messageTextAsId, 
            @RequestParam("ban") Optional<Boolean> banned, 
            @RequestParam("warning") Optional<Boolean> warned, Model model) {

        log.info("message " + messageTextAsId + "moderated. banned: " + banned + " . Warned: " + warned);

       // if (banned.isEmpty() || !banned.get()) {
       if (banned.orElse(false)) {
            mod.itIsBomb(messageTextAsId);
        } else if(warned.orElse(false)){
            mod.itIsWarning(messageTextAsId);
        }else {
            mod.okMessage(messageTextAsId);
        }

        prepareModel(model);
        return "redirect:/mod/mod";
    }

    private void prepareModel(Model model) {
        mod.setMessageForVerification(service.getMessageForModeration());
        model.addAttribute("name", mod.getName());
        model.addAttribute("list", mod.getMessageForVerification());
    }
}
