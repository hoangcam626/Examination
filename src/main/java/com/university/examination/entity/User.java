package com.university.examination.entity;

import com.university.examination.util.constant.ERole;
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

    @OneToOne(mappedBy = "user")
    private Payment Payment;

    @Column(name = "role")
    private ERole role;
}
