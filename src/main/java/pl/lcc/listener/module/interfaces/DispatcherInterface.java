/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.interfaces;

/**
 * Public interface of module. ONe mrthod for disptching events
 * @author piko
 */
public interface DispatcherInterface {

    public void dispatch(LccEvent e);
    
}
