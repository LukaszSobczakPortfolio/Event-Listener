/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Nauczyciel
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NewUserScenariosTest {
   
    @Autowired
    private MockMvc mvc;
    
    @Test
    @WithUserDetails("test")
    public void testWarningWithNotWarned() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .doesNotContain("You were warned by Mods!!!");
    }
    
    @Test
    @WithUserDetails("enthalpy")
    public void testWarningWithWarned() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .contains("You were warned by Mods!!!");
    }
    
    @Test
    @WithUserDetails("test")
    public void testPublicMsgVisibilityUser() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .containsPattern("Your Messages(?s:.)*Public One(?s:.)*Public Messages")
                .containsPattern("Public Messages(?s:.)*Public One");
    }
    
    @Test
    @WithUserDetails("enthalpy")
    public void testPublicMsgVisibilityOther() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                 .doesNotContainPattern("Your Messages(?s:.)*Public One(?s:.)*Public Messages")
                 .containsPattern("Public Messages(?s:.)*Public One");
    }
    
    @Test
    @WithUserDetails("test")
    public void testPrivateMsgVisibilityUser() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .containsPattern("Your Messages(?s:.)*By Tester created(?s:.)*Public Messages")
                .doesNotContainPattern("Public Messages(?s:.)*By Tester created");
    }
    
    @Test
    @WithUserDetails("enthalpy")
    public void testPrivateMsgVisibilityOther() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                 .doesNotContainPattern("By Tester created");
    }
    
    @Test
    @WithUserDetails("test")
    public void testSwitchToModNotVisibleWithUser() throws Exception {
       var result = mvc.perform(get("/user/panel"))                
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .doesNotContainPattern("Go to Mod Panel");
    }
}
