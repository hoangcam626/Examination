package com.university.examination.repository;

import com.university.examination.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.user.userInfo.id = :userInfoId")
    Payment findByUserInfoId(Long userInfoId);

    Boolean existsByTransactionNo(String transactionNo);
}
