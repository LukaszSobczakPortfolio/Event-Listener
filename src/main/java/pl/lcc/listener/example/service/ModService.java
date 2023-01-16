/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import pl.lcc.listener.example.service.VerificationService;
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

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listenToEvent(BombModEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
