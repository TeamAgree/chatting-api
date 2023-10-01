package com.agree.chattingapi.services;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.file.FileResponse;
import com.agree.chattingapi.entities.FileInfo;
import com.agree.chattingapi.repositories.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public CommonResponse<String> uploadFile(List<MultipartFile> files, String userId) {
        Path path = Paths.get("/file/");
        Long fileId = fileRepository.findMaxFileId().orElse(0L) + 1;

        for (MultipartFile file : files) {
            FileInfo fileInfo = new FileInfo(
                    fileId,
                    generateFileName(),
                    getFileNameWithoutExtension(Objects.requireNonNull(file.getOriginalFilename())),
                    getFileExtension(Objects.requireNonNull(file.getOriginalFilename())),
                    path.toString(),
                    path.toString(),
                    userId
            );

            try {
                byte[] bytes;
                bytes = file.getBytes();
                Path filePath = Paths.get("/file/" + fileInfo.getFileName());
                Files.write(filePath, bytes);

                fileRepository.save(fileInfo);
            } catch (Exception e) {
                return new CommonResponse<>("Cannot save files", null);
            }
        }

        return new CommonResponse<>("Files uploaded successfully");
    }

    @Transactional
    public FileResponse downloadFile(String fileName) {
        try {
            FileInfo fileInfo = fileRepository.findByFileName(fileName);

            Path filePath = Paths.get("/file/" + fileInfo.getFileName());
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new FileNotFoundException("File not found " + fileInfo.getFileName());
            }

            return new FileResponse(resource, fileInfo.getRealName());

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while downloading the file", e);
        }

    }

    private String generateFileName() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String formattedDate = sdf.format(now);

        int countForToday = fileRepository.countByFileNameStartsWith(formattedDate);
        int nextNumber = countForToday + 1;

        String formattedNumber = String.format("%05d", nextNumber);

        return formattedDate + formattedNumber;
    }

    private static String getFileExtension(String fileName) {
        int lastDotPosition = fileName.lastIndexOf('.');

        if (lastDotPosition == -1 || lastDotPosition == fileName.length() - 1) {
            return null;
        }

        return fileName.substring(lastDotPosition + 1);
    }

    private static String getFileNameWithoutExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if(dotIndex == -1) { // No extension found
            return filename;
        }
        return filename.substring(0, dotIndex);
    }

}
