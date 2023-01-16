/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
/**
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class User implements LccEventListener<BanEvent>{
    
    List<LocalDateTime> values;
    String name;
    boolean flagged;

    public User() {
        System.out.println("new User");
        values = new ArrayList<>();
        flagged = false;
    } 
    
    public User put(){
        values.add(LocalDateTime.now());
        return this;
    }
    
    public List<LocalDateTime> get(){
        return values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "values=" + values + ", name=" + name + '}';
    }

    public boolean isFlagged() {
        return flagged;
    }
    
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    @Override
    public String getInfo() {
        return toString();
    }

    @Override
    public void listenToEvent(BanEvent event) {
       if (event.getName().equals(getName())){
           setFlagged(true);
       }
    }

    
    
}
