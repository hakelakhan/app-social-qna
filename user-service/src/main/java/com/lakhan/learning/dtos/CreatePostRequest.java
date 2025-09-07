package com.lakhan.learning.dtos;

import lombok.Data;

@Data
public class CreatePostRequest {
    private Long userId;
    private String content;
}

