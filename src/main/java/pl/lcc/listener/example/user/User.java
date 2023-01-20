/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
/**
 *
 * @author Nauczyciel
 */
@Component
@SessionScope
public class User{
    
    List<LocalDateTime> values;
    
    String name;
    
    boolean flagged;

    public User() {
        System.out.println("new User");
        values = new ArrayList<>();
        flagged = false;
    } 
    
    @Deprecated
    public User put(){
        values.add(LocalDateTime.now());
        return this;
    }
    
    @Deprecated
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

}
