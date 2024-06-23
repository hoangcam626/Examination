package com.university.examination.dto.email.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class EmailTemplateSdo {
    private String message;
}
