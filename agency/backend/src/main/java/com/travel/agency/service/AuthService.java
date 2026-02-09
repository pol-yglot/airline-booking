package com.travel.agency.service;

import com.travel.agency.dto.LoginRequest;
import com.travel.agency.dto.LoginResponse;
import com.travel.agency.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 인증 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration:1800000}")
    private long tokenExpiration;

    /**
     * 로그인 처리
     * 기본 사용자명/비밀번호: admin / admin123
     */
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("로그인 시도: {}", loginRequest.getUsername());

        // 기본 자격증명 검증 (실제 운영환경에서는 데이터베이스 조회 필요)
        if (!validateCredentials(loginRequest.getUsername(), loginRequest.getPassword())) {
            throw new IllegalArgumentException("사용자명 또는 비밀번호가 올바르지 않습니다");
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(loginRequest.getUsername());
        
        // 고객사 정보 조회 (기본 관리자용 첫 고객사)
        // schema.sql에서 기본 고객사 ID=1로 추가됨
        Long customerId = 1L;
        
        log.info("로그인 성공: {}", loginRequest.getUsername());

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .username(loginRequest.getUsername())
                .customerId(customerId)
                .expiresIn(tokenExpiration / 1000)
                .build();
    }

    /**
     * 자격증명 검증
     * 기본 사용자: admin / admin123
     */
    private boolean validateCredentials(String username, String password) {
        // 기본 사용자 검증 (application.yaml에 정의된 사용자)
        String defaultUsername = "admin";
        String defaultPassword = "admin123";

        if (defaultUsername.equals(username) && defaultPassword.equals(password)) {
            return true;
        }

        // 추후 데이터베이스에서 사용자 정보 조회 및 검증
        return false;
    }

    /**
     * 로그아웃 처리
     * JWT는 stateless이므로 실제로는 클라이언트에서 토큰 제거
     * 필요시 토큰 블랙리스트 관리 가능
     */
    public void logout(String token) {
        log.info("로그아웃 처리됨");
        // TODO: 토큰 블랙리스트에 추가 (Redis 사용 권장)
    }
}
