/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.security.SecuredUser;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 * includes in Memory storarge
 * @author Nauczyciel
 */
@Component
@Slf4j
@LccListenerClass(targetEvent = BanEvent.class)
public class InMemoryUnsafeUserService implements LccEventListener<BanEvent>{
    
    UserDetailsManager manager;

    public InMemoryUnsafeUserService(UserDetailsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listenToEvent(BanEvent event) {
            
        log.info("got Ban event for: " + event.getName());
        var user = (SecuredUser) manager.loadUserByUsername(event.getName());
        manager.updateUser(user.lock());
    }
    
}
