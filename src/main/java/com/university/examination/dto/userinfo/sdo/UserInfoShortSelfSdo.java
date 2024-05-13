package com.university.examination.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfoShortSelfSdo {
    private Long userId;
    private String username;
    private Long avatarId;
}
