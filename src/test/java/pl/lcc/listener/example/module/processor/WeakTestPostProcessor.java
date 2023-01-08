/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.lang.ref.WeakReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author piko
 */
@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestListeners.ListenersConfiguration.class)
public class WeakTestPostProcessor {

    @Autowired
    ApplicationContext ctx;

    @Autowired
    DispatcherInterface dispatcher;
    
    @Test
    public void testSetupTest() {
        var listener = ctx.getBean(TestListeners.SingletonListener.class);
        var listener2 = ctx.getBean(TestListeners.SingletonListener.class);
        assertThat(listener==listener2).as("shpuld be different").isFalse();
        
        dispatcher.dispatch(new TestEvents.EmptyEvent());
        
        assertThat(listener.count).as("should be 1").isEqualTo(1);
        assertThat(listener2.count).as("should be 1").isEqualTo(1);
        
    }
    
    @Test
    public void testDispatchWithGC() {
        
        TestListeners.SingletonListener.resetStaticCount();
        
        var listener = ctx.getBean(TestListeners.SingletonListener.class);
        var listener2 = ctx.getBean(TestListeners.SingletonListener.class);
        assertThat(listener==listener2).as("shpuld be different").isFalse();
        
        dispatcher.dispatch(new TestEvents.EmptyEvent());
        
        log.info(listener.getInfo());
        log.info(listener2.getInfo());
        
        assertThat(listener.count).as("should be 1").isEqualTo(1);
        assertThat(listener2.count).as("should be 1").isEqualTo(1);
        assertThat(TestListeners.SingletonListener.getStaticCount())
                .as("total calls should be 2")
                .isEqualTo(2);
        
        listener2 = null;
        log.info(listener.getInfo());
        
        waitForGC();
        
        dispatcher.dispatch(new TestEvents.EmptyEvent());
         
         assertThat(listener.count).as("should be 2").isEqualTo(2);
          assertThat(TestListeners.SingletonListener.getStaticCount())
                .as("total calls should be 3")
                .isEqualTo(3);
        
         listener = null;
         
         waitForGC();
         
         dispatcher.dispatch(new TestEvents.EmptyEvent());
         
        assertThat(TestListeners.SingletonListener.getStaticCount())
                .as("total calls still should be 3")
                .isEqualTo(3);
        
    }
    
    private void waitForGC(){
        var weakReference = new WeakReference<Object>(new Object());
        while (weakReference.get()!=null){
            System.gc();
        }
    }

}
