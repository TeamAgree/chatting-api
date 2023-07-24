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
        TokenInfo tokenInfo = new TokenInfo(userInfo.getId(), token, userInfo.getRoles());

        repository.save(tokenInfo);
    }

}
