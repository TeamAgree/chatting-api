package com.agree.chattingapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_info")
public class FileInfo extends CommonEntity {

    @Id
    @Column(name = "file_id", nullable = false)
    private Long id;

    @Column(name = "file_name", nullable = false, length = 20)
    private String name;

    @Column(name = "extension", nullable = false, length = 5)
    private String extension;

    @Column(name = "local_path", nullable = false, length = 200)
    private String localPath;

    @Column(name = "uri_path", nullable = false, length = 100)
    private String uriPath;

    @Column(name = "remark", nullable = false, length = 200)
    private String remark = "";

    public FileInfo() {
    }

    public FileInfo(Long id, String name, String extension, String localPath, String uriPath) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.localPath = localPath;
        this.uriPath = uriPath;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getRemark() {
        return remark;
    }
}
