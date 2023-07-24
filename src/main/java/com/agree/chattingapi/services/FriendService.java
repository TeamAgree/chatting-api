package com.agree.chattingapi.services;

import com.agree.chattingapi.constants.FriendShipStatus;
import com.agree.chattingapi.dtos.friend.AddFriendRequest;
import com.agree.chattingapi.entities.FriendInfo;
import com.agree.chattingapi.entities.FriendInfoId;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.FriendRepository;
import com.agree.chattingapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository){
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String addFriend(AddFriendRequest request){
        FriendInfo addFriend = new FriendInfo(request.getId(), request.getFriendId());

        friendRepository.save(addFriend);

        return "success";
    }

    @Transactional
    public String setFavorite(AddFriendRequest request){
        FriendInfo findFriend = friendRepository.findById(new FriendInfoId(request.getId(), request.getFriendId())).orElse(null);

        findFriend.setFriendShipStatus(FriendShipStatus.FAVORITE);

        return "success";
    }

    @Transactional
    public String setBlock(AddFriendRequest request){
        FriendInfo findFriend = friendRepository.findById(new FriendInfoId(request.getId(), request.getFriendId())).orElse(null);

        findFriend.setFriendShipStatus(FriendShipStatus.BLOCK);

        return "success";
    }

}
