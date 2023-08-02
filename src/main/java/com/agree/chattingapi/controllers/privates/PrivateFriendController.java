package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.FriendService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/friend")
public class PrivateFriendController {

    private final FriendService friendService;

    public PrivateFriendController(FriendService friendService){
        this.friendService = friendService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<String>> addFriend(@RequestBody AddRemoveFriendRequest request){
        return ResponseEntity.ok(new CommonResponse<>(friendService.addFriend(request)));
    }

    @DeleteMapping
    public CommonResponse<String> deleteFriend(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.deleteFriend(request));
    }

    @PutMapping("/favorite")
    public CommonResponse<String> setFavorite(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.setFavorite(request));
    }

    @PutMapping("/block")
    public CommonResponse<String> setBlock(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.setBlock(request));
    }

    @GetMapping
    public CommonResponse<List<String>> getFriends(HttpServletRequest request){
        return new CommonResponse<>(friendService.getFriends(request));
    }

    @GetMapping("/detail/{id}")
    public CommonResponse<UserDetailResponse> getFriendDetail(@PathVariable String id){
        return new CommonResponse<>(friendService.getFriendDetail(id));
    }

}
