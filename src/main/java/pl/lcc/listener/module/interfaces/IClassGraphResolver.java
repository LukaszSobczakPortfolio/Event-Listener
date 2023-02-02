/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.interfaces;

import java.util.stream.Stream;

/**
 *
 * @author piko
 */
public interface IClassGraphResolver {
    
    public Class<?>[] findAllInterfaces(Object subject);

    Stream<Class<?>> findAllInterfacesToStream(Object subject);
    
}
