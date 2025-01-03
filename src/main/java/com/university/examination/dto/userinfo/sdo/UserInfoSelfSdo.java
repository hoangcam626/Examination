package com.university.examination.dto.userinfo.sdo;

import com.university.examination.dto.payment.PaymentSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfoSelfSdo {

    private Long id;

    private Long imageId;

    private String fullName;

    private Integer gender;

    private LocalDate dateOfBirth;

    private String birthPlace;

    private String identifyNo;

    private LocalDate issueDate;

    private String issuePlace;

    private Long frontImageId;

    private Long backImageId;

    private String email;

    private String phoneNumber;

    private String placeOfPermanent;

    private String receiverName;

    private String receiverPhone;

    private String parentPhone;

    private String receiverAddress;

    private Integer graduationYear;

    private String createdAt;

    private PaymentSdo paymentSdo;
}
