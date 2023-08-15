package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.UserChatroom;
import com.agree.chattingapi.entities.UserChatroomId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatroomRepository extends JpaRepository<UserChatroom, UserChatroomId> {
}
