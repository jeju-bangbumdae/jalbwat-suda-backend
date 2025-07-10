package com.backend.template.service;

import com.backend.template.base.authorization.TokenService;
import com.backend.template.entity.User;
import com.backend.template.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 에 해당하는 유저가 존재하지 않습니다."));
        return tokenService.generateToken(user);
    }
}
