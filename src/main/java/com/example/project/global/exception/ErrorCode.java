package com.example.project.global.exception;

import org.springframework.http.HttpStatus;

//예외마다 HTTP 상태 코드와 메시지를 가질 수 있도록 계약 역할
public interface ErrorCode {
    HttpStatus getHttpStatus();
    String getMessage();
}
