package com.sns.ss.auth.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.ss.dto.response.MemberLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MemberLoginResponse errorResponse = MemberLoginResponse.of(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}