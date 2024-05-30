package com.university.examination.security;

import com.university.examination.entity.User;
import com.university.examination.exception.CustomException;
import com.university.examination.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepo userRepository;
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("User not found with username: " + username);
        }

        return UserDetailsImpl.build(user);
    }
}