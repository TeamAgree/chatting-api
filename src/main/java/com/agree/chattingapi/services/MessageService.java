package com.agree.chattingapi.services;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.constants.MessageStatus;
import com.agree.chattingapi.dtos.chat.MessageDto;
import com.agree.chattingapi.entities.ChatroomInfo;
import com.agree.chattingapi.entities.MessageInfo;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.ChatroomRepository;
import com.agree.chattingapi.repositories.MessageRepository;
import com.agree.chattingapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    public MessageService(SimpMessageSendingOperations simpMessageSendingOperations,
                          MessageRepository messageRepository,
                          ChatroomRepository chatroomRepository,
                          UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    @Transactional
    public void sendMessage(MessageDto message){
        ChatroomInfo findChatroom = chatroomRepository.findById(message.getChatroomId()).orElse(null);
        UserInfo findUser = userRepository.findById(message.getWriter()).orElse(null);
        Long msgSeq = messageRepository.findMaxSeqByChatroomId(message.getChatroomId()).orElse(1L) + 1;

        log.warn(msgSeq.toString());
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setUser(findUser);
        messageInfo.setChatroom(findChatroom);
        messageInfo.setMessage(message.getMessage());
        messageInfo.setMessageSeq(msgSeq);
        messageInfo.setMessageStatus(MessageStatus.SUCCESS);
        messageInfo.setCreatedBy(message.getWriter());
        messageInfo.setUpdatedBy(message.getWriter());

        messageRepository.save(messageInfo);

        simpMessageSendingOperations.convertAndSend("/sub/chat/" + message.getChatroomId(), message);
    }

}
