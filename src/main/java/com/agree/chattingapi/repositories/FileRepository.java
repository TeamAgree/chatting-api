package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.FileInfo;
import com.agree.chattingapi.entities.FileInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, FileInfoId> {
}
