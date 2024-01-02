/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEvent;

/**
 * Receiver: User
 * Meaning: Server Wide Message
 * Sender: Mod.class, UserClass ( <- should be paid, lol) 
 * @author Nauczyciel
 */
public class AdEvent implements LccEvent{
    
    private final Message message;

    public Message getMessage() {
        return message;
    }

    public AdEvent(Message message) {
        this.message = message;
    }
    
    
}
