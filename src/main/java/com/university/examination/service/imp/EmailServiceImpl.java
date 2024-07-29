package com.university.examination.service.imp;

import com.university.examination.dto.email.sdi.EmailSendSdi;
import com.university.examination.dto.email.sdi.EmailTemplateSdi;
import com.university.examination.exception.CustomException;
import com.university.examination.service.EmailService;
import com.university.examination.util.constant.EmailTemplate;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.university.examination.util.constant.LabelKey.ERROR_SEND_MAIL_FAILED;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    public static final String UTF_8_ENCODING = "UTF-8";

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    @Override
    @Async
    public void sendSimpleMailMessage(EmailSendSdi request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(request.getSubject());
            message.setTo(request.getEmailTo());
            message.setText(request.getContent());
            emailSender.send(message);
        } catch (Exception e) {
            throw new CustomException(ERROR_SEND_MAIL_FAILED);
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachments(EmailSendSdi request, String filePath) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(request.getSubject());
            helper.setTo(request.getEmailTo());
            helper.setText(request.getContent());
            //Add attachments
            FileSystemResource file
                    = new FileSystemResource(new File(filePath));
            if (file.getFilename() != null) {
                helper.addAttachment(file.getFilename(), file);
                emailSender.send(message);
            }
            {
                throw new CustomException(ERROR_SEND_MAIL_FAILED);
            }

        } catch (Exception e) {
            throw new CustomException(ERROR_SEND_MAIL_FAILED);
        }
    }

    @Override
    @Async
    public void sendMailWithUserAccount(EmailTemplateSdi req) {

        String send = String.format(
                "Chào bạn %s.\n" +
                        "Bạn đã đăng ký thành công kỳ thi vẽ mỹ thuật do trường ĐHXD tổ chức. Sau đây là tài khoản đăng nhập của bạn:\n" +
                        "\tTên đăng nhập: <Mã cccd mà bạn đã đăng ký>\n" +
                        "\tMật khẩu: %s\n" +
                        "Hãy đăng nhập vào tài khoản để kiểm tra thông tin bạn đã đăng ký và nhận thông báo từ nhà trường tại trang web https://vmt.huce.edu.vn/.\n" +
                        "Phòng QLSV, trường ĐHXD thông báo.\n", req.getFullName(), req.getPassword());
        this.sendEmail(req.getEmailTo(), EmailTemplate.TITLE_SENT_USER_ACCOUNT, send);
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

}
