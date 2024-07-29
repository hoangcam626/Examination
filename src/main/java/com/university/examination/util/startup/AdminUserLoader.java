package com.university.examination.util.startup;

import com.university.examination.entity.User;
import com.university.examination.repository.UserRepo;
import com.university.examination.util.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminUserLoader implements CommandLineRunner {

    @Value("${account_admin.username}")
    private String username;

    @Value("${account_admin.password}")
    private String password;

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("admin") == null) {
            User adminUser = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(ERole.ROLE_ADMIN).build();
            userRepo.save(adminUser);
        }
        if (userRepo.findByUsername("phongtaivu") == null) {
            User paymentChecker = User.builder()
                    .username("phongtaivu")
                    .password(passwordEncoder.encode(password))
                    .role(ERole.ROLE_PAYMENT_CHECKER).build();
            userRepo.save(paymentChecker);
        }
        if (userRepo.findByUsername("phongquanlysv") == null) {
            User moderator = User.builder()
                    .username("phongquanlysv")
                    .password(passwordEncoder.encode(password))
                    .role(ERole.ROLE_MODERATOR).build();
            userRepo.save(moderator);
        }
    }
}
