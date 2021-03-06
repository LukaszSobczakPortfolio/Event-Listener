package pl.lcc.listener.module.interfaces;

import java.util.EventListener;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piko
 * @param <A>
 */

public interface LccEventListener <A extends LccEvent> extends EventListener {
   
    public String getInfo();
    
    public void listenToEvent(A event);
    
    @PreDestroy
    private void removeMyselfFromDispatcher(){
        System.out.println("pre Destroy for Listener");
    }
    
}
