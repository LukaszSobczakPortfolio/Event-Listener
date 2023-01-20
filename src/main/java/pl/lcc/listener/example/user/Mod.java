/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class Mod{

  
   private final VerificationService service;
   
   private List<Message> messageForVerification;
   
   private String name;
    
    public Mod(VerificationService service) {
        
        this.service = service;
        messageForVerification = service.getMessageForModeration();
    }
    
    @Override
    public String toString() {
        return name + " :message: " + messageForVerification.toString();
    }

    public boolean okMessage(Message msg){
        service.removeMessage(msg);
        return true;
    }
    
    public boolean itIsBomb(Message msg){
       service.banUser(msg.getUserName());
       return false;
    }

    public List<Message> getMessageForVerification() {
        return messageForVerification;
    }

    public void setName(String name) {
        this.name = name;
    } 
    
    public String getName() {
        return name;
    }

    public void setMessageForVerification(List<Message> messageForVerification) {
        this.messageForVerification = messageForVerification;
    }
    
    
}
