/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.List;
import pl.lcc.listener.example.user.Message;

/**
 * Service for messages: gets signal from Controller, propagate to storage, can perform "automatic moderation"
 * @author Nauczyciel
 */

public interface MessageService {

    MessageService addMessage(Message msg);

    List<Message> getMessages(String user);

    List<Message> getPublicMessages();
    
    @Deprecated(since = "for testing only")
    public void resetDB();
}
