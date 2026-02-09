package com.travel.agency.controller;

import com.travel.agency.dto.LoginRequest;
import com.travel.agency.dto.LoginResponse;
import com.travel.agency.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 인증 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true", maxAge = 3600)
@Tag(name = "인증", description = "로그인/로그아웃 API")
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인
     * POST /api/auth/login
     */
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자명과 비밀번호로 JWT 토큰을 발급받습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "사용자명 또는 비밀번호 오류")
    })
    public ResponseEntity<LoginResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 정보", required = true)
            @RequestBody LoginRequest loginRequest) {
        log.info("POST: 로그인 요청, username = {}", loginRequest.getUsername());
        
        try {
            LoginResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("로그인 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 로그아웃
     * POST /api/auth/logout
     */
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "현재 세션의 JWT 토큰을 무효화합니다.")
    @ApiResponse(responseCode = "204", description = "로그아웃 성공")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        log.info("POST: 로그아웃 요청");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            authService.logout(token);
        }
        
        return ResponseEntity.noContent().build();
    }

    /**
     * 테스트 엔드포인트 - JWT 토큰 검증 확인
     * GET /api/auth/verify
     */
    @GetMapping("/verify")
    @Operation(summary = "토큰 검증", description = "현재 요청의 JWT 토큰이 유효한지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "토큰 유효"),
            @ApiResponse(responseCode = "401", description = "토큰 무효 또는 만료")
    })
    public ResponseEntity<String> verifyToken() {
        log.info("GET: 토큰 검증 요청");
        return ResponseEntity.ok("토큰이 유효합니다");
    }

    /**
     * 현재 로그인 사용자 정보 조회
     * GET /api/auth/me
     */
    @GetMapping("/me")
    @Operation(summary = "현재 로그인 사용자 정보", description = "JWT 토큰의 현재 로그인 사용자 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        log.info("GET: 현재 사용자 정보 요청");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("authenticated", authentication.isAuthenticated());
        
        return ResponseEntity.ok(userInfo);
    }
}
