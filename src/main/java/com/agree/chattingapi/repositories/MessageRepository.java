package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageInfo, Long> {

    List<MessageInfo> findMessageInfosByChatroomId(Long chatroom_id);

}
