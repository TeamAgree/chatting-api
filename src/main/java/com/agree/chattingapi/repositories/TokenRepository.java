package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenInfo, String>{}
