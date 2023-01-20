/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.processor;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author piko
 */
@Slf4j
@Component
public class EventsDispatcher implements DispatcherInterface{

    private final IEventStorage eventMapper;
    
    public EventsDispatcher(IEventStorage mapper) {
        log.info("Dispatcher constructor");
        eventMapper = mapper;
    }

    @Override
    public void dispatch(LccEvent event) {
        log.info("looking for dispatch " + event.toString());
        List<LccEventListener<? extends LccEvent>> listeners = eventMapper.getListenersForEvent(event);
        log.info("found : " + listeners.size() + " : " + listeners.toString());
        listeners.forEach(listener->((LccEventListener<LccEvent>)listener).listenToEvent(event));
    }

    @Override
    public void addListener(LccEventListener<?> listener) {
        eventMapper.addListener(listener);
    }

    @Override
    public String getAllListenersInfo() {
       var x = eventMapper.getStreamOfListenersDescription()
               .collect(Collectors.joining("\n"));                
       return x;
    }
    
    
    
}
