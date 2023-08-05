package com.agree.chattingapi.services;

import com.agree.chattingapi.entities.TokenInfo;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {

    private final TokenRepository repository;

    public TokenService(TokenRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void generateTokenForLogin(UserInfo userInfo, String token){
        String userId = userInfo.getId();
        TokenInfo findToken = repository.findById(userId).orElse(null);
        if(findToken == null){
            TokenInfo tokenInfo = new TokenInfo(userId, token, userInfo.getRoles());
            tokenInfo.setCreatedBy(userId);
            tokenInfo.setUpdatedBy(userId);

            repository.save(tokenInfo);
        }else {
            findToken.setToken(token);
        }
    }

}
