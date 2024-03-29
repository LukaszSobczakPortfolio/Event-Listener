/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pl.lcc.listener.example.security.Authority;

/**
 * Tests Login only - Data taken from LoadFakeDataRunner. 
 * @author Nauczyciel
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    void userLoginTest() throws Exception{
        var login = formLogin()
            .user("test")
            .password("pass");
        
        mvc.perform(login)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/panel"))
            .andExpect(authenticated().withUsername("test").withAuthorities(List.of(Authority.USER)));
    }      
       
    @Test
    void wrongLoginTest() throws Exception{
        var login = formLogin()
            .user("unknown")
            .password("plainlyWrong");
        
        mvc.perform(login)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?error"))
            .andExpect(unauthenticated());
    }    
    
     @Test
    void modLoginTest() throws Exception{
        var login = formLogin()
            .user("admin")
            .password("admin");
        
        mvc.perform(login)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/mod/mod"))
            .andExpect(authenticated().withUsername("admin").withAuthorities(List.of(Authority.MOD, Authority.USER)));
    }        
    
    @Test
    void bannedUserLoginTest() throws Exception{
        var login = formLogin()
            .user("bomber-man")
            .password("bomb");
        
       var result = mvc.perform(login)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?error"))
            .andExpect(unauthenticated())
               .andReturn();
       var myAttribute = result.getRequest().getSession().getAttribute("errorMessage");
       assertThat(myAttribute.toString()).isEqualTo("Account Disabled");
       
                
    }  
    
}
