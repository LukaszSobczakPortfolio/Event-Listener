/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class Mod {

    private final VerificationService service;

    private List<Message> messageForVerification;

    private String name;

    private final DispatcherInterface dispatcher;

    public Mod(VerificationService service, DispatcherInterface dispatcher) {
        name = "mod";
        this.service = service;
        this.dispatcher = dispatcher;
        messageForVerification = service.getMessageForModeration();
    }

    @Override
    public String toString() {
        return name + " :message: " + messageForVerification.toString();
    }

    public boolean okMessage(Message msg) {
        service.removeMessage(msg);
        return true;
    }

    public boolean itIsBomb(Message message) {
        service.removeMessage(message);
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
        var targetMessage = messageForVerification.stream().filter(m -> m.message.equals(messageAsId)).findFirst().get();
        messageForVerification.remove(targetMessage);
        return targetMessage;
    }

    public List<Message> getMessageForVerification() {
        return messageForVerification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMessageForVerification(List<Message> messageForVerification) {
        this.messageForVerification = messageForVerification;
    }

}
