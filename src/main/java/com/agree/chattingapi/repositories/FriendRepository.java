package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.FriendInfo;
import com.agree.chattingapi.entities.FriendInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendInfo, FriendInfoId> {}
