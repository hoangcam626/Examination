package com.university.examination.dto.user.sdo;

import lombok.Data;

@Data
public class UserLoginSdo {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String username;
    private String role;

    public UserLoginSdo(String accessToken, Long id, String username, String role) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
