/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author piko
 */
@Slf4j
@Controller
public class WebController {

    private final ApplicationContext ctx;

    public WebController(ApplicationContext ctx) {
        log.info("WebController Constructor");
        this.ctx = ctx;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings";
    }

    @GetMapping("/down")
    public void shutdownApp() {
        log.info("Shutdown started...");
        int exitCode = SpringApplication.exit(ctx, () -> 0);
        log.info("Going to exit...");
        System.exit(exitCode);
    }

}
