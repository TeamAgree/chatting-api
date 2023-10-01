package com.agree.chattingapi.controllers.publics;

import com.agree.chattingapi.dtos.CommonResponse;
import com.agree.chattingapi.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<CommonResponse<Resource>> downloadFile(@PathVariable String fileName){
        return ResponseEntity.ok(fileService.downloadFile(fileName));
    }

    @GetMapping("/download/list/{fileId}")
    @Operation(summary = "파일 목록 일괄 다운로드", description = "파일 목록 일괄 다운로드")
    public ResponseEntity<CommonResponse<String>> downloadFileList(@PathVariable String fileId){
        return ResponseEntity.ok(new CommonResponse<>());
    }

}
