package com.server.hispath.auth.presentation;

import com.server.hispath.auth.application.AuthService;
import com.server.hispath.auth.application.dto.LoginRequestDto;
import com.server.hispath.auth.application.dto.LoginResponseDto;
import com.server.hispath.auth.presentation.response.TokenResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/{oauthProvider}/login-student/token")
    public ResponseEntity<TokenResponse> loginStudent(@PathVariable String oauthProvider, @RequestParam String code) {

        LoginResponseDto loginResponseDto = authService.studentLogin(new LoginRequestDto(oauthProvider, code));
        return ResponseEntity.ok(TokenResponse.from(loginResponseDto));
    }

    @GetMapping("/auth/{oauthProvider}/login-manager/token")
    public ResponseEntity<TokenResponse> loginManager(@PathVariable String oauthProvider, @RequestParam String code) {

        LoginResponseDto loginResponseDto = authService.managerLogin(new LoginRequestDto(oauthProvider, code));
        return ResponseEntity.ok(TokenResponse.from(loginResponseDto));
    }
}