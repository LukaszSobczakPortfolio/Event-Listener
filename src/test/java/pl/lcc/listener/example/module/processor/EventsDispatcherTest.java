/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import pl.lcc.listener.utils.TestListeners;
import pl.lcc.listener.utils.TestEvents;
import pl.lcc.listener.module.processor.EventsDispatcher;
import pl.lcc.listener.utils.HashMapEventStorageStorage;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.InternalDispatcherInterface;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author piko
 */
public class EventsDispatcherTest {

    InternalDispatcherInterface dispatcher;

    @BeforeEach
    public void setUp() {
        dispatcher = new EventsDispatcher(new HashMapEventStorageStorage());
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testEmptyStorage() {
        var result = dispatcher.getAllListenersInfo();
        assertThat(result).as("empty Array").isEqualTo("[]");
    }

    @Test
    public void testOneStorage() {
        var listener = new TestListeners.CatchAllListener();
        dispatcher.addListener(listener);

        String result = dispatcher.getAllListenersInfo();
        assertThat(result).as("OneHit").isNotEmpty();
    }

    @Test
    public void testMultipleStorage() {

        ThreeDifferentListenersList()
                .forEach(dispatcher::addListener);

        var result = dispatcher.getAllListenersInfo();
        assertThat(result).as("Three different hits").hasLineCount(3);

        //containsPattern(pattern)
    }

    private static List<LccEventListener<? extends LccEvent>> ThreeDifferentListenersList() {
        return List.<LccEventListener<? extends LccEvent>>of(
                new TestListeners.CatchAllListener(),
                new TestListeners.AnimalListener(),
                new TestListeners.PlantListener()
        );
    }

    @Test
    public void testMultipleStorage_OneType() {

        ThreeCatchAllListenersList()
                .forEach(dispatcher::addListener);

        var result = dispatcher.getAllListenersInfo();
        assertThat(result).as("Three Hits")
                .hasLineCount(3);

    }

    private static List<LccEventListener<LccEvent>> ThreeCatchAllListenersList() {
        return List.<LccEventListener<LccEvent>>of(
                new TestListeners.CatchAllListener(),
                new TestListeners.CatchAllSecondListener(),
                new TestListeners.CatchAllThirdListener()
        );
    }

    @Test
    public void FireOneEventToOneStorage() {
        var listener = new TestListeners.PlantListener();
        dispatcher.addListener(listener);
        dispatcher.dispatch(new TestEvents.PlantEvent(10));

        assertThat(listener.getInfo()).as("OneHit").isEqualTo("1");
    }

    @Test
    public void FireOneEventToOneCatchAllStorage() {
        var listener = new TestListeners.CatchAllListener();
        dispatcher.addListener(listener);
        dispatcher.dispatch(new TestEvents.PlantEvent(10));

        assertThat(listener.getInfo()).as("OneHit").isEqualTo("1");
    }

    public void FireOneEventToManyCatchAllStorages() {
        var ThreeListenersList = ThreeCatchAllListenersList();
        ThreeListenersList.forEach(dispatcher::addListener);
        dispatcher.dispatch(new TestEvents.PlantEvent(10));

        assertThat(ThreeListenersList).as("Three Catch All With One Hit Each")
                .allMatch((listener)-> "1".equals(listener.getInfo()));
    }
}
