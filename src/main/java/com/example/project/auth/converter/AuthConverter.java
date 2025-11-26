package com.example.project.auth.converter;

import com.example.project.auth.dto.JwtLoginResponse;
import com.example.project.user.entity.UserEntity;

public class AuthConverter {

    public static JwtLoginResponse toJwtLoginResponse(UserEntity user, String accessToken, String refreshToken) {
        return JwtLoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .jwtAccessToken("Bearer " + accessToken)
                .jwtRefreshToken(refreshToken)
                .build();
    }
}
