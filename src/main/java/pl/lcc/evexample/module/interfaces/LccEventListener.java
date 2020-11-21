package pl.lcc.evexample.module.interfaces;

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

public interface LccEventListener <A extends LccEvent> {
   
    public String getInfo();
    
    public void listenToEvent(A event);
    
}
