package com.agree.chattingapi.controllers.privates;

import com.agree.chattingapi.dtos.user.ModifyUserRequest;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/user")
public class PrivateUserController {

    private UserService userService;

    @Autowired
    public PrivateUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public CommonResponse<UserInfo> getUser(HttpServletRequest request){
        return new CommonResponse<>(userService.getUser(request));
    }

    @PutMapping("/user")
    public CommonResponse<String> modifyUser(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyUser(request));
    }

    @PutMapping("/user/pw")
    public CommonResponse<String> modifyPw(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPw(request));
    }

    @PutMapping("/firebase/push-key")
    public CommonResponse<String> modifyPushKey(@RequestBody ModifyUserRequest request){
        return new CommonResponse<>(userService.modifyPushKey(request));
    }

    @GetMapping("/logout")
    public CommonResponse<String> logout(HttpServletResponse response){
        return new CommonResponse<>(userService.logout(response));
    }

}
