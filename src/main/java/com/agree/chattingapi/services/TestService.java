package com.agree.chattingapi.services;

import com.agree.chattingapi.conf.ApplicationConfig;
import com.agree.chattingapi.entities.TestEntity;
import com.agree.chattingapi.repositories.TestRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @Transactional
    public List<TestEntity> getTest(){
        List<TestEntity> result = testRepository.findAll();
        return result;
    }

    @Transactional
    public String setTest(){

        List<TestEntity> list = new ArrayList<>();
        list.add(new TestEntity(new Date(20000101), 3.14, 1.15, 6.55));
        list.add(new TestEntity(new Date(20000701), 1.14, 9.11, 6.66));
        list.add(new TestEntity(new Date(20010101), 5.24, 5.55, 1.89));
        list.add(new TestEntity(new Date(20010701), 2.17, 4.19, 7.88));
        list.add(new TestEntity(new Date(20020101), 8.1, 6.99, 9.99));
        list.add(new TestEntity(new Date(20020701), 6.11, 1.13, 4.44));
        list.add(new TestEntity(new Date(20030101), 3.34, 5.51, 5.14));
        list.add(new TestEntity(new Date(20040701), 3.18, 4.29, 2.78));
        list.add(new TestEntity(new Date(20050101), 8.87, 5.25, 1.32));
        list.add(new TestEntity(new Date(20050701), 6.29, 5.15, 5.77));

        testRepository.saveAll(list);

        return "success";
    }

    public String getWebServerIp(){
        return new ApplicationConfig().getWebIp();
    }

}
