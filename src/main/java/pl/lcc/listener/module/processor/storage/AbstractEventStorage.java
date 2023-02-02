/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.processor.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;
import pl.lcc.listener.module.processor.ClassGraphResolver;

/**
 * implements all required methods, but newList method depends on type of lisr used (array list, weak list, etc
 * TODO : can I have Generics instead of abstracct class?
 * like EventStorage <Map.class, List.class>
 * @author piko
 */
public abstract class AbstractEventStorage implements IEventStorage {

    private final Map<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>> map;

    protected static Function<Map.Entry<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>>, Stream<String>> makeListenerDescription() {
        return (mapEntry) -> {
            String key = mapEntry.getKey().toGenericString();
            return mapEntry.getValue()
                    .stream()
                    .map((listener) -> listener.getClass().toGenericString())
                    .map((listenerDesc) -> listenerDesc + " : " + key);            
        };
    }

    protected AbstractEventStorage(Map<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>> map) {
        this.map = map;
    }

    @Override
    public void addListener(LccEventListener<? extends LccEvent> listener) {
        Class<? extends LccEvent> key = findTargetEventClass(listener);
        LccEventListener<?> value = listener;
        map.merge(key, newList(value), addNewValueToList());
    }

    protected Class<? extends LccEvent> findTargetEventClass(LccEventListener<? extends LccEvent> listener) {
        return listener.getClass().getAnnotation(LccListenerClass.class).targetEvent();
    }

    protected List<LccEventListener<? extends LccEvent>> newList(LccEventListener<? extends LccEvent> listener) {
        List<LccEventListener<? extends LccEvent>> list = new ArrayList<>();
        list.add(listener);
        return list;
    }

    protected BiFunction<? super List<LccEventListener<? extends LccEvent>>, ? super List<LccEventListener<? extends LccEvent>>, ? extends List<LccEventListener<? extends LccEvent>>> addNewValueToList() {
        return (oldList, newList) -> {
            oldList.addAll(newList);
            return oldList;
        };
    }

    @Override
    public List<LccEventListener<? extends LccEvent>> getListenersForEvent(LccEvent event) {
        return new ClassGraphResolver()
                .FindAllInterfacesToStream(event)
                .filter(LccEvent.class::isAssignableFrom)
                .map((klazz) -> map.get(klazz))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public Stream<String> getStreamOfListenersDescription() {
        return map.isEmpty() ? Stream.of("[]") : getStreamOfListenerSummaries();
    }

    protected Stream<String> getStreamOfListenerSummaries() {
        return map.entrySet().stream().flatMap(makeListenerDescription());
    }

}
