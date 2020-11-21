/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.module.interfaces;

import java.util.List;

/**
 *
 * @author piko
 */
public interface DispatcherInterface {

    public void dispatch(LccEvent e);

    void addListener(LccEventListener<?> listener);

    public String getAllListenersInfo();
}
