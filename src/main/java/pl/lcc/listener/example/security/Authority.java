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
public enum Authority implements GrantedAuthority {

    USER("role-user"),
    MOD("role-mod");

    String role;

    private Authority(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Authority{" + "role=" + role + '}';
    }

    @Override
    public String getAuthority() {
        return role;
    }

}
