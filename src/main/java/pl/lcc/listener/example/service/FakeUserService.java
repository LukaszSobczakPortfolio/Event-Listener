/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import pl.lcc.listener.example.user.UserCore;

/**
 *
 * @author Nauczyciel
 */
@Component
public class FakeUserService implements UserService{

    Map<String, String> passwords = new ConcurrentHashMap<>();
    Map<String, UserCore> users = new ConcurrentHashMap<>();
    
    
    @Override
    public Optional<UserCore> tryCreateUser(String name, String password, boolean isAdmin) {
        if (!hasExist(name)){        
            passwords.put(name, password);
            UserCore user = new UserCore(name);
            users.put(name, user);
            return Optional.of(user);
        } else{
           return Optional.empty();
        }
        
    }

    @Override
    public boolean hasExist(String name) {
       return users.containsKey(name);
    }

    @Override
    public Optional<UserCore> tryGetUserCore(String name, String password) {
      return password.equals(passwords.get(name)) 
              ? Optional.of(users.get(name)) 
              : Optional.empty();

    }

    @Override
    public Optional<UserCore> tryCreateUser(String name, String password) {
        return tryCreateUser(name, password, false);
    }

  
    
}
