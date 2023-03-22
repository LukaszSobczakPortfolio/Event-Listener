/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

import java.util.Objects;

/**
 * Receiver: UserService
 * Meaning: User with given name got ban.
 * Sender: Mod.class
 * @author Nauczyciel
 */

public final class BanEvent extends AbstractModerationEvent{
    
    public BanEvent(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.offenderName);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        BanEvent otherBanEvent = (BanEvent) other;
        return Objects.equals(this.offenderName, otherBanEvent.offenderName);
    }
    
    
}
