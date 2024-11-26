package com.dbtest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ValidateTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                /*if (token != null) {*/
                    //todo set authorities
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    //todo set userdata
                    User userdata = new User("admin","admin",authorities);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userdata, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                /*} else {
                    generateResponse(response, "error.Authentication.notLogin", HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }*/
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                generateResponse(response, "error.system_exception", HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void generateResponse(HttpServletResponse response, String messageKey, int httpErrorCode) {
        String dto = "401 error";
        response.setStatus(httpErrorCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), dto);
        } catch (IOException e) {
            System.out.println("error in generate response! caused by : " + e);
        }
    }
}
