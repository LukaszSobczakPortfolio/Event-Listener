/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.util.List;
import pl.lcc.listener.example.user.Message;

/**
 *
 * @author Nauczyciel
 */
public interface MessageService {

    MessageService addMessage(String user, Message msg);

    List<Message> getMessages(String user);
    
}
