package com.university.examination.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoDeleteSdo {
    private Boolean success;
}