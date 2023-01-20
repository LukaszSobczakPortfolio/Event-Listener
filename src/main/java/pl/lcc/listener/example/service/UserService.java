/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Optional;
import pl.lcc.listener.example.user.UserCore;

/**
 *
 * @author Nauczyciel
 */

public interface UserService {
    
    UserCore CreateUser (String name, String password);
    
    boolean hasExist(String name);
    
    Optional<UserCore> getUserCore (String name, String password);
}
