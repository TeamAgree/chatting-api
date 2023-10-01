package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {

    @Query("select max(f.id) from FileInfo f")
    Long findMaxFileId();

    int countByFileNameStartsWith(String prefix);

    FileInfo findByFileName(String fileName);
}
