package com.agree.chattingapi.services;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.constants.AuthConstants;
import com.agree.chattingapi.dtos.user.LoginRequest;
import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.entities.TokenInfo;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.TokenRepository;
import com.agree.chattingapi.repositories.UserRepository;
import com.agree.chattingapi.utils.TokenUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public UserDetailResponse join(UserInfo userInfo) {
        userInfo.setCreatedBy(userInfo.getId());
        userInfo.setUpdatedBy(userInfo.getId());
        userRepository.save(userInfo);
        return new UserDetailResponse(userInfo);
    }

    @Transactional
    public Optional<UserInfo> login(LoginRequest request) {
        return userRepository.findById(request.getId());
    }

    @Transactional
    public String doubleCheck(String type, String input) {
        boolean cnt;
        switch (type) {
            case "id":
                cnt = userRepository.existsById(input);

                if (cnt) {
                    return input + "는 이미 사용중입니다.";
                } else {
                    return "사용 가능한 아이디입니다.";
                }
            case "mobileNo":
                cnt = userRepository.existsByMobileNo(input);

                if (cnt) {
                    return input + "은/는 이미 사용중입니다.";
                } else {
                    return "사용 가능한 휴대폰번호입니다.";
                }
            default:
                return null;
        }
    }

    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("chatting-app")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Transactional
    public UserInfo getUser(HttpServletRequest request) {
        String header = TokenUtils.getTokenFromHeader(request.getHeader(AuthConstants.AUTH_HEADER));

        String userId = TokenUtils.getUserIdFromToken(header);

        UserInfo findUser = userRepository.findById(userId).orElse(null);

        return findUser;
    }

    @Transactional
    public String modifyUser(ModifyUserRequest request) {
        UserInfo findUser = userRepository.findById(request.getId()).orElse(null);

        if (findUser != null) {
            findUser.setName(request.getName());
            findUser.setBirth(request.getBirth());
            findUser.setMobileNo(request.getMobileNo());
            return "success";
        } else {
            return "fail";
        }
    }

    @Transactional
    public String deleteUser(String userId) {
        UserInfo findUser = userRepository.findById(userId).orElse(null);

        if (findUser != null) {
            findUser.setStatus("N");
            return "success";
        } else {
            return "fail";
        }

    }

    @Transactional
    public String modifyPw(ModifyUserRequest request) {
        UserInfo findUser = userRepository.findById(request.getId()).orElse(null);

        if (findUser != null) {
            findUser.setPw(request.getPw());
            return "success";
        } else {
            return "fail";
        }
    }

    @Transactional
    public String modifyPushKey(ModifyUserRequest request) {
        UserInfo findUser = userRepository.findById(request.getId()).orElse(null);

        if (findUser != null) {
            findUser.setPushKey(request.getPushKey());
            return "success";
        } else {
            return "fail";
        }
    }

    @Transactional
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("chatting-app", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        String userId = TokenUtils.getUserIdFromToken(TokenUtils.getTokenFromHeader(request.getHeader(AuthConstants.AUTH_HEADER)));
        TokenInfo findToken = tokenRepository.findById(userId).orElse(null);
        if (findToken != null) {
            findToken.setToken(null);
        }

        SecurityContextHolder.clearContext();

        return "success";
    }

    @Transactional
    public List<UserInfo> getUserList(String search) {
        List<UserInfo> findUsers = userRepository.findUsersByAnyMatchingParameter("%" + search + "%");
        return findUsers;
    }

}
