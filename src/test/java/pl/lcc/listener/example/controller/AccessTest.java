/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Tests access to pages, with different credentioals - Data taken from LoadFakeDataRunner. 
 * @author Nauczyciel
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AccessTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAccesModPageUnauthenticated() throws Exception {
        mvc.perform(get("/mod/mod"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAccesUserPageUnauthenticated() throws Exception {
        mvc.perform(get("/user/panel"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAccessLoginUnauthenticated() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithUserDetails("test")
    public void testUserPageWithUserPrivileges() throws Exception {
        mvc.perform(get("/user/panel"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithUserDetails("admin")
    public void testUserPageWithModPrivileges() throws Exception {
        mvc.perform(get("/user/panel"))
               .andExpect(status().isOk());
    }
    
    @Test
    @WithUserDetails("test")
    public void testModPageWithUserPrivileges() throws Exception {
        mvc.perform(get("/mod/mod"))                
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    @WithUserDetails("admin")
    public void testModPageWithModPriviledges() throws Exception {
        mvc.perform(get("/mod/mod"))
                .andExpect(status().isOk());
    }
}
