/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.utils;

import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lcc.listener.module.interfaces.LccEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.processor.storage.AbstractEventStorage;

/**
 * is used for testing only, in case weak LIst gives strange results.
 * @author piko
 */
@Component
@Slf4j
public class HashMapEventStorageStorage extends AbstractEventStorage{

    public HashMapEventStorageStorage() {
        super(new HashMap<Class<? extends LccEvent>, List<LccEventListener<? extends LccEvent>>> ());
    }
   
}
