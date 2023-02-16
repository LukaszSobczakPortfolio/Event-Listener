/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
/**
 * stores data required to display UserPanel
 * @author Nauczyciel
 */
@Slf4j
@Component
@SessionScope
public class User{
    
    SecuredUserDetails core;

    public User() {
        log.info("new User");
    } 
    
    public User setCore (SecuredUserDetails core){
        this.core = core;
        return this;
    }
    
    public String getName() {
        return core.getName();
    }

    public void setName(String name) {
        throw new UnsupportedOperationException("to do");    }

        
    
    @Override
    public String toString() {
        return "User{" + "name=" + getName() + '}';
    }

    public boolean isFlagged() {
        return core.isBanned();
    }
    
    public void setFlagged(boolean flagged) {
         throw new UnsupportedOperationException("to do");
    }

     public boolean isAdmin() {
        return core.isAdmin();
    }
    
    public void setAdmin(boolean admin) {
         throw new UnsupportedOperationException("to do");
    }
    
}
