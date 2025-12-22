package com.example.project.websocket.service;

import com.example.project.websocket.domain.Notification;
import com.example.project.websocket.domain.NotificationType;
import com.example.project.websocket.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    // 전체 사용자에게 알림 (브로드캐스트)
    public void sendToAll(String message, Long postId, String author) {
        NotificationDto notification = NotificationDto.builder()
                .type("NEW_POST")
                .message(message)
                .postId(postId)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        messagingTemplate.convertAndSend("/topic/notification", notification);
    }

    // 특정 사용자에게 알림
    public void sendToUser(Long userId, String message) {
        NotificationDto notification = NotificationDto.builder()
                .type("PERSONAL")
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();

        messagingTemplate.convertAndSendToUser(
                userId.toString(),
                "/queue/notification",
                notification
        );
    }
}
