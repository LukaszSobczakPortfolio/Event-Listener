/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.List;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author Nauczyciel
 */
public interface MessageService extends LccEventListener<BanEvent>{

    MessageService addMessage(Message msg);

    List<Message> getMessages(String user);
    
}
