package com.dbtest.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @author m.Shahrestanaki @createDate 11/25/2024
 */
public class CustomAccessDeniedException implements AccessDeniedHandler {
    public CustomAccessDeniedException() {
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String dto = "401 error";
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        try {
            (new ObjectMapper()).writeValue(response.getOutputStream(), dto);
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
}
