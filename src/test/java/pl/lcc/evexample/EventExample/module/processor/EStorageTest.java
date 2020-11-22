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
       //store.addListener(new TestListeners.CatchAllListener);
        List<Class<?>> result = store
                .getAllInterfaces(new TestEvents.EmptyEvent())
                .peek(System.out::println)
                .collect(Collectors.toList());
        
        assertThat(result).as("Top LccEvent Only")
                .hasSize(1)
                .allMatch(klazz->"LccEvent".equals(klazz.getSimpleName()));
    }
    
    @Test
    public void testgetAllInterfacesTwo(){
        List<Class<?>> result = store
                .getAllInterfaces(new TestEvents.AdvancedEmptyEvent())
                .peek(System.out::println)
                .collect(Collectors.toList());        
                  
        assertThat(result).as("Top and Advanced")
                .hasSize(2)
                .allMatch(klazz->klazz.getSimpleName().contains("LccEvent"));
    }
}
