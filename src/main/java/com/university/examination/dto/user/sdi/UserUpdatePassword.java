package com.university.examination.dto.user.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserUpdatePassword {
    private Long id;
    private String prePassword;
    private String password;
}
