package com.university.examination.controller;

import com.university.examination.config.vnpay.VNPayService;
import com.university.examination.dto.payment.PaymentCreateSdi;
import com.university.examination.dto.payment.PaymentCreateSdo;
import com.university.examination.dto.payment.PaymentSaveSdi;
import com.university.examination.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class VNPController {
    private final VNPayService vnPayService;
    private final PaymentService paymentService;
    @PostMapping("/create")
    public ResponseEntity<PaymentCreateSdo> create(PaymentCreateSdi req) {
        return ResponseEntity.ok(vnPayService.createOrder(req));
    }

    @GetMapping("/return")
    public ResponseEntity returnTransaction(
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String orderInfo,
            @RequestParam(value = "vnp_ResponseCode") String responseCode,
            @RequestParam(value = "vnp_TransactionNo") String transactionNo,
            @RequestParam(value = "vnp_PayDate") String payDate,
            @RequestParam(value = "vnp_TxnRef") String txnRef
    ) {
        PaymentSaveSdi req = PaymentSaveSdi.of(amount, bankCode, orderInfo, responseCode, transactionNo, payDate, txnRef);
        paymentService.save(req);
        if (responseCode.equals("00")) {
            return ResponseEntity.ok(req);
        } else {
            return ResponseEntity.ok(PaymentCreateSdo.of("No", "Fail", ""));
        }
    }
}
