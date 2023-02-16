/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Optional;
import pl.lcc.listener.example.user.SecuredUserDetails;

/**
 * Logic for create and retrieve user data
 * @author Nauczyciel
 */

public interface UserService {
    
    Optional<SecuredUserDetails> tryCreateUser (String name, String password);
    
    Optional<SecuredUserDetails>  tryCreateUser  (String name, String password, boolean isAdmin);
    
    boolean hasExist(String name);
    
    Optional<SecuredUserDetails> tryGetUserCore (String name, String password);
    
    
}
