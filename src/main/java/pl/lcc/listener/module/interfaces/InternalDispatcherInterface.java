/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.module.interfaces;

/**
 * Functions for internal use.
 * getAlllLIstenersInfo is for dabug mostly
 * @author Nauczyciel
 */
public interface InternalDispatcherInterface extends DispatcherInterface{
    
    void addListener(LccEventListener<?> listener);

    public String getAllListenersInfo();
    
}
