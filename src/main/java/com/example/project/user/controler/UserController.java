package com.example.project.user.controler;

import com.example.project.auth.dto.PrincipalUserDetails;
import com.example.project.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "사용자 API")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "인증된 사용자 확인", description = "로그인한 사용자를 확인합니다.")
    public ResponseEntity<String> getAuthenticatedUserInfo(@AuthenticationPrincipal PrincipalUserDetails principal) {
        Long userId=principal.getUserId();

        return ResponseEntity.ok("인증된 사용자 ID: " + userId);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃" ,description = "사용자를 로그아웃합니다.")
    public ResponseEntity<String> logout(@AuthenticationPrincipal PrincipalUserDetails principal){
        Long userId=principal.getUserId();
        String message=userService.logout(userId);
        return ResponseEntity.ok(message);
    }
}