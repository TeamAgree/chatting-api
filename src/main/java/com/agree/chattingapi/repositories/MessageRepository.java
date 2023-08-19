package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.MessageInfo;
import com.agree.chattingapi.entities.MessageInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageInfo, MessageInfoId> {

    List<MessageInfo> findMessageInfosByChatroomId(Long chatroom_id);

}
