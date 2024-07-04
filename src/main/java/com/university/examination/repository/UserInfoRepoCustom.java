package com.university.examination.repository;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.UserInfoSearchSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInfoRepoCustom {
    Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo);
    Page<UserInfoShortSelfSdo> search(UserInfoSearchSdi req, PageInfo pageInfo);

    List<UserInfoShortSelfSdo> getUsers();
}
