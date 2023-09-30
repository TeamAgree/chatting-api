package com.agree.chattingapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "file_info")
public class FileInfo extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false)
    private Long id;

    @Column(name = "file_name", nullable = false, length = 20, unique = true)
    private String fileName;

    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Column(name = "local_path", nullable = false, length = 10)
    private String localPath;

    @Column(name = "uri_path", nullable = false, length = 30)
    private String uriPath;

    public FileInfo() {
    }

    public FileInfo(String fileName, String extension, String localPath, String uriPath, String userId) {
        this.fileName = fileName;
        this.extension = extension;
        this.localPath = localPath;
        this.uriPath = uriPath;
        this.setCreatedBy(userId);
        this.setUpdatedBy(userId);
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getUriPath() {
        return uriPath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath;
    }
}
