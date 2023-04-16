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
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pl.lcc.listener.example.configuration.LoadFakeDataRunner;
import pl.lcc.listener.example.service.InMemoryMessageService;

/**
 *
 * @author Nauczyciel
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NewUserMutableScenarios {

    @Autowired
    private MockMvc mvc;

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
    @WithUserDetails("test")
    public void testWarningWithNotWarned() throws Exception {
        var result = mvc.perform(get("/user/panel"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .doesNotContain("You were warned by Mods!!!");
    }

    @Test
    @WithUserDetails("test")
    public void testPostPrivate() throws Exception {

        mvc.perform(post("/user/addMessage")
                .param("messageBody", "Test message###")
                .param("privateCheckBox", "true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        var result = mvc.perform(get("/user/panel"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("Test message###");

        assertThat(db.getMessages("test"))
                .anyMatch(msg -> msg.getMessageBody().equals("Test message###") && !msg.isPublic());

    }

    @Test
    @WithUserDetails("test")
    public void testPostPublic() throws Exception {

        mvc.perform(post("/user/addMessage")
                .param("messageBody", "Test message###")
                .param("privateCheckBox", "false")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        var result = mvc.perform(get("/user/panel"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("Test message###");

        assertThat(db.getMessages("test"))
                .anyMatch(msg -> msg.getMessageBody().equals("Test message###") && msg.isPublic());

    }

}
