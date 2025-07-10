package com.backend.template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // CSRF 비활성화를 위한 임포트
import org.springframework.security.config.http.SessionCreationPolicy; // 세션 관리를 위한 임포트
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**", "/").permitAll() // 루트 경로와 인증 API는 모두 허용
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}