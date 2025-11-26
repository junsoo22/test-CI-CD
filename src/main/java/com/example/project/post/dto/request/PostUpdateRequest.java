package com.example.project.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@NoArgsConstructor
@Schema(description = "게시글 수정 요청")
public class PostUpdateRequest {
    @Schema(description = "수정할 게시글 내용", example = "수정된 게시글 내용입니다.")
    private String content;
}
