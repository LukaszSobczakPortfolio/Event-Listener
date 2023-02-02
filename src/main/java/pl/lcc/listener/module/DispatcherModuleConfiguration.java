/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.module;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Handle @Import => for Componenet Scan 
 * @author Nauczyciel
 */
@Configuration
@ComponentScan
public class DispatcherModuleConfiguration {
    //empty for component scan -> @Import handle
}
