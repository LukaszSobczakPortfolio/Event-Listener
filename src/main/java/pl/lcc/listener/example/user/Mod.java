/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.controller.VerificationService;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class Mod implements LccEventListener<BombModEvent>{

    private final VerificationService service;
    
    private final Queue<Message> toDecide;

    public Mod(VerificationService service) {
        toDecide = new ConcurrentLinkedQueue<>();
        this.service = service;
    }
    
    @Override
    public String getInfo() {
        return toDecide.toString();
    }

    @Override
    public void listenToEvent(BombModEvent event) {
        toDecide.add(event.getMsg());
    }

    public boolean okMessage(Message msg){
        return toDecide.remove(msg);
    }
    
    public boolean itIsBomb(Message msg){
       service.banUser(msg.getUserName());
       return false;
    }
}
