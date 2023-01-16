/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.controller;

import pl.lcc.listener.example.user.Message;

/**
 *
 * @author Nauczyciel
 */
public interface VerificationService {
    
    void checkMessage(String user, Message msg);
    
    void banUser(String name);
    
}
