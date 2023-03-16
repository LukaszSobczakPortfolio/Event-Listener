/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.events.ShutDownEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 * includes in Memory storarge
 *
 * @author Nauczyciel
 */
@Slf4j
@Service
@LccListenerClass(targetEvent = BombModEvent.class)
public class ModService implements VerificationService, SystemService, AdService {

    private final Queue<Message> toDecide;
    private final DispatcherInterface dispatcher;
    
    
    public ModService(DispatcherInterface dispatcher) {
        this.dispatcher = dispatcher;
        toDecide = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void listenToEvent(BombModEvent event) {
        toDecide.offer(event.getMsg());
    }

    @Override
    public List<Message> getMessageForModeration() {
        return new ArrayList<>(toDecide);
    }

    @Override
    public void removeMessageFromModeration(Message msg) {
        log.info("removed " + msg.toString());
        toDecide.remove(msg);
    }

    @Override
    public void shutdown() {
       dispatcher.dispatch(new ShutDownEvent());
    }
}
