package com.university.examination.service.imp;

import com.university.examination.dto.payment.PaymentSaveSdi;
import com.university.examination.dto.payment.PaymentSdo;
import com.university.examination.dto.user.sdi.UserRegisterSdi;
import com.university.examination.entity.Payment;
import com.university.examination.entity.User;
import com.university.examination.entity.UserInfo;
import com.university.examination.repository.PaymentRepo;
import com.university.examination.repository.UserInfoRepo;
import com.university.examination.service.PaymentService;
import com.university.examination.service.UserService;
import com.university.examination.util.DataUtil;
import com.university.examination.util.DateTimeConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final UserInfoRepo userInfoService;
    private final UserService userService;
    @Transactional
    public void save(PaymentSaveSdi req){
        if(!req.getResponseCode().equals("00")){
            UserInfo userInfo = userInfoService.findByIdentifyNo(req.getTxnRef());
            userInfoService.delete(userInfo);
        }else {
            Payment payment = DataUtil.copyProperties(req, Payment.class);
            LocalDateTime payDate = DateTimeConvert.stringToDateTime(req.getPayDate(), "yyyyMMddHHmmss");
            payment.setPayDate(payDate);
            User user = userService.create(UserRegisterSdi.of(req.getTxnRef()));
            payment.setUser(user);
            paymentRepo.save(payment);
        }
    }
    public PaymentSdo getPayment(Long userId){
        Payment payment = paymentRepo.findByUserInfoId(userId);
        return DataUtil.copyProperties(payment, PaymentSdo.class);
    }
}
