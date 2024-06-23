package com.university.examination.service.imp;

import com.university.examination.repository.UserRepo;
import com.university.examination.security.UserDetailsImpl;
import com.university.examination.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final UserRepo userRepository;

    public UserDetails userDetails() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        } else {
            return null;
        }
    }

    public Long getIdLogin() {

        UserDetails userDetails = userDetails();
        if (userDetails == null) {
            return null;
        }

        return ((UserDetailsImpl) userDetails).getId();
    }

    public String getUsernameLogin() {

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
        if (userDetails == null) {
            return null;
        }

        return userDetails.getUsername();
    }

//    public String getLang() {
//        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
//        if (userDetails == null) {
//            return "vi";
//        }
//        return userDetails.getLanguage();
//    }

//    public Long getSessionId() {
//        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
//        if (userDetails == null) {
//            return null;
//        }
//        return userDetails.getSessionId();
//    }

    public Boolean existUser(Long userId) {
        return userRepository.findById(userId).isPresent();
    }
}
