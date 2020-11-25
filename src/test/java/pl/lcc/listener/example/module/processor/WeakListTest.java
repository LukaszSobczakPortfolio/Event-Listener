/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
public class WeakListTest {
    
       
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndSize() {
        SoftAssertions softly = new SoftAssertions();
        var list = new WeakArrayList<Object>();
        var koko = new Object();
        softly.assertThat(list).as("No Elements").hasSize(0);
        softly.assertThat(list).as("is Empty check").isEmpty();
        list.add(koko);
        softly.assertThat(list).as("added one element").hasSize(1);
        softly.assertThat(list).as("list not empty").isNotEmpty();
        list.add(new Object());
        softly.assertThat(list).as("added one element").hasSize(2);
        softly.assertThat(list).as("list not empty").isNotEmpty();
        koko = null;
        System.out.println("memory one: " + Runtime.getRuntime().freeMemory());             
        System.gc();
        System.out.println("memory two: " + Runtime.getRuntime().freeMemory());
        softly.assertThat(list).as("added one element").hasSize(0);
        softly.assertThat(list).as("list not empty").isEmpty();
        softly.assertAll();
        }
    
}
