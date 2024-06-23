package com.university.examination.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table
public class User extends AbstractAudit{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    @Column(name="check_info", columnDefinition = "")
    private Boolean checkInfo;

    @Column(name="check_pay")
    private Boolean checkPay;

    @Column(name = "role")
    private String role;
}
