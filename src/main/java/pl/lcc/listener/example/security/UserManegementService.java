/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Component
public class UserManegementService implements UserDetailsService, UserDetailsManager {

    Map<String, UserDetails> users;

    public UserManegementService() {
        log.info("--- created UMS ---");
        users = new ConcurrentHashMap<>();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("--- try to load User: " + username + "---");

        if (users.containsKey(username)) {
            return ((SecuredUser) users.get(username)).clone();
        } else {
            throw new UsernameNotFoundException("UMS: username not found: " + username);
        }
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            throw new IllegalArgumentException("User Already Exist");
        } else {
            users.put(user.getUsername(), user);
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            users.put(user.getUsername(), user);
        } else {
            throw new UsernameNotFoundException("UMS: Update Failed. Username not found: " + user.getUsername());
        }
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean userExists(String username) {
       return users.containsKey(username);
    }

}
