/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.processor.EventsDispatcher;

/**
 * Displays active LIsteners
 *
 * @author piko
 */
@Slf4j
@Component
@Profile("!test")
@WebEndpoint(id = "listeners")
public class ListenersInfo {

    //class, as I nees access to getAllListenersInfo method
    private final EventsDispatcher dispatcher;

    public ListenersInfo(EventsDispatcher dispatcher) {
        log.info("actuator constructor");
        this.dispatcher = dispatcher;
    }

    @ReadOperation
    public String readOp() {
        return dispatcher.getAllListenersInfo();
    }

}
