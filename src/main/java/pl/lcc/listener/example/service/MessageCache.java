/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Simple cache for Messages, or other stuff. Keeps only few last elements and forgets other;
 * @author Nauczyciel
 * @param <E> 
 */
@Component
@Scope("prototype")
public class MessageCache <E> {
    
    private final Queue<E> queue;
    private static final int MAX_SIZE = 3;

    public MessageCache() {
        queue = new LinkedBlockingQueue<>();
    }

    public void addMessage(E element) {
        if (queue.size() >= MAX_SIZE) {
            queue.poll();
        }
        queue.offer(element);
    }

    public List<E> getList() {
        return new ArrayList<>(queue);
    }
    
}
