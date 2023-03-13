/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 * value class, messageBody
 * @author Nauczyciel
 */
@Slf4j
public class Message {
    
    private final LocalDateTime createTime;
    
    private final String messageBody;
    
    private final String userName;
    
    //Default private
    private MsgStatus status;

    public Message(LocalDateTime createTime, String messageBody, String user) {
        this.createTime = createTime;
        this.messageBody = messageBody;
        this.userName = user;
        status = MsgStatus.PRIVATE;
    }
    
//    public Message(LocalDateTime createTime, String messageBody, String user, MsgStatus status) {
//        this.createTime = createTime;
//        this.messageBody = messageBody;
//        this.userName = user;
//        this.status = status;
//    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "Message{" + "createTime=" + createTime + ", message=" + messageBody + ", userName=" + userName + '}';
    }

   public Message setPublic(){
       status = MsgStatus.PUBLIC;
       return this;
   }  
   
   public Message setPrivate(){
       status = MsgStatus.PRIVATE;
       return this;
   }  
    
   public MsgStatus getStatus() {
    return status;
    }
   
   public boolean isPublic(){
       log.info("Question about " + messageBody + ". Answer isPublic " + MsgStatus.PUBLIC.equals(status));
       return MsgStatus.PUBLIC.equals(status);
   }
}

