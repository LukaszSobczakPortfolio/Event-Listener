/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import pl.lcc.listener.utils.TestListeners;
import pl.lcc.listener.utils.TestEvents;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.lcc.listener.module.interfaces.DispatcherInterface;

/**
 *
 * @author piko
 */
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestListeners.ListenersConfiguration.class)
public class LccPostProcessorTest {

    @Autowired
    ApplicationContext ctx;

    @Autowired
    TestListeners.CatchAllListener catched;

    @Autowired
    TestListeners.PlantListener planty;

    @Autowired
    TestListeners.AnimalListener anime;

    @Autowired
    DispatcherInterface dispatcher;

    @AfterEach
    void tearDown() {
    }

    @Test
    void testOneEvent() {
        dispatcher.dispatch(new TestEvents.EmptyEvent());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(catched.getInfo()).as("got one Event").contains("1");
        softly.assertThat(anime.getInfo()).as("should be 0").isEqualTo("0");
        softly.assertThat(planty.getInfo()).as("should be 0").isEqualTo("0");
        softly.assertAll();
    }

    @Test
    void ThreeEvents() {

        dispatcher.dispatch(new TestEvents.AnimalEvent("boo"));
        dispatcher.dispatch(new TestEvents.EmptyEvent());
        dispatcher.dispatch(new TestEvents.PlantEvent(1));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(catched.getInfo()).as("got one Event").contains("3");
        softly.assertThat(anime.getInfo()).as("should be 1").isEqualTo("1");
        softly.assertThat(planty.getInfo()).as("should be 1").isEqualTo("1");
        softly.assertAll();

    }

}
