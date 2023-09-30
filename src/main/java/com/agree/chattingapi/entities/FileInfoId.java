package com.agree.chattingapi.entities;

import java.io.Serializable;

public class FileInfoId implements Serializable {

    private Long id;
    private String fileName;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
