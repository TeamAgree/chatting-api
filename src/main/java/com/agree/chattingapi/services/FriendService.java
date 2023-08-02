package com.agree.chattingapi.services;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.constants.AuthConstants;
import com.agree.chattingapi.constants.FriendShipStatus;
import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.entities.FriendInfo;
import com.agree.chattingapi.entities.FriendInfoId;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.FriendRepository;
import com.agree.chattingapi.repositories.UserRepository;
import com.agree.chattingapi.utils.CustomizedException;
import com.agree.chattingapi.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(FriendService.class);
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository){
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String addFriend(AddRemoveFriendRequest request){
        UserInfo userInfo = userRepository.findById(request.getId())
                .orElseThrow(() -> new CustomizedException("회원정보를 찾을 수 없습니다."));
        FriendInfo addFriend = new FriendInfo(userInfo, request.getFriendId());

        friendRepository.save(addFriend);

        return "success";
    }

    @Transactional
    public String deleteFriend(AddRemoveFriendRequest request){
        UserInfo userInfo = userRepository.findById(request.getId())
                .orElseThrow(() -> new CustomizedException("회원정보를 찾을 수 없습니다."));
        FriendInfo findFriend = friendRepository.findById(new FriendInfoId(userInfo, request.getFriendId()))
                .orElseThrow(() -> new CustomizedException("친구정보를 찾을 수 없습니다."));

        friendRepository.delete(findFriend);

        return "success";
    }

    @Transactional
    public String setFavorite(AddRemoveFriendRequest request){
        UserInfo userInfo = userRepository.findById(request.getId())
                .orElseThrow(() -> new CustomizedException("회원정보를 찾을 수 없습니다."));
        FriendInfo findFriend = friendRepository.findById(new FriendInfoId(userInfo, request.getFriendId())).orElse(null);

        if (findFriend != null) {
            findFriend.setFriendShipStatus(FriendShipStatus.FAVORITE);
        }

        return "success";
    }

    @Transactional
    public String setBlock(AddRemoveFriendRequest request){
        UserInfo userInfo = userRepository.findById(request.getId())
                .orElseThrow(() -> new CustomizedException("회원정보를 찾을 수 없습니다."));
        FriendInfo findFriend = friendRepository.findById(new FriendInfoId(userInfo, request.getFriendId())).orElse(null);

        if (findFriend != null) {
            findFriend.setFriendShipStatus(FriendShipStatus.BLOCK);
        }

        return "success";
    }

    @Transactional
    public List<String> getFriends(HttpServletRequest request){
        String userId = TokenUtils.getUserIdFromToken(TokenUtils.getTokenFromHeader(request.getHeader(AuthConstants.AUTH_HEADER)));

        return userRepository.findById(userId).get().getFriends();
    }

    @Transactional
    public UserDetailResponse getFriendDetail(String id){
        UserInfo findFriend = userRepository.findById(id).orElse(null);
        UserDetailResponse result = new UserDetailResponse(findFriend);

        return result;
    }

}
