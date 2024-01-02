/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.events.ShutDownEvent;
import pl.lcc.listener.module.interfaces.LccEventListener;
import pl.lcc.listener.module.interfaces.LccListenerClass;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Component
@LccListenerClass(targetEvent = ShutDownEvent.class)
public class SytemService implements LccEventListener<ShutDownEvent>{

   private final ApplicationContext ctx;

    public SytemService(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    
    @Override
    public void listenToEvent(ShutDownEvent event) {
        log.info("Shutdown started...");
        int exitCode = SpringApplication.exit(ctx, () -> 0);
        log.info("Going to exit...");
        System.exit(exitCode);
    }
    
}
