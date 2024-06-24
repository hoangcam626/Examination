package com.university.examination.repository;

import com.university.examination.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>, UserInfoRepoCustom{
    UserInfo findByUserId(Long userId);

}
