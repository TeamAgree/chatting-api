package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.friend.AddFriendRequest;
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
    public ResponseEntity<CommonResponse<String>> addFriend(@RequestBody AddFriendRequest request){
        return ResponseEntity.ok(new CommonResponse<>(friendService.addFriend(request)));
    }

    @PutMapping("/favorite")
    public CommonResponse<String> setFavorite(@RequestBody AddFriendRequest request){
        return new CommonResponse<>(friendService.setFavorite(request));
    }

    @PutMapping("/block")
    public CommonResponse<String> setBlock(@RequestBody AddFriendRequest request){
        return new CommonResponse<>(friendService.setBlock(request));
    }

    @GetMapping
    public CommonResponse<List<String>> getFriends(HttpServletRequest request){
        return new CommonResponse<>(friendService.getFriends(request));
    }

}
