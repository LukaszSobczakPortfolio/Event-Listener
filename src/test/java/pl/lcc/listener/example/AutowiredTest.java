package pl.lcc.listener.example;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import pl.lcc.listener.example.actuator.ListenersInfo;
import pl.lcc.listener.example.controller.WebController;
import pl.lcc.listener.module.interfaces.DispatcherInterface;
import pl.lcc.listener.module.interfaces.ILccPostProcessor;

@SpringBootTest
class AutowiredTest {

    @Autowired
    ApplicationContext ctx;
    
    // test if all beans are present
    @Test
    public void contextLoads() {
        SoftAssertions softly = new SoftAssertions();
        
        List.<Class<?>>of(
                DispatcherInterface.class,
                ILccPostProcessor.class,
                WebController.class,
                ListenersInfo.class)
                .forEach(klazz -> softly
                        .assertThat(ctx.getBeanNamesForType(klazz))
                        .as("Bean should Exist: " + klazz.toGenericString())
                        .hasSizeGreaterThanOrEqualTo(1)
                        );
        
        softly.assertAll();
    }

}
