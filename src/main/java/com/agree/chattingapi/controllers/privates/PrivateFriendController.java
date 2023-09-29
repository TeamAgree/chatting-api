package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.friend.AddRemoveFriendRequest;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.services.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/friend")
@Tag(name = "[private] 친구")
public class PrivateFriendController {

    private final FriendService friendService;

    public PrivateFriendController(FriendService friendService){
        this.friendService = friendService;
    }

    @PostMapping
    @Operation(summary = "친구 추가", description = "친구 추가")
    public ResponseEntity<CommonResponse<String>> addFriend(@RequestBody AddRemoveFriendRequest request){
        return ResponseEntity.ok(new CommonResponse<>(friendService.addFriend(request)));
    }

    @DeleteMapping
    @Operation(summary = "친구 삭제", description = "친구 삭제")
    public CommonResponse<String> deleteFriend(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.deleteFriend(request));
    }

    @PutMapping("/favorite")
    @Operation(summary = "즐겨찾기 추가", description = "즐겨찾기 추가")
    public CommonResponse<String> setFavorite(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.setFavorite(request));
    }

    @PutMapping("/block")
    public CommonResponse<String> setBlock(@RequestBody AddRemoveFriendRequest request){
        return new CommonResponse<>(friendService.setBlock(request));
    }

    @GetMapping("/my-friends")
    public CommonResponse<List<String>> getFriends(HttpServletRequest request){
        return new CommonResponse<>(friendService.getFriends(request));
    }

    @GetMapping("/detail/{id}")
    public CommonResponse<UserDetailResponse> getFriendDetail(@PathVariable String id){
        return new CommonResponse<>(friendService.getFriendDetail(id));
    }

}
