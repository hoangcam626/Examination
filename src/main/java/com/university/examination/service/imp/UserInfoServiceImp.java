package com.university.examination.service.imp;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.util.constant.EmailTemplate;
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
import com.university.examination.util.password.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.university.examination.util.constant.Error.*;
import static com.university.examination.util.DataUtil.copyProperties;
import static com.university.examination.util.DateTimeConvert.*;
import static com.university.examination.util.DateTimeUtils.DATE_TIME_FORMAT;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoServiceImp implements UserInfoService {

    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final ImageService imageService;
    private final CommonService commonService;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    public void create(UserInfoCreateSdi req) {
        PasswordGenerator pw = new PasswordGenerator(8, 12);
        String password = pw.generatePassword();
        User user = User.builder()
                .username(req.getIdentifyNo())
                .password(encoder.encode(password)).build();
        UserInfo userInfo = copyProperties(req, UserInfo.class);
        userInfo.setImageId(imageService.uploadFile(req.getImage()));
        userInfo.setFrontImageId(imageService.uploadFile(req.getFrontImage()));
        userInfo.setBackImageId(imageService.uploadFile(req.getBackImage()));
        userInfo.setUser(user);

        userRepo.save(user);
        userInfoRepo.save(userInfo);
        String send = EmailTemplate.BODY_SENT_USER_ACCOUNT + "\n Ten dang nhap: <Ma CCCD cua ban>" + "\n Mat khau: " + password + "\n" + EmailTemplate.TEXT_BOTTOM;
        emailService.sendEmail(userInfo.getEmail(), EmailTemplate.TITLE_SENT_USER_ACCOUNT, send);
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
        userInfoRepo.save(userInfo);
        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }

    //    public UserInfoUpdateSdo updateAvatar(UserInfoUpdateAvatarSdi req) {
//        Long loginId = commonService.getIdLogin();
//
//        UserInfo userInfo = userInfoRepo.findByUserId(loginId);
//
//        if (userInfo.getAvatarId() != null) {
//            imageService.delete(userInfo.getAvatarId());
//        }
//
//        Long avatarId = imageService.uploadFile(req.getImage());
//        userInfo.setAvatarId(avatarId);
//
//        userInfoRepo.save(userInfo);
//
//        return UserInfoUpdateSdo.of(Boolean.TRUE);
//    }
//
    public UserInfoSelfSdo self(UserInfoSelfSdi req) {

        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
        UserInfoSelfSdo res = copyProperties(userInfo, UserInfoSelfSdo.class);

        res.setCreatedAt(dateTimeToString(userInfo.getCreatedAt(), DATE_TIME_FORMAT));
        return res;
    }

//    public UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req) {
//        User user = userRepo.findById(req.getUserId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
//        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
//        return UserInfoShortSelfSdo.of(userInfo.getUserId(), user.getUsername(), userInfo.getAvatarId());
//    }
    public Page<UserInfoShortSelfSdo> getUsers(PageInfo pageInfo){
        Pageable pageable = PageRequest.of(pageInfo.getCurrentPage(), pageInfo.getPageSize());
        return userInfoRepo.find(pageable);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoRepo.findByUserId(userId);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}
