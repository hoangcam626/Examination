package com.university.examination.dto.user.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserDeleteSdo {
    private Boolean success;
}
