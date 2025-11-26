package com.example.project.post.entity;

import com.example.project.global.BaseTimeEntity;
import com.example.project.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    public void updateContent(String content){
        this.content=content;

    }

}
