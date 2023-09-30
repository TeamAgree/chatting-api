package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.FileInfo;
import com.agree.chattingapi.entities.FileInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, FileInfoId> {

    @Query("select count(f) from FileInfo f where f.fileName like :formattedDate || '%'")
    Long countByFileNameStartingWith(@Param("formattedDate") String formattedDate);

}
