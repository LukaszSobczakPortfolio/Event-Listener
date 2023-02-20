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
public class Authority {
    
    private static GrantedAuthority user;
    private static GrantedAuthority mod;
    
    public static GrantedAuthority user(){
        if (user == null){
            user = new User();
        }
        return user;
    }
    
     public static GrantedAuthority mod(){
        if (mod == null){
            mod = new Mod();
        }
        return mod;
    }
    
    
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
