package com.university.examination.dto.email.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class EmailTemplateSdi {

    private String emailTo;

    private String fullName;

    private String password;
}
