package com.university.examination.repository;

import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>, UserInfoRepoCustom{
    UserInfo findByUserId(Long userId);

}
