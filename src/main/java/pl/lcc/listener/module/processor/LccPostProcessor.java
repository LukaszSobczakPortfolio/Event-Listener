/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.processor;

import pl.lcc.listener.module.interfaces.ILccPostProcessor;
import java.util.Arrays;
import pl.lcc.listener.module.interfaces.LccEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.processor.storage.IEventStorage;

/**
 * Adds correct beans to 
 * @author piko
 */
@Slf4j
@Component
class LccPostProcessor implements ILccPostProcessor {

    private final IEventStorage storage;

    public LccPostProcessor(IEventStorage storage) {
        log.info("lccPP constructor");
        this.storage = storage;
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
   
        if (bean instanceof LccEventListener){
            var listener = (LccEventListener) bean;
            storage.addListener(listener);
            log.info("lcc post processor - listener: " + bean.getClass().toGenericString());
        }
        return bean;
    }

    private void displayDebugData(Object bean, String beanName) {
        log.info(bean.toString());
        log.info(beanName);
        log.info(Arrays.asList(bean.getClass().getInterfaces()).toString());
    }
}
