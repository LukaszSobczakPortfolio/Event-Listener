/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.controller;

import java.time.LocalDateTime;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.lcc.listener.example.service.FakeMsgService;
import pl.lcc.listener.example.service.MessageService;
import pl.lcc.listener.example.service.VerificationService;
import pl.lcc.listener.example.user.Message;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.utils.TestEvents;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;
import org.springframework.mock.web.MockHttpSession;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.service.UserService;

/**
 *
 * @author Nauczyciel
 */

@ActiveProfiles("test")
@WebMvcTest
public class ModControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    MessageService mService;
    
    @Autowired
    UserService uService;
    
    @Autowired
    VerificationService vService;
    
    @SpyBean
    private DispatcherInterface dispatcher;

    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() throws InterruptedException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

       applicationContext.getBean(FakeMsgService.class).resetDB();
       vService.getMessageForModeration().forEach(vService::removeMessage);
       
       uService.tryCreateUser("innocent", "inno");
       uService.tryCreateUser("bomber", "bomb");
       uService.tryCreateUser("san_ticlaws", "st");
       uService.tryCreateUser("normals", "ok");
       uService.tryCreateUser("outlaw", "fugitive");
       uService.tryCreateUser("admin", "admin", true);
       
       mService.addMessage(new Message (LocalDateTime.now(), "normal message", "innocent" ));
       mService.addMessage(new Message (LocalDateTime.now(), "bomb message", "bomber" ));
       mService.addMessage(new Message (LocalDateTime.now(), "second normal message", "innocent" ));
       mService.addMessage(new Message (LocalDateTime.now(), "glass_christmas_bombka", "san_ticlaws" ));
       mService.addMessage(new Message (LocalDateTime.now(), "second bomb message", "bomber" ));
       mService.addMessage(new Message (LocalDateTime.now(), "other message", "normals" ));
       mService.addMessage(new Message (LocalDateTime.now(), "did_bomb", "outlaw" ));
       Thread.sleep(10);
       System.out.println("setup: All set");
       Mockito.reset(dispatcher);
    }
    
    public ModControllerTest() {
    }

    @Test
    public void setupTest(){
        SoftAssertions softly = new SoftAssertions();
        
        softly.assertThat(mService.getMessages("innocent")).hasSize(2);
        softly.assertThat(mService.getMessages("bomber")).hasSize(2);
        softly.assertThat(vService.getMessageForModeration()).hasSize(4);
        
        softly.assertAll();
    }
    
    
    
    @Test
    public void testModWithBomb() throws Exception {
        
         MockHttpSession mocksession = new MockHttpSession();
        
         var result = mockMvc
                .perform(get("/mod").session(mocksession))
                .andExpect(view().name("Mod"))
                .andExpect(model().attribute("list", hasSize(4)))
                .andReturn();
         
         System.out.println("");
         System.out.println(result.getModelAndView().getModel());
         
          var result2 = mockMvc
                .perform(post("/verified/?id=glass_christmas_bombka").session(mocksession))
                .andExpect(status().is3xxRedirection())
                .andReturn();  
          
          var result3 = mockMvc
                .perform(get("/mod").session(mocksession))
                .andExpect(view().name("Mod"))
                .andExpect(model().attribute("list", hasSize(3)))
          .andReturn();      
         
          Mockito.verify(dispatcher, Mockito.times(0)).dispatch(Mockito.any());
          
         //System.out.println(result.getResponse().getContentAsString());
       
          System.out.println("");
          System.out.println(result2.getResponse().getContentAsString());
        // System.out.println(result2.getModelAndView().getModel());
        
        var result4 = mockMvc
                .perform(post("/verified/?id=did_bomb&ban=true").session(mocksession))
                .andExpect(status().is3xxRedirection())
                .andReturn();  
          
          var result5 = mockMvc
                .perform(get("/mod").session(mocksession))
                .andExpect(view().name("Mod"))
                .andExpect(model().attribute("list", hasSize(2)))
          .andReturn();      
         
          Mockito.verify(dispatcher, Mockito.times(1)).dispatch(Mockito.any(BanEvent.class));
          Mockito.verify(dispatcher, Mockito.times(1)).dispatch(new BanEvent("outlaw"));
          
          
          
    }
    
    
    @Test
    @Deprecated
    public void testOtherMethod() {
        dispatcher.dispatch(new TestEvents.EmptyEvent());
        Mockito.verify(dispatcher, Mockito.times(1)).dispatch(Mockito.any());
        System.out.println("2");
    }
}
