/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import org.springframework.stereotype.Service;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 *
 * @author Nauczyciel
 */
@Service
public class ModService implements VerificationService{

    DispatcherInterface dispatcher;
    
    @Override
    public void checkMessage(String user, Message msg) {
        if (msg.getMessage().toLowerCase().contains("bomb")){
            dispatcher.dispatch(new BombModEvent(user, msg));
        }
    }

    @Override
    public void banUser(String name) {
       
    }
    
}
