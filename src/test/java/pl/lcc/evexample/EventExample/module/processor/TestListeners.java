/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.module.processor;

import lombok.extern.slf4j.Slf4j;
import pl.lcc.evexample.module.interfaces.LccEvent;
import pl.lcc.evexample.module.interfaces.LccEventListener;
import pl.lcc.evexample.module.interfaces.LccListenerClass;

/**
 *
 * @author piko
 */
@Slf4j
public class TestListeners {
    
    @LccListenerClass(targetEvent = LccEvent.class)
    public static class CatchAllListener implements LccEventListener<LccEvent>{

        public static int count =0;
        
        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(LccEvent event) {
            count++;
            log.info(event.toString());
        }
        
    }
    
    @LccListenerClass(targetEvent = LccEvent.class)
    public static class CatchAllSecondListener implements LccEventListener<LccEvent>{

        public static int count =0;
        
        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(LccEvent event) {
            count++;
            log.info(event.toString());
        }
        
    }
    
    @LccListenerClass(targetEvent = LccEvent.class)
    public static class CatchAllThirdListener implements LccEventListener<LccEvent>{

        public static int count =0;
        
        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(LccEvent event) {
            count++;
            log.info(event.toString());
        }
        
    }
    
     @LccListenerClass(targetEvent = TestEvents.PlantEvent.class)
    public static class PlantListener implements LccEventListener<TestEvents.PlantEvent>{

         public static int count =0;
         
        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(TestEvents.PlantEvent event) {
            count ++;
            log.info("Plant got sun: " + event.photosynthesized());
        }
        
    }
     
    @LccListenerClass(targetEvent = TestEvents.AnimalEvent.class)
    public static class AnimalListener implements LccEventListener<TestEvents.AnimalEvent>{

        public static int count =0;
        
        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(TestEvents.AnimalEvent event) {
            count ++;
            log.info("Animal sounds: " + event.getSound());
        }
        
    }
      
      
}
