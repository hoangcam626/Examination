package com.university.examination.service;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.*;
import com.university.examination.dto.userinfo.sdo.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public interface UserInfoService {

    void create(UserInfoCreateSdi req);

    UserInfoUpdateSdo update(UserInfoUpdateSdi req);

    UserInfoDeleteSdo delete(UserInfoDeleteSdi req);

    UserInfoSelfSdo self(UserInfoSelfSdi req);

    UserInfoSelfSdo mySelf();

    Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo);

    Page<UserInfoShortSelfSdo> search(UserInfoSearchSdi req, PageInfo pageInfo);

    ByteArrayInputStream loadFileExcel();
}
