package com.university.examination.service;

import com.university.examination.dto.payment.PaymentSaveSdi;
import com.university.examination.dto.payment.PaymentSdo;

public interface PaymentService {
    void save(PaymentSaveSdi req);
    PaymentSdo getPayment(Long userId);
}
