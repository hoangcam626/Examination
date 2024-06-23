package com.university.examination.dto.user.sdo;

import com.university.examination.util.constant.ERole;
import lombok.Data;

@Data
public class UserLoginSdo {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String username;
    private ERole role;

    public UserLoginSdo(String accessToken, Long id, String username) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
    }
}
