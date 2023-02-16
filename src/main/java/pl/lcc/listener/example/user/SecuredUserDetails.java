/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

import java.util.Objects;

/**
 * Value class. User name, password, privilaedges
 * @author Nauczyciel
 */
public class SecuredUserDetails {

   private String name;
   
   private boolean banned;
   
   private boolean admin; 

    public SecuredUserDetails(String name) {
        this.name = name;
        banned = false;
        admin = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserCore{" + "name=" + name + ", banned=" + banned + ", admin=" + admin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (this.banned ? 1 : 0);
        hash = 89 * hash + (this.admin ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SecuredUserDetails other = (SecuredUserDetails) obj;
        if (this.banned != other.banned) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
    
}
