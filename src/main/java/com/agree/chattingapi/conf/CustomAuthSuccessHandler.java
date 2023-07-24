package com.agree.chattingapi.conf;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.dtos.user.UserDetailsDto;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

import static com.agree.chattingapi.utils.TokenUtils.generateAccessToken;
import static com.agree.chattingapi.utils.TokenUtils.generateRefreshToken;

@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = (Logger) LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfo user = ((UserDetailsDto) authentication.getPrincipal()).getUserInfo();

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        tokenService.generateTokenForLogin(user, refreshToken);
        createCookie(response, refreshToken);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(new ObjectMapper().writeValueAsString(new CommonResponse<>(accessToken)));
        printWriter.flush();
        printWriter.close();
    }

    private void createCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie("chatting-app", value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); //https
        cookie.setMaxAge(86400 * 7); //1주일
        response.addCookie(cookie);
    }
}
