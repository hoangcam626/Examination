package com.university.examination.repository;

import com.university.examination.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Payment findByTxnRef(String txnRef);
}
