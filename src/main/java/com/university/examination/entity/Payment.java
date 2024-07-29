package com.university.examination.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payment")
public class Payment extends AbstractAudit {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private Integer amount;
    @Column
    private String bankCode;
    @Column
    private String orderInfo;
    @Column
    private String transactionNo;
    @Column
    private LocalDateTime payDate;
    @Column
    private String txnRef;
}
