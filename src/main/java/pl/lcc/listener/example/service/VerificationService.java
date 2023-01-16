/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author Nauczyciel
 */
public interface VerificationService extends LccEventListener<BombModEvent>{
    
    void checkMessage(String user, Message msg);
    
    void banUser(String name);
    
}
