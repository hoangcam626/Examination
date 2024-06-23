package com.university.examination.controller;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.user.sdi.UpdatePasswordSdi;
import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdo.UpdatePasswordSdo;
import com.university.examination.dto.userinfo.sdi.UserInfoCreateSdi;
import com.university.examination.dto.userinfo.sdi.UserInfoSelfSdi;
import com.university.examination.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoSelfSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoUpdateSdo;
import com.university.examination.service.UserInfoService;
import com.university.examination.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class UserController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    @PostMapping("auth/register")
    public ResponseEntity register(UserInfoCreateSdi req) {
        userInfoService.create(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("auth/login")
    public ResponseEntity login(UserLoginSdi req) {
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping("user/change-password")
    public ResponseEntity<UpdatePasswordSdo> changePassword(UpdatePasswordSdi req) {
        return ResponseEntity.ok(userService.updatePassword(req));
    }

    @PutMapping("user/update")
    public ResponseEntity<UserInfoUpdateSdo> update(UserInfoUpdateSdi req) {
        return ResponseEntity.ok(userInfoService.update(req));
    }

    @PostMapping("user/self")
    public ResponseEntity<UserInfoSelfSdo> self(UserInfoSelfSdi req) {
        return ResponseEntity.ok(userInfoService.self(req));
    }

    @PostMapping("user/users")
    public ResponseEntity<Page<UserInfoShortSelfSdo>> getUsers(PageInfo page) {
        return ResponseEntity.ok(userInfoService.getUsers(page));
    }
}
