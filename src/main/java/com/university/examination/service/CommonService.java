package com.university.examination.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service

public interface CommonService {
    UserDetails userDetails();

    Long getIdLogin();

    String getUsernameLogin();

//    String getLang();

//    Long getSessionId();

    Boolean existUser(Long userId);
}
