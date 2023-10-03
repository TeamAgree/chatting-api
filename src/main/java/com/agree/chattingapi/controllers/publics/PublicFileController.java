package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.dtos.file.FileResponse;
import com.agree.chattingapi.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/public/file")
@Tag(name = "[public] 파일")
public class PublicFileController {

    private FileService fileService;

    public PublicFileController(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    @Operation(summary = "파일 업로드", description = "파일 업로드")
    public ResponseEntity<CommonResponse<String>> uploadFile(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("userId") String userId
    ) {
        return ResponseEntity.ok(fileService.uploadFile(files, userId));
    }

    @GetMapping("/download/{fileName}")
    @Operation(summary = "파일 다운로드", description = "파일 다운로드")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        FileResponse result = fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(result.getResource());
    }

    @GetMapping("/list/{fileId}")
    @Operation(summary = "파일 목록 조회", description = "파일 목록 조회")
    public ResponseEntity<CommonResponse<List<String>>> getFileList(@PathVariable String fileId){
        return ResponseEntity.ok(fileService.getFileList(fileId));
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<Resource> showFile(@PathVariable String filename) {
        Path fileLocation = Paths.get("/file/" + filename);
        Resource fileResource = null;

        try {
            fileResource = new UrlResource(fileLocation.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        if (!fileResource.exists() || !fileResource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = "image/jpeg";

        try {
            contentType = Files.probeContentType(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(fileResource);
    }


}
