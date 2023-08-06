package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageInfo, Long> {
}
