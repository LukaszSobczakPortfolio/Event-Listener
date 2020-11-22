/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.module.processor;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pl.lcc.evexample.module.interfaces.LccEvent;
import pl.lcc.evexample.module.interfaces.LccEventListener;

/**
 *
 * @author piko
 */
public class EStorageTest {

    EStorage store;

    @BeforeEach
    public void setUp() {
        store = new EStorage();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testgetAllInterfacesOne() {
        store.addListener(new TestListeners.CatchAllListener());
        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.EmptyEvent());

        System.out.println(result);

        assertThat(result).as("Top LccEvent Only")
                .hasSize(1)
                .allMatch(listener -> "CatchAllListener".equals(listener.getClass().getSimpleName()));
    }

    @Test
    public void testgetAllInterfacesTwo() {
        store.addListener(new TestListeners.CatchAllListener());
        store.addListener(new TestListeners.CatchAllSecondListener());
        store.addListener(new TestListeners.CatchAllThirdListener());

        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.AdvancedEmptyEvent());

        System.out.println(result);

        assertThat(result).as("Top and Advanced")
                .hasSize(3)
                .allMatch(listener -> listener.getClass().getSimpleName().contains("CatchAll"));
    }

    @Test
    public void testgetAllInterfacesNoListener() {

        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.EmptyEvent());

        System.out.println(result);

        assertThat(result).as("No Listener")
                .hasSize(0)
                .isEmpty();

    }

    @Test
    public void testgetAllInterfacesWrongListener() {
        store.addListener(new TestListeners.AnimalListener());
        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.PlantEvent(2));

        System.out.println(result);

        assertThat(result).as("Wrong Listener")
                .isEmpty();
    }

    @Test
    public void testgetAllInterfacesSomeListeners() {
        store.addListener(new TestListeners.AnimalListener());
        store.addListener(new TestListeners.AnimalListener());
        store.addListener(new TestListeners.PlantListener());

        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.PlantEvent(2));

        System.out.println(result);

        assertThat(result).as("One Out Of three")
                .hasSize(1)
                .allMatch(listener -> "PlantListener".equals(listener.getClass().getSimpleName()));
    }

    @Test
    public void testgetAllInterfacesSomeListeners2() {
        store.addListener(new TestListeners.AnimalListener());
        store.addListener(new TestListeners.AnimalListener());
        store.addListener(new TestListeners.PlantListener());

        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.AnimalEvent("Bzyk"));

        System.out.println(result);

        assertThat(result).as("Two Out Of three")
                .hasSize(2)
                .allMatch(listener -> "AnimalListener".equals(listener.getClass().getSimpleName()));
    }

    @Test
    public void testgetAllInterfacesSomeListeners3() {
        store.addListener(new TestListeners.AnimalListener());
        store.addListener(new TestListeners.CatchAllListener());
        store.addListener(new TestListeners.PlantListener());

        List<LccEventListener<? extends LccEvent>> result = store
                .getListenersForEvent(new TestEvents.AnimalEvent("Bzyk"));

        System.out.println(result);

        assertThat(result).as("Two Out Of three")
                .hasSize(2)
                .anyMatch(listener -> "AnimalListener".equals(listener.getClass().getSimpleName()))
                .anyMatch(listener -> "CatchAllListener".equals(listener.getClass().getSimpleName()));
    }

}
