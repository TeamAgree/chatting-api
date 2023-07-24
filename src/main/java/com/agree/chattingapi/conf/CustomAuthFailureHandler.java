package com.agree.chattingapi.conf;

import com.agree.chattingapi.responses.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();

        if(exception instanceof BadCredentialsException){
            printWriter.print(new ObjectMapper().writeValueAsString(new CommonResponse<>(
                    exception.getMessage(), "fail")));
        }else if(exception instanceof AuthenticationServiceException){
            printWriter.print(new ObjectMapper().writeValueAsString(new CommonResponse<>(
                    exception.getMessage(),"fail")));
        }else {
            printWriter.print(new ObjectMapper().writeValueAsString(new CommonResponse<>(
                    "fail", "fail")));
        }
        printWriter.flush();
        printWriter.close();

    }
}
