package com.university.examination.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PaymentSdo implements Serializable {
    private String status;
    private String message;
    private String url;
}
