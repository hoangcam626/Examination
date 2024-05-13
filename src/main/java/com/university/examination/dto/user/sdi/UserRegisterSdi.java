package com.university.examination.dto.user.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserRegisterSdi {
    private String username;
    private String email;
    private String password;

}
