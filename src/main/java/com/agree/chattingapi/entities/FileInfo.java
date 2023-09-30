package com.agree.chattingapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "file_info")
@IdClass(FileInfoId.class)
public class FileInfo extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false)
    private Long id;

    @Id
    @Column(name = "file_name", nullable = false, length = 20)
    private String fileName;

    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Column(name = "local_path", nullable = false, length = 10)
    private String localPath;

    @Column(name = "uri_path", nullable = false, length = 30)
    private String uriPath;

    public FileInfo() {
    }

    public FileInfo(Long id, String fileName, String extension, String localPath, String uriPath) {
        this.id = id;
        this.fileName = fileName;
        this.extension = extension;
        this.localPath = localPath;
        this.uriPath = uriPath;
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
