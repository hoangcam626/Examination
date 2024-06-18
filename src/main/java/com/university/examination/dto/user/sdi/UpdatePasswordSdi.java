package com.university.examination.dto.user.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UpdatePasswordSdi {
    private Long id;
    private String prePassword;
    private String password;
}
