/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.time.LocalDateTime;

/**
 * value class, messageBody
 * @author Nauczyciel
 */
public class Message {
    
    private final LocalDateTime createTime;
    
    private final String messageBody;
    
    private final String userName;

    public Message(LocalDateTime createTime, String messageBody, String user) {
        this.createTime = createTime;
        this.messageBody = messageBody;
        this.userName = user;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getMessage() {
        return messageBody;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "Message{" + "createTime=" + createTime + ", message=" + messageBody + ", userName=" + userName + '}';
    }
    
    
}
