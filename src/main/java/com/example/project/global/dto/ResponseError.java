package com.example.project.global.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseError {
    private String path;
    private String messageDetail;
    private String errorDetail;
}
