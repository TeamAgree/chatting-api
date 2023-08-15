package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.services.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
