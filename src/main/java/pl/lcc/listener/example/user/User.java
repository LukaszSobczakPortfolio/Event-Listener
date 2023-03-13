/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.lcc.listener.example.security.Authority;
/**
 * stores data required to display UserPanel
 * @author Nauczyciel
 */
@Slf4j
@Component
@SessionScope
public class User{
    
    Authentication auth;

    public User() {
        log.info("new User");  
        
        this.auth = SecurityContextHolder
            .getContext()
            .getAuthentication();
    
        log.info("The User created with auth: " + auth.getName());
    } 
    
    public String getName() {
        return auth.getName();
    }
    
    @Override
    public String toString() {
        return "User{" + "name=" + getName() + '}';
    }

    //TODO - waiting for warning stuff
    public boolean isFlagged() {
        return false;
    }

     public boolean isAdmin() {
        return auth.getAuthorities().contains(Authority.MOD);
    }
    
}