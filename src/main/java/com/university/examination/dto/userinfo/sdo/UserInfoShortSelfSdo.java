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
    private Long id;
    private String fullName;
    private String dateOfBirth;
    private String identifyNo;
    private int gender;
}
