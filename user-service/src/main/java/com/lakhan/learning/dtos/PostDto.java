package com.lakhan.learning.dtos;

import com.lakhan.learning.entities.Post;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private Long userId;
    private String content;

    public static PostDto fromEntity(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setUserId(post.getCreatedBy().getId());
        dto.setContent(post.getContent());
        return dto;
    }
}

