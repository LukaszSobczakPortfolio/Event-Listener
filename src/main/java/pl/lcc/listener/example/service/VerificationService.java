/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.List;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 * store messages for moderation, sends them to moderators, sends ban event
 * @author Nauczyciel
 */

public interface VerificationService extends LccEventListener<BombModEvent>{
    
    List<Message> getMessageForModeration();
    
    void removeMessageFromModeration(Message msg);
    
    void banUser(String name);
    
}
