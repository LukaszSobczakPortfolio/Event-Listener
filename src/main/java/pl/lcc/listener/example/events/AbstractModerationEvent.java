/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

import pl.lcc.listener.module.interfaces.LccEvent;

/**
 *
 * @author Nauczyciel
 */
public abstract sealed class AbstractModerationEvent implements LccEvent permits BanEvent, WarningEvent{

    protected final String offenderName;

    public AbstractModerationEvent(String offenderName) {
        this.offenderName = offenderName;
    }
    
    public String getName() {
        return offenderName;
    }
   
}
