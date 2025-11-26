package com.example.project.auth.service;

import com.example.project.auth.client.KakaoApiClient;
import com.example.project.auth.converter.AuthConverter;
import com.example.project.auth.dto.JwtLoginResponse;
import com.example.project.auth.dto.KakaoUserInfo;
import com.example.project.redis.service.RefreshTokenRedisService;
import com.example.project.security.jwt.JwtUtil;
import com.example.project.user.entity.Role;
import com.example.project.user.entity.UserEntity;
import com.example.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final KakaoApiClient kakaoApiClient;
    private final RefreshTokenRedisService refreshTokenRedisService;

    @Value("${jwt.expiration.access}")
    private Long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.expiration.refresh}")
    private Long REFRESH_TOKEN_EXPIRE_TIME;

    public JwtLoginResponse loginOrRegister(String code){


        //1. 인가 코드로 카카오 access token 요청
        String kakaoAccessToken=kakaoApiClient.requestAccessToken(code);

        //2. 카카오 access token으로 사용자 정보 조회
        KakaoUserInfo kakaoUserInfo=kakaoApiClient.getUserInfo(kakaoAccessToken);
        String email=kakaoUserInfo.getKakaoAccount().getEmail();
        String profileImage=kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl();

        //3. 회원 조회 또는 생성
        UserEntity user=userRepository.findByEmail(email)
                .orElseGet(()-> {
                    String nicknameToUse = generateRandomNickname();
                    return userRepository.save(UserEntity.builder()
                            .email(email)
                            .nickName(nicknameToUse)
                            .role(Role.USER)
                            .imageUrl(profileImage)
                            .build());
                });

        //4. Access & RefreshToken 생성
        String userIdStr=user.getId().toString();
        Authentication authentication=jwtUtil.getAuthenticationFromUserId(userIdStr);
        String accessToken=jwtUtil.generateAccessToken(authentication,userIdStr);
        String refreshToken=jwtUtil.generateRefreshToken(authentication,userIdStr);

        //5. Refresh Token을 Redis에 저장
        refreshTokenRedisService.saveRefreshToken(user.getId(), refreshToken,REFRESH_TOKEN_EXPIRE_TIME);

        //6. JwtLoginResponse 반환
        return AuthConverter.toJwtLoginResponse(user,accessToken,refreshToken);
    }

    //닉네임 random 생성
    private String generateRandomNickname(){
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }


}
