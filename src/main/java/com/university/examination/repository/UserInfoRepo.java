package com.university.examination.repository;

import com.university.examination.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>, UserInfoRepoCustom{
    UserInfo findByUserId(Long userId);

    @Query("select count(u)>0 from UserInfo u where u.identifyNo like :identifyNo and u.status<>2 and YEAR(u.createdAt) = YEAR(NOW())")
    Boolean existsByIdentifyNo(String identifyNo);
    UserInfo findByIdentifyNo(String identifyNo);


}
