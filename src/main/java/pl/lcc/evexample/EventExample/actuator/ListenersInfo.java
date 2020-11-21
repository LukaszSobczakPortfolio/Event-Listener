/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.actuator;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import pl.lcc.evexample.EventExample.module.processor.EventsDispatcher;

/**
 *
 * @author piko
 */
@Slf4j
@Component
@WebEndpoint(id = "listeners")
public class ListenersInfo {
    
    private final EventsDispatcher dispatcher;

    public ListenersInfo(EventsDispatcher dispatcher) {
        log.info("actuator constructor");
        this.dispatcher = dispatcher;
    }    
        
    @ReadOperation
    public String readOp(@Selector String param) {
        return dispatcher.getAllListenersInfo();       
    }
    
    @WriteOperation
    public void writeOp(@Selector String param, String value) {
    }
    
    @DeleteOperation
    public void deleteOp(@Selector String param) {
    }
    
}
