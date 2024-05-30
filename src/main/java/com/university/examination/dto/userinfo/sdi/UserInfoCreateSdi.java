package com.university.examination.dto.userinfo.sdi;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoCreateSdi {

    @NotNull
    private MultipartFile image;

    @NotNull
    private String fullName;

    @NotNull
    private Integer gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String birthPlace;

    @NotNull
    private String identifyNo;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private String issuePlace;

    @NotNull
    private MultipartFile frontImage;

    @NotNull
    private MultipartFile backImage;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String placeOfPermanent;

    private String receiverName;

    @NotNull
    private String receiverPhone;

    @NotNull
    private String parentPhone;

    @NotNull
    private String receiverAddress;

    @NotNull
    private Integer graduationYear;
}
