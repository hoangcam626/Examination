package com.university.examination.service.imp;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.payment.PaymentSdo;
import com.university.examination.entity.Payment;
import com.university.examination.dto.userinfo.sdi.*;
import com.university.examination.dto.userinfo.sdo.*;
import com.university.examination.entity.User;
import com.university.examination.entity.UserInfo;
import com.university.examination.exception.CustomException;
import com.university.examination.repository.UserInfoRepo;
import com.university.examination.repository.UserRepo;
import com.university.examination.service.CommonService;
import com.university.examination.service.EmailService;
import com.university.examination.service.ImageService;
import com.university.examination.service.UserInfoService;
import com.university.examination.util.excel.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

import static com.university.examination.util.DataUtil.isNullObject;
import static com.university.examination.util.constant.Error.*;
import static com.university.examination.util.DataUtil.copyProperties;
import static com.university.examination.util.DateTimeConvert.*;
import static com.university.examination.util.DateTimeUtils.DATE_TIME_FORMAT;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final ImageService imageService;
    private final CommonService commonService;
    private final EmailService emailService;

    public UserInfoCreateSdo create(UserInfoCreateSdi req) {

        if (userInfoRepo.existsByIdentifyNo(req.getIdentifyNo())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }

        UserInfo userInfo = copyProperties(req, UserInfo.class);
        userInfo.setImageId(imageService.uploadFile(req.getImage()));
        userInfo.setFrontImageId(imageService.uploadFile(req.getFrontImage()));
        userInfo.setBackImageId(imageService.uploadFile(req.getBackImage()));
        userInfo.setCheck(0);
        userInfoRepo.save(userInfo);
        return UserInfoCreateSdo.of(userInfo.getId());
    }

    public UserInfoUpdateSdo update(UserInfoUpdateSdi req) {

        Long loginId = commonService.getIdLogin();
        UserInfo userInfo = getUser(loginId).getUserInfo();

        userInfo.setFullName(req.getFullName());
        userInfo.setDateOfBirth(req.getDateOfBirth());
        userInfo.setBirthPlace(req.getBirthPlace());
        userInfo.setGender(req.getGender());
        userInfo.setIssueDate(req.getIssueDate());
        userInfo.setIssuePlace(req.getIssuePlace());
        userInfo.setEmail(req.getEmail());
        userInfo.setGraduationYear(req.getGraduationYear());
        userInfo.setParentPhone(req.getParentPhone());
        userInfo.setPhoneNumber(req.getPhoneNumber());
        userInfo.setPlaceOfPermanent(req.getPlaceOfPermanent());
        userInfo.setReceiverAddress(req.getReceiverAddress());
        userInfo.setReceiverName(req.getReceiverName());
        userInfo.setReceiverPhone(req.getReceiverPhone());
        userInfo.setImageId(imageService.uploadFile(req.getImage()));
        userInfo.setFrontImageId(imageService.uploadFile(req.getFrontImage()));
        userInfo.setBackImageId(imageService.uploadFile(req.getBackImage()));
        userInfo.setUpdatedAt(LocalDateTime.now());

        if(userInfo.getCheck() == 2){
            userInfo.setCheck(0);
        }

        userInfoRepo.save(userInfo);
        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }

    public UserInfoDeleteSdo delete(UserInfoDeleteSdi req) {

        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
        userInfo.setStatus(2);

        User user = userInfo.getUser();
        if (isNullObject(user)) {
            user.setStatus(2);
            userRepo.save(user);
        }

        userInfoRepo.save(userInfo);


        return UserInfoDeleteSdo.of(Boolean.TRUE);
    }

    public UserInfoSelfSdo self(UserInfoSelfSdi req) {

        UserInfo userInfo = getUserInfo(req.getUserInfoId());
        UserInfoSelfSdo res = copyProperties(userInfo, UserInfoSelfSdo.class);
        Payment payment = userInfo.getUser().getPayment();
        res.setPaymentSdo(copyProperties(payment, PaymentSdo.class));
        res.setCreatedAt(dateTimeToString(userInfo.getCreatedAt(), DATE_TIME_FORMAT));
        return res;
    }

    public UserInfoSelfSdo mySelf() {

        Long loginId = commonService.getIdLogin();
        UserInfo userInfo = getUser(loginId).getUserInfo();
        UserInfoSelfSdo res = copyProperties(userInfo, UserInfoSelfSdo.class);
        Payment payment = userInfo.getUser().getPayment();
        res.setPaymentSdo(copyProperties(payment, PaymentSdo.class));
        res.setCreatedAt(dateTimeToString(userInfo.getCreatedAt(), DATE_TIME_FORMAT));
        return res;
    }

    public Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo) {
        return userInfoRepo.getUsers(pageInfo);
    }

    public UserInfo getUserInfo(Long id) {
        return userInfoRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public Page<UserInfoShortSelfSdo> search(UserInfoSearchSdi req, PageInfo pageInfo) {
        return userInfoRepo.search(req, pageInfo);
    }

    public ByteArrayInputStream loadFileExcel() {
        List<UserInfoShortSelfSdo> users = userInfoRepo.getUsers();

        return ExcelHelper.infoToExcel(users);
    }

    public void checkSuccess(UserInfoSelfSdi req) {
        UserInfo userInfo = getUserInfo(req.getUserInfoId());
        userInfo.setCheck(1);
        userInfoRepo.save(userInfo);
        String subject = "[DHXD] Thông tin đăng ký dự thi của bạn đã duyệt";
        String body = "Chào em " + userInfo.getFullName() + "\n" +
                "Phòng quản lý đã duyệt thông tin đăng ký dự thi của em và lưu vào danh sách các thí sinh dự thi. " +
                "Hãy đợi thông báo tiếp theo từ nhà trường về kỳ thi.\n" +
                "Đây là thông tin mà em đã đăng ký trên hệ thống:\n" +
                "Họ tên: " + userInfo.getFullName() + "\n\t" +
                "Giới tính: " + userInfo.getGender() + "\n\t" +
                "Ngày sinh: " + userInfo.getDateOfBirth() + "\n\t" +
                "Nơi sinh: " + userInfo.getBirthPlace() + "\n\t" +
                "Số CCCD: " + userInfo.getIdentifyNo() + "\n\t" +
                "Sđt: " + userInfo.getPhoneNumber() + "\n\t" +
                "Hộ khẩu thường trú: " + userInfo.getPlaceOfPermanent() + "\n\t" +
                "Năm tốt nghiệp: " + userInfo.getGraduationYear() + "\n\t" + "\n" +
                "Người nhận tin\n\t" +
                "Họ tên: " + userInfo.getReceiverName() + "\n\t" +
                "Sđt nhận tin: " + userInfo.getReceiverPhone() + "\n\t" +
                "Sđt bố mẹ: " + userInfo.getParentPhone() + "\n\t" +
                "Địa chỉ nhận tin: " + userInfo.getReceiverAddress() + "\n" +
                "Phòng QLSV, trường ĐHXD thông báo.\n";
        emailService.sendEmail(userInfo.getEmail(), subject, body);

    }

    public void checkFail(UserInfoSelfSdi req) {
        UserInfo userInfo = getUserInfo(req.getUserInfoId());
        userInfo.setCheck(2);
        userInfoRepo.save(userInfo);
        String subject = "[DHXD] Thông tin đăng ký dự thi của bạn không được chấp nhận";
        String body = "Chào em " + userInfo.getFullName() + "\n" +
                "Phòng QLSV trường ĐHXD phát hiện thông tin đăng ký dự thi của em có sai sót.\n" +
                "Hãy đăng nhập vào tài khoản đăng ký dự thi để kiểm tra và chỉnh sửa lại thông tin đăng ký.\n" +
                "Chúng tôi sẽ kiểm tra lại sau khi bạn chỉnh sửa lại thông tin của mình.\n" +
                "Phòng QLSV, trường ĐHXD thông báo.\n";
        emailService.sendEmail(userInfo.getEmail(), subject, body);

    }

    public Page<UserInfoShortSelfSdo> getUserInfoNoCheck(PageInfo req) {
        return userInfoRepo.getUsersNoCheck(req);
    }

    public Page<UserInfoShortSelfSdo> getUserInfoFailCheck(PageInfo req) {
        return userInfoRepo.getUsersFailCheck(req);
    }

}
