package com.university.examination.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PaymentSaveSdi {
    private Integer amount;
    private String bankCode;
    private String orderInfo;
    private String responseCode;
    private String transactionNo;
    private String payDate;
    private String txnRef;
}
