package com.agree.chattingapi.services;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.entities.FileInfo;
import com.agree.chattingapi.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public CommonResponse<String> uploadFile(MultipartFile file, String userId){
        try {
            // 파일 저장 디렉토리가 없으면 생성
            Path path = Paths.get("/file/");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            FileInfo fileInfo = new FileInfo();

            fileInfo.setFileName(generateFileName());
            fileInfo.setExtension(getFileExtension(Objects.requireNonNull(file.getOriginalFilename())));
            fileInfo.setLocalPath(path.toString());
            fileInfo.setUriPath(path.toString());

            fileRepository.save(fileInfo);

            // 파일 저장
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get("/file/" + fileInfo.getFileName());
            Files.write(filePath, bytes);

            return new CommonResponse<>("File uploaded successfully");

        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResponse<>("File upload failed", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse<>(e.getMessage(), null);
        }
    }

    private String generateFileName() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String formattedDate = sdf.format(now);

        int countForToday = fileRepository.countByFileNameStartingWith(formattedDate);
        int nextNumber = countForToday + 1;

        String formattedNumber = String.format("%05d", nextNumber);

        return formattedDate + formattedNumber;
    }

    private static String getFileExtension(String fileName){
        int lastDotPosition = fileName.lastIndexOf('.');

        if (lastDotPosition == -1 || lastDotPosition == fileName.length() - 1) {
            return null;
        }

        return fileName.substring(lastDotPosition + 1);
    }

}
