package com.university.examination.controller;

import com.university.examination.dto.common.pagination.PageInfo;
import com.university.examination.dto.userinfo.sdi.UserInfoDeleteSdi;
import com.university.examination.dto.userinfo.sdi.UserInfoSearchSdi;
import com.university.examination.dto.userinfo.sdi.UserInfoSelfSdi;
import com.university.examination.dto.userinfo.sdo.UserInfoDeleteSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoSelfSdo;
import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.service.UserInfoService;
import com.university.examination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserInfoService userInfoService;

    @PostMapping("/self-user")
    public ResponseEntity<UserInfoSelfSdo> self(UserInfoSelfSdi req) {
        return ResponseEntity.ok(userInfoService.self(req));
    }

    @PostMapping("/delete-user")
    public ResponseEntity<UserInfoDeleteSdo> delete(UserInfoDeleteSdi req) {
        return ResponseEntity.ok(userInfoService.delete(req));
    }

    @PostMapping("/all-users")
    public ResponseEntity<Page<UserInfoShortSelfSdo>> getUsers(PageInfo page) {
        return ResponseEntity.ok(userInfoService.getUsers(page));
    }

    @PostMapping("/search-user")
    public ResponseEntity<Page<UserInfoShortSelfSdo>> search(UserInfoSearchSdi req, PageInfo pageInfo) {
        return ResponseEntity.ok(userInfoService.search(req, pageInfo));
    }

    @GetMapping("/download-file")
    public ResponseEntity<Resource> getFile() {
        String filename = "VeMyThuat.xlsx";
        InputStreamResource file = new InputStreamResource(userInfoService.loadFileExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
