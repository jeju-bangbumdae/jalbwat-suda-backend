package com.backend.template.controller;

import com.backend.template.dto.LoginRequestDto;
import com.backend.template.dto.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
//        // 실제 사용자 인증 로직 (예: 데이터베이스에서 사용자 조회 및 비밀번호 일치 여부 확인)
//        // 여기서는 예시를 위해 간단한 더미 인증을 수행합니다.
//        // 실제 애플리케이션에서는 UserDetailsService, PasswordEncoder 등을 사용하여 구현해야 합니다.
//        if ("test@example.com".equals(loginRequest.getEmail()) && "password123".equals(loginRequest.getPassword())) {
//            // 더미 사용자 객체 생성 (실제로는 DB에서 조회한 User 엔티티 사용)
//            User user = new User();
//            user.setId(1L); // 더미 ID
//            user.setEmail(loginRequest.getEmail());
//            // user.setPassword(loginRequest.getPassword()); // 실제 User 엔티티에는 비밀번호 필드가 있을 수 있습니다.
//
//            // JWT 토큰 생성
//            String token = jwtTokenService.generateToken(user);
//
//            // 토큰을 포함한 성공 응답 반환
//            return ResponseEntity.ok(new LoginResponse(token));
//        } else {
//            // 인증 실패 시 401 Unauthorized 반환
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
//        }
//    }
}
