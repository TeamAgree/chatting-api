package com.agree.chattingapi.utils;

import com.agree.chattingapi.conf.AuthConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = TokenUtils.getTokenFromHeader(request.getHeader(AuthConstants.AUTH_HEADER));
        if (token != null) {
            String userId = TokenUtils.getUserIdFromToken(token);
            request.setAttribute("userId", userId);
        }
        return true;
    }
}

