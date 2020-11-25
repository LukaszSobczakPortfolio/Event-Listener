/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 *
 * @author piko
 */
@Slf4j
public class TestListeners {

    @LccListenerClass(targetEvent = LccEvent.class)
    public static class CatchAllListener implements LccEventListener<LccEvent> {

        int count = 0;

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
    public static class CatchAllSecondListener implements LccEventListener<LccEvent> {

        int count = 0;

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
    public static class CatchAllThirdListener implements LccEventListener<LccEvent> {

        int count = 0;

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
    public static class PlantListener implements LccEventListener<TestEvents.PlantEvent> {

        int count = 0;

        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(TestEvents.PlantEvent event) {
            count++;
            log.info("Plant got sun: " + event.photosynthesized());
        }

    }

    @LccListenerClass(targetEvent = TestEvents.AnimalEvent.class)
    public static class AnimalListener implements LccEventListener<TestEvents.AnimalEvent> {

        int count = 0;

        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(TestEvents.AnimalEvent event) {
            count++;
            log.info("Animal sounds: " + event.getSound());
        }

    }

    @LccListenerClass(targetEvent = TestEvents.EmptyEvent.class)
    public static class SingletonListener implements LccEventListener<TestEvents.EmptyEvent> {

        private static final Object LOCK = new Object();
        static int staticCount = 0;
        int count = 0;
        UUID id = UUID.randomUUID();

        @Override
        public String getInfo() {
            return String.valueOf(count);
        }

        @Override
        public void listenToEvent(TestEvents.EmptyEvent event) {
            synchronized (LOCK) {
                count++;
                staticCount++;
                log.info("Singleton Listener" + id.toString());
            }
        }

        int getCount() {
            return count;
        }

        static void resetStaticCount() {
            staticCount = 0;
        }

        static synchronized int getStaticCount() {
            return staticCount;
        }

    }

    @TestConfiguration
    public static class ListenersConfiguration {

        @Bean
        @Scope(SCOPE_PROTOTYPE)
        TestListeners.SingletonListener getSingle() {
            return new TestListeners.SingletonListener();
        }

        @Bean
        TestListeners.CatchAllListener catchAll() {
            return new TestListeners.CatchAllListener();
        }

        @Bean
        TestListeners.AnimalListener catchAnimal() {
            return new TestListeners.AnimalListener();
        }

        @Bean
        TestListeners.PlantListener catchPlant() {
            return new TestListeners.PlantListener();
        }

    }

}
