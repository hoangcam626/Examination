package com.university.examination.controller;

import com.university.examination.dto.user.sdi.UpdatePasswordSdi;
import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdo.UpdatePasswordSdo;
import com.university.examination.dto.userinfo.sdi.UserInfoCreateSdi;
import com.university.examination.service.UserInfoService;
import com.university.examination.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(UserInfoCreateSdi req) {
        userInfoService.create(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(UserLoginSdi req) {
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping("/change-password")
    public ResponseEntity<UpdatePasswordSdo> changePassword(UpdatePasswordSdi req) {
        return ResponseEntity.ok(userService.updatePassword(req));
    }

}
