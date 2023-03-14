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
import pl.lcc.listener.example.security.SecuredUser;
/**
 * stores data required to display UserPanel
 * @author Nauczyciel
 */
@Slf4j
@Component
@SessionScope
public class User{
    
    SecuredUser principal;

    public User() {
        log.info("new User");  

       var tmpPrincipal = SecurityContextHolder
            .getContext()
            .getAuthentication()
               .getPrincipal();
       if (tmpPrincipal instanceof SecuredUser securedUser){
           this.principal = securedUser;
           
       } else {
           throw new IllegalStateException("User Class works with Secured User!");
       }
    
        log.info("The User created with auth: " + principal.getUsername());
    } 
    
    public String getName() {
        return principal.getUsername();
    }
    
    @Override
    public String toString() {
        return "User{" + "name=" + getName() + '}';
    }

    public boolean isFlagged() {
       return principal.isAccountNonWarned();
    }

     public boolean isAdmin() {
        return principal.getAuthorities().contains(Authority.MOD);
    }
    
}