/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.user;

/**
 *
 * @author Nauczyciel
 */
public class UserCore {

   private String name;
   
   private boolean banned;
   
   private boolean admin; 

    public UserCore(String name) {
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
    
    
    
}
