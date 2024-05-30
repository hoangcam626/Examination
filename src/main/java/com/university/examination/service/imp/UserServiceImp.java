package com.university.examination.service.imp;

import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdi.UserRegisterSdi;
import com.university.examination.dto.user.sdi.UserUpdatePassword;
import com.university.examination.dto.user.sdo.UserLoginSdo;
import com.university.examination.dto.user.sdo.UserRegisterSdo;
import com.university.examination.entity.User;
import com.university.examination.entity.UserInfo;
import com.university.examination.exception.CustomException;
import com.university.examination.repository.UserInfoRepo;
import com.university.examination.repository.UserRepo;
import com.university.examination.security.JwtUtils;
import com.university.examination.security.UserDetailsImpl;
import com.university.examination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.university.examination.constant.Error.*;
import static com.university.examination.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final PasswordEncoder encoder;

//    public UserRegisterSdo register(UserRegisterSdi req) {
//
//        if (userRepo.existsByEmail(req.getEmail())) {
//            throw new CustomException(ERROR_EXIT_EMAIL);
//        }
//
//        User user = copyProperties(req, User.class);
//        String password = encoder.encode(req.getPassword());
//        user.setPassword(password);
//        userRepo.save(user);
//        UserInfo userInfo = UserInfo.builder().userId(user.getId()).build();
//        userInfoRepo.save(userInfo);
//        return UserRegisterSdo.of(user.getId());
//    }

    public User getUser(Long id) {

        return userRepo.findById(id).orElseThrow(() -> new CustomException("Error: no use"));
    }

    public void updatePassword(UserUpdatePassword req) {

        User user = this.getUser(userId);

        if (!encoder.matches(prePassword, user.getPassword())) {
            throw new CustomException(ERROR_OLD_PASSWORD);
        }

        user.setPassword(encoder.encode(password));
        userRepo.save(user);
    }
    private final AuthenticationManager authenticationManager;
    private final JwtUtils tokenProvider;
    public UserLoginSdo login (UserLoginSdi req){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = tokenProvider.generateJwtToken(authentication);
        return new UserLoginSdo(jwt, userDetails.getId(), userDetails.getUsername());
    }

    public void delete(Long useId) {
        userRepo.deleteById(useId);
    }
}
