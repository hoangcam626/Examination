package com.university.examination.service;

import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdi.UserRegisterSdi;
import com.university.examination.dto.user.sdo.UserLoginSdo;
import com.university.examination.dto.user.sdo.UserRegisterSdo;
import com.university.examination.entity.User;
public interface UserService {

    UserRegisterSdo register(UserRegisterSdi req);

    UserLoginSdo login (UserLoginSdi req);

    User getUser(Long id);

    void updatePassword(String prePassword, String password, Long userId);

    void delete(Long useId);
}
