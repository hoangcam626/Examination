package com.university.examination.repository;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import org.springframework.data.domain.Page;

public interface UserInfoRepoCustom {
    Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo);
}
