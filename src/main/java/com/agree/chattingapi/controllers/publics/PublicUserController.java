package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<CommonResponse<UserDetailResponse>> join(@RequestBody UserInfo user){
        return ResponseEntity.ok(new CommonResponse<>(userService.join(user)));
    }

    @GetMapping("/double-check/{param}")
    public CommonResponse<String> doubleCheck(@PathVariable String param, @RequestParam String type){
        return new CommonResponse<>(userService.doubleCheck(param, type));
    }

    @GetMapping("/cookie")
    public CommonResponse<String> getCookie(HttpServletRequest request){
        return new CommonResponse<>(userService.getCookie(request));
    }

}
