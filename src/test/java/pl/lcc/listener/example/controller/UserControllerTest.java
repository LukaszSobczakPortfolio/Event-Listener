/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.controller;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.lcc.listener.example.user.User;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 *
 * @author Nauczyciel
 */
//@SpringBootTest
@WebMvcTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @SpyBean
    private DispatcherInterface dispatcher;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void testLogin() throws Exception {

        MockHttpSession mocksession = new MockHttpSession();

        var result = mockMvc
                .perform(get("/login").session(mocksession))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void testLogin2() throws Exception {

        User user = new User();
        user.setName("Otto");

        MockHttpSession mocksession = new MockHttpSession();
        var result = mockMvc
                .perform(post("/login").session(mocksession)
                        .param("name", "Otto"))
                .andExpect(view().name("UserPanel"))
                .andExpect(model().attributeExists("user"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getModelAndView().getModel());

        var result2 = mockMvc
                .perform(post("/addMessage").session(mocksession)
                        .param("message", "Goyy you"))
                .andExpect(view().name("UserPanel"))
                .andReturn();

        System.out.println(result2.getResponse().getContentAsString());
        System.out.println(result2.getModelAndView().getModel());

        var result3 = mockMvc
                .perform(post("/addMessage").session(mocksession)
                        .param("message", "I bomb you"))
                .andExpect(view().name("UserPanel"))
                .andReturn();

        Mockito.verify(dispatcher, Mockito.times(1)).dispatch(Mockito.any());

        System.out.println(result3.getResponse().getContentAsString());
        System.out.println(result3.getModelAndView().getModel());

    }

}