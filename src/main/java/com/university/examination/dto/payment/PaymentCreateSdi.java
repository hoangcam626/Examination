package com.university.examination.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PaymentCreateSdi {
    private Long amount;
    private String no;
}
