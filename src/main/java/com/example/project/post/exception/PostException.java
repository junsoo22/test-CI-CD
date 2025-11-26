package com.example.project.post.exception;

import com.example.project.global.exception.ErrorCode;
import com.example.project.global.exception.CustomException;

public class PostException extends CustomException {
    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostException(ErrorCode errorCode,String message){
        super(errorCode,message);
    }

}
