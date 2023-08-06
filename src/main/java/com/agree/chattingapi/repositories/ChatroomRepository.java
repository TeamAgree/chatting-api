package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.ChatroomInfo;
import com.agree.chattingapi.entities.ChatroomInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomInfo, ChatroomInfoId> {
}
