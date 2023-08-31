package com.agree.chattingapi.repositories;

import com.agree.chattingapi.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

    @Query("SELECT u FROM UserInfo u WHERE u.id LIKE :search OR u.nickName LIKE :search OR u.name LIKE :search")
    List<UserInfo> findUsersByAnyMatchingParameter(@Param("search") String search);

    boolean existsByMobileNo(String mobileNo);

}