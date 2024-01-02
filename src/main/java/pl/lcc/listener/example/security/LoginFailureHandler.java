/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Other exceptions: BadCredentialsException , DisabledException, LockedException, AccountExpiredException,
 * CredentialsExpiredException, SessionAuthenticationException, AuthenticationCredentialsNotFoundException
 * @author Nauczyciel
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler  {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        
        if (exception instanceof LockedException) {
            request.getSession().setAttribute("errorMessage", "Account Disabled");
        } else {
            request.getSession().setAttribute("errorMessage", "Wrong username or password.");
        }
        response.sendRedirect(request.getContextPath() + "/login?error");
    }
    
}
