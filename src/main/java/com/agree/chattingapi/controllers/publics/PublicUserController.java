package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.user.UserDetailResponse;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
@Tag(name = "[public] 사용자")
public class PublicUserController {

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    @Operation(summary = "회원 가입", description = "회원 가입")
    public ResponseEntity<CommonResponse<UserDetailResponse>> join(@RequestBody UserInfo user){
        return ResponseEntity.ok(new CommonResponse<>(userService.join(user)));
    }

    @GetMapping("/double-check/{type}/{input}")
    @Operation(summary = "ID/휴대폰번호 중복체크", description = "ID/휴대폰번호 중복체크")
    public CommonResponse<String> doubleCheck(@PathVariable String type, @PathVariable String input){
        return new CommonResponse<>(userService.doubleCheck(type, input));
    }

    @GetMapping("/cookie")
    public CommonResponse<String> getCookie(HttpServletRequest request){
        return new CommonResponse<>(userService.getCookie(request));
    }

}
