package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.entities.TestEntity;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping
    public CommonResponse<List<TestEntity>> getTest(){
        return new CommonResponse<>(testService.getTest());
    }

    @PostMapping
    public CommonResponse<String> setTest(){
        return new CommonResponse<>(testService.setTest());
    }

}
