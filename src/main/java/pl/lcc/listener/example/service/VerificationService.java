/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 *
 * @author Nauczyciel
 */
@LccListenerClass(targetEvent = BombModEvent.class)
public interface VerificationService extends LccEventListener<BombModEvent>{
    
    Message getMessageForModeration();
    
    void removeMessage(Message msg);
    
    void banUser(String name);
    
}
