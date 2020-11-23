/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.util.List;
import java.util.stream.Stream;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 * Map For Listeners
 *
 * @author piko
 */
public interface IEventStorage {

    void addListener(LccEventListener<? extends LccEvent> listener);

    List<LccEventListener<? extends LccEvent>> getListenersForEvent(LccEvent event);

    public Stream<String> getStreamOfListenersDescription();
}
