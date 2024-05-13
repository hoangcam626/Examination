package com.university.examination.dto.userinfo.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoUpdateAvatarSdi {
    private MultipartFile image;
}
