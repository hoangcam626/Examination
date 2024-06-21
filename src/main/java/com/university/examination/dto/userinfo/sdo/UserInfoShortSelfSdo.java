package com.university.examination.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfoShortSelfSdo {
//    private Long userId;
    private String fullName;
    private LocalDate dateOfBirth;
    private String identifyNo;
}
