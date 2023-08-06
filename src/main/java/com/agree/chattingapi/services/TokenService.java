package com.agree.chattingapi.services;

import com.agree.chattingapi.entities.TokenInfo;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.TokenRepository;
import com.agree.chattingapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void generateTokenForLogin(UserInfo userInfo, String token){
        String userId = userInfo.getId();
        TokenInfo findToken = tokenRepository.findById(userId).orElse(null);

        if(findToken == null){
            UserInfo existingUserInfo = userRepository.findById(userId).orElse(userInfo);
            TokenInfo tokenInfo = new TokenInfo(existingUserInfo, token, userInfo.getRoles());
            tokenInfo.setCreatedBy(userId);
            tokenInfo.setUpdatedBy(userId);

            tokenRepository.save(tokenInfo);
        }else {
            findToken.setToken(token);
        }
    }

}
