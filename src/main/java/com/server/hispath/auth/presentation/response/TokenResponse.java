package com.server.hispath.auth.presentation.response;

import com.server.hispath.auth.application.dto.LoginResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private boolean needRegister;
    private String token;

    public static TokenResponse from(LoginResponseDto loginResponseDto) {
        return new TokenResponse(loginResponseDto.isNeedRegister(), loginResponseDto.getToken());
    }
}

