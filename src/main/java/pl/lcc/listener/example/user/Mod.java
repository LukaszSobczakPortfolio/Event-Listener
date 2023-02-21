/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 * stores data required to display UserPanel
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class Mod {

    private final VerificationService service;

    private List<Message> messagesForVerification;

    Authentication auth;

    private final DispatcherInterface dispatcher;

    public Mod(VerificationService service, DispatcherInterface dispatcher) {
       this.auth = SecurityContextHolder
            .getContext()
            .getAuthentication();
        this.service = service;
        this.dispatcher = dispatcher;
        messagesForVerification = service.getMessageForModeration();
    }

    @Override
    public String toString() {
        return auth.getName() + " :message: " + messagesForVerification.toString();
    }

    public boolean okMessage(Message msg) {
        service.removeMessageFromModeration(msg);
        return true;
    }

    public boolean itIsBomb(Message message) {
        service.removeMessageFromModeration(message);
        dispatcher.dispatch(new BanEvent(message.getUserName()));
        return false;
    }

    public boolean okMessage(String messageAsId) {
        okMessage(processId(messageAsId));
        return true;
    }

    public boolean itIsBomb(String messageAsId) {
        itIsBomb(processId(messageAsId));
        return false;
    }

    private Message processId(String messageAsId) {
        var targetMessage = messagesForVerification.stream().filter(m -> m.getMessageBody().equals(messageAsId)).findFirst().get();
        messagesForVerification.remove(targetMessage);
        return targetMessage;
    }

    public List<Message> getMessageForVerification() {
        return messagesForVerification;
    }

    public String getName() {
        return auth.getName();
    }

    public void setMessageForVerification(List<Message> messageForVerification) {
        this.messagesForVerification = messageForVerification;
    }

}
