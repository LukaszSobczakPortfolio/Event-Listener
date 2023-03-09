/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Logging-filter");
        var wrappedRequest = new ContentCachingRequestWrapper(request);
        log.info("logging reqUrl map: " + wrappedRequest.getRequestURL());
        log.info("logging param names: " + wrappedRequest.getParameterNames().toString());
        log.info("logging servletPath: " + wrappedRequest.getServletPath());
        log.info("logging param ParamMap: " + wrappedRequest.getParameterMap().toString());
        
        log.info("logging content: " + Arrays.toString(wrappedRequest.getContentAsByteArray()));
        log.info("logging content v2: " + new String(wrappedRequest.getContentAsByteArray(), Charset.defaultCharset()));
        log.info(wrappedRequest.toString());
        filterChain.doFilter(wrappedRequest, response);

    }

}
