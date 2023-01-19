/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    private final DispatcherInterface dispatcher;
    
    private final Queue<Message> toDecide;

    public ModService(DispatcherInterface dispatcher) {
        this.dispatcher = dispatcher;
        toDecide = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void banUser(String name) {
         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listenToEvent(BombModEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Message getMessageForModeration() {
        return toDecide.peek();
    }

    @Override
    public void removeMessage(Message msg) {
      toDecide.remove(msg);
    }
}
