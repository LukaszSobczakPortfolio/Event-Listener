/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.module.interfaces;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean scans new Beans, finds Listeners, Keeps them in collection. 
 * @author piko
 */
public interface ILccPostProcessor extends BeanPostProcessor {

    @Override
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
    
}
