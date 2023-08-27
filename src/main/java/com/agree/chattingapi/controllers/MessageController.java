package com.agree.chattingapi.controllers;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.dtos.chat.MessageDto;
import com.agree.chattingapi.services.FriendService;
import com.agree.chattingapi.services.MessageService;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @MessageMapping("/message")
    public void sendMessage(MessageDto message){
        messageService.sendMessage(message);
    }

}
