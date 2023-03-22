/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.AbstractModerationEvent;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.events.WarningEvent;
import pl.lcc.listener.example.security.SecuredUser;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 * includes in Memory storarge
 *
 * @author Nauczyciel
 */
@Component
@Slf4j
@LccListenerClass(targetEvent = AbstractModerationEvent.class)
public class ListenerForModifyUserEvents implements LccEventListener<AbstractModerationEvent> {

    UserDetailsManager manager;

    public ListenerForModifyUserEvents(UserDetailsManager manager) {
        this.manager = manager;
    }

    @Override
    public void listenToEvent(AbstractModerationEvent event) {

        log.info("listener got Moderation event for: " + event.getName() + ". Evenr type: " + event.getClass().getSimpleName());

//      still in preview 
//      switch(event){
//            case BanEvent b -> banUser(b);
//            case WarningEvent w -> warnUser(w);

        if (event instanceof BanEvent ban) {
            banUser(ban);
        } else if (event instanceof WarningEvent warning) {
            warnUser(warning);
        }

    }

    private void banUser(BanEvent event) {
        var user = (SecuredUser) manager.loadUserByUsername(event.getName());
        manager.updateUser(user.lock());
    }

    private void warnUser(WarningEvent event) {
        var user = (SecuredUser) manager.loadUserByUsername(event.getName());
        manager.updateUser(user.warn());
    }

}
