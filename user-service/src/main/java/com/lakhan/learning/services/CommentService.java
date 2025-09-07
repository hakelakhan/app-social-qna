package com.lakhan.learning.services;

import com.lakhan.learning.dao.CommentDao;
import com.lakhan.learning.dtos.CreateCommentRequest;
import com.lakhan.learning.dtos.CommentDto;
import com.lakhan.learning.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto addComment(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCreatedAt(request.getUserId());
        comment.setPostId(request.getPostId());
        comment = commentDao.save(comment);
        return CommentDto.fromEntity(comment);
    }

    public void deleteComment(Long commentId) {
        commentDao.deleteById(commentId);
    }

    public List<CommentDto> getCommentsForPost(Long postId) {
        return commentDao.findByPostId(postId).stream().map(CommentDto::fromEntity).collect(Collectors.toList());
    }
}

