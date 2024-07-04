package com.university.examination.service;

import com.university.examination.dto.email.sdi.EmailSendSdi;
import com.university.examination.dto.email.sdi.EmailTemplateSdi;
import com.university.examination.exception.CustomException;
import com.university.examination.util.constant.EmailTemplate;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.university.examination.util.constant.LabelKey.ERROR_SEND_MAIL_FAILED;

@Service
public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendSimpleMailMessage(EmailSendSdi request);

    void sendMimeMessageWithAttachments(EmailSendSdi request, String filePath);

    void sendMailWithUserAccount(EmailTemplateSdi req);
}
