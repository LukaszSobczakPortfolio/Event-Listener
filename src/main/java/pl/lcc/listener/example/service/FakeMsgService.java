/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import pl.lcc.listener.example.service.MessageService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.events.BombModEvent;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Component
@LccListenerClass(targetEvent = BanEvent.class)
public class FakeMsgService implements MessageService, LccEventListener<BanEvent> {

    private final DispatcherInterface dispatcher;
    private final Map<String, List<Message>> db;
    private final List<Message> DEFAULT_MESSAGE_LIST = List.of(new Message(LocalDateTime.now(), "Write some messages", ""));

    public FakeMsgService(DispatcherInterface dis) {
        dispatcher = dis;
        db = new HashMap<>();
        log.info("Fake msg service");
        log.info(Arrays.toString(this.getClass().getAnnotations()));
    }

    @Override
    public MessageService addMessage( Message msg) {
        var user = msg.getUserName();
        autoCheckMessageService(msg);
        db.merge(user,
                ListWithMsg(msg),
                (prev, next) -> {
                    prev.add(msg);
                    return prev;
                });
        return this;
    }

    private List<Message> ListWithMsg(Message msg) {
        var list = new ArrayList<Message>();
        list.add(msg);
        return list;
    }

    @Override
    public List<Message> getMessages(String user) {
        return db.getOrDefault(user, DEFAULT_MESSAGE_LIST);
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listenToEvent(BanEvent event) {
        //ban given user
        
        log.info("got Ban event for: " + event.getName());
    }

    private void autoCheckMessageService(Message msg) {
            log.info("testing for possible Illegal Bomb: " + msg.getUserName());
            if (msg.getMessage().toLowerCase().contains("bomb")){
                log.info("!!!!!!!!!!!BOMB!!!!!!!!!!!!! in message " + msg.toString());
                dispatcher.dispatch(new BombModEvent(msg.getUserName(), msg));
            }
    }

    @Deprecated(since="for testing noly")
    public void resetDB() {
        db.clear();
    }
    
    

}
