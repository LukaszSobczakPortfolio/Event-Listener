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
public class Message {
    LocalDateTime createTime;
    String message;
    String userName;

    public Message(LocalDateTime createTime, String message, String user) {
        this.createTime = createTime;
        this.message = message;
        this.userName = user;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "Message{" + "createTime=" + createTime + ", message=" + message + ", userName=" + userName + '}';
    }
    
    
}
