package com.university.examination.controller;

import com.university.examination.dto.user.sdi.UpdatePasswordSdi;
import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdo.UpdatePasswordSdo;
import com.university.examination.dto.user.sdo.UserLoginSdo;
import com.university.examination.dto.userinfo.sdi.UserInfoCreateSdi;
import com.university.examination.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoCreateSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoSelfSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoUpdateSdo;
import com.university.examination.service.UserInfoService;
import com.university.examination.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/v1/")
public class UserController {

    private final UserInfoService userInfoService;
    private final UserService userService;

    @PostMapping("auth/register")
    public ResponseEntity<UserInfoCreateSdo> register(UserInfoCreateSdi req) {
        return ResponseEntity.ok(userInfoService.create(req));
    }

    @PostMapping("auth/login")
    public ResponseEntity<UserLoginSdo> login(UserLoginSdi req) {
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

    @PostMapping("user/update-password")
    public ResponseEntity<UpdatePasswordSdo> updatePassword(UpdatePasswordSdi req) {
        return ResponseEntity.ok(userService.updatePassword(req));
    }

    @GetMapping("user/my-self")
    public ResponseEntity<UserInfoSelfSdo> getSelf() {
        return ResponseEntity.ok(userInfoService.mySelf());
    }
}
