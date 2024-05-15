package com.university.examination.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "user_info")
public class UserInfo extends AbstractAudit {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nơi sinh")
    private String birthPlace;

    @Column(name = "identify_number", nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT 'Số CCCD'")
    private String identifyNo;

    @Column(name = "issue_date", columnDefinition = "DATE COMMENT 'Ngày cấp CCCD'")
    private LocalDate issueDate;

    @Column(name = "issue_place", columnDefinition = "VARCHAR(255) COMMENT 'Nơi cấp CCCD'")
    private String issuePlace;

    @Column(name = "front_image_id" , columnDefinition = "BIGINT COMMENT 'Mặt trước CCCD/Hộ chiếu'")
    private Long frontImageId;

    @Column(name = "back_image_id", nullable = false, columnDefinition = "BIGINT COMMENT 'Mặt sau CCCD/Hộ chiếu'")
    private Long backImageId;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Email liên lạc'")
    private String email;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(255) COMMENT 'Số điện thoại di động'")
    private String phoneNumber;

    @Column(name = "place_of_permanent", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Hộ khẩu thường chú")
    private String placeOfPermanent;

    @Column(name = "receiver_name", columnDefinition = "VARCHAR(255) COMMENT 'Tên người nhận tin")
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Số điện thoại người nhận tin")
    private String receiverPhone;

    @Column(name = "parent_phone", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Số điện thoại của bố mẹ")
    private String parentPhone;

    @Column(name = "receiver_address", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Địa chỉ nhận tin")
    private String receiverAddress;

    @Column(name = "graduation_year", nullable = false, length = 4, columnDefinition = "INT COMMENT 'Năm tốt nghiệp THPT")
    private Integer graduationYear;

}
