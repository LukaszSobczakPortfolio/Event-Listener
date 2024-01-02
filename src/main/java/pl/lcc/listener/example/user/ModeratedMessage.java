/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.time.LocalDateTime;

/**
 *
 * @author Nauczyciel
 */
public class ModeratedMessage extends Message{
    
    private final boolean notWarned;
    
    public ModeratedMessage(LocalDateTime createTime, String messageBody, String user) {
        super(createTime, messageBody, user);
        notWarned = false;
    }
    
    public ModeratedMessage(Message msg, boolean notWarned){
        super (msg.getCreateTime(), msg.getMessageBody(), msg.getUserName());
        this.notWarned = notWarned;
    }

    public boolean isNotWarned() {
        return notWarned;
    }
    
}
