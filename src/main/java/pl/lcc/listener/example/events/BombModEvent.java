/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

import lombok.extern.slf4j.Slf4j;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.example.user.ModeratedMessage;
import pl.lcc.listener.module.interfaces.LccEvent;

/**
 * Receiver: Mod Service
 * Reason: Post with suspitious words (BOMB)
 * Sender: Message Service
 * @author Nauczyciel
 */
@Slf4j
public class BombModEvent implements LccEvent{

    private final ModeratedMessage msg;
    
    private final String user;

    public BombModEvent(String user, Message msg, boolean isNotWarned) {
        log.info("Event send to moderation: " + msg.toString());
        this.msg = new ModeratedMessage(msg, isNotWarned);
        this.user = user;
    }

    public ModeratedMessage getMsg() {
        return msg;
    }

    public String getUser() {
        return user;
    }

}
