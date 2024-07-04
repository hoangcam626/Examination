package com.university.examination.dto.userinfo.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoSearchSdi {
    private String keyword;
}
