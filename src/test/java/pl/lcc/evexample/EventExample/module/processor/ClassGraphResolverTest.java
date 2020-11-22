/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.module.processor;

import java.io.Closeable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.core.AliasRegistry;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 *
 * @author piko
 */
public class ClassGraphResolverTest {
    
    ClassGraphResolver resolver;
        
    @BeforeEach
    public void setUp() {
        resolver = new ClassGraphResolver();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        var result = resolver.resolve(new LinkedList<String>());
        System.out.println(result.length);
        assertThat(result).as("Linked List Resolved")
                .hasSizeGreaterThan(11)
                .contains(LinkedList.class, Object.class, List.class, Iterable.class);
    }
    
    @Test
    void testContext(){
        var result = resolver.resolve( new GenericWebApplicationContext());
         assertThat(result).as("Linked List Resolved")
                 .hasSizeGreaterThan(20)
                 .contains(Object.class, AliasRegistry.class, Closeable.class);
         Arrays.asList(result).forEach(System.out::println);
    }
            
    
}
