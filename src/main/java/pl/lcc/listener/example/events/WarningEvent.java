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
public final class WarningEvent extends AbstractPenaltyEvent{
    
    public WarningEvent(String offenderName) {
        super(offenderName);
    }
    
}
