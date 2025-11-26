package com.example.project.redis.service;

import com.example.project.redis.entity.RefreshToken;
import com.example.project.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private final RefreshTokenRepository refreshTokenRepository;

    //refreshToken 저장
    public void saveRefreshToken(Long userId, String refreshToken, Long ttl) {
        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .ttl(ttl)
                .build();

        refreshTokenRepository.save(token);
    }

    //refreshToken 조회
    public Optional<RefreshToken> findRefreshToken(Long userId){
        return refreshTokenRepository.findById(userId);
    }

    //refreshToken 삭제
    public boolean deleteRefreshToken(Long userId){
        if (refreshTokenRepository.existsById(userId)) {
            refreshTokenRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
