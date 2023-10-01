package com.agree.chattingapi.dtos.file;

import org.springframework.core.io.Resource;

public class FileResponse {
    private Resource resource;
    private String fileName;

    public FileResponse(Resource resource, String fileName) {
        this.resource = resource;
        this.fileName = fileName;
    }

    public Resource getResource() {
        return resource;
    }

    public String getFileName() {
        return fileName;
    }
}
