/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.listener.example.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.provisioning.UserDetailsManager;
import pl.lcc.listener.example.events.BanEvent;
import pl.lcc.listener.example.events.WarningEvent;
import pl.lcc.listener.example.security.SecuredUser;

/**
 *
 * @author Nauczyciel
 */
public class ListenerForModifyUserEventsTest {

    @Mock
    UserDetailsManager manager;

    @Captor
    ArgumentCaptor<SecuredUser> userCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBanEventRecived() {
        Mockito.when(manager.loadUserByUsername("Joe")).thenReturn(new SecuredUser("Joe", "1234"));
        var sut = new ListenerForModifyUserEvents(manager);
        sut.listenToEvent(new BanEvent("Joe"));
        Mockito.verify(manager).updateUser(userCaptor.capture());

        assertThat(userCaptor.getValue().isAccountNonLocked()).isFalse();
        assertThat(userCaptor.getValue().isAccountNonWarned()).isTrue();

    }

    @Test
    public void testWarningEventRecived() {
        Mockito.when(manager.loadUserByUsername("Joe")).thenReturn(new SecuredUser("Joe", "1234"));
        var sut = new ListenerForModifyUserEvents(manager);
        sut.listenToEvent(new WarningEvent("Joe"));
        Mockito.verify(manager).updateUser(userCaptor.capture());

        assertThat(userCaptor.getValue().isAccountNonLocked()).isTrue();
        assertThat(userCaptor.getValue().isAccountNonWarned()).isFalse();

    }
}
