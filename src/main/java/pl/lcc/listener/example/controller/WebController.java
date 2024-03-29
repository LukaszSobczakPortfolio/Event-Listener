/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lcc.listener.example.events.ShutDownEvent;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 * Controller for testing, for Removal in final
 *
 * @author piko
 */
class InputDataDTO {
    
    String name;
    Long id;
    Boolean check;
    
    public InputDataDTO(String name, Long id, Boolean check) {
        this.name = name;
        this.id = id;
        this.check = check;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    public Boolean isCheck() {
        return check;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCheck(Boolean check) {
        this.check = check;
    }
    
    @Override
    public String toString() {
        return "dataPart{" + "name=" + name + ", id=" + id + ", check=" + check + '}';
    }
    
}

@Deprecated
@Slf4j
@Controller
public class WebController {
    
    DispatcherInterface dispatcher;
    
    List<InputDataDTO> data;
    
    public WebController(DispatcherInterface dispatcher) {
        log.info("WebController Constructor");
        this.dispatcher = dispatcher;
        data = List.of(new InputDataDTO("kilo", 1l, false),
                new InputDataDTO("foxtrot", 2l, false),
                new InputDataDTO("typhoon", 100l, false),
                new InputDataDTO("victor 3", 10l, false)
        );
    }
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("datax", data);
        return "Greetings";
    }
    
    @PostMapping("/")
    public String postIndex(@ModelAttribute InputDataDTO data1, Model model) {
        
        model.addAttribute("datax", data);
        return "Greetings";
    }
    
    @GetMapping("/down")
    public void shutdownApp() {
        dispatcher.dispatch(new ShutDownEvent());
    }
    
}
