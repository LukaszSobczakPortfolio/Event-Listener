/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Nauczyciel
 */
public class Authorities {
    
    public static class User implements GrantedAuthority{

        @Override
        public String getAuthority() {
            return "User_Role";
        }
        
    }
    
    public static class Mod implements GrantedAuthority{

        @Override
        public String getAuthority() {
            return "User_Mod";
        }
        
    }
    
}
