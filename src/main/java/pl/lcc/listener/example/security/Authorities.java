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
    
    static public class User implements GrantedAuthority{

        @Override
        public String getAuthority() {
            return "User_Role";
        }
        
    }
    
    static public class Mod implements GrantedAuthority{

        @Override
        public String getAuthority() {
            return "User_Mod";
        }
        
    }
    
}
