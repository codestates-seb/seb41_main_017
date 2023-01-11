package com.codestates.culinari.config.security.utils;

import com.codestates.culinari.global.advice.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ErrorResponder {

    private ErrorResponder() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = ErrorResponse.of(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
