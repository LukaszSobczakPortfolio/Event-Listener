/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Nauczyciel
 */
public class SecuredUser implements UserDetails {

    String userName;
    String password;
    List<GrantedAuthority> auths;
    boolean nonLocked;

    public SecuredUser(String userName, String password) {
        System.out.println("SU -> create " + userName);
        this.userName = userName;
        this.password = password;
        nonLocked = true;
        auths = new LinkedList<>();
        auths.add(new Authorities.User());
    }

    public SecuredUser setAuthorities(List<GrantedAuthority> auths) {
        this.auths = auths;
        return this;
    }

    public SecuredUser lock() {
        nonLocked = false;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
