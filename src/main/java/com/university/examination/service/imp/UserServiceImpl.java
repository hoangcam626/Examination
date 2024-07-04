package com.university.examination.service.imp;

import com.university.examination.dto.email.sdi.EmailTemplateSdi;
import com.university.examination.dto.user.sdi.UserDeleteSdi;
import com.university.examination.dto.user.sdi.UserLoginSdi;
import com.university.examination.dto.user.sdi.UpdatePasswordSdi;
import com.university.examination.dto.user.sdi.UserRegisterSdi;
import com.university.examination.dto.user.sdo.UpdatePasswordSdo;
import com.university.examination.dto.user.sdo.UserDeleteSdo;
import com.university.examination.dto.user.sdo.UserLoginSdo;
import com.university.examination.entity.User;
import com.university.examination.entity.UserInfo;
import com.university.examination.exception.CustomException;
import com.university.examination.repository.UserInfoRepo;
import com.university.examination.repository.UserRepo;
import com.university.examination.security.JwtUtils;
import com.university.examination.security.UserDetailsImpl;
import com.university.examination.service.EmailService;
import com.university.examination.service.UserService;
import com.university.examination.util.constant.ERole;
import com.university.examination.util.password.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.university.examination.util.constant.Error.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils tokenProvider;
    private final EmailService emailService;

    public User create (UserRegisterSdi req){
        PasswordGenerator pw = new PasswordGenerator(8, 12);
        String password = pw.generatePassword();
        User user = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(password))
                .role(ERole.ROLE_USER)
                .build();
        userRepo.save(user);
        UserInfo userInfo = userInfoRepo.findByIdentifyNo(req.getUsername());
        userInfo.setUser(user);
        userInfoRepo.save(userInfo);
        emailService.sendMailWithUserAccount(EmailTemplateSdi.of(userInfo.getEmail(), userInfo.getFullName(), password));
    return user;
    }

    public User getUser(Long id) {

        return userRepo.findById(id).orElseThrow(() -> new CustomException("Error: no use"));
    }

    public UpdatePasswordSdo updatePassword(UpdatePasswordSdi req) {

        User user = this.getUser(req.getId());

        if (!encoder.matches(req.getPrePassword(), user.getPassword())) {
            throw new CustomException(ERROR_OLD_PASSWORD);
        }

        user.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(user);
        return UpdatePasswordSdo.of(Boolean.TRUE);
    }

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
        return new UserLoginSdo(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getAuthorities().toString());
    }

    public UserDeleteSdo delete(UserDeleteSdi req) {
        User user = this.getUser(req.getId());
        user.setStatus(2);
        userRepo.save(user);
        return UserDeleteSdo.of(Boolean.TRUE);
    }
}
