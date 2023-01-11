/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.processor;

import pl.lcc.listener.module.processor.storage.WeakArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;

/**
 *
 * @author piko
 */
@Slf4j
@Component
@Primary
public class WeakEStorage extends EStorageMapWrapper{

    public WeakEStorage( ) {
        
       super( new HashMap<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>> () );
    }
    
    @Override
    protected List<LccEventListener<? extends LccEvent>> newList(LccEventListener<? extends LccEvent> listener) {
       WeakArrayList<LccEventListener<? extends LccEvent>> list = new WeakArrayList<>();
       list.add(listener);
       return list;
    }
    
}
