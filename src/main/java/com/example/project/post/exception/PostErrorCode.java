package com.example.project.post.exception;

import com.example.project.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum PostErrorCode implements ErrorCode {

    POST_ID_NOT_FOUND(HttpStatus.BAD_REQUEST,"해당 게시글이 존재하지 않습니다."),
    POST_ACCESS_DENIED(HttpStatus.FORBIDDEN,"해당 게시글에 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
