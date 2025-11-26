package com.example.project.post.converter;

import com.example.project.post.dto.request.PostRequest;
import com.example.project.post.dto.response.PostResponse;
import com.example.project.post.entity.Post;
import com.example.project.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostConverter {

    public Post toPost(PostRequest dto, UserEntity user){
        return Post.builder()
                .title(dto.getTitle())
                .user(user)
                .content(dto.getContent())
                .build();
    }

    public static PostResponse toPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreateAt())
                .build();
    }
}
