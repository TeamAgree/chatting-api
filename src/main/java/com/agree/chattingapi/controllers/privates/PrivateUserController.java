package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/user")
@Tag(name = "[private] 사용자")
public class PrivateUserController {

    private UserService userService;

    @Autowired
    public PrivateUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "사용자 상세정보", description = "사용자 상세정보")
    public CommonResponse<UserInfo> getUser(HttpServletRequest request){
        return new CommonResponse<>(userService.getUser(request));
    }

    @PutMapping
    @Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정")
    public ResponseEntity<CommonResponse<String>> modifyUser(@RequestBody ModifyUserRequest request){
        return ResponseEntity.ok(userService.modifyUser(request));
    }

    @DeleteMapping
    @Operation(summary = "유저 삭제", description = "유저 삭제")
    public CommonResponse<String> deleteUser(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.deleteUser(request.getId()));
    }

    @PutMapping("/pw")
    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    public CommonResponse<String> modifyPw(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPw(request));
    }

    @PutMapping("/firebase/push-key")
    @Operation(summary = "유저 push key 수정", description = "유저 push key 수정")
    public CommonResponse<String> modifyPushKey(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPushKey(request));
    }

    @GetMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃")
    public CommonResponse<String> logout(HttpServletRequest request, HttpServletResponse response){
        return new CommonResponse<>(userService.logout(request, response));
    }

    @GetMapping("/list")
    @Operation(summary = "유저 정보 목록 조회", description = "유저 정보 목록 조회")
    public CommonResponse<List<UserInfo>> getUserList(@RequestParam(value = "search") String search){
        return new CommonResponse<>(userService.getUserList(search));
    }

}
