/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pl.lcc.listener.example.user.UserDetails;

/**
 *
 * @author Nauczyciel
 */
public class FakeUserServiceTest {
    
   
    @Test
    void testSimpleCreateUser() {
        
        var service = new InMemoryUnsafeUserService();
        
       var result = service.tryCreateUser("kilo", "kolo");
        
       assertThat(result.get()).isEqualTo(new UserDetails("kilo"));
    }
    
    @Test
    void testDoExist(){
        var service = new InMemoryUnsafeUserService();
        
       var result = service.tryCreateUser("kilo", "kolo");
       
        assertThat(service.hasExist("kilo")).isTrue();
        assertThat(service.hasExist("wrong")).isFalse();
       
    }
    
    @Test
    void testSimpleGetUser(){
       var service = new InMemoryUnsafeUserService();
       service.tryCreateUser("kilo", "kolo");
       
       assertThat(service.tryGetUserCore("wrong", "kolo")).isEmpty();
       assertThat(service.tryGetUserCore("kilo", "wrong")).isEmpty();
       assertThat(service.tryGetUserCore("wrong", "wrong")).isEmpty();
       assertThat(service.tryGetUserCore("kilo", "kolo").get()).isEqualTo(new UserDetails("kilo"));
    }
}
