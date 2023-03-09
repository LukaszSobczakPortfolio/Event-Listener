/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Nauczyciel
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Autowired
    LoggingLogoutHandler loggingLogoutHandler;
    
    
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureHandler(loginFailureHandler)
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .addLogoutHandler(loggingLogoutHandler)
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                .sessionManagement()
                    .invalidSessionUrl("/login?expired")
                .and()
                .addFilterBefore(new LoggingFilter(), ChannelProcessingFilter.class);
               
                    

        return http.build();
    }

    @Bean
    PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
