/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

import lombok.extern.slf4j.Slf4j;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.LccEvent;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
public class BombModEvent implements LccEvent{

    private final Message msg;
    
    String user;

    public BombModEvent(String user, Message msg) {
        log.info("Event send to moderation: " + msg.toString());
        this.msg = msg;
        this.user = user;
    }

    public Message getMsg() {
        return msg;
    }

    public String getUser() {
        return user;
    }
    
}
