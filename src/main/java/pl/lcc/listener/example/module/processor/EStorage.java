/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 *
 * @author piko
 */
@Component
@Slf4j
public class EStorage implements IEventStorage{

    private final Map<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>> map;

    public EStorage() {
        map = new HashMap<>();
    }
     
    @Override
    public void addListener(LccEventListener<? extends LccEvent> listener) {
        var key = findTargetEventClass(listener);
        var value = listener;
        map.merge(key, newList(value), addNewValueToList());
    }

    private Class<? extends LccEvent> findTargetEventClass(LccEventListener<? extends LccEvent> listener){
        return listener.getClass().getAnnotation(LccListenerClass.class).targetEvent();
    }
    
    private  ArrayList<LccEventListener<? extends LccEvent>> newList(LccEventListener<? extends LccEvent> listener){
        var list = new ArrayList<LccEventListener<? extends LccEvent>>();
        list.add(listener);
        return list;
    }
    
    private BiFunction<? super List<LccEventListener<? extends LccEvent>>, ? super List<LccEventListener<? extends LccEvent>>, ? extends List<LccEventListener<? extends LccEvent>>> addNewValueToList() {
        return (oldList,newList) -> { 
                oldList.addAll(newList);
                return oldList;
        };
    }
    
    @Override
    public List<LccEventListener<? extends LccEvent>> getListenersForEvent(LccEvent event) {
     
        List<LccEventListener<? extends LccEvent>> result = new ClassGraphResolver()
                .resolveToStream(event)
                .filter(LccEvent.class::isAssignableFrom)
                .map(klazz -> map.get(klazz))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(toList());
        return result;
    }

    @Override
    public Stream<String> getStreamOfListenersDescription() {
        return map.isEmpty() ? Stream.of("[]") : getStreamOfListenerSummaries(); 
    }

    private Stream<String> getStreamOfListenerSummaries() {
        return map.entrySet()
                .stream()
                .flatMap(makeListenerDescription());
    }

    private static Function<Map.Entry<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>>, Stream<String>> makeListenerDescription() {
        return entry -> {
            String key = entry.getKey().toGenericString();
            Stream<String> values = entry.getValue().stream()
                    .map(listener -> listener.getClass().toGenericString())
                    .map(listenerDesc -> listenerDesc + " : " + key );
            return values;
        };
    }

    
    
}
