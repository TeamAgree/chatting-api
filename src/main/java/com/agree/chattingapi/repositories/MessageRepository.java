package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.MessageInfo;
import com.agree.chattingapi.entities.MessageInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageInfo, MessageInfoId> {

    List<MessageInfo> findMessageInfosByChatroomId(Long chatroom_id);

    @Query("select max(m.messageSeq) from MessageInfo m where m.chatroom.id = :chatroomId")
    Optional<Long> findMaxSeqByChatroomId(@Param("chatroomId") Long chatroom_id);

}
