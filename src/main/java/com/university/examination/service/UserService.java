package com.university.examination.service;

import com.university.examination.dto.user.sdi.UpdatePasswordSdi;
import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdi.UserRegisterSdi;
import com.university.examination.dto.user.sdo.UpdatePasswordSdo;
import com.university.examination.dto.user.sdo.UserDeleteSdo;
import com.university.examination.dto.user.sdo.UserLoginSdo;
import com.university.examination.dto.user.sdo.UserRegisterSdo;
import com.university.examination.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    UserLoginSdo login (UserLoginSdi req);

    UpdatePasswordSdo updatePassword(UpdatePasswordSdi req);

    UserDeleteSdo delete(Long useId);
}
