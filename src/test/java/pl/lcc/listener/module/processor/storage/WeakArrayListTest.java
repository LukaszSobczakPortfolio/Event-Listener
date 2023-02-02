/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.module.processor.storage;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.assertj.core.api.SoftAssertions;

/**
 *
 * @author Nauczyciel
 */
public class WeakArrayListTest {
    
    WeakArrayList<Integer> testList; 
    List<Integer> solidList = List.of(1,2,3);
    
    @BeforeEach
    void makeList(){

        testList = new WeakArrayList<>();
        solidList.forEach(e -> testList.add(e));
    }
    

    @Test
    public void testAdd_GenericType() {
        var newValue = Integer.valueOf(17);
        testList.add(newValue);
        assertThat(testList.size()).isEqualTo(4);
        
    }
    
    @Test
    public void testAdd_int_GenericType() {
        var softly = new SoftAssertions();
        var newValue = Integer.valueOf(17);
        testList.add(1,newValue);
        softly.assertThat(testList.size()).isEqualTo(4);
        softly.assertThat(testList.get(1)).isEqualTo(17);
        softly.assertAll();
    }

    @Test
    public void testGet() {
        assertThat(testList.get(1)).isEqualTo(2);
    }

    @Test
    public void testSize() {
        var softly = new SoftAssertions();
        softly.assertThat(testList.size()).as("original solid 3").isEqualTo(3);        
        softly.assertAll();
        
    }
    

    @Test
    public void testStream() {
        var softly = new SoftAssertions();
        softly.assertThat(testList.stream().toList().size()).as("stream 3").isEqualTo(3);
        softly.assertThat(testList.stream().toList().toString()).as("stream 3").isEqualTo("[1, 2, 3]");
        
        softly.assertAll();
    }

    @Test
    public void testForEach() {
        var softly = new SoftAssertions();
        var list = new ArrayList<Integer>();
        testList.forEach(e -> list.add(e));
        softly.assertThat(list.size()).as("initial 3").isEqualTo(3);
        softly.assertThat(list.toString()).isEqualTo("[1, 2, 3]");
        softly.assertAll();
    }
    
    @Test
    public void testAddAndSize() {
        SoftAssertions softly = new SoftAssertions();
        var list = new WeakArrayList<Object>();
        var koko = new Object();
        softly.assertThat(list).as("No Elements").hasSize(0);
        softly.assertThat(list).as("is Empty check").isEmpty();
        
        list.add(koko);
        softly.assertThat(list).as("koko inside").hasSize(1);
        softly.assertThat(list).as("list not empty").isNotEmpty();
        
        list.add(new Object());
        softly.assertThat(list).as("koko and Object").hasSize(2);
        softly.assertThat(list).as("list not empty").isNotEmpty();
        
        System.gc();
        
        softly.assertThat(list).as("Object Garbaged, koko inside").hasSize(1);
        softly.assertThat(list).as("list not empty").isNotEmpty();
        
        koko = null;
        System.gc();

        softly.assertThat(list).as("koko Garbaged too").hasSize(0);
        softly.assertThat(list).as("should be empty again").isEmpty();
        softly.assertAll();
        }

}
