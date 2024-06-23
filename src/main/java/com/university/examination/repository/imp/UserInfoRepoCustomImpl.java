package com.university.examination.repository.imp;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.repository.UserInfoRepoCustom;
import com.university.examination.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserInfoRepoCustomImpl implements UserInfoRepoCustom {
    private final QueryRepo queryRepo;

    public Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo) {
        Map<String, Object> queryParams = new HashMap<>();
        String sqlCountAll = "select count(1) ";
        String sqlGetData = "select id, full_name, date_of_birth, identify_no ";
        StringBuffer sqlConditional = new StringBuffer();
        sqlConditional.append("from user_info ");
        sqlConditional.append("where status <> 2 ");

        return queryRepo.queryPage(sqlCountAll, sqlGetData, sqlConditional.toString(), queryParams, UserInfoShortSelfSdo.class, pageInfo);

    }
}
