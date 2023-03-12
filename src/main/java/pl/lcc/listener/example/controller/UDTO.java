/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

//User DTO for catching data from login form

class UDTO {

    String username;
    String password;
    boolean admin = false;

    public String getUsername() {
        return username;
    }

    public UDTO setUsername(String name) {
        this.username = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public UDTO setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    @Override
    public String toString() {
        return "UDTO{" + "name=" + username + ", password=" + password + ", admin=" + admin + '}';
    }
    
}
