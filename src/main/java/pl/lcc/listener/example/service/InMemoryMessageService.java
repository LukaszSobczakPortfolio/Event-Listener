/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.security.UserManegementService;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 * includes in Memory storarge
 * @author Nauczyciel
 */
@Slf4j
@Component
public class InMemoryMessageService implements MessageService {

    private final DispatcherInterface dispatcher;
    
    private final UserManegementService uService;
    
    private final Map<String, List<Message>> db;
    
    private final List<Message> DEFAULT_MESSAGE_LIST = List.of(new Message(LocalDateTime.now(), "Write some messages", ""));

    public InMemoryMessageService(DispatcherInterface dis, UserManegementService uService) {
        dispatcher = dis;
        this.uService = uService;
        db = new HashMap<>();
        log.info("Fake msg service");
        log.info(Arrays.toString(this.getClass().getAnnotations()));
    }

    @Override
    public MessageService addMessage(Message msg) {
        var userName = msg.getUserName();
        if(!uService.userExists(userName)){
            throw new SecurityException("Message with illegal user: " + userName);
        }
        autoCheckMessageService(msg);
        db.merge(userName,
                arrayListWith(msg),
                (prev, next) -> {
                    prev.add(msg);
                    return prev;
                });
        return this;
    }

    private List<Message> arrayListWith(Message msg) {
        var list = new ArrayList<Message>();
        list.add(msg);
        return list;
    }

    @Override
    public List<Message> getMessages(String userName) {
         if(!uService.userExists(userName)){
            throw new SecurityException("Request for messages for illegal user: " + userName);
        }
        return db.getOrDefault(userName, DEFAULT_MESSAGE_LIST);
    }

    private void autoCheckMessageService(Message msg) {
            log.info("testing for possible Illegal Bomb: " + msg.getUserName());
            log.info("message is: " + msg.getMessageBody());
            if (msg.getMessageBody().toLowerCase().contains("bomb")){
                log.info("!!!!!!!!!!!BOMB!!!!!!!!!!!!! in message " + msg.toString());
                dispatcher.dispatch(new BombModEvent(msg.getUserName(), msg));
            }
    }

    @Deprecated(since="for testing noly")
    public void resetDB() {
        db.clear();
    }
    
    

}
