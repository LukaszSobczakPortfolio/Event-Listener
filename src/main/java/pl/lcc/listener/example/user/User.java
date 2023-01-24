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
    
    UserCore core;

    public User() {
        System.out.println("new User");
        values = new ArrayList<>();
    } 
    
    public User setCore (UserCore core){
        this.core = core;
        return this;
    }
    
    public String getName() {
        return core.getName();
    }

    public void setName(String name) {
        throw new RuntimeException("to do");    }

    @Override
    public String toString() {
        return "User{" + "name=" + getName() + '}';
    }

    public boolean isFlagged() {
        return core.isBanned();
    }
    
    public void setFlagged(boolean flagged) {
         throw new RuntimeException("to do");
    }

}
