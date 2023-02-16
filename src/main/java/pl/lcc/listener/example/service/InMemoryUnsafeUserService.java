/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.user.SecuredUserDetails;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 * includes in Memory storarge
 * @author Nauczyciel
 */
@Component
@Slf4j
@LccListenerClass(targetEvent = BanEvent.class)
public class InMemoryUnsafeUserService implements UserService, LccEventListener<BanEvent>{

    Map<String, String> passwords = new ConcurrentHashMap<>();
    Map<String, SecuredUserDetails> users = new ConcurrentHashMap<>();
    
    
    @Override
    public Optional<SecuredUserDetails> tryCreateUser(String name, String password, boolean isAdmin) {
        if (!hasExist(name)){        
            passwords.put(name, password);
            SecuredUserDetails user = new SecuredUserDetails(name);
                    user.setAdmin(isAdmin);
            users.put(name, user);
           
            return Optional.of(user);
        } else {
           return Optional.empty();
        }
        
    }

    @Override
    public boolean hasExist(String name) {
       return users.containsKey(name);
    }

    @Override
    public Optional<SecuredUserDetails> tryGetUserCore(String name, String password) {
      return password.equals(passwords.get(name)) 
              ? Optional.of(users.get(name)) 
              : Optional.empty();

    }

    @Override
    public Optional<SecuredUserDetails> tryCreateUser(String name, String password) {
        return tryCreateUser(name, password, false);
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listenToEvent(BanEvent event) {
            
        log.info("got Ban event for: " + event.getName());
        users.get(event.getName()).setBanned(true);
    }
    
}
