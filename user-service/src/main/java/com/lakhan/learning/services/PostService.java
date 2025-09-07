package com.lakhan.learning.services;

import com.lakhan.learning.dao.PostDao;
import com.lakhan.learning.dtos.CreatePostRequest;
import com.lakhan.learning.dtos.PostDto;
import com.lakhan.learning.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setContent(request.getContent());
        post.setUserId(request.getUserId());
        post = postDao.save(post);
        return PostDto.fromEntity(post);
    }

    public PostDto getPost(Long postId) {
        Post post = postDao.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDto.fromEntity(post);
    }

    public void deletePost(Long postId) {
        postDao.deleteById(postId);
    }

    public List<PostDto> listPosts() {
        return postDao.findAll().stream().map(PostDto::fromEntity).collect(Collectors.toList());
    }
}

