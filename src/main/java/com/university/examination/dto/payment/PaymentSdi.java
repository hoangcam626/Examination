package com.university.examination.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PaymentSdi {
    private Long amount;
}
