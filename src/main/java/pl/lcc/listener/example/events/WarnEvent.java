/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.events;

/**
 * Receiver: UserService
 * Meaning: User get warning - before ban
 * Sender: Mod.class
 * 
 * @author Nauczyciel
 */
public class WarnEvent {

    private final String offenderName;

    public WarnEvent(String offenderName) {
        this.offenderName = offenderName;
    }

    public String getName() {
        return offenderName;
    }

}
