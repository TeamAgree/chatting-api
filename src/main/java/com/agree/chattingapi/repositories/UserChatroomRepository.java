package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.UserChatroom;
import com.agree.chattingapi.entities.UserChatroomId;
import com.agree.chattingapi.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChatroomRepository extends JpaRepository<UserChatroom, UserChatroomId> {

    List<UserChatroom> findUserChatroomsByUser(UserInfo user);

}
