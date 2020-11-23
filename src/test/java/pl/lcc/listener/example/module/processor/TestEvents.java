/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import pl.lcc.listener.module.interfaces.LccEvent;

/**
 *
 * @author piko
 */
@Slf4j
public class TestEvents {
    
    public static class PlantEvent implements LccEvent {
        
        private final int amount;

        public PlantEvent(int amount) {
            this.amount = amount;
        }
                
        public int photosynthesized(){
            return amount;
        }
        
    }
    
    public static class AnimalEvent implements LccEvent{
        
        private final String sound;

        public AnimalEvent(String sound) {
            this.sound = sound;
        }
        
        public String getSound(){
        return sound;
    }
        
    }
    
     public static class EmptyEvent implements LccEvent,Serializable{}
     
     interface AdvancedLccEvent extends LccEvent{}
     
     public static class AdvancedEmptyEvent implements AdvancedLccEvent {}
     
     }
    

