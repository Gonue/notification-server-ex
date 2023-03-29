package com.sns.ss.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.ss.exception.ErrorResponse;
import com.sns.ss.exception.SnsApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, SnsApplicationException {
        log.error("인증 실패 : {}", exception.getMessage());



        sendErrorResponse(response, exception);
    }


    //TODO : 추후 Exception 수정
    private void sendErrorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }


}
