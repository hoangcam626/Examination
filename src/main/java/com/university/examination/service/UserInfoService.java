package com.university.examination.service;

import com.university.examination.dto.userinfo.sdi.*;
import com.university.examination.dto.userinfo.sdo.*;

public interface UserInfoService {
    UserInfoUpdateSdo update(UserInfoUpdateSdi req);
    UserInfoUpdateSdo updateAvatar(UserInfoUpdateAvatarSdi req);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
    UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req);
}
