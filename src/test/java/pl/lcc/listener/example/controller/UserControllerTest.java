///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package pl.lcc.listener.example.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import pl.lcc.listener.module.interfaces.DispatcherInterface;
//import static org.hamcrest.Matchers.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//import org.springframework.test.context.ActiveProfiles;
//import pl.lcc.listener.example.events.BanEvent;
//
///**
// *
// * @author Nauczyciel
// */
//
//@ActiveProfiles("test")
//@SpringBootTest
//public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @SpyBean
//    private DispatcherInterface dispatcher;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    void testLogin() throws Exception {
//
//        MockHttpSession mocksession = new MockHttpSession();
//
//        var result = mockMvc
//                .perform(get("/login").session(mocksession))
//                .andExpect(status().isOk())
//                .andExpect(view().name("Login"))
//                .andReturn();
//
//        System.out.println(result.getResponse().getContentAsString());
//
//    }
//
//    @Test
//    void testLoginWrongUser() throws Exception {
//        MockHttpSession mocksession = new MockHttpSession();
//        var result = mockMvc
//                .perform(post("/login").session(mocksession)
//                        .param("name", "Otto")
//                        .param("password", "Hahn"))
//                .andExpect(view().name("Login"))
//                .andReturn();
//    }
//
//    @Test
//    void testLoginExistingUser() throws Exception {
//
//        MockHttpSession mocksession = new MockHttpSession();
//        var result = mockMvc
//                .perform(post("/login").session(mocksession)
//                        .param("name", "Stas")
//                        .param("password", "Ulam")
//                        .param("create", "true"))
//                .andExpect(view().name("redirect:/addMessage"))
////                .andExpect(model().attribute("name", is("Stas")))
//        //        .andExpect(model().attribute("admin", is(false)))
//                .andReturn();
//        System.out.println("-------------------");
//        System.out.println(result.getResponse().getContentAsString());
//        System.out.println(result.getModelAndView().getModel());
//        System.out.println("-------------------");
//        
//        var result2 = mockMvc
//                .perform(post("/addMessage").session(mocksession)
//                        .param("messageBody", "Goyy you"))
//                .andExpect(view().name("UserPanel"))
//                .andExpect(model().attribute("messages", hasSize(1)))
//                .andExpect(model().attribute("message", hasProperty("messageBody", is("Goyy you"))))
//                .andReturn();
//
////        System.out.println(result2.getResponse().getContentAsString());
////        System.out.println(result2.getModelAndView().getModel());    
//        var result3 = mockMvc
//                .perform(post("/addMessage").session(mocksession)
//                        .param("messageBody", "I bomb you"))
//                .andExpect(view().name("UserPanel"))
//                .andExpect(model().attribute("messages", hasSize(2)))
//                .andReturn();
//
//        Mockito.verify(dispatcher, Mockito.times(1)).dispatch(Mockito.any());
//
//        System.out.println(result3.getResponse().getContentAsString());
//        System.out.println(result3.getModelAndView().getModel());
//
//    }
//
//    @Test
//    void banRunningTest() throws Exception {
//        MockHttpSession sessionOKUser = new MockHttpSession();
//        MockHttpSession sessionBombUser = new MockHttpSession();
//
//        System.out.println("--------------------------------------------------------");
//        
//        var result = mockMvc
//                .perform(post("/login").session(sessionOKUser)
//                        .param("name", "OK")
//                        .param("password", "OK")
//                        .param("create", "true"))
//               // .andExpect(model().attribute("banned", false))
//                .andReturn();
//
//         System.out.println(result.getResponse().getContentAsString());
//        System.out.println(result.getModelAndView().getModel());
//        
//        mockMvc
//                .perform(post("/addMessage").session(sessionOKUser)
//                        .param("messageBody", "ok"))
//                .andReturn();
//
//        System.out.println("##################################################");
//        
//        var result3 = mockMvc
//                .perform(post("/login").session(sessionBombUser)
//                        .param("name", "Bomber-wtf")
//                        .param("password", "bomb")
//                        .param("create", "true"))
//                .andExpect(view().name("redirect:/addMessage"))
//                .andReturn();
//
//         System.out.println(result3.getResponse().getContentAsString());
//        System.out.println(result3.getModelAndView().getModel());
//        
//        mockMvc
//                .perform(post("/addMessage").session(sessionBombUser)
//                        .param("messageBody", "not ok"))
//                .andExpect(view().name("UserPanel"))
//                .andReturn();
//
//        dispatcher.dispatch(new BanEvent("Bomber-wtf"));
//
//        var result5 = mockMvc
//                .perform(post("/login").session(sessionBombUser)
//                        .param("name", "Bomber-wtf")
//                        .param("password", "bomb"))
////                .andExpect(model().attribute("banned", true))
//                .andReturn();
//
//    }
//
//}
