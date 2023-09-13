package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/user")
public class PrivateUserController {

    private UserService userService;

    @Autowired
    public PrivateUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public CommonResponse<UserInfo> getUser(HttpServletRequest request){
        return new CommonResponse<>(userService.getUser(request));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<String>> modifyUser(@RequestBody ModifyUserRequest request){
        return ResponseEntity.ok(userService.modifyUser(request));
    }

    @DeleteMapping
    public CommonResponse<String> deleteUser(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.deleteUser(request.getId()));
    }

    @PutMapping("/pw")
    public CommonResponse<String> modifyPw(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPw(request));
    }

    @PutMapping("/firebase/push-key")
    public CommonResponse<String> modifyPushKey(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPushKey(request));
    }

    @GetMapping("/logout")
    public CommonResponse<String> logout(HttpServletRequest request, HttpServletResponse response){
        return new CommonResponse<>(userService.logout(request, response));
    }

    @GetMapping("/list")
    public CommonResponse<List<UserInfo>> getUserList(@RequestParam(value = "search") String search){
        return new CommonResponse<>(userService.getUserList(search));
    }

}
