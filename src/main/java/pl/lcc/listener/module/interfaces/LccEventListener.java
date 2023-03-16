package pl.lcc.listener.module.interfaces;

import java.util.EventListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * / Listener, listen for  event, kind of lccEvent.class
 * @author piko
 * @param <A>
 */

public interface LccEventListener <A extends LccEvent> extends EventListener {
   
    public default String getInfo(){
        return " This is default implementation of getInfo for: " + this.getClass().getName();
    };
    
    public void listenToEvent(A event);
    
}
