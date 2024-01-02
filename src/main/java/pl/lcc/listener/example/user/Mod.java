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
import pl.lcc.listener.example.events.WarningEvent;
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
    private List<ModeratedMessage> messagesForVerification;
    private final Authentication auth;
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

    public void okMessage(String messageBodyAsId) {
        var message = processId(messageBodyAsId);
        service.removeMessageFromModeration(message);
    }
    
    public void itIsWarning(String messageBodyAsId) {
        var message = processId(messageBodyAsId);
        service.removeMessageFromModeration(message);
        dispatcher.dispatch(new WarningEvent(message.getUserName()));
    }

    public void itIsBomb(String messageBodyAsId) {
        var message = processId(messageBodyAsId);
        service.removeMessageFromModeration(message);
        dispatcher.dispatch(new BanEvent(message.getUserName()));
    }

    private ModeratedMessage processId(String messageAsId) {
        var targetMessage = messagesForVerification.stream().filter(m -> m.getMessageBody().equals(messageAsId)).findFirst().get();
        messagesForVerification.remove(targetMessage);
        return targetMessage;
    }

    public List<ModeratedMessage> getMessageForVerification() {
        return messagesForVerification;
    }

    public String getName() {
        return auth.getName();
    }

    public void setMessageForVerification(List<ModeratedMessage> messageForVerification) {
        this.messagesForVerification = messageForVerification;
    }

  
}
