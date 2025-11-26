package com.example.project.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "게시글 생성 요청")
public class PostRequest {

    @Schema(description = "생성된 게시글 정보")
    private String title;
    private String content;

}
