/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import pl.lcc.listener.example.configuration.LoadFakeDataRunner;
import pl.lcc.listener.example.security.SecuredUser;
import pl.lcc.listener.example.service.InMemoryMessageService;

/**
 *
 * @author Nauczyciel
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NewModScenarioTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserDetailsManager userDb;

    @Autowired
    private LoadFakeDataRunner db_loader;

    @Autowired
    InMemoryMessageService db;

    @BeforeEach
    void resetData() throws Exception {
        db_loader.clearDb();
        db_loader.run("_");
    }

    @Test
    @WithUserDetails("admin")
    public void testSwitchToModVisibleWithAdmin() throws Exception {
        var result = mvc.perform(get("/user/panel"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .containsPattern("Go to Mod Panel");
    }

    @Test
    @WithUserDetails("admin")
    public void testWarningWithNotWarned() throws Exception {

        var bomber = (SecuredUser) userDb.loadUserByUsername("bomber-man");
        assertThat(bomber.isAccountNonWarned()).isTrue();

        mvc.perform(post("/mod/verified?id=I made a Bomb!&warning=true").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mod/mod"))
                .andReturn();

        bomber = (SecuredUser) userDb.loadUserByUsername("bomber-man");
        assertThat(bomber.isAccountNonWarned()).isFalse();

    }
    
    @Test
    @WithUserDetails("admin")
    public void testWarningWithNotBanned() throws Exception {

        var bomber = userDb.loadUserByUsername("enthalpy");
        assertThat(bomber.isAccountNonLocked()).isTrue();

        mvc.perform(post("/mod/verified?id=This is calorimetric bomb!&ban=true").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mod/mod"))
                .andReturn();

        bomber =  userDb.loadUserByUsername("enthalpy");
        assertThat(bomber.isAccountNonLocked()).isFalse();

    }

}
