/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Optional;
import pl.lcc.listener.example.user.UserDetails;

/**
 * Logic for create and retrieve user data
 * @author Nauczyciel
 */

public interface UserService {
    
    Optional<UserDetails> tryCreateUser (String name, String password);
    
    Optional<UserDetails>  tryCreateUser  (String name, String password, boolean isAdmin);
    
    boolean hasExist(String name);
    
    Optional<UserDetails> tryGetUserCore (String name, String password);
    
    
}
