package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {

    @Query("select max(f.id) from FileInfo f")
    Optional<Long> findMaxFileId();

    int countByFileNameStartsWith(String prefix);

    FileInfo findByFileName(String fileName);

    @Query("select f.fileName from FileInfo f where f.id = :id")
    List<String> findFileNameById(String id);
}
