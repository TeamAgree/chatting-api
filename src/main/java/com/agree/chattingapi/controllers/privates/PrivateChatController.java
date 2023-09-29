package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.entities.UserChatroom;
import com.agree.chattingapi.services.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/chat")
@Tag(name = "[private] 채팅")
public class PrivateChatController {

    private final ChatService chatService;

    public PrivateChatController(ChatService chatSservice){
        this.chatService = chatSservice;
    }

    @PostMapping("/room")
    @Operation(summary = "채팅방 생성", description = "채팅방 생성")
    public CommonResponse<String> createChatroom(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(chatService.createChatroom(request));
    }

    @GetMapping("/rooms")
    @Operation(summary = "채팅방 목록 조회", description = "채팅방 목록 조회")
    public CommonResponse<List<UserChatroom>> getChatrooms(HttpServletRequest request){
        return new CommonResponse<>(chatService.getChatrooms(request));
    }

}
