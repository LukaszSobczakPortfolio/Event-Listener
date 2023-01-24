/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pl.lcc.listener.example.user.UserCore;

/**
 *
 * @author Nauczyciel
 */
public class FakeUserServiceTest {
    
   
    @Test
    public void testSimpleCreateUser() {
        
        var service = new FakeUserService();
        
       var result = service.tryCreateUser("kilo", "kolo");
        
       assertThat(result.get()).isEqualTo(new UserCore("kilo"));
    }
    
    @Test
    public void testDoExist(){
        var service = new FakeUserService();
        
       var result = service.tryCreateUser("kilo", "kolo");
       
        assertThat(service.hasExist("kilo")).isTrue();
        assertThat(service.hasExist("wrong")).isFalse();
       
    }
    
    @Test
    public void testSimpleGetUser(){
       var service = new FakeUserService();
       service.tryCreateUser("kilo", "kolo");
       
       assertThat(service.tryGetUserCore("wrong", "kolo")).isEmpty();
       assertThat(service.tryGetUserCore("kilo", "wrong")).isEmpty();
       assertThat(service.tryGetUserCore("wrong", "wrong")).isEmpty();
       assertThat(service.tryGetUserCore("kilo", "kolo").get()).isEqualTo(new UserCore("kilo"));
    }
}
