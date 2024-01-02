/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.lcc.listener.example.user.Message;

/**
 *
 * @author Nauczyciel
 */
public class MessageCacheTest {
    
   MessageCache<Message> sut = new MessageCache<>();
       
   @Test
    public void testAddingMessagesToCache(){
        //fill three elements to list
        Stream
                .of("1","2","3")
                .map(n -> new Message(LocalDateTime.now(), n, "some user"))
                .forEach(msg-> sut.addMessage(msg));
        var result = sut.getList();                
        Assertions.assertThat(result).map(msg -> msg.getMessageBody()).contains("1", "2", "3");
        
        //add fourth -> 1st should be removed
        sut.addMessage(new Message(LocalDateTime.now(), "Other message", "any user"));        
        var resultAfter4th = sut.getList();
        Assertions.assertThat(resultAfter4th).map(msg -> msg.getMessageBody()).contains("2", "3", "Other message");
    }
    
    @Test
    void testEmptyCache(){
        MessageCache<Message> sut = new MessageCache();
        
        var emptyResult = sut.getList();
        System.out.println(emptyResult);        
        Assertions.assertThat(emptyResult).isEmpty();
    }
    
}
