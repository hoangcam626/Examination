package com.university.examination.dto.email.sdi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendSdi {

    private String emailTo;

    private String subject;

    private String content;
}
