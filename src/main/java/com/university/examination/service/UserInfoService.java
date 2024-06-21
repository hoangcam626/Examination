package com.university.examination.service;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.*;
import com.university.examination.dto.userinfo.sdo.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    void create(UserInfoCreateSdi req);

    UserInfoUpdateSdo update(UserInfoUpdateSdi req);

    UserInfoSelfSdo self(UserInfoSelfSdi req);

    //    UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req);
    Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo);
}
