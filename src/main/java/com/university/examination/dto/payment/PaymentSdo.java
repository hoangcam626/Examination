package com.university.examination.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PaymentSdo {
    private String amount;
    private String bankCode;
    private String order;
    private String orderInfo;
    private String transactionNo;
    private LocalDateTime payDate;
    private String txnRef;
}
