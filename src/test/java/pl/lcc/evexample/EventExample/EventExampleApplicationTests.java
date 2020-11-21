package pl.lcc.evexample.EventExample;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import pl.lcc.evexample.EventExample.actuator.ListenersInfo;
import pl.lcc.evexample.EventExample.controller.WebController;
import pl.lcc.evexample.module.interfaces.DispatcherInterface;
import pl.lcc.evexample.module.interfaces.ILccPostProcessor;

@SpringBootTest
class EventExampleApplicationTests {

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
