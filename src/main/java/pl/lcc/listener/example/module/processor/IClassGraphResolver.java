/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.util.stream.Stream;

/**
 *
 * @author piko
 */
public interface IClassGraphResolver {
    
    public Class<?>[] resolve(Object subject);

    Stream<Class<?>> resolveToStream(Object subject);
    
}
