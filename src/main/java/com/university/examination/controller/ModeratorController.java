package com.university.examination.controller;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.UserInfoSelfSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_MODERATORMODERATOR' && 'ROLE_ADMIN')")
@RequestMapping("/api/v1/admin")
public class ModeratorController {

    private final UserInfoService userInfoService;
    @PostMapping("/users-no-check")
    public ResponseEntity<Page<UserInfoShortSelfSdo>> getUserNoCheck(PageInfo req){
        return ResponseEntity.ok(userInfoService.getUserInfoNoCheck(req));
    }
    @PostMapping("/users-fail-check")
    public ResponseEntity<Page<UserInfoShortSelfSdo>> getUserFailCheck(PageInfo req){
        return ResponseEntity.ok(userInfoService.getUserInfoFailCheck(req));
    }

    @PostMapping("/check-success")
    public ResponseEntity<String> checkSuccess(UserInfoSelfSdi req){
        userInfoService.checkSuccess(req);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/check-fail")
    public ResponseEntity<String> checkFail(UserInfoSelfSdi req){
        userInfoService.checkFail(req);
        return ResponseEntity.ok("Success");
    }

}
