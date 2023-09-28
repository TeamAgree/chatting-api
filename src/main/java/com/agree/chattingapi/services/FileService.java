package com.agree.chattingapi.services;

import com.agree.chattingapi.dtos.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public CommonResponse<String> uploadFile(MultipartFile file){
        try {
            // 파일 저장 디렉토리가 없으면 생성
            Path path = Paths.get("/file/");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // 파일 저장
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get("/file/" + file.getOriginalFilename());
            Files.write(filePath, bytes);

            return new CommonResponse<>("File uploaded successfully");

        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResponse<>("File upload failed", null);
        }
    }

}
