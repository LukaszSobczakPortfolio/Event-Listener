/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import pl.lcc.listener.example.user.UserCore;

/**
 *
 * @author Nauczyciel
 */
public class FakeUserService implements UserService{

    Map<String, String> passwords = new ConcurrentHashMap<>();
    Map<String, UserCore> users = new ConcurrentHashMap<>();
    
    
    @Override
    public UserCore CreateUser(String name, String password) {
        if (passwords.get(name) == null){        
            passwords.put(name, password);
            UserCore user = new UserCore(name);
            users.put(name, user);
            return user;
        } else{
            throw new IllegalArgumentException("User " + name +" already exist!");
        }
        
    }

    @Override
    public boolean hasExist(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<UserCore> getUserCore(String name, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
