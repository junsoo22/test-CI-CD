package com.example.project.user.service;

import com.example.project.redis.service.RefreshTokenRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final RefreshTokenRedisService refreshTokenRedisService;

    public String logout(Long userId){
        boolean isDeleted= refreshTokenRedisService.deleteRefreshToken(userId);
        if (isDeleted){
            return "Logout successful";
        }
        else{
            return "Logout failed: User not found";
        }
    }
}

