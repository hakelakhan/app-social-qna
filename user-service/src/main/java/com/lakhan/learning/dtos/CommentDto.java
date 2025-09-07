package com.lakhan.learning.dtos;

import com.lakhan.learning.entities.Comment;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

    public static CommentDto fromEntity(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setUserId(comment.getCreatedBy().getId());
        dto.setPostId(comment.getPost().getId());
        dto.setContent(comment.getContent());
        return dto;
    }
}

