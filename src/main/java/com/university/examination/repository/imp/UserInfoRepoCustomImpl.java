package com.university.examination.repository.imp;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.UserInfoSearchSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.repository.UserInfoRepoCustom;
import com.university.examination.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.university.examination.util.DataUtil.isNullObject;

@RequiredArgsConstructor
public class UserInfoRepoCustomImpl implements UserInfoRepoCustom {
    private final QueryRepo queryRepo;

    public Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo) {
        Map<String, Object> queryParams = new HashMap<>();
        String sqlCountAll = "select count(1) ";
        String sqlGetData = "select id, full_name, date_of_birth, identify_no, gender ";
        StringBuffer sqlConditional = new StringBuffer();
        sqlConditional.append("from user_info ");
        sqlConditional.append("where status <> 2 ");

        return queryRepo.queryPage(sqlCountAll, sqlGetData, sqlConditional.toString(), queryParams, UserInfoShortSelfSdo.class, pageInfo);

    }

    public Page<UserInfoShortSelfSdo> search(UserInfoSearchSdi req, PageInfo pageInfo) {
        String keyword = req.getKeyword();
        Map<String, Object> queryParams = new HashMap<>();
        String sqlCountAll = "select count(1) ";
        String sqlGetData = "select id, full_name, date_of_birth, identify_no, gender ";
        StringBuffer sqlConditional = new StringBuffer();
        sqlConditional.append("from user_info ");
        sqlConditional.append("where status <> 2 ");
        if(!isNullObject(keyword)){
            queryParams.put("keyword", "%"+keyword+"%");
            sqlConditional.append("and full_name like :keyword or identify_no like :keyword ");
        }

        return queryRepo.queryPage(sqlCountAll, sqlGetData, sqlConditional.toString(), queryParams, UserInfoShortSelfSdo.class, pageInfo);

    }
    public List<UserInfoShortSelfSdo> getUsers(){
        StringBuffer sqlConditional = new StringBuffer();
        sqlConditional.append("select id, full_name, date_of_birth, identify_no ");
        sqlConditional.append("from user_info ");
        sqlConditional.append("where status <> 2 ");
        Map<String, Object> queryParams = new HashMap<>();
        return queryRepo.queryList(sqlConditional.toString(), queryParams, UserInfoShortSelfSdo.class);
    }

}
