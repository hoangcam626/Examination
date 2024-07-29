package com.university.examination.controller;

import com.university.examination.config.vnpay.VNPayService;
import com.university.examination.dto.payment.PaymentCreateSdi;
import com.university.examination.dto.payment.PaymentCreateSdo;
import com.university.examination.dto.payment.PaymentSaveSdi;
import com.university.examination.dto.payment.PaymentSdo;
import com.university.examination.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView returnTransaction(
            @RequestParam(value = "vnp_Amount") Integer amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String orderInfo,
            @RequestParam(value = "vnp_ResponseCode") String responseCode,
            @RequestParam(value = "vnp_TransactionNo") String transactionNo,
            @RequestParam(value = "vnp_PayDate") String payDate,
            @RequestParam(value = "vnp_TxnRef") String txnRef
    ) {
        PaymentSaveSdi req = PaymentSaveSdi.of(amount, bankCode, orderInfo, responseCode, transactionNo, payDate, txnRef);
        paymentService.save(req);

        ModelAndView mav = new ModelAndView();
        if (responseCode.equals("00")) {
            mav.setViewName("vnpay_ok");
            mav.addObject("transaction", req);
            return mav;
        } else {
            mav.setViewName("vnpay_fail");
            mav.addObject("message", "Transaction failed");
            return mav;
        }
    }
    @PreAuthorize("hasRole('ROLE_PAYMENT_CHECKER'&&'ROLE_ADMIN')")
    @PostMapping("/return-information")
    public ResponseEntity<PaymentSdo> getPayment(Long userId){
        return ResponseEntity.ok(paymentService.getPayment(userId));
    }

}
