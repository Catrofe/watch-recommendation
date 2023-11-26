package com.example.watchrecommendation.module.auth.dto;

public record LoginReturnSuccesDto(String accessToken, String refreshToken, long expiresIn, long ExpiresRefreshTokenIn) {

    public LoginReturnSuccesDto(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, 3600, 1800);
    }


}
