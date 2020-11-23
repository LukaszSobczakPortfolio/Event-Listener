/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.util.UUID;

/**
 *
 * @author piko
 */
public class Identifier implements IIdentifier{
    
    private final UUID id;

    public Identifier() {
        this.id = UUID.randomUUID();
    }
    
    @Override
    public String getId(){
        return id.toString();
    }
    
}
