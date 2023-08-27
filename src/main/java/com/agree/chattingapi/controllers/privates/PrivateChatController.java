package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.chat.MessageDto;
import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.entities.UserChatroom;
import com.agree.chattingapi.services.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/chat")
public class PrivateChatController {

    private final ChatService chatService;

    public PrivateChatController(ChatService chatSservice){
        this.chatService = chatSservice;
    }

    @PostMapping("/room")
    public CommonResponse<String> createChatroom(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(chatService.createChatroom(request));
    }

    @GetMapping("/rooms")
    public CommonResponse<List<UserChatroom>> getChatrooms(HttpServletRequest request){
        return new CommonResponse<>(chatService.getChatrooms(request));
    }

}
