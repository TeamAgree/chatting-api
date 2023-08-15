package com.agree.chattingapi.services;

import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.entities.ChatroomInfo;
import com.agree.chattingapi.entities.UserChatroom;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.ChatroomRepository;
import com.agree.chattingapi.repositories.UserChatroomRepository;
import com.agree.chattingapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final UserRepository userRepository;
    private final ChatroomRepository chatroomRepository;

    private final UserChatroomRepository userChatroomRepository;

    public ChatService(UserRepository userRepository, ChatroomRepository chatroomRepository, UserChatroomRepository userChatroomRepository){
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
        this.userChatroomRepository = userChatroomRepository;
    }

    @Transactional
    public String createChatroom(AddRemoveFriendRequest request){
        ChatroomInfo createRoom = new ChatroomInfo();
        chatroomRepository.save(createRoom);

        UserInfo findUser1 = userRepository.findById(request.getId()).orElse(null);
        UserInfo findUser2 = userRepository.findById(request.getFriendId()).orElse(null);

        UserChatroom createRoom1 = new UserChatroom(findUser1, findUser2.getName(), createRoom);
        userChatroomRepository.save(createRoom1);

        UserChatroom createRoom2 = new UserChatroom(findUser2, findUser1.getName(), createRoom);
        userChatroomRepository.save(createRoom2);

        return "success";
    }

}
