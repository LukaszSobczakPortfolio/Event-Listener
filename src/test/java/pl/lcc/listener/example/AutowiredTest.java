package pl.lcc.listener.example;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import pl.lcc.listener.example.actuator.AllMetricsInfo;
import pl.lcc.listener.example.actuator.ListenersInfo;
import pl.lcc.listener.example.controller.UserController;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.ILccPostProcessor;

@SpringBootTest
class AutowiredTest {

    @Autowired
    ApplicationContext ctx;
    
    // test if all important beans are present
    @Test
    public void contextLoads() {
        SoftAssertions softly = new SoftAssertions();
        
        List.<Class<?>>of(
                DispatcherInterface.class,
                ILccPostProcessor.class,
                UserController.class,
                ListenersInfo.class,
                AllMetricsInfo.class)
                .forEach(klazz -> softly
                        .assertThat(ctx.getBeanNamesForType(klazz))
                        .as("Bean should Exist: " + klazz.toGenericString())
                        .hasSizeGreaterThanOrEqualTo(1)
                        );
        
        softly.assertAll();
    }

    
    
    
}
