package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.user.FirebaseRequest;
import com.agree.chattingapi.repositories.UserRepository;
import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.services.FirebaseService;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "firebase")
public class FirebaseController {

    private final FirebaseService firebaseService;
    private final UserRepository userRepository;

    public FirebaseController(FirebaseService firebaseService, UserRepository userRepository){
        this.firebaseService = firebaseService;
        this.userRepository = userRepository;
    }

    @PostMapping("/firebase/send")
    @Operation(summary = "push 메시지 발송", description = "push 메시지 발송")
    public CommonResponse<String> send(@RequestBody FirebaseRequest sendPushRequest) throws FirebaseMessagingException {
        firebaseService.sendMessage(sendPushRequest.getFrom(), sendPushRequest.getMsg(), sendPushRequest.getTo());

        return new CommonResponse<>("success");
    }

}
