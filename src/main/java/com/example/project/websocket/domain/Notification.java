package com.example.project.websocket.domain;

import com.example.project.post.entity.Post;
import com.example.project.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;  // 알림 받는 사람 (프롬프트 작성자)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;    // 좋아요 누른 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prompt_id")
    private Post post;    // 좋아요 대상 프롬프트

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String content;
    private boolean isRead = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    public static Notification createLikeNotification(UserEntity receiver, UserEntity sender, Post post) {
        Notification notification = new Notification();
        notification.receiver = receiver;
        notification.sender = sender;
        notification.post = post;
        notification.type = NotificationType.LIKE;
        notification.content = sender.getNickName() + "님이 \"" + post.getTitle() + "\" 프롬프트를 좋아합니다.";
        return notification;
    }

    public void markAsRead() {
        this.isRead = true;
    }
}

