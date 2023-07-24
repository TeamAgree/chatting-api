package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<CommonResponse<UserInfo>> join(@RequestBody UserInfo user){
        return ResponseEntity.ok(new CommonResponse<>(userService.join(user)));
    }

    @GetMapping("/double-check/{id}")
    public CommonResponse<String> doubleCheck(@PathVariable String id){
        return new CommonResponse<>(userService.doubleCheck(id));
    }

    @GetMapping("/cookie")
    public CommonResponse<String> getCookie(HttpServletRequest request){
        return new CommonResponse<>(userService.getCookie(request));
    }

    @GetMapping("/hi")
    public CommonResponse<String> hi(@AuthenticationPrincipal User user){
        System.out.println();
        String name = user.getUsername();
        return new CommonResponse<>(name);
    }

}
