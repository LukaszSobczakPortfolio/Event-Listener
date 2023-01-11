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

    public Message(LocalDateTime createTime, String message) {
        this.createTime = createTime;
        this.message = message;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getMessage() {
        return message;
    }
    
    
}
